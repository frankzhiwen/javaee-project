package frank.config.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import frank.model.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    private ObjectMapper objectMapper;

    public LoginInterceptor(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if(session != null){
            User user = (User) session.getAttribute("user");
            if(user != null){
                //权限验证
                return true;
            }
        }
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/json; charset=UTF-8");
//        PrintWriter writer = response.getWriter();
//        ResponseResult result = ResponseResult.error("401", "未被授权的请求");
//        writer.println(objectMapper.writeValueAsString(result));
//        writer.flush();
        response.sendRedirect(request.getContextPath()+"/public/index.html");
        return false;
    }

}
