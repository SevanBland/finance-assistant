package com.sevanbland.finance_assistant;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.net.URI;

@RestController
@RequestMapping("/notes") // every endpoint here starts with /notes
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService) { // dependency injection
        this.noteService = noteService;
    }

    @PostMapping // POST /notes -> create a new note
    public ResponseEntity<Note> create(@RequestBody Note note) {
        Note created = noteService.create(note.title(), note.content());
        URI location = URI.create("/notes/" + created.id());
        return ResponseEntity.created(location).body(created); // 201 Created + Location header
    }

    @GetMapping // GET /notes?search=spring -> return only matching notes
    public List<Note> getAll(@RequestParam(defaultValue = "") String search) {
        if (search.isEmpty()) {
            return noteService.findAll(); // blank search term -> return all notes
        }
        return noteService.search(search); // search term -> filter
    }

    @GetMapping("/{id}") // GET /notes/1 -> return note #1
    public ResponseEntity<Note> getOne(@PathVariable Long id) {
        Note note = noteService.findById(id);

        if (note == null) {
            return ResponseEntity.notFound().build(); // 404
        }
        return ResponseEntity.ok(note); // 200
    }

    @PutMapping("/{id}") // PUT /notes/1 -> replace note #1's contents
    public ResponseEntity<Note> update(@PathVariable Long id, @RequestBody Note note) {
        Note updated = noteService.update(id, note.title(), note.content());

        if (updated == null) {
            return ResponseEntity.notFound().build(); // 404
        }
        return ResponseEntity.ok(updated); // 200
    }

    @DeleteMapping("/{id}") // DELETE /notes/1 -> delete note #1
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean removed = noteService.delete(id);

        if (!removed) {
            return ResponseEntity.notFound().build(); // 404
        }
        return ResponseEntity.noContent().build(); // 204
    }
}