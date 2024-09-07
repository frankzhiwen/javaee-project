package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.base.BaseMapper;
import org.example.model.Book;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface BookMapper extends BaseMapper<Book> {
    List<Book> queryAsDict();
}