package de.neuefische.backend.controller;

import de.neuefische.backend.model.ToDo;
import de.neuefische.backend.service.KanbanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class KanbanController {

    private final KanbanService kanbanService;

    @GetMapping ("api/todo")
    public List<ToDo> getAllToDos (){
        return kanbanService.getAllToDos();
    }

    @PostMapping ("api/todo")
    public ToDo addToDo (@RequestBody String task){
        return kanbanService.addToDo(task);
    }

    @GetMapping ("board/todo")
    public List<ToDo> getAllToDosBoard (){
        return kanbanService.getAllToDos();
    }

}
