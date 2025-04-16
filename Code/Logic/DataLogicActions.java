package Logic;

import java.util.HashMap;
import java.util.stream.Stream;

interface DataLogicActions<T>{
    HashMap<String,String> toMap(T t);

    String create(HashMap<String,String> hm);

    Stream<T> getAll();
    HashMap<String,String> get(String ID);

    private void edit(String ID){
        return;
    };

    void delete(String ID);
}
