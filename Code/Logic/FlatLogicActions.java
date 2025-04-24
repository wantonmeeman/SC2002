package Logic;

import Data.Models.Flat;
import Data.Repository.FlatRepository;
import Exceptions.ModelAlreadyExistsException;
import Exceptions.ModelNotFoundException;
import Exceptions.UnauthorizedActionException;
import Util.DefaultGenerateID;
import Util.Interfaces.IDGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * The type Flat logic actions.
 */
public class FlatLogicActions extends DataLogicActions<Flat>{
    private static FlatLogicActions instance;

    /**
     * Instantiates a new Flat logic actions.
     *
     * @param idGenerator the id generator
     */
    public FlatLogicActions(IDGenerator idGenerator) {
        super(idGenerator);
    }

    @Override
    protected HashMap<String, String> toMap(Flat flat) {
        HashMap<String, String> flatMap = new HashMap<>();

        flatMap.put("ID", flat.getID());
        flatMap.put("Type", flat.getType());
        flatMap.put("TotalUnits", String.valueOf(flat.getTotalUnits()));
        flatMap.put("Price", String.format("%.2f",flat.getPrice()));

        return flatMap;
    }

    public String create(HashMap<String, String> hm) {
        String flatID = generateID();
        String type = hm.get("Type");
        int totalUnits = Integer.parseInt(hm.get("TotalUnits"));
        float price = Float.parseFloat(hm.get("Price"));

        Flat flat = new Flat(flatID, type, totalUnits, price);

        try {
            FlatRepository.getInstance().create(flat);
        } catch (ModelAlreadyExistsException e) {
            create(hm);
        }

        return flatID;
    }

    @Override
    protected Stream<Flat> getAllObject(){
        return FlatRepository.getInstance().getAll()
                .stream()
                .map(model -> (Flat) model);
    }

    @Override
    public void delete(String ID) throws ModelNotFoundException{
        FlatRepository.getInstance().delete(ID);
    }

    /**
     * Book.
     *
     * @param flatID the flat id
     * @throws ModelNotFoundException      the model not found exception
     * @throws UnauthorizedActionException the unauthorized action exception
     */
    public void book(String flatID) throws ModelNotFoundException, UnauthorizedActionException {

        Flat flat = getObject(flatID);
        if(flat.getTotalUnits() > 0) {
            flat.setTotalUnits(flat.getTotalUnits() - 1);
            FlatRepository.getInstance().update();
        }else{
            throw new UnauthorizedActionException();
        }
    }

    /**
     * Edit price.
     *
     * @param flatID the flat id
     * @param price  the price
     * @throws ModelNotFoundException the model not found exception
     */
    public void editPrice(String flatID, Float price) throws ModelNotFoundException{

        Flat flat = getObject(flatID);
        flat.setPrice(price);

        FlatRepository.getInstance().update();
    }

    /**
     * Delete all linked.
     *
     * @param flatID the flat id
     * @throws ModelNotFoundException the model not found exception
     */
    public void deleteAllLinked(String flatID) throws ModelNotFoundException{
        Optional<Flat> matchedFlat = getAllObject()
                .filter(flat -> flat.getID().equals(flatID))
                .findFirst();

        if (matchedFlat.isPresent()) {
            Flat flat = matchedFlat.get();

            ApplicationLogicActions.getInstance().deleteByFlatID(flat.getID());

            delete(flat.getID());
        }else{
            throw new ModelNotFoundException();
        }
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static FlatLogicActions getInstance() {
        if (instance == null)
            instance = new FlatLogicActions(new DefaultGenerateID());
        return instance;
    }
}
