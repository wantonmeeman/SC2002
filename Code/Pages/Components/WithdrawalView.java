package Pages.Components;

import Exceptions.ModelNotFoundException;
import Logic.ApplicationLogicActions;
import Logic.WithdrawalLogicActions;

import java.util.HashMap;

public class WithdrawalView {
    public static String detailedView(String ID) throws ModelNotFoundException {
        HashMap<String,String> whm = WithdrawalLogicActions.getInstance().get(ID);

        return "Withdrawal Status: " + whm.get("Status");
    }
}
