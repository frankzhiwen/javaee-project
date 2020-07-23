package frank.service;

import com.github.pagehelper.PageHelper;
import frank.exception.BusinessException;
import frank.mapper.BorrowRecordMapper;
import frank.model.BorrowRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class BorrowRecordService {

    @Autowired
    private BorrowRecordMapper borrowRecordMapper;

    public List<BorrowRecord> query(BorrowRecord borrowRecord) {
        PageHelper.startPage(borrowRecord);
        return borrowRecordMapper.query(borrowRecord);
    }

    public BorrowRecord queryById(Integer id) {
        return borrowRecordMapper.queryById(id);
    }

    @Transactional
    public int insert(BorrowRecord borrowRecord) {
        borrowRecord.setCreateTime(new Date());
        int num = borrowRecordMapper.insertSelective(borrowRecord);
        if(num != 1)
            throw new BusinessException("-CLS001", "插入班级数据数量异常");
        return num;
    }

    @Transactional
    public int updateById(BorrowRecord borrowRecord) {
        int num = borrowRecordMapper.updateByPrimaryKeySelective(borrowRecord);
        if(num != 1)
            throw new BusinessException("-CLS002", "修改班级数据数量异常");
        return num;
    }

    @Transactional
    public int deleteByIds(List<Integer> ids) {
        return borrowRecordMapper.deleteByIds(ids);
    }
}
