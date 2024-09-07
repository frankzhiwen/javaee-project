package org.example.controller;

import org.example.model.Classes;
import org.example.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classes")
public class ClassesController {

    @Autowired
    private ClassesService classesService;

    /**
     * 查询班级数据字典
     * GET api/classes/queryAsDict
     * 响应数据：
     *  {
     *      success: true,
     *      data: [ {
     *          "dictionaryTagKey" : "1",
     *          "dictionaryTagValue" : "幼儿园\uD83D\uDE02大班",
     *          "classesGraduateYear" : "000001001",
     *          "classesMajor" : "000002003"
     *      }, ...]
     *  返回的数据，可以使用
     *  （1）再定义一个类，包含以上4个字段
     *  （2）返回Classes对象，Classes继承DictionaryTag
     *  我们采取第二种方式，简单实现就行
     *  注意：
     *  转换班级id为数据字典标签的key（dictionaryTagKey）
     *  转换班级name为数据字典标签的value（dictionaryTagValue）
     */
    @GetMapping("/queryAsDict")
    public Object queryAsDict(){
        //查询所有班级信息，
        List<Classes> dicts = classesService.queryAsDict();
        return dicts;
    }

    /**
     * 查询班级列表（分页）
     * GET api/classes/query?searchText=&sortOrder=asc&pageSize=7&pageNumber=1&_=1627202269740
     */
    @GetMapping("/query")
    public Object query(Classes classes){
        List<Classes> list = classesService.query(classes);
        return list;
    }

    /**
     * 新增班级信息
     * POST api/classes/add
     * {班级类型非主键字段}
     */
    @PostMapping("/add")
    public Object add(@RequestBody Classes classes){
        int n = classesService.add(classes);
        return null;
    }

    /**
     * 修改班级信息
     * POST api/classes/update
     * {班级类型字段}
     */
    @PostMapping("/update")
    public Object update(@RequestBody Classes classes){
        int n = classesService.update(classes);
        return null;
    }

    /**
     * 删除班级
     * GET api/classes/delete?ids=1&ids=2
     */
    @GetMapping("/delete")
    public Object delete(@RequestParam("ids") List<Integer> ids){
        int n = classesService.delete(ids);
        return null;
    }

    /**
     * 查询班级详情：
     * GET api/classes/queryById?id=1
     */
    @GetMapping("/queryById")
    public Object queryById(Integer id){
        Classes classes = classesService.queryById(id);
        return classes;
    }

}
