package caribehostal.caseroserver.comunication;

/**
 * Created by asio on 9/30/2017.
 */

public class FixMessage {

    //TO RECEIVE
    private static int ACTION_RECEIVE_REGISTER_CLIENT = 1;
    private static int ACTION_RECEIVE_REGISTER_OWNER = 2;

    //TO SEND
    private static int ACTION_SEND_CORRECT_OWNER = 1;
    private static int ACTION_SEND_WRONG_OWNER = 2;

    public static int getActionSendCorrectOwner() {
        return ACTION_SEND_CORRECT_OWNER;
    }

    public static int getActionSendWrongOwner() {
        return ACTION_SEND_WRONG_OWNER;
    }

    public static int getActionReceiveRegisterOwner() {

        return ACTION_RECEIVE_REGISTER_OWNER;
    }

    public static int getActionReceiveRegisterClient() {
        return ACTION_RECEIVE_REGISTER_CLIENT;
    }
}
