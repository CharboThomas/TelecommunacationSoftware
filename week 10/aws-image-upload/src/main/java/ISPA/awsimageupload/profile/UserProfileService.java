package ISPA.awsimageupload.profile;


import ISPA.awsimageupload.bucket.BucketName;
import ISPA.awsimageupload.filestore.FileStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.*;

@Service


public class UserProfileService {

    private final UserProfileDataAccessService userProfileDataAccessService;
    private final FileStore fileStore;

    @Autowired
    public UserProfileService(UserProfileDataAccessService userProfileDataAccessService, FileStore fileStore) {
        this.userProfileDataAccessService = userProfileDataAccessService;
        this.fileStore = fileStore;
    }

    List<UserProfile> getUserProfiles(){
        return userProfileDataAccessService.getUserProfiles();
    }


    public void uploadUserProfileImage(UUID userProfileId,
                                       MultipartFile file) {

        // step 1 => check if the file is empty
        if (file.isEmpty()) {
            throw new IllegalStateException("Can't upload empty file [ " + file.getSize() + " ] ");
        }

        // step 2 => check the format of the file
        /*
        if(!Arrays.asList(ContentType.Imagejpeg).contains(file.getContentType())){
            throw new IllegalStateException("File must be an image ");
        }*/

        // step 3 => check the existance of the user
        UserProfile user = userProfileDataAccessService
                .getUserProfiles()
                .stream()
                .filter(userProfile -> userProfile.getUserProfileId().equals(userProfileId))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("User profile %s doesn't found",userProfileId)));

        // Step 4 => grab some metada from file if any
        Map<String,String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));

        // Step 5 =>
        String path = String.format("%s/%s",  BucketName.PROFILE_IMAGE.getBucketName(), user.getUserProfileId());
        String fileName = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());

        try {
            fileStore.save(path,fileName,Optional.of(metadata),file.getInputStream());
            user.setUserProfileImageLink(fileName);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public byte[] downloadUserProfileImage(UUID userProfileId) {
        UserProfile user = userProfileDataAccessService
                .getUserProfiles()
                .stream()
                .filter(userProfile -> userProfile.getUserProfileId().equals(userProfileId))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("User profile %s doesn't found",userProfileId)));

        String path = String.format("%s/%s",
                BucketName.PROFILE_IMAGE.getBucketName(),
                user.getUserProfileId());

        return user.getUserProfileImageLink()
                .map(link -> fileStore.download(path, link))
                .orElse((new byte[0]));


    }
}
