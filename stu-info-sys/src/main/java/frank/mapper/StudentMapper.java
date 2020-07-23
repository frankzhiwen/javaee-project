package frank.mapper;

import frank.base.BaseMapper;
import frank.model.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper extends BaseMapper<Student> {

    List<Student> query(Student student);

    Student queryById(Integer id);

    List<Student> queryAsDict(String dictionaryKey);
}