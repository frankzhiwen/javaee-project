package frank.service;

import frank.mapper.DictionaryMapper;
import frank.mapper.DictionaryTagMapper;
import frank.model.DictionaryTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryTagService {

    @Autowired
    private DictionaryMapper dictionaryMapper;
    @Autowired
    private DictionaryTagMapper dictionaryTagMapper;

    public List<DictionaryTag> query(String dictionaryKey) {
        List<DictionaryTag> tags = dictionaryTagMapper.selectByFullKey(dictionaryKey);
        return tags;
    }
}
