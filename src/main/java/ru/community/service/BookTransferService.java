package ru.community.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.community.entity.BookTransfer;
import ru.community.exception.LibraryException;
import ru.community.exception.Message;
import ru.community.repository.BookTransferRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookTransferService {

    private final BookTransferRepository bookTransferRepository;

    public BookTransfer getBookTransfer(int id){
        return bookTransferRepository.findById(id)
                .orElseThrow(()-> new LibraryException(Message.BOOK_TRANSFER_NOT_FOUND));
    }

    public List<BookTransfer> getAllGetBookTransfer(){
        return bookTransferRepository.findAll();
    }
}
