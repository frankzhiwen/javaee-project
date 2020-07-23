package frank.mapper;

import frank.base.BaseMapper;
import frank.model.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper extends BaseMapper<Book> {

    List<Book> queryAsDict(String dictionaryKey);
}