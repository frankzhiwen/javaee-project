package frank.service;

import com.github.pagehelper.PageHelper;
import frank.exception.BusinessException;
import frank.mapper.ClassesMapper;
import frank.model.Classes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ClassesService {

    @Autowired
    private ClassesMapper classesMapper;

    public List<Classes> query(Classes classes) {
        PageHelper.startPage(classes);
        return classesMapper.selectByCondition(classes);
    }

    public Classes queryById(Integer id) {
        return classesMapper.selectByPrimaryKey(id);
    }

    @Transactional
    public int insert(Classes classes) {
        classes.setCreateTime(new Date());
        int num = classesMapper.insertSelective(classes);
        if(num != 1)
            throw new BusinessException("-CLS001", "插入班级数据数量异常");
        return num;
    }

    @Transactional
    public int updateById(Classes classes) {
        int num = classesMapper.updateByPrimaryKeySelective(classes);
        if(num != 1)
            throw new BusinessException("-CLS002", "修改班级数据数量异常");
        return num;
    }

    @Transactional
    public int deleteByIds(List<Integer> ids) {
        return classesMapper.deleteByIds(ids);
    }

    public List<Classes> queryAsDict(String dictionaryKey) {
        return classesMapper.queryAsDict(dictionaryKey);
    }
}
