package org.example.service;

import org.example.mapper.BookMapper;
import org.example.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookMapper bookMapper;

    public List<Book> queryAsDict() {
        return bookMapper.queryAsDict();
    }
}
