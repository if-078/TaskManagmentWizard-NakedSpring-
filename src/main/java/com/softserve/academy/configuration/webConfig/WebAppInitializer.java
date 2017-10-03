package com.softserve.academy.configuration.webConfig;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(WebAppConfig.class);
        context.setServletContext(servletContext);
        servletContext.addListener(new ContextLoaderListener(context));
        ServletRegistration.Dynamic servlet = servletContext.addServlet("MainServlet", new DispatcherServlet(context));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
    }
   }
