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

    public String create(HashMap<String, String> hm) throws ModelAlreadyExistsException {
        String flatID = GenerateID.generateID(8);
        String type = hm.get("Type");
        int totalUnits = Integer.parseInt(hm.get("TotalUnits"));
        float price = Float.parseFloat(hm.get("Price"));

        Flat flat = new Flat(flatID, type, totalUnits, price);

        FlatRepository.getInstance().create(flat);

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

    public static FlatLogicActions getInstance() {
        if (instance == null)
            instance = new FlatLogicActions();
        return instance;
    }
}
