package frank.service;

import com.github.pagehelper.PageHelper;
import frank.exception.BusinessException;
import frank.mapper.CourseMapper;
import frank.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseMapper courseMapper;

    public List<Course> query(Course course) {
        PageHelper.startPage(course);
        return courseMapper.selectByCondition(course);
    }

    public Course queryById(Integer id) {
        return courseMapper.selectByPrimaryKey(id);
    }

    @Transactional
    public int insert(Course course) {
        course.setCreateTime(new Date());
        int num = courseMapper.insertSelective(course);
        if(num != 1)
            throw new BusinessException("-CLS001", "插入班级数据数量异常");
        return num;
    }

    @Transactional
    public int updateById(Course course) {
        int num = courseMapper.updateByPrimaryKeySelective(course);
        if(num != 1)
            throw new BusinessException("-CLS002", "修改班级数据数量异常");
        return num;
    }

    @Transactional
    public int deleteByIds(List<Integer> ids) {
        return courseMapper.deleteByIds(ids);
    }

    public List<Course> queryAsDict(String dictionaryKey) {
        return courseMapper.queryAsDict(dictionaryKey);
    }
}
