package de.neuefische.backend.repository;

import de.neuefische.backend.model.ToDo;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
@Data
public class RepoToDo {

    private final List<ToDo> allToDos = new ArrayList<>();

    public ToDo addToDo(String task) {
        ToDo toDo = new ToDo(task);
        allToDos.add(toDo);
        return toDo;
    }
}
