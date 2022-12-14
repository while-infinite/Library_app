package ru.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.community.entity.LibraryDepartment;

import java.util.Optional;

@Repository
public interface LibraryDepartmentRepository extends JpaRepository<LibraryDepartment, Integer> {

    Optional<LibraryDepartment> findByTitle(String title);
}
