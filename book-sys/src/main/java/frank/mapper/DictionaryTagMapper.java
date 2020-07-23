package frank.mapper;

import frank.base.BaseMapper;
import frank.model.DictionaryTag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DictionaryTagMapper extends BaseMapper<DictionaryTag> {

    List<DictionaryTag> selectByFullKey(String dictionaryKey);
}