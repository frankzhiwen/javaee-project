package org.example.service;

import org.example.mapper.DictionaryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DictionaryService {

    @Autowired
    private DictionaryMapper dictionaryMapper;
}
