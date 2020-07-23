package frank.mapper;

import frank.base.BaseMapper;
import frank.model.Exam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExamMapper extends BaseMapper<Exam> {

    List<Exam> query(Exam exam);

    Exam queryById(Integer id);

    List<Exam> queryAsDict(String dictionaryKey);
}