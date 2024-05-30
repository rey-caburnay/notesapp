package com.exam.ota.notes.controller;

import com.exam.ota.notes.model.Note;
import com.exam.ota.notes.service.NoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class NoteControllerTest {

    private MockMvc mockMvc;

    @Mock
    private NoteService noteService;

    @InjectMocks
    private NoteController noteController;

    private Note note;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(noteController).build();
        note = new Note();
        note.setId(1L);
        note.setTitle("Test Title");
        note.setBody("Test Body");
    }

    @Test
    void testCreateNote() throws Exception {
        when(noteService.createNote(any(Note.class))).thenReturn(note);

        mockMvc.perform(post("/notes")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Test Title\", \"body\":\"Test Body\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value(note.getTitle()))
                .andExpect(jsonPath("$.body").value(note.getBody()));

        verify(noteService, times(1)).createNote(any(Note.class));
    }

    @Test
    void testFindAllNotes() throws Exception {
        List<Note> notes = new ArrayList<>();
        notes.add(note);

        when(noteService.findAllNotes()).thenReturn(notes);

        mockMvc.perform(get("/notes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(note.getTitle()))
                .andExpect(jsonPath("$[0].body").value(note.getBody()));

        verify(noteService, times(1)).findAllNotes();
    }

    @Test
    void testFindNoteById() throws Exception {
        when(noteService.findNoteById(anyLong())).thenReturn(note);

        mockMvc.perform(get("/notes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(note.getTitle()))
                .andExpect(jsonPath("$.body").value(note.getBody()));

        verify(noteService, times(1)).findNoteById(anyLong());
    }

    @Test
    void testUpdateNote() throws Exception {
        note.setTitle("Updated Title");
        note.setBody("Updated Body");
        when(noteService.findNoteById(anyLong())).thenReturn(note);
        when(noteService.updateNote(anyLong(), any(Note.class))).thenReturn(note);

        mockMvc.perform(put("/notes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Updated Title\", \"body\":\"Updated Body\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(note.getTitle()))
                .andExpect(jsonPath("$.body").value(note.getBody()));

        verify(noteService, times(1)).updateNote(any(Long.class), any(Note.class));
    }

    @Test
    void testDeleteNoteById() throws Exception {
        when(noteService.findNoteById(anyLong())).thenReturn(note);
        doNothing().when(noteService).deleteNoteById(anyLong());

        mockMvc.perform(delete("/notes/1"))
                .andExpect(status().isNoContent());

        verify(noteService, times(1)).deleteNoteById(anyLong());
    }
}
