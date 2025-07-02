package com.shop.rainboweasywalk.interceptor;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthFilter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession(false);
        boolean loggedIn = session != null && session.getAttribute("loggedInUser") != null;

        String uri = request.getRequestURI();

        if (!loggedIn && !uri.startsWith("/auth")) {
            response.sendRedirect("/auth/login-required");
            return false;
        }
        return true;
    }
}
