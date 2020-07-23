package frank.mapper;

import frank.base.BaseMapper;
import frank.model.Course;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseMapper extends BaseMapper<Course> {

    List<Course> queryAsDict(String dictionaryKey);
}