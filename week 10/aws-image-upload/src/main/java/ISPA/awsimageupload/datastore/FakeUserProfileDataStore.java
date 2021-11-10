package ISPA.awsimageupload.datastore;


import ISPA.awsimageupload.profile.UserProfile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FakeUserProfileDataStore {

    private static final List<UserProfile> USER_PROFILES = new ArrayList<>();

    static {
        USER_PROFILES.add(new UserProfile( UUID.fromString("2a8af238-df2d-488d-a8df-4f6ca17c62b5"),"paul", null ));
        USER_PROFILES.add(new UserProfile( UUID.fromString("4148f089-9df4-44cb-b8d4-27382ce362ac"),"pierre", null ));
    }

    public List<UserProfile> getUserProfiles(){
        return USER_PROFILES;
    }
}
