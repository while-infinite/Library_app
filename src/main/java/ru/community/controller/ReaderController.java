package ru.community.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.community.entity.Book;
import ru.community.entity.BookRating;
import ru.community.entity.Reader;
import ru.community.service.ReaderService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReaderController {

    private final ReaderService service;

    @PostMapping("/reader")
    public ResponseEntity<Reader> addReader(@Valid @RequestBody Reader reader){
        service.addReader(reader);
        return ResponseEntity.ok(reader);
    }

    @GetMapping("/reader/{id}/me")
    public Reader getReader(@PathVariable int id){
        return service.getReader(id);
    }

    @PutMapping("/reader/{id}/me/edit")
    public ResponseEntity<Reader> editReader(@PathVariable int id, @Valid @RequestBody Reader reader) {
        service.editReader(id, reader);
        return ResponseEntity.ok(service.getReader(id));
    }

    @GetMapping("/reader/list")
    public List<Reader> getAlReaders(){
        return service.getAllReaders();
    }

    @DeleteMapping("/reader/{id}")
    public void deleteReader(@PathVariable int id){
        service.deleteReader(id);
    }

    @PostMapping("/reader/{readerId}/book/{bookId}/rate")
    public ResponseEntity<BookRating> addFeedbackAndRate(@PathVariable int readerId,
                                         @PathVariable int bookId,
                                         @RequestBody String review,
                                         @RequestParam  int rate){
        BookRating bookRating = service.addFeedbackAndRate(readerId, bookId, review, rate);
        return ResponseEntity.ok(bookRating);
    }
}
