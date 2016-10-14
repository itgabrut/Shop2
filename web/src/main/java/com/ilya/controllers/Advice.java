package com.ilya.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ilya on 14.10.2016.
 */
@ControllerAdvice
public class Advice {

    protected Logger logger;
    public Advice() {
        logger = LoggerFactory.getLogger(getClass());
    }

    @ExceptionHandler( value = Exception.class)
    public ModelAndView global(HttpServletRequest req,Exception e)throws Exception{

        logger.error(e.getMessage(),e.getStackTrace(),e.getCause());
        if (AnnotationUtils.findAnnotation
                (e.getClass(), ResponseStatus.class) != null)
            throw e;
        // Otherwise setup and send the user to a default error-view.
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("ErrorPage");
        return mav;
    }
}
