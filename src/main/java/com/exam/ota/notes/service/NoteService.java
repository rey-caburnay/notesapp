package com.exam.ota.notes.service;

import com.exam.ota.notes.exception.NoteRequiredFieldException;
import com.exam.ota.notes.model.Note;
import com.exam.ota.notes.repository.NoteRepository;
import com.exam.ota.notes.exception.NoteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> findAllNotes() {
        return noteRepository.findAll();
    }

    public Note findNoteById(Long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException("Note not found with id " + id));
    }

    public Note createNote(Note note) {

//        validateNote(note);
        return noteRepository.save(note);

    }

    public Note updateNote(Long id, Note noteDetails) {
        Note note = findNoteById(id);
        if (note == null) {
            throw new NoteNotFoundException("Note not found with id " + id);
        }
//        validateNote(noteDetails);
        note.setTitle(noteDetails.getTitle());
        note.setBody(noteDetails.getBody());
        note = noteRepository.save(note);
        return note;
    }

    public void deleteNoteById(Long id) {
//        Note note = findNoteById(id);
        noteRepository.deleteById(id);
    }

//    private void validateNote(Note note) {
//        if(note == null) throw new NullPointerException();
//        if(note.getTitle() == null || note.getTitle().isEmpty()) {
//            throw new NoteRequiredFieldException("note title is required");
//        }
//        if(note.getBody() == null || note.getBody().isEmpty()) {
//            throw new NoteRequiredFieldException("note body is required");
//        }
//    }
}
