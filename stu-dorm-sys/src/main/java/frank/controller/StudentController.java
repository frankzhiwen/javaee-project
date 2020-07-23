package frank.controller;

import frank.model.Student;
import frank.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/query")
    public Object query(Student student){
        List<Student> students = studentService.query(student);
        return students;
    }

    @GetMapping("/queryById")
    public Object queryById(Integer id){
        Student student = studentService.queryById(id);
        return student;
    }

    @PostMapping("/add")
    public Object insert(@RequestBody Student student){
        studentService.insert(student);
        return null;
    }

    @PostMapping("/update")
    public Object updateById(@RequestBody Student student){
        studentService.updateById(student);
        return null;
    }

    @GetMapping("/delete")
    public Object deleteByIds(@RequestParam("ids") List<Integer> ids){
        studentService.deleteByIds(ids);
        return null;
    }

    @PostMapping("/apply")
    public Object apply(@RequestBody Student student){
        studentService.apply(student);
        return null;
    }
}
