package com.softserve.academy.tmw.configuration.webConfig;

import com.softserve.academy.tmw.security.JwtAuthenticationEntryPoint;
import com.softserve.academy.tmw.security.TokenAuthenticationFilter;
import com.softserve.academy.tmw.security.TokenAuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@PropertySources(@PropertySource("classpath:security.properties"))
public class SecurityConfig extends WebSecurityConfigurerAdapter {


  private String LOGIN_URL = "/login";


  private String SECURE_URL = "/api/**";


  @Autowired
  private JwtAuthenticationEntryPoint unauthorizedHandler;

  @Autowired
  private TokenAuthenticationFilter tokenAuthenticationFilter;
  @Autowired
  private TokenAuthenticationManager tokenAuthenticationManager;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }


  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()

        .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()

        // don't create session
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

        .authorizeRequests()
        //.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()

        // allow anonymous resource requests
        .antMatchers(
            HttpMethod.GET,
            "/",
            "/*.html",
            "/favicon.ico",
            "/**/*.html",
            "/**/*.css",
            "/**/*.js",
            "/static/**"
        ).permitAll()
        .antMatchers(HttpMethod.POST, "/login").permitAll()
        .antMatchers(HttpMethod.POST, "/register").permitAll()
        .anyRequest().authenticated();

    // Custom JWT based security filter
    http
        .addFilterBefore(getTokenAuthenticationFilter(),
            UsernamePasswordAuthenticationFilter.class);

    // disable page caching
    http.headers().cacheControl();
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/resources/**");
  }

  @Bean
  public TokenAuthenticationFilter getTokenAuthenticationFilter() throws Exception {
    TokenAuthenticationFilter filter = new TokenAuthenticationFilter(SECURE_URL);
    filter.setAuthenticationManager(tokenAuthenticationManager);
    return filter;
  }
}
