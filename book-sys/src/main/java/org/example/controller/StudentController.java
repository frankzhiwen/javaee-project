package org.example.controller;

import org.example.model.Student;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 查询学生列表（数据字典，为选择班级下拉菜单后，学生下拉菜单初始化）
     * GET api/student/queryAsDict?dictionaryKey=2
     *   "success" : true,
     *   "data" : [ {
     *     "dictionaryTagKey" : "6",
     *     "dictionaryTagValue" : "小小的梦想\uD83D\uDC37B1",
     *     "studentNo" : "s00006",
     *     "idCard" : "222222222222222227"
     *   }, ...]
     */
    @GetMapping("/queryAsDict")
    public Object queryAsDict(Integer dictionaryKey){
        //在班级表，根据传入的班级id查询所有关联学生列表
        List<Student> students = studentService.queryAsDict(dictionaryKey);
        return students;
    }

    /**
     * 查询学生列表
     * GET api/student/query?searchText=&sortOrder=asc&pageSize=7&pageNumber=1&_=1627202269740
     * 响应数据格式，可以查看main.js中，学生管理columns中每个字段（field绑定）
     * 需要的是List<Student>，每个Student除本身的字段，还有classes字段包含班级信息
     */
    @GetMapping("/query")
    public Object query(Student student){
        List<Student> students = studentService.query(student);
        return students;
    }

    /**
     * 新增学生信息
     * POST api/student/add
     * {studentName: "发送到发送到", studentNo: "sdads", idCard: "231221313", classesId: "1", studentEmail: "fsdfd"}
     */
    @PostMapping("/add")
    public Object add(@RequestBody Student student){
        int n = studentService.add(student);
        return null;
    }

    /**
     * 修改学生信息：
     * POST api/student/update
     * Content-Type: application/json
     * 数据格式和add添加一样，多了id字段
     */
    @PostMapping("/update")
    public Object update(@RequestBody Student student){
        int n = studentService.update(student);
        return null;
    }

    /**
     * 删除学生
     * GET api/student/delete?ids=1&ids=2
     */
    @GetMapping("/delete")
    public Object delete(@RequestParam("ids") List<Integer> ids){
        int n = studentService.delete(ids);
        return null;
    }

    /**
     * 查询学生详情：
     * GET api/student/queryById?id=1
     * 响应数据格式和查询学生列表一样，只是返回一个student
     */
    @GetMapping("/queryById")
    public Object queryById(Integer id){
        Student student = studentService.queryById(id);
        return student;
    }
}
