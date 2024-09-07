package org.example.config.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.base.ResponseResult;
import org.example.model.User;
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
        //判断是否登录
        HttpSession session = request.getSession(false);
        if(session != null){//获取登录时保存的用户信息
            User user = (User) session.getAttribute("user");
            if(user != null){//登录，允许访问
                return true;
            }
        }
        //未登陆，不允许访问
        String servletPath = request.getServletPath();//服务路径
        if(servletPath.startsWith("/api/")){//后端接口，未登陆返回401，json
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.setStatus(401);
            ResponseResult json = new ResponseResult();
            json.setMessage("未登陆，不允许访问");
            response.getWriter().println(objectMapper.writeValueAsString(json));
        }else{//前端页面，未登陆，重定向到登录页面
            String schema = request.getScheme();// http
            String host = request.getServerName();//服务端ip或域名
            int port = request.getServerPort();//port
            String contextPath = request.getContextPath();//应用上下文路径
            String basePath = schema+"://"+host+":"+port+contextPath;
            response.sendRedirect(basePath+"/index.html");
        }
        return false;
    }
}
