package com.example.testweb;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

@Slf4j
public class LogInterceptor implements AsyncHandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("url : {}", request.getRequestURL());
        log.info("request : {} response : {}", request.getMethod(), request.getRequestURL());

        return AsyncHandlerInterceptor.super.preHandle(request, response, handler);
    }
}
