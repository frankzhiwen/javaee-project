package frank.controller;

import frank.model.DictionaryTag;
import frank.model.Dorm;
import frank.service.DormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dorm")
public class DormController {

    @Autowired
    private DormService dormService;

    @GetMapping("/query")
    public Object query(Dorm dorm){
        List<Dorm> dorms = dormService.query(dorm);
        return dorms;
    }

    @GetMapping("/queryById")
    public Object queryById(Integer id){
        Dorm dorm = dormService.queryById(id);
        return dorm;
    }

    @PostMapping("/add")
    public Object insert(@RequestBody Dorm dorm){
        dormService.insert(dorm);
        return null;
    }

    @PostMapping("/update")
    public Object updateById(@RequestBody Dorm dorm){
        dormService.updateById(dorm);
        return null;
    }

    @GetMapping("/delete")
    public Object deleteByIds(@RequestParam("ids") List<Integer> ids){
        dormService.deleteByIds(ids);
        return null;
    }

    @GetMapping("/queryAsDict")
    public Object queryAsDict(String dictionaryKey){
        List<DictionaryTag> tags = dormService.queryAsDict(dictionaryKey);
        return tags;
    }
}
