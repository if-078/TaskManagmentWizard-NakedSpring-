package com.softserve.academy.configuration.webConfig;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(WebAppConfig.class);
		context.setServletContext(servletContext);
		servletContext.addListener(new ContextLoaderListener(context));

		DispatcherServlet dispatcherServlet = new DispatcherServlet(context);
		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);

		ServletRegistration.Dynamic servlet = servletContext.addServlet("MainServlet", dispatcherServlet);
		servlet.addMapping("/");
		servlet.setLoadOnStartup(1);
	}


}
