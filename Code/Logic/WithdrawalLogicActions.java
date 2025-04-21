package Logic;

import Data.Models.Withdrawal;
import Data.Repository.WithdrawalRepository;
import Exceptions.ModelAlreadyExistsException;
import Exceptions.ModelNotFoundException;

import java.util.HashMap;
import java.util.stream.Stream;

public class WithdrawalLogicActions extends DataLogicActions<Withdrawal>{
    private static WithdrawalLogicActions instance;

    @Override
    protected HashMap<String, String> toMap(Withdrawal withdrawal) {
        HashMap<String, String> withdrawalMap = new HashMap<>();

        withdrawalMap.put("ID", withdrawal.getID());
        withdrawalMap.put("Status", withdrawal.getStatus());

        return withdrawalMap;
    }

    public String create(HashMap<String, String> hm){
        String withdrawalID = hm.get("ID");//GenerateID.generateID(8);
        String status = hm.get("Status");

        Withdrawal withdrawal = new Withdrawal(
                withdrawalID,
                status
        );

        try {
            WithdrawalRepository.getInstance().create(withdrawal);
        } catch (ModelAlreadyExistsException e) {
            try {
                Withdrawal w = (Withdrawal) WithdrawalRepository.getInstance().get(withdrawalID);
                w.setStatus("Pending");
            }catch(ModelNotFoundException exception){
                System.out.println("This will not happen");
            }
        }

        return withdrawalID;
    }

    @Override
    protected Stream<Withdrawal> getAllObject(){
        return WithdrawalRepository.getInstance().getAll()
                .stream()
                .map(model -> (Withdrawal) model);
    }

    @Override
    public void delete(String ID) throws ModelNotFoundException{
        WithdrawalRepository.getInstance().delete(ID);
    }

    public void approve(String withdrawalID) throws ModelNotFoundException{
        Withdrawal w = getObject(withdrawalID);
        w.setStatus("Successful");
        WithdrawalRepository.getInstance().update();
    }

    public void reject(String withdrawalID) throws ModelNotFoundException{
        Withdrawal w = getObject(withdrawalID);
        w.setStatus("Unsuccessful");
        WithdrawalRepository.getInstance().update();
    }

    public void withdraw(String applicationID){
        HashMap<String,String> whm = new HashMap<>();
        whm.put("ID",applicationID);
        whm.put("Status","Pending");
        create(whm);
    }

    public static WithdrawalLogicActions getInstance() {
        if (instance == null)
            instance = new WithdrawalLogicActions();
        return instance;
    }
}
