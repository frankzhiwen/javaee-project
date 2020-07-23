package frank.controller;

import frank.exception.BusinessException;
import frank.model.User;
import frank.service.UserService;
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

    @GetMapping("/query")
    public Object query(User user){
        return userService.query(user);
    }

    @GetMapping("/queryById")
    public Object queryById(Integer id){
        User user = userService.queryById(id);
        return user;
    }

    @PostMapping("/login")
    public Object login(@RequestBody User user, HttpServletRequest request){
        User exist = userService.login(user);
        HttpSession session = request.getSession();
        session.setAttribute("user", exist);
        return exist;
    }

    @GetMapping("/logout")
    public Object logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session == null)
            throw new BusinessException("-USR005", "还没进来呢，怎么就要走了");
        session.removeAttribute("user");
        return null;
    }

    @PostMapping("/register")
    public Object register(@RequestBody User user){
        userService.insert(user);
        return null;
    }

    @PostMapping("/update")
    public Object updateById(@RequestBody User user){
        int num = userService.updateById(user);
        return num;
    }

    @GetMapping("/delete")
    public Object deleteByIds(@RequestParam("ids") List<Integer> ids){
        int num = userService.deleteByIds(ids);
        return num;
    }
}
