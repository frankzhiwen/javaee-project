package org.example.controller;

import org.example.model.Book;
import org.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * 查询图书数据字典（图书下拉菜单初始化）
     * GET api/book/queryAsDict
     */
    @GetMapping("/queryAsDict")
    public Object queryAsDict(){
        List<Book> books = bookService.queryAsDict();
        return books;
    }
}
