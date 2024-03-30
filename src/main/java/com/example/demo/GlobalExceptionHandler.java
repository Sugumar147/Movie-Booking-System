package com.example.demo;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error"); // Set view name for the error page
        modelAndView.addObject("ex", ex);
        // Add error message
        modelAndView.addObject("errorMessage", ex.getMessage());

        // Add exception class name
        modelAndView.addObject("exceptionName", ex.getClass().getName());

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String stackTrace = sw.toString();
        modelAndView.addObject("stackTrace", stackTrace);

        // You can add more details as needed

        return modelAndView;
    }
    // Add more @ExceptionHandler methods for specific exception types if needed
}
