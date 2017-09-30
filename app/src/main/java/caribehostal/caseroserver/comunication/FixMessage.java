package caribehostal.caseroserver.comunication;

/**
 * Created by asio on 9/30/2017.
 */

public class FixMessage {

    private static int ACTION_REGISTER_CLIENT = 1;
    private static int ACTION_REGISTER_OWNER = 2;

    public static int getActionRegisterOwner() {

        return ACTION_REGISTER_OWNER;
    }

    public static int getActionRegisterClient() {
        return ACTION_REGISTER_CLIENT;
    }
}
