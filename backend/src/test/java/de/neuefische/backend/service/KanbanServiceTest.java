package de.neuefische.backend.service;

import de.neuefische.backend.model.ToDo;
import de.neuefische.backend.repository.RepoToDo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class KanbanServiceTest {

    RepoToDo repoToDo = mock(RepoToDo.class);
    GeneradeUUID generadeUUID = new GeneradeUUID();
    KanbanService kanbanService = new KanbanService(repoToDo, generadeUUID);
    ToDo toDo1 = new ToDo("Eating", "OPEN", String.valueOf(generadeUUID));
    ToDo toDo2 = new ToDo("Sleeping", "DOING", String.valueOf(generadeUUID));

    @Test
    void getAllToDos_showAllToDos() {
        //GIVEN
        //WHEN
        when(repoToDo.getAllToDos()).thenReturn(List.of(toDo1, toDo2));
        List <ToDo> actual = kanbanService.getAllToDos();
        //THEN
        assertEquals(List.of(toDo1, toDo2), actual);
        verify(repoToDo).getAllToDos();
    }

    @Test
    void addToDo_returnTheToDo() {
        //GIVEN
        //WHEN
        when(repoToDo.addToDo(toDo1)).thenReturn(toDo1);
        ToDo actual = kanbanService.addToDo(toDo1);
        //THEN
        assertEquals(toDo1, actual);
        verify(repoToDo).addToDo(toDo1);
    }

    @Test
    void getToDo_giveOneId_getThisToDo() {
        //GIVEN
        String id = String.valueOf(generadeUUID);
        //WHEN
        when(repoToDo.getToDo(id)).thenReturn(toDo1);
        ToDo actual = kanbanService.getToDo(id);
        //THEN
        assertEquals(toDo1, actual);
        verify(repoToDo).getToDo(id);
    }

    @Test
    void editToDo_returnTheListOfAllToDosWithTheEditToDo() {
        //GIVEN
        String id = String.valueOf(generadeUUID);
        List<ToDo> expected = new ArrayList<>(List.of(toDo1,toDo2));
        //WHEN
        when(repoToDo.editToDo(id,toDo1)).thenReturn(List.of(toDo1, toDo2));
        List<ToDo> actual = kanbanService.editToDo(id, toDo1);
        //THEN
        assertEquals(expected, actual);
        verify(repoToDo).editToDo(id, toDo1);
    }

    @Test
    void deleteToDo_returnTheListOfAllToDosWithoutTheDeleteToDo() {
        //GIVEN
        String id = String.valueOf(generadeUUID);
        List<ToDo> expected = new ArrayList<>(List.of(toDo2));
        //WHEN
        when(repoToDo.deleteToDo(id)).thenReturn(List.of(toDo2));
        List<ToDo> actual = kanbanService.deleteToDo(id);
        //THEN
        assertEquals(expected, actual);
        verify(repoToDo).deleteToDo(id);
    }
}