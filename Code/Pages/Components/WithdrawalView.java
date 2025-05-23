package Pages.Components;

import Exceptions.ModelNotFoundException;
import Logic.ApplicationLogicActions;
import Logic.WithdrawalLogicActions;

import java.util.HashMap;

/**
 * The interface Withdrawal view.
 */
public interface WithdrawalView {
    /**
     * Detailed view string.
     *
     * @param ID the id
     * @return the string
     * @throws ModelNotFoundException the model not found exception
     */
    static String detailedView(String ID) throws ModelNotFoundException {
        HashMap<String,String> whm = WithdrawalLogicActions.getInstance().get(ID);

        return "Withdrawal Status: " + whm.get("Status");
    }
}
