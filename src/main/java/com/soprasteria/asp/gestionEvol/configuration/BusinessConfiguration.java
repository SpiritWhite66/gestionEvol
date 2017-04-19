package com.soprasteria.asp.gestionEvol.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.soprasteria.asp.gestionEvol.service._Service;

@Configuration
@ComponentScan(basePackageClasses = _Service.class)
public class BusinessConfiguration {

}
	

