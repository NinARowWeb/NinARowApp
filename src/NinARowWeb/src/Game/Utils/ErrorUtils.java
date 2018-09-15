package Game.Utils;

import users.UserManager;

import javax.servlet.ServletContext;

public class ErrorUtils {
    private static final Object errorManagerLock = new Object();
    private static Error errorLoginMessage;
    private static Error errorUploadGameMessage;

    public static void setErrorLoginMessage(Error i_errorLoginMessage) {
        errorLoginMessage = i_errorLoginMessage;
    }

    public static String getErrorLoginMessage(){
        if(errorLoginMessage != null)
            return errorLoginMessage.getMessage();
        return null;
    }

    public static void setErrorUploadGameError(Error i_errorUpdateGameMessage){
        errorUploadGameMessage = i_errorUpdateGameMessage;
    }

    public static String getErrorUploadMessage(){
        if(errorUploadGameMessage != null)
            return errorUploadGameMessage.getMessage();
        return null;
    }
}
