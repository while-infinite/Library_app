package ru.community.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.community.entity.Book;
import ru.community.entity.Librarian;
import ru.community.exception.LibrarianNotFound;
import ru.community.service.LibraryService;
import javax.validation.Valid;
import java.io.File;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class LibrarianController {

    private final LibraryService service;


    @PostMapping("/librarian")
    public ResponseEntity<Librarian> addLibrarian(@Valid @RequestBody Librarian librarian) throws LibrarianNotFound {
        service.addLibrarian(librarian);
        return ResponseEntity.ok(librarian);
    }

    @GetMapping("/librarian/{id}")
    public Librarian getLibrarian(@PathVariable int id){
        return service.getLibrarian(id);
    }

    @GetMapping("/librarian/list")
    public List<Librarian> getAllLibrarian(){
        return service.getAllLibrarian();
    }

    @DeleteMapping("/librarian/delete/{id}")
    public void deleteLibrarian(@PathVariable int id){
        service.deleteLibrarian(id);
    }

    @PostMapping("librarian/{librarianId}/addBook")
    public Book addBooks(@RequestBody Book book,
                         @PathVariable int librarianId,
                         @RequestParam(name = "bookCount") int bookCount,
                         @RequestParam(name = "causeOfParish") String causeOfParish,
                         @RequestParam(name = "comment") String comment) throws Exception {

        return service.addBooks(book, bookCount, causeOfParish, librarianId, comment);
    }

    @PostMapping("librarian/{librarianId}/add")
    public List<Book> addBooksFromFile(@RequestParam(name = "file") MultipartFile file/*,
                                       @PathVariable int librarianId,
                                       @RequestParam(name = "causeOfParish") String registerOfParish,
                                       @RequestParam(name = "comment") String comment*/)  {
        return service.addBooksFromFile(file);
    }
}
