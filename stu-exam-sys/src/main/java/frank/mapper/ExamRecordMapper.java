package frank.mapper;

import frank.base.BaseMapper;
import frank.model.ExamRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExamRecordMapper extends BaseMapper<ExamRecord> {

    List<ExamRecord> query(ExamRecord examRecord);

    ExamRecord queryById(Integer id);
}