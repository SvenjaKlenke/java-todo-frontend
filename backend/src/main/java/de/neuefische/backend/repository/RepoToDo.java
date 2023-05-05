package de.neuefische.backend.repository;

import de.neuefische.backend.model.ToDo;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
@Data
public class RepoToDo {

    private final Map<String, ToDo> allToDos = new HashMap<>();

    public List<ToDo> getAllToDos (){
        return new ArrayList<>(allToDos.values());
    }

    public ToDo addToDo(ToDo toDo){
        allToDos.put(toDo.getId(),toDo);
        return toDo;
    }

    public ToDo getToDo(String id) {
        return allToDos.get(id);
    }

    public List <ToDo> editToDo(String id, ToDo toDo) {
        allToDos.put(id, toDo);
        return getAllToDos();


    }

    public List<ToDo> deleteToDo(String id) {
        allToDos.remove(id);
        return getAllToDos();
    }
}
