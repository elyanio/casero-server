package caribehostal.caseroserver.comunication;

/**
 * Created by asio on 9/30/2017.
 */

public class FixMessage {

    //TO RECEIVE
    private static String ACTION_RECEIVE_REGISTER_CLIENT = "1";
    private static String ACTION_RECEIVE_REGISTER_OWNER = "2";

    //TO SEND
    private static String ACTION_SEND_CORRECT_PETITION = "1";
    private static String ACTION_SEND_CORRECT_OWNER = "2";
    private static String ACTION_SEND_WRONG_OWNER = "3";


    //TO SEND WRONG DATAS TO CLIENT
    private static String NAME = "#1";
    private static String CARNET_IDENTIDAD = "#2";
    private static String USER = "#3";
    private static String PASSWORD = "#4";
    private static String ADDRESS = "#5";
    private static String REFERENCIA = "#6";

    public static String getActionSendCorrectOwner() {
        return ACTION_SEND_CORRECT_OWNER;
    }

    public static String getActionSendWrongOwner() {
        return ACTION_SEND_WRONG_OWNER;
    }

    public static String getActionReceiveRegisterOwner() {
        return ACTION_RECEIVE_REGISTER_OWNER;
    }

    public static String getActionReceiveRegisterClient() {
        return ACTION_RECEIVE_REGISTER_CLIENT;
    }


    public static String getNAME() {
        return NAME;
    }

    public static String getCarnetIdentidad() {
        return CARNET_IDENTIDAD;
    }

    public static String getUSER() {
        return USER;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }

    public static String getADDRESS() {
        return ADDRESS;
    }

    public static String getREFERENCIA() {
        return REFERENCIA;
    }

    public static String getActionSendCorrectPetition() {
        return ACTION_SEND_CORRECT_PETITION;
    }
}
