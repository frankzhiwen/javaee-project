package frank.mapper;

import frank.base.BaseMapper;
import frank.model.DictionaryTag;
import frank.model.Dorm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DormMapper extends BaseMapper<Dorm> {

    List<Dorm> query(Dorm dorm);

    Dorm queryById(Integer id);

    List<DictionaryTag> queryAsDict(String dictionaryKey);
}