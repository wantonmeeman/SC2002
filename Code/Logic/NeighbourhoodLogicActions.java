package Logic;

import Data.Models.Neighbourhood;
import Data.Models.Neighbourhood;
import Data.Repository.NeighbourhoodRepository;
import Exceptions.ModelAlreadyExistsException;
import Exceptions.ModelNotFoundException;
import Util.GenerateID;

import java.util.HashMap;
import java.util.stream.Stream;

public class NeighbourhoodLogicActions extends DataLogicActions<Neighbourhood>{
    private static NeighbourhoodLogicActions instance;

    @Override
    protected HashMap<String, String> toMap(Neighbourhood neighbourhood) {
        HashMap<String, String> neighbourhoodMap = new HashMap<>();

        neighbourhoodMap.put("ID", neighbourhood.getID());
        neighbourhoodMap.put("Name", neighbourhood.getName());

        return neighbourhoodMap;
    }

    public String create(HashMap<String, String> hm){
        String neighbourhoodID = GenerateID.generateID(8);
        String name = hm.get("Name");

        Neighbourhood neighbourhood = new Neighbourhood(
                neighbourhoodID, name
        );

        try {
            NeighbourhoodRepository.getInstance().create(neighbourhood);
        } catch (ModelAlreadyExistsException e) {
            create(hm);
        }

        return neighbourhoodID;
    }

    @Override
    protected Stream<Neighbourhood> getAllObject(){
        return NeighbourhoodRepository.getInstance().getAll()
                .stream()
                .map(model -> (Neighbourhood) model);
    }

    @Override
    public void delete(String ID) throws ModelNotFoundException{
        NeighbourhoodRepository.getInstance().delete(ID);
    }

    public void editName(String neighbourhoodID, String name) throws ModelNotFoundException{
        Neighbourhood neighbourhood = getObject(neighbourhoodID);
        neighbourhood.setName(name);
        NeighbourhoodRepository.getInstance().update();
    }

    public static NeighbourhoodLogicActions getInstance() {
        if (instance == null)
            instance = new NeighbourhoodLogicActions();
        return instance;
    }
}
