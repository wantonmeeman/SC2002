package Logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Stream;
import Data.Models.Model;
import Exceptions.ModelNotFoundException;

import Exceptions.ModelAlreadyExistsException;
import Logic.Interfaces.LogicCreatable;
import Logic.Interfaces.LogicDeletable;
import Logic.Interfaces.LogicGetable;
import Logic.Interfaces.LogicIdentifiable;
import Util.DefaultGenerateID;
import Util.Interfaces.IDGenerator;

/**
 * The type Data logic actions.
 *
 * @param <T> the type parameter
 */
public abstract class DataLogicActions<T extends Model>
        implements LogicIdentifiable, LogicCreatable, LogicGetable, LogicDeletable {
    private IDGenerator idGenerator;

    /**
     * Instantiates a new Data logic actions.
     *
     * @param idGenerator the id generator
     */
    public DataLogicActions(IDGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    public String generateID() {
        return idGenerator.generateID(8);
    }

    /**
     * To map hash map.
     *
     * @param t the t
     * @return the hash map
     */
    abstract protected HashMap<String,String> toMap(T t);

    abstract public String create(HashMap<String,String> hm) throws ModelAlreadyExistsException;

    /**
     * Get all object stream.
     *
     * @return the stream
     */
    protected Stream<T> getAllObject(){
        return Stream.empty();
    }

    /**
     * Gets object.
     *
     * @param ID the id
     * @return the object
     * @throws ModelNotFoundException the model not found exception
     */
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

    abstract public void delete(String ID) throws ModelNotFoundException;
}
