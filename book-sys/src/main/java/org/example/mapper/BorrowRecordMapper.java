package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.base.BaseMapper;
import org.example.model.BorrowRecord;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface BorrowRecordMapper extends BaseMapper<BorrowRecord> {
    List<BorrowRecord> query(BorrowRecord input);

    BorrowRecord queryById(Integer id);
}