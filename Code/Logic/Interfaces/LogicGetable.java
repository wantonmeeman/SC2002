package Logic.Interfaces;

import Exceptions.ModelNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;

public interface LogicGetable {
    HashMap<String,String> get(String ID) throws ModelNotFoundException;
    ArrayList<HashMap<String,String>> getAll();
}
