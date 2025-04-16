package Logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Stream;
import Data.Models.Model;
import Exceptions.ModelNotFoundException;

import Exceptions.ModelAlreadyExistsException;
import Util.GenerateID;

public abstract class DataLogicActions<T extends Model>{
    protected HashMap<String,String> toMap(T t){
        //throw new Exception TODO UNAUTH
        return null;
    };

    abstract public String create(HashMap<String,String> hm) throws ModelAlreadyExistsException;

    protected Stream<T> getAllObject(){
        return Stream.empty();
    }

    protected String generateID(){
        return GenerateID.generateID(8);
    }

    protected T getObject(String ID) throws ModelNotFoundException{
        Optional<T> itemOpt = getAllObject().filter(
                item -> item.getID().equals(ID)
        ).findFirst();

        if (itemOpt.isPresent()) {
            return itemOpt.get();
        }else{
            throw new ModelNotFoundException();
        }
    }

    public HashMap<String,String> get(String ID) throws ModelNotFoundException{
        return toMap(getObject(ID));
    }

    public ArrayList<HashMap<String,String>> getAll(){
        ArrayList<HashMap<String, String>> modelList = new ArrayList<>();
        getAllObject()
                .forEach(model -> {
                    modelList.add(toMap(model));
                });
        return modelList;
    }

//    private void edit(String ID){
//        return; Should define own edit function for each class
//    };

    abstract void delete(String ID) throws ModelNotFoundException;
}
