package de.neuefische.backend.service;


import de.neuefische.backend.model.ToDo;
import de.neuefische.backend.repository.RepoToDo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KanbanService {

    private final RepoToDo repoToDo;
    private final GeneradeUUID generadeUUID;

    public List<ToDo> getAllToDos() {
        return repoToDo.getAllToDos();
    }

    public ToDo addToDo(ToDo toDo) {
        toDo.setId(generadeUUID.getUUID());
        return repoToDo.addToDo(toDo);
    }

    public ToDo getToDo(String id) {
        return repoToDo.getToDo(id);
    }

    public List<ToDo> editToDo(String id, ToDo toDo) {
        return repoToDo.editToDo (id, toDo);
    }

    public List<ToDo> deleteToDo(String id) {
        return repoToDo.deleteToDo(id);
    }
}
