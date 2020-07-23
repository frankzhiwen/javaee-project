package frank.mapper;

import frank.base.BaseMapper;
import frank.model.BorrowRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BorrowRecordMapper extends BaseMapper<BorrowRecord> {

    List<BorrowRecord> query(BorrowRecord borrowRecord);

    BorrowRecord queryById(Integer id);
}