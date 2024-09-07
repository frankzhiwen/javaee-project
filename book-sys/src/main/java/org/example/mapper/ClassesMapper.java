package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.base.BaseMapper;
import org.example.model.Classes;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ClassesMapper extends BaseMapper<Classes> {
    List<Classes> queryAsDict();
}