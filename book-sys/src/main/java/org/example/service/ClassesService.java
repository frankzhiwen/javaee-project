package org.example.service;

import com.github.pagehelper.PageHelper;
import org.example.mapper.ClassesMapper;
import org.example.model.Classes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassesService {

    @Autowired
    private ClassesMapper classesMapper;

    public List<Classes> queryAsDict() {
        return classesMapper.queryAsDict();
    }

    public List<Classes> query(Classes classes) {
        PageHelper.startPage(classes);
        return classesMapper.selectAll();
    }

    public int add(Classes classes) {
        return classesMapper.insertSelective(classes);
    }

    public int update(Classes classes) {
        return classesMapper.updateByPrimaryKeySelective(classes);
    }

    public int delete(List<Integer> ids) {
        return classesMapper.deleteByIds(ids);
    }

    public Classes queryById(Integer id) {
        return classesMapper.selectByPrimaryKey(id);
    }
}
