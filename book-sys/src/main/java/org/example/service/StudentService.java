package org.example.service;

import com.github.pagehelper.PageHelper;
import org.example.mapper.StudentMapper;
import org.example.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    public List<Student> queryAsDict(Integer classesId) {
        return studentMapper.queryAsDict(classesId);
    }

    public List<Student> query(Student student) {
        PageHelper.startPage(student);
        student.setSearchText("%"+student.getSearchText()+"%");
        return studentMapper.query(student);
    }

    public int add(Student student) {
        return studentMapper.insertSelective(student);
    }

    public int update(Student student) {
        return studentMapper.updateByPrimaryKeySelective(student);
    }

    public int delete(List<Integer> ids) {
        return studentMapper.deleteByIds(ids);
    }

    public Student queryById(Integer id) {
        return studentMapper.queryById(id);
    }
}
