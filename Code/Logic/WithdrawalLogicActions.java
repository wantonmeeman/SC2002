package Logic;

import Data.Models.Withdrawal;
import Data.Repository.WithdrawalRepository;
import Exceptions.ModelAlreadyExistsException;
import Exceptions.ModelNotFoundException;
import Util.DefaultGenerateID;
import Util.Interfaces.IDGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * The type Withdrawal logic actions.
 */
public class WithdrawalLogicActions extends DataLogicActions<Withdrawal>{
    private static WithdrawalLogicActions instance;

    /**
     * Instantiates a new Withdrawal logic actions.
     *
     * @param idGenerator the id generator
     */
    public WithdrawalLogicActions(IDGenerator idGenerator) {
        super(idGenerator);
    }

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

    /**
     * Approve.
     *
     * @param withdrawalID the withdrawal id
     * @throws ModelNotFoundException the model not found exception
     */
    public void approve(String withdrawalID) throws ModelNotFoundException{
        Withdrawal w = getObject(withdrawalID);
        w.setStatus("Successful");

        ApplicationLogicActions.getInstance().withdraw(withdrawalID);

        WithdrawalRepository.getInstance().update();
    }

    /**
     * Reject.
     *
     * @param withdrawalID the withdrawal id
     * @throws ModelNotFoundException the model not found exception
     */
    public void reject(String withdrawalID) throws ModelNotFoundException{
        Withdrawal w = getObject(withdrawalID);
        w.setStatus("Unsuccessful");
        WithdrawalRepository.getInstance().update();
    }

    /**
     * Withdraw.
     *
     * @param applicationID the application id
     */
    public void withdraw(String applicationID){
        HashMap<String,String> whm = new HashMap<>();
        whm.put("ID",applicationID);
        whm.put("Status","Pending");
        create(whm);
    }

    /**
     * Delete by application id.
     *
     * @param applicationID the application id
     * @throws ModelNotFoundException the model not found exception
     */
    public void deleteByApplicationID(String applicationID) throws ModelNotFoundException{
        Optional<Withdrawal> match = getAllObject()
                .filter(withdrawal -> withdrawal.getID().equals(applicationID))
                .findFirst();

        if (match.isPresent()) {
            delete(match.get().getID());
        }
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static WithdrawalLogicActions getInstance() {
        if (instance == null)
            instance = new WithdrawalLogicActions(new DefaultGenerateID());
        return instance;
    }
}
