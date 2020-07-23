package frank.service;

import com.github.pagehelper.PageHelper;
import frank.exception.BusinessException;
import frank.mapper.ExamRecordMapper;
import frank.model.ExamRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ExamRecordService {

    @Autowired
    private ExamRecordMapper examRecordMapper;

    public List<ExamRecord> query(ExamRecord examRecord) {
        PageHelper.startPage(examRecord);
        return examRecordMapper.query(examRecord);
    }

    public ExamRecord queryById(Integer id) {
        return examRecordMapper.queryById(id);
    }

    @Transactional
    public int insert(ExamRecord examRecord) {
        examRecord.setCreateTime(new Date());
        int num = examRecordMapper.insertSelective(examRecord);
        if(num != 1)
            throw new BusinessException("-CLS001", "插入班级数据数量异常");
        return num;
    }

    @Transactional
    public int updateById(ExamRecord examRecord) {
        int num = examRecordMapper.updateByPrimaryKeySelective(examRecord);
        if(num != 1)
            throw new BusinessException("-CLS002", "修改班级数据数量异常");
        return num;
    }

    @Transactional
    public int deleteByIds(List<Integer> ids) {
        return examRecordMapper.deleteByIds(ids);
    }
}
