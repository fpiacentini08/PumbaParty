package test.java.pumba.room;

import main.java.pumba.rooms.Room;
import main.java.pumba.users.User;
import test.java.pumba.users.UserFixture;

public class RoomFixture
{
    public static final User master = UserFixture.withDefaults();
    
    

    private Room build()
    {
    	Room room = new Room(master);
        return room;
    }

    public static Room withDefaults()
    {
        return new RoomFixture().build();
    }
}
