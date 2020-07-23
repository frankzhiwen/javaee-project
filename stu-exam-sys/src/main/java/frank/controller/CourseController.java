package frank.controller;

import frank.model.Course;
import frank.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/query")
    public Object query(Course course){
        List<Course> courseList = courseService.query(course);
        return courseList;
    }

    @GetMapping("/queryById")
    public Object queryById(Integer id){
        Course course = courseService.queryById(id);
        return course;
    }

    @PostMapping("/add")
    public Object insert(@RequestBody Course course){
        courseService.insert(course);
        return null;
    }

    @PostMapping("/update")
    public Object updateById(@RequestBody Course course){
        courseService.updateById(course);
        return null;
    }

    @GetMapping("/delete")
    public Object deleteByIds(@RequestParam("ids") List<Integer> ids){
        courseService.deleteByIds(ids);
        return null;
    }

    @GetMapping("/queryAsDict")
    public Object queryAsDict(String dictionaryKey){
        List<Course> tags = courseService.queryAsDict(dictionaryKey);
        return tags;
    }
}
