package frank.service;

import com.github.pagehelper.PageHelper;
import frank.exception.BusinessException;
import frank.mapper.DormMapper;
import frank.model.DictionaryTag;
import frank.model.Dorm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class DormService {

    @Autowired
    private DormMapper dormMapper;


    public List<Dorm> query(Dorm dorm) {
        PageHelper.startPage(dorm);
        List<Dorm> dorms = dormMapper.query(dorm);
        return dorms;
    }

    public Dorm queryById(Integer id) {
        return dormMapper.queryById(id);
    }

    @Transactional
    public int insert(Dorm dorm) {
        dorm.setCreateTime(new Date());
        int num = dormMapper.insertSelective(dorm);
        if(num != 1)
            throw new BusinessException("-DRM001", "插入宿舍楼数据数量异常");
        return num;
    }

    @Transactional
    public int updateById(Dorm dorm) {
        int num = dormMapper.updateByPrimaryKeySelective(dorm);
        if(num != 1)
            throw new BusinessException("-DRM002", "修改宿舍楼数据数量异常");
        return num;
    }

    @Transactional
    public int deleteByIds(List<Integer> ids) {
        return dormMapper.deleteByIds(ids);
    }

    public List<DictionaryTag> queryAsDict(String dictionaryKey) {
        return dormMapper.queryAsDict(dictionaryKey);
    }
}
