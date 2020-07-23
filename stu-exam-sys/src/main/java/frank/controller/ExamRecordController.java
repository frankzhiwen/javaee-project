package frank.controller;

import frank.model.ExamRecord;
import frank.service.ExamRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/examRecord")
public class ExamRecordController {

    @Autowired
    private ExamRecordService examRecordService;

    @GetMapping("/query")
    public Object query(ExamRecord examRecord){
        List<ExamRecord> examRecordList = examRecordService.query(examRecord);
        return examRecordList;
    }

    @GetMapping("/queryById")
    public Object queryById(Integer id){
        ExamRecord examRecord = examRecordService.queryById(id);
        return examRecord;
    }

    @PostMapping("/add")
    public Object insert(@RequestBody ExamRecord examRecord){
        examRecordService.insert(examRecord);
        return null;
    }

    @PostMapping("/update")
    public Object updateById(@RequestBody ExamRecord examRecord){
        examRecordService.updateById(examRecord);
        return null;
    }

    @GetMapping("/delete")
    public Object deleteByIds(@RequestParam("ids") List<Integer> ids){
        examRecordService.deleteByIds(ids);
        return null;
    }
}
