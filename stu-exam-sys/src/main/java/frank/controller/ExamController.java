package frank.controller;

import frank.model.Exam;
import frank.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    private ExamService examService;

    @GetMapping("/query")
    public Object query(Exam exam){
        List<Exam> examList = examService.query(exam);
        return examList;
    }

    @GetMapping("/queryById")
    public Object queryById(Integer id){
        Exam exam = examService.queryById(id);
        return exam;
    }

    @PostMapping("/add")
    public Object insert(@RequestBody Exam exam){
        examService.insert(exam);
        return null;
    }

    @PostMapping("/update")
    public Object updateById(@RequestBody Exam exam){
        examService.updateById(exam);
        return null;
    }

    @GetMapping("/delete")
    public Object deleteByIds(@RequestParam("ids") List<Integer> ids){
        examService.deleteByIds(ids);
        return null;
    }

    @GetMapping("/queryAsDict")
    public Object queryAsDict(String dictionaryKey){
        List<Exam> tags = examService.queryAsDict(dictionaryKey);
        return tags;
    }
}
