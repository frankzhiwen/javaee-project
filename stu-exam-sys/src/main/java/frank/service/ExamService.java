package frank.service;

import com.github.pagehelper.PageHelper;
import frank.exception.BusinessException;
import frank.mapper.ExamMapper;
import frank.model.Exam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ExamService {

    @Autowired
    private ExamMapper examMapper;

    public List<Exam> query(Exam exam) {
        PageHelper.startPage(exam);
        return examMapper.query(exam);
    }

    public Exam queryById(Integer id) {
        return examMapper.queryById(id);
    }

    @Transactional
    public int insert(Exam exam) {
        exam.setCreateTime(new Date());
        int num = examMapper.insertSelective(exam);
        if(num != 1)
            throw new BusinessException("-CLS001", "插入班级数据数量异常");
        return num;
    }

    @Transactional
    public int updateById(Exam exam) {
        int num = examMapper.updateByPrimaryKeySelective(exam);
        if(num != 1)
            throw new BusinessException("-CLS002", "修改班级数据数量异常");
        return num;
    }

    @Transactional
    public int deleteByIds(List<Integer> ids) {
        return examMapper.deleteByIds(ids);
    }

    public List<Exam> queryAsDict(String dictionaryKey) {
        return examMapper.queryAsDict(dictionaryKey);
    }
}
