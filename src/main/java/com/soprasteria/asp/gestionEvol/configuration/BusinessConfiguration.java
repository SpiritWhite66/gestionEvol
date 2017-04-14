package com.soprasteria.asp.gestionEvol.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.soprasteria.asp.gestionEvol.service._Service;

@Configuration
@ComponentScan(basePackageClasses = _Service.class)
public class BusinessConfiguration {
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
	    FilterRegistrationBean registrationBean = new FilterRegistrationBean();
	    CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
	    characterEncodingFilter.setForceEncoding(true);
	    characterEncodingFilter.setEncoding("UTF-8");
	    registrationBean.setFilter(characterEncodingFilter);
	    return registrationBean;
	}
	
}
