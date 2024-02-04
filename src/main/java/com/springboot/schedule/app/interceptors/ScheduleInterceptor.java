package com.springboot.schedule.app.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;

@Component("schedule")
public class ScheduleInterceptor implements HandlerInterceptor {
    @Value("${config.schedule.open}")
    private Integer open;
    @Value("${config.schedule.close}")
    private Integer close;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour >= open && hour < close) {
            StringBuilder message = new StringBuilder("Welcome to customer service,");
            message.append(" we attend between ").append(open).append(" and ").append(close).append(" hours");
            request.setAttribute("message",message.toString());
            return true;
        }
        response.sendRedirect(request.getContextPath().concat("/closed"));
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
        String message = (String) request.getAttribute("message");
        modelAndView.addObject("schedule",message);
    }
}
