package com.mailsending.configuration;


import org.springframework.context.annotation.Configuration;

import org.thymeleaf.TemplateEngine;

import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
public class ThymeleafConfig 
//implements WebMvcConfigurer
{
	
	/*
	 * @Bean 
	 * public ClassLoaderTemplateResolver templateResolver() {
	 * 
	 * ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
	 * 
	 * resolver.setPrefix("templates/"); // Location of thymeleaf template
	 * resolver.setCacheable(false); // Turning of cache to facilitate template
	 * changes resolver.setSuffix(".html"); // Template file extension
	 * resolver.setTemplateMode("HTML"); // Template Type
	 * resolver.setCharacterEncoding("UTF-8");
	 * 
	 * return resolver; }
	 * 
	 * @Bean public SpringTemplateEngine templateEngine() { SpringTemplateEngine
	 * engine = new SpringTemplateEngine();
	 * engine.setTemplateResolver(templateResolver());
	 * 
	 * return engine; }
	 */

	
	public TemplateEngine mailProperties() {
		TemplateEngine te = new TemplateEngine();
		te.setTemplateResolver(getDetails());
		return te;
	}

	public ITemplateResolver getDetails() {
		ClassLoaderTemplateResolver cltr = new ClassLoaderTemplateResolver();
		cltr.setPrefix("/templates/");
		cltr.setSuffix(".html");
		cltr.setTemplateMode("HTML");
		cltr.setCharacterEncoding("UTF-8");
		return cltr;
	}

}
