package frank.mapper;

import frank.base.BaseMapper;
import frank.model.Classes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClassesMapper extends BaseMapper<Classes> {

    List<Classes> queryAsDict(String dictionaryKey);
}