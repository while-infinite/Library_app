package ru.community.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.community.entity.BookStorage;
import ru.community.repository.BookStorageRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookStorageService {

    private final BookStorageRepository bookStorageRepository;

    public List<BookStorage> getAllAvailableBooks(){
        return bookStorageRepository.findAllAvailableBooks();
    }

    public List<BookStorage> getAllBooksByDepartment(int departmentId){
        return bookStorageRepository.findAllByDepartmentId(departmentId);
    }

    public List<BookStorage> getAllAvailableBooksByDepartmentId(int departmentId){
        return bookStorageRepository.findAllAvailableBooksByDepartmentId(departmentId);
    }

}
