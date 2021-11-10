package ISPA.awsimageupload.datastore;

import ISPA.awsimageupload.profile.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class FakeUserProfileDataStore {
    private static final List<UserProfile> USER_PROFILES= new ArrayList<>();

    static {
        USER_PROFILES.add(new UserProfile(UUID.fromString("88d813ab-8598-429e-b974-11fd46f9489c"),"anthony", null));
        USER_PROFILES.add(new UserProfile(UUID.fromString("8c45bbe6-b2ec-466f-a29d-161468961e97"),"eleanore", null));
    }

    public List<UserProfile> getUserProfiles(){
        return USER_PROFILES;
    }
}
