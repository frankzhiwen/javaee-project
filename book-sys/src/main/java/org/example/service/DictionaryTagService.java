package org.example.service;

import org.example.mapper.DictionaryTagMapper;
import org.example.model.DictionaryTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryTagService {

    @Autowired
    private DictionaryTagMapper dictionaryTagMapper;

    public List<DictionaryTag> query(String dictionaryKey) {
        return dictionaryTagMapper.query(dictionaryKey);
    }
}
