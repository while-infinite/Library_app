package ru.community.parser;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CustomParser {

    <T> List<T> read(Class<T> clazz, MultipartFile file) throws IOException;
}
