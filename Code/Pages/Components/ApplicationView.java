package Pages.Components;

import Exceptions.ModelNotFoundException;
import Logic.ApplicationLogicActions;
import Logic.UserLogicActions;
import Logic.WithdrawalLogicActions;

import java.util.HashMap;

/**
 * The interface Application view.
 */
public interface ApplicationView {
    /**
     * Detailed view string.
     *
     * @param ID the id
     * @return the string
     * @throws ModelNotFoundException the model not found exception
     */
    static String detailedView(String ID) throws ModelNotFoundException {
        HashMap<String,String> ahm = ApplicationLogicActions.getInstance().get(ID);

        return "Application Status: " + ahm.get("Status");
    }
}
