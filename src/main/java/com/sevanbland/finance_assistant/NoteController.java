package com.sevanbland.finance_assistant;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes") // every endpoint here starts with /notes
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService) { // dependency injection
        this.noteService = noteService;
    }

    @PostMapping // POST /notes -> create a new note
    public Note create(@RequestBody Note note) {
        return noteService.create(note.title(), note.content());
    }

    @GetMapping // GET /notes -> return all notes
    public List<Note> getAll() {
        return noteService.findAll();
    }

    @GetMapping("/{id}") // GET /notes/1 -> return note #1
    public Note getOne(@PathVariable Long id) {
        return noteService.findById(id);
    }

    @PutMapping("/{id}") // PUT /notes/1 -> replace note #1's contents
    public Note update(@PathVariable Long id, @RequestBody Note note) {
        return noteService.update(id, note.title(), note.content());
    }

    @DeleteMapping("/{id}") // DELETE /notes/1 -> delete note #1
    public void delete(@PathVariable Long id) {
        noteService.delete(id);
    }
}