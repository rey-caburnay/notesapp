package com.exam.ota.notes.repository;

import com.exam.ota.notes.model.Note;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class NoteRepository {
    private final List<Note> notes = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    public List<Note> findAll() {
        return notes;
    }

    public Optional<Note> findById(Long id) {
        return notes.stream().filter(note -> note.getId().equals(id)).findFirst();
    }

    public Note save(Note note) {
        note.setId(counter.incrementAndGet());
        notes.add(note);
        return note;
    }

    public void deleteById(Long id) {
        notes.removeIf(note -> note.getId().equals(id));
    }
}
