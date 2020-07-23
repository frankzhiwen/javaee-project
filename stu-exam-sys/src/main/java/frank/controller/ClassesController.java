package frank.controller;

import frank.model.Classes;
import frank.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classes")
public class ClassesController {

    @Autowired
    private ClassesService classesService;

    @GetMapping("/query")
    public Object query(Classes classes){
        List<Classes> classesList = classesService.query(classes);
        return classesList;
    }

    @GetMapping("/queryById")
    public Object queryById(Integer id){
        Classes classes = classesService.queryById(id);
        return classes;
    }

    @PostMapping("/add")
    public Object insert(@RequestBody Classes classes){
        classesService.insert(classes);
        return null;
    }

    @PostMapping("/update")
    public Object updateById(@RequestBody Classes classes){
        classesService.updateById(classes);
        return null;
    }

    @GetMapping("/delete")
    public Object deleteByIds(@RequestParam("ids") List<Integer> ids){
        classesService.deleteByIds(ids);
        return null;
    }

    @GetMapping("/queryAsDict")
    public Object queryAsDict(String dictionaryKey){
        List<Classes> tags = classesService.queryAsDict(dictionaryKey);
        return tags;
    }
}
