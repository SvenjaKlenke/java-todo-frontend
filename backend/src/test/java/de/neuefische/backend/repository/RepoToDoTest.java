package de.neuefische.backend.repository;

import de.neuefische.backend.model.ToDo;
import de.neuefische.backend.service.GeneradeUUID;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class RepoToDoTest {


    String id1 = "1" ;
    String id2 = "2" ;

    RepoToDo repoToDo = new RepoToDo();
    ToDo toDo1 = new ToDo("Eating", "OPEN", id1);
    ToDo toDo2 = new ToDo("Sleeping", "DOING", id2);

    @Test
    void getAllToDos_ShowListOfAllToDos() {
        //GIVEN

        repoToDo.addToDo(toDo1);
        repoToDo.addToDo(toDo2);

        //WHEN

        List <ToDo> actual = repoToDo.getAllToDos();
        //THEN
        assertEquals(List.of(toDo1, toDo2), actual);
    }

    @Test
    void addToDo_ShowTheToDoWeAdd() {
        //GIVEN
        //WHEN

        ToDo actual = repoToDo.addToDo(toDo1);
        //THEN
        assertEquals(toDo1, actual);

    }

    @Test
    void getToDo_ShowTheToDoByID() {
        //GIVEN
        repoToDo.addToDo(toDo1);
        repoToDo.addToDo(toDo2);
        //WHEN
        ToDo actual = repoToDo.getToDo("1");
        //THEN
        assertEquals(toDo1, actual);
    }

    @Test
    void editToDo_returnTheListOfAllToDosWithTheEditToDo() {
        //GIVEN
        repoToDo.addToDo(toDo1);
        repoToDo.addToDo(toDo2);

        ToDo toDo3 = new ToDo("Zocken","OPEN","1");
        //WHEN
        List<ToDo> actual = repoToDo.editToDo("1",toDo3);
        //THEN
        assertEquals(List.of(toDo3, toDo2), actual);
    }

    @Test
    void deleteToDo_returnTheListOfAllToDosWithoutTheDeleteToDo() {
        //GIVEN
        ToDo toDo3 = new ToDo("Zocken","OPEN","3");
        repoToDo.addToDo(toDo1);
        repoToDo.addToDo(toDo2);
        repoToDo.addToDo(toDo3);
        //WHEN
        List<ToDo> actual = repoToDo.deleteToDo("1");
        //THEN
        assertEquals(List.of(toDo2,toDo3), actual);
    }
}