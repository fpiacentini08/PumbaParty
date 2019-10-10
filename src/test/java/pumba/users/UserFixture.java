package pumba.users;

import java.util.Random;

public class UserFixture
{
    public static final String username = "user.test";
    public static final String password = "1q2w3e4r";
    public static final long roomId = new Random(100).nextLong();
    

    private User build()
    {
        User user = new User(username, password);
        user.setRoomId(roomId);
        return user;
    }

    public static User withDefaults()
    {
        return new UserFixture().build();
    }
}
