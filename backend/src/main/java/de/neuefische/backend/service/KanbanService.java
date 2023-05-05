package de.neuefische.backend.service;


import de.neuefische.backend.model.ToDo;
import de.neuefische.backend.repository.RepoToDo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KanbanService {

    private final RepoToDo repoToDo;

    public List<ToDo> getAllToDos() {
        return repoToDo.getAllToDos();
    }

    public ToDo addToDo(String task) {
        return repoToDo.addToDo(task);
    }
}
