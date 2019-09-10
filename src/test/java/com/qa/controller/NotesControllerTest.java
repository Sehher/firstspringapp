package com.qa.controller;

import com.qa.controllers.HomeController;
import com.qa.controllers.NotesController;
import com.qa.models.Note;
import com.qa.repository.NotesRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class NotesControllerTest {

    @InjectMocks
    private NotesController notesController;

    @Mock
    private NotesRepository repository;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetAllNotes(){
        List<Note> notesList = new ArrayList<>();
        Note note = new Note();
        note.setDescription("blah");
        note.setName("blah");
        notesList.add(note);

        when(repository.findAll()).thenReturn(notesList); //if you need a test that needs to return something from the repo, use this test
        //notesController.listAllNotes();
        assertEquals(notesController.listAllNotes().get(0).getName(),"blah");
    }

    @Test
    public void testGetOneNote(){
        Note note = new Note();
        note.setName("blah");
        when(repository.findOne(1L)).thenReturn(note);
        notesController.getNote(1L);//1L means 1 Long
    }

    @Test
    public void testAddNote(){
        Note note = new Note();
        note.setName("blah");
        note.setDescription("blah blah");
        when(repository.saveAndFlush(note)).thenReturn(note);
        notesController.addNote(note);
    }

    @Test
    public void testDeleteNote(){
        Note note = new Note();
        note.setName("blah");
        when(repository.findOne(1L)).thenReturn(note);
        notesController.deleteNote(1L);
    }

}