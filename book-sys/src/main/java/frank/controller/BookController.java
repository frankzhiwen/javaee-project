package frank.controller;

import frank.model.Book;
import frank.model.Student;
import frank.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/query")
    public Object query(Book book){
        List<Book> bookList = bookService.query(book);
        return bookList;
    }

    @GetMapping("/queryById")
    public Object queryById(Integer id){
        Book book = bookService.queryById(id);
        return book;
    }

    @PostMapping("/add")
    public Object insert(@RequestBody Book book){
        bookService.insert(book);
        return null;
    }

    @PostMapping("/update")
    public Object updateById(@RequestBody Book book){
        bookService.updateById(book);
        return null;
    }

    @GetMapping("/delete")
    public Object deleteByIds(@RequestParam("ids") List<Integer> ids){
        bookService.deleteByIds(ids);
        return null;
    }

    @GetMapping("/queryAsDict")
    public Object queryAsDict(String dictionaryKey){
        List<Book> tags = bookService.queryAsDict(dictionaryKey);
        return tags;
    }
}
