package com.moon.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        Object obj = request.getSession().getAttribute("user");
//        if (obj == null) {
//            request.getRequestDispatcher("/login").forward(request, response);
//            return false;
//        }
//        //success
//        return true;
//    }
}
