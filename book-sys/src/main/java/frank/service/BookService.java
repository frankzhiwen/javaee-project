package frank.service;

import com.github.pagehelper.PageHelper;
import frank.exception.BusinessException;
import frank.mapper.BookMapper;
import frank.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookMapper bookMapper;

    public List<Book> query(Book book) {
        PageHelper.startPage(book);
        return bookMapper.selectByCondition(book);
    }

    public Book queryById(Integer id) {
        return bookMapper.selectByPrimaryKey(id);
    }

    @Transactional
    public int insert(Book book) {
        book.setCreateTime(new Date());
        int num = bookMapper.insertSelective(book);
        if(num != 1)
            throw new BusinessException("-CLS001", "插入班级数据数量异常");
        return num;
    }

    @Transactional
    public int updateById(Book book) {
        int num = bookMapper.updateByPrimaryKeySelective(book);
        if(num != 1)
            throw new BusinessException("-CLS002", "修改班级数据数量异常");
        return num;
    }

    @Transactional
    public int deleteByIds(List<Integer> ids) {
        return bookMapper.deleteByIds(ids);
    }

    public List<Book> queryAsDict(String dictionaryKey) {
        return bookMapper.queryAsDict(dictionaryKey);
    }
}
