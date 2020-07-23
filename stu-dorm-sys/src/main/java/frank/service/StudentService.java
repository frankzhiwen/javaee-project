package frank.service;

import com.github.pagehelper.PageHelper;
import frank.exception.BusinessException;
import frank.mapper.StudentMapper;
import frank.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    public List<Student> query(Student student) {
        PageHelper.startPage(student);
        return studentMapper.query(student);
    }

    public Student queryById(Integer id) {
        return studentMapper.queryById(id);
    }

    @Transactional
    public int insert(Student student) {
        student.setCreateTime(new Date());
        int num = studentMapper.insertSelective(student);
        if(num != 1)
            throw new BusinessException("-STU001", "插入学生数据数量异常");
        return num;
    }

    @Transactional
    public int updateById(Student student) {
        int num = studentMapper.updateByPrimaryKeySelective(student);
        if(num != 1)
            throw new BusinessException("-STU002", "修改学生数据数量异常");
        return num;
    }

    @Transactional
    public int deleteByIds(List<Integer> ids) {
        return studentMapper.deleteByIds(ids);
    }

    @Transactional
    public int apply(Student student) {
        int num = studentMapper.apply(student);
        if(num != student.getIds().size())
            throw new BusinessException("-STU003", "分配学生住宿操作，更新数量异常");
        return num;
    }
}
