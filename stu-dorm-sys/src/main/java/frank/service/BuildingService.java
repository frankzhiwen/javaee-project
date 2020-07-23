package frank.service;

import com.github.pagehelper.PageHelper;
import frank.exception.BusinessException;
import frank.mapper.BuildingMapper;
import frank.model.Building;
import frank.model.DictionaryTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class BuildingService {

    @Autowired
    private BuildingMapper buildingMapper;


    public List<Building> query(Building building) {
        PageHelper.startPage(building);
        return buildingMapper.query(building);
    }

    public Building queryById(Integer id) {
        return buildingMapper.queryById(id);
    }

    @Transactional
    public int insert(Building building) {
        building.setCreateTime(new Date());
        int num = buildingMapper.insertSelective(building);
        if(num != 1)
            throw new BusinessException("-BLD001", "插入宿舍楼数据数量异常");
        return num;
    }

    @Transactional
    public int updateById(Building building) {
        int num = buildingMapper.updateByPrimaryKeySelective(building);
        if(num != 1)
            throw new BusinessException("-BLD002", "修改宿舍楼数据数量异常");
        return num;
    }

    @Transactional
    public int deleteByIds(List<Integer> ids) {
        return buildingMapper.deleteByIds(ids);
    }

    public List<DictionaryTag> queryAsDict(String dictionaryKey) {
        return buildingMapper.queryAsDict(dictionaryKey);
    }
}
