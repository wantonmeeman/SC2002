package Logic;

import Data.Models.Applicant;
import Data.Models.Flat;
import Data.Repository.FlatRepository;
import Exceptions.ModelAlreadyExistsException;
import Exceptions.ModelNotFoundException;
import Exceptions.RepositoryNotFoundException;
import Logic.DataLogicActions;
import Logic.UserLogicActions;
import Util.GenerateID;

import java.util.HashMap;
import java.util.stream.Stream;

public class FlatLogicActions extends DataLogicActions<Flat>{
    private static FlatLogicActions instance;

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
        String flatID = GenerateID.generateID(8);
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

    public void book(String flatID) throws ModelNotFoundException{

        Flat flat = getObject(flatID);
        flat.setTotalUnits(flat.getTotalUnits()-1);

        FlatRepository.getInstance().update();
    }

    public void editPrice(String flatID, Float price) throws ModelNotFoundException{

        Flat flat = getObject(flatID);
        flat.setPrice(price);

        FlatRepository.getInstance().update();
    }

    public static FlatLogicActions getInstance() {
        if (instance == null)
            instance = new FlatLogicActions();
        return instance;
    }
}
