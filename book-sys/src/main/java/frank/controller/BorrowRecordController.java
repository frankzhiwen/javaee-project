package frank.controller;

import frank.model.BorrowRecord;
import frank.service.BorrowRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borrowRecord")
public class BorrowRecordController {

    @Autowired
    private BorrowRecordService borrowRecordService;

    @GetMapping("/query")
    public Object query(BorrowRecord borrowRecord){
        List<BorrowRecord> borrowRecordList = borrowRecordService.query(borrowRecord);
        return borrowRecordList;
    }

    @GetMapping("/queryById")
    public Object queryById(Integer id){
        BorrowRecord borrowRecord = borrowRecordService.queryById(id);
        return borrowRecord;
    }

    @PostMapping("/add")
    public Object insert(@RequestBody BorrowRecord borrowRecord){
        borrowRecordService.insert(borrowRecord);
        return null;
    }

    @PostMapping("/update")
    public Object updateById(@RequestBody BorrowRecord borrowRecord){
        borrowRecordService.updateById(borrowRecord);
        return null;
    }

    @GetMapping("/delete")
    public Object deleteByIds(@RequestParam("ids") List<Integer> ids){
        borrowRecordService.deleteByIds(ids);
        return null;
    }
}
