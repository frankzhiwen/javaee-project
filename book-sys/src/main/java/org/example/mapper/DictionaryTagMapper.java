package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.base.BaseMapper;
import org.example.model.DictionaryTag;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface DictionaryTagMapper extends BaseMapper<DictionaryTag> {
    List<DictionaryTag> query(String dictionaryKey);
}