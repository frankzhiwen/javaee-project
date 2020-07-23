package frank.mapper;

import frank.base.BaseMapper;
import frank.model.Building;
import frank.model.DictionaryTag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BuildingMapper extends BaseMapper<Building> {

    List<Building> query(Building building);

    Building queryById(Integer id);

    List<DictionaryTag> queryAsDict(String dictionaryKey);
}