package org.example.controller;

import org.example.exception.AppException;
import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 根据登录时抓包数据定义登录接口
     * POST /api/user/login
     * {username: "abc", password: "123"}
     */
    @PostMapping("/login")
    public Object login(@RequestBody User input,
                        HttpServletRequest req){
        //数据库，通过用户输入的账号查询用户信息
        User query = userService.queryByUsername(input.getUsername());
        if(query == null)
            throw new AppException("用户不存在");
        if(!query.getPassword().equals(input.getPassword()))
            throw new AppException("账号或密码错误");
        //登录成功，创建session并保存用户信息
        HttpSession session = req.getSession();
        session.setAttribute("user", query);
        return null;
    }

    @GetMapping("/query")
    public Object query(User user){
        List<User> list = userService.query(user);
        return list;
    }

    @GetMapping("/queryById")
    public Object queryById(Integer id){
        User user = userService.queryById(id);
        return user;
    }

    @PostMapping("/register")
    public Object add(@RequestBody User user){
        int n = userService.add(user);
        return null;
    }

    @PostMapping("/update")
    public Object update(@RequestBody User user){
        int n = userService.update(user);
        return null;
    }

    @GetMapping("/delete")
    public Object delete(@RequestParam List<Integer> ids){
        int n = userService.delete(ids);
        return null;
    }
}
