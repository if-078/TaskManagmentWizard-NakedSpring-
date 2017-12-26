package com.softserve.academy.tmw.configuration.webConfig;

import com.softserve.academy.tmw.configuration.AppConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class[]{WebAppConfig.class, AppConfig.class, SecurityConfig.class};
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class[]{};
  }

  @Override
  protected String[] getServletMappings() {
    return new String[]{"/"};
  }
}