package frank.controller;

import frank.model.DictionaryTag;
import frank.service.DictionaryTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dict/tag")
public class DictionaryTagController {

    @Autowired
    private DictionaryTagService dictionaryTagService;

    @GetMapping("/query")
    public Object query(String dictionaryKey){
        List<DictionaryTag> tags = dictionaryTagService.query(dictionaryKey);
        return tags;
    }
}
