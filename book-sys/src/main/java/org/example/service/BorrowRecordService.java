package org.example.service;

import com.github.pagehelper.PageHelper;
import org.example.mapper.BorrowRecordMapper;
import org.example.model.BorrowRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowRecordService {

    @Autowired
    private BorrowRecordMapper borrowRecordMapper;

    public List<BorrowRecord> query(BorrowRecord input) {
        PageHelper.startPage(input);
        input.setSearchText("%"+input.getSearchText()+"%");
        return borrowRecordMapper.query(input);
    }

    public int add(BorrowRecord record) {
        return borrowRecordMapper.insertSelective(record);
    }

    public int delete(List<Integer> ids) {
        return borrowRecordMapper.deleteByIds(ids);
    }

    public BorrowRecord queryById(Integer id) {
        return borrowRecordMapper.queryById(id);
    }

    public int update(BorrowRecord record) {
        return borrowRecordMapper.updateByPrimaryKeySelective(record);
    }
}
