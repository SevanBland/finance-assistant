package com.sevanbland.finance_assistant;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.List;
import java.util.ArrayList;

@Service
public class NoteService {
    private final Map<Long, Note> notes = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    public Note create(String title, String content) {
        long id = idCounter.incrementAndGet();
        Note note = new Note(id, title, content);
        notes.put(id, note);
        return note;
    }

    public List<Note> findAll() {
        return new ArrayList<>(notes.values());
    }

    public Note findById(Long id) {
        return notes.get(id); // returns null if missing – will handle this properly later
    }

    public Note update(Long id, String title, String content) {
        if (!notes.containsKey(id)) {
            return null;
        }

        Note updated = new Note(id, title, content);
        notes.replace(id, updated);
        return updated;
    }

    public boolean delete(Long id) {
        return notes.remove(id) != null;
    }

    public List<Note> search(String keyword) {
        return notes.values().stream()
                .filter(n -> n.title().toLowerCase().contains(keyword.toLowerCase()))
                .toList();
    }
}