package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.base.BaseMapper;
import org.example.model.Student;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface StudentMapper extends BaseMapper<Student> {
    List<Student> queryAsDict(Integer classesId);

    List<Student> query(Student student);

    Student queryById(Integer id);
}