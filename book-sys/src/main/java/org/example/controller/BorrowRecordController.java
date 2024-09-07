package org.example.controller;

import org.example.model.BorrowRecord;
import org.example.service.BorrowRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borrowRecord")
public class BorrowRecordController {

    @Autowired
    private BorrowRecordService borrowRecordService;

    /**
     * 查询图书借阅列表
     * GET api/borrowRecord/query?searchText=&sortOrder=asc&pageSize=7&pageNumber=1
     * 响应数据格式：
     *   "success" : true,
     *   "total" : 9,
     *   "data" : [ {
     *     "id" : 1,
     *     "startTime" : "2020-01-01 14:20:00",
     *     "endTime" : "2020-02-01 14:20:00",
     *     "createTime" : "2020-06-17 15:52:33",
     *     "book" : {
     *       "id" : 1,
     *       "bookName" : "高等数学",
     *       "author" : "马拉司机",
     *       "price" : 115.20
     *     },
     *     "classes" : {
     *       "id" : 1,
     *       "classesName" : "幼儿园\uD83D\uDE02大班",
     *       "classesGraduateYear" : "000001001",
     *       "classesMajor" : "000002002",
     *       "classesDesc" : "已经4岁，即将成为大人的大班同学，不再是3岁小孩子了呀"
     *     },
     *     "student" : {
     *       "id" : 1,
     *       "studentName" : "小小的梦想\uD83D\uDC37A1",
     *       "studentNo" : "s00001",
     *       "idCard" : "222222222222222222",
     *       "studentEmail" : "123@qq.com"
     *     }
     *   }, ...]
     */
    @GetMapping("/query")
    public Object query(BorrowRecord input){
        List<BorrowRecord> records = borrowRecordService.query(input);
        return records;
    }

    /**
     * 新增借阅记录
     * POST api/borrowRecord/add
     * Content-Type: application/json
     *
     * {"studentId":"7","bookId":"3","startTime":"2020-06-10 19:40:56","endTime":""}
     */
    @PostMapping("/add")
    public Object add(@RequestBody BorrowRecord record){
        int n = borrowRecordService.add(record);
        return null;
    }

    /**
     * 删除借阅记录
     * GET api/borrowRecord/delete?ids=2&ids=3
     */
    @GetMapping("/delete")
    public Object delete(@RequestParam("ids")List<Integer> ids){
        int n = borrowRecordService.delete(ids);
        return null;
    }

    /**
     * 查询借阅详情：修改借阅记录弹出窗中，根据借阅记录id查询一条数据
     * GET api/borrowRecord/queryById?id=1
     * 响应数据格式，和query接口类似，只是是一条数据
     */
    @GetMapping("/queryById")
    public Object queryById(Integer id){
        BorrowRecord record = borrowRecordService.queryById(id);
        return record;
    }

    /**
     * 修改借阅信息
     * POST api/borrowRecord/update
     * Content-Type: application/json
     *
     * {"id":"10","studentId":"3","bookId":"4","startTime":"2020-06-11 19:24:46","endTime":"2020-06-18 19:54:49"}
     */
    @PostMapping("/update")
    public Object update(@RequestBody BorrowRecord record){
        int n = borrowRecordService.update(record);
        return null;
    }
}
