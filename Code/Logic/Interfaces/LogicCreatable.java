package Logic.Interfaces;

import Exceptions.ModelAlreadyExistsException;

import java.util.HashMap;

public interface LogicCreatable {
    String create(HashMap<String,String> hm) throws ModelAlreadyExistsException;
}
