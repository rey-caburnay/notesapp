package com.exam.ota.notes.service;


import com.exam.ota.notes.model.Note;
import com.exam.ota.notes.repository.NoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class NoteServiceTest {

    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    private NoteService noteService;

    private Note note;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        note = new Note();
        note.setId(1L);
        note.setTitle("Test Title");
        note.setBody("Test Body");
    }

    @Test
    void testCreateNote() {
        when(noteRepository.save(any(Note.class))).thenReturn(note);

        Note createdNote = noteService.createNote(note);

        assertEquals(note.getTitle(), createdNote.getTitle());
        assertEquals(note.getBody(), createdNote.getBody());
        verify(noteRepository, times(1)).save(any(Note.class));
    }

    @Test
    void testFindAllNotes() {
        List<Note> notes = new ArrayList<>();
        notes.add(note);

        when(noteRepository.findAll()).thenReturn(notes);

        List<Note> fetchedNotes = noteService.findAllNotes();

        assertEquals(1, fetchedNotes.size());
        assertEquals(note.getTitle(), fetchedNotes.get(0).getTitle());
        verify(noteRepository, times(1)).findAll();
    }

    @Test
    void testFindNoteById() {
        when(noteRepository.findById(anyLong())).thenReturn(Optional.of(note));

        Note fetchedNote = noteService.findNoteById(1L);

        assertTrue(fetchedNote != null);
        assertEquals(note.getTitle(), fetchedNote.getTitle());
        verify(noteRepository, times(1)).findById(anyLong());
    }

    @Test
    void testUpdateNote() {
        when(noteRepository.findById(anyLong())).thenReturn(Optional.of(note));
        when(noteRepository.save(any(Note.class))).thenReturn(note);

        Note updatedNote = noteService.updateNote(note.getId(), note);

        assertEquals(note.getTitle(), updatedNote.getTitle());
        assertEquals(note.getBody(), updatedNote.getBody());
        verify(noteRepository, times(1)).save(any(Note.class));
        }

    @Test
    void testDeleteNoteById() {
        doNothing().when(noteRepository).deleteById(anyLong());

        noteService.deleteNoteById(1L);

        verify(noteRepository, times(1)).deleteById(anyLong());
    }
}
