package ru.community.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.community.entity.BookBinding;
import ru.community.entity.Reader;
import ru.community.entity.Status;
import ru.community.exception.LibraryException;
import ru.community.exception.Message;
import ru.community.repository.BookBindingRepository;
import ru.community.repository.ReaderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReaderService {

    private final ReaderRepository repository;
    private final BookBindingRepository bookBindingRepository;

    public void addReader(Reader reader) {
        if (repository.findByNameAndSurnameAndDateOfBirth(reader.getName(), reader.getSurname(), reader.getDateOfBirth()).isPresent()) {
            throw new LibraryException(Message.READER_ALREADY_EXISTS);
        } else {
            repository.save(reader);
        }
    }

    public Reader getReader(int id) {
        return repository.findById(id).orElseThrow(() -> new LibraryException(Message.READER_NOT_FOUND));
    }

    public List<Reader> getAllReaders() {
        return repository.findAll();
    }

    public void deleteReader(int id) {
        Reader reader = repository.findById(id).orElseThrow(() -> new LibraryException(Message.READER_NOT_FOUND));
        repository.delete(reader);
    }

    public Reader editReader(int id, Reader editReader) {
        var reader = repository.findById(id).orElseThrow(() -> new LibraryException(Message.READER_NOT_FOUND));
        reader.setPhoneNumber(editReader.getPhoneNumber());
        reader.setEmail(editReader.getEmail());
        reader.setDateOfBirth(editReader.getDateOfBirth());
        repository.save(reader);
        return reader;
    }

    public List<BookBinding> getBookBindingByReaderAndStatus(int readerId, List<Status> statuses) {
        repository.findById(readerId).orElseThrow(() -> new LibraryException(Message.READER_NOT_FOUND));
        return bookBindingRepository.findBookBindingByReader(readerId)
                .orElseThrow(() -> new LibraryException(Message.BOOK_BINDING_NOT_FOUND)).stream()
                .filter(bookBinding -> statuses.contains(bookBinding.getStatus()))
                .collect(Collectors.toList());
    }
}
