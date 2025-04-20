package Pages.Components;

import Exceptions.ModelNotFoundException;
import Logic.ApplicationLogicActions;
import Logic.UserLogicActions;

import java.util.HashMap;

public class ApplicationView {
    public static String detailedView(String ID) throws ModelNotFoundException {
        HashMap<String,String> ahm = ApplicationLogicActions.getInstance().get(ID);

        return "Application Status: " + ahm.get("Status");
    }
}
