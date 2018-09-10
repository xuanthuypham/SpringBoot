package com.xuanthuy.springbootdemo.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.xuanthuy.springbootdemo.interceptor.LoginInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	

	//++++++++++++++++++++++++Da ngon ngu++++++++++++++++++++++++++++++++++
	@Bean (name = "localeResolver") //ten bean phai chinh xac
	public LocaleResolver getLocaleResolver() { //Bean chi dinh lay cac thong tin dia phuong (local) trong cooke cuar nguoi dung
		CookieLocaleResolver resolver = new CookieLocaleResolver();
		resolver.setCookieDomain("myAppLocaleCookie");
		resolver.setCookieMaxAge(60*60);
		return resolver;
	}
	
	@Bean (name = "messageSource") //ten bean phai chinh xac
	public MessageSource getMessageSource() {
		ReloadableResourceBundleMessageSource messageResource = new ReloadableResourceBundleMessageSource();
		
		//Doc file theo duong dan i18n/message_xx.properties
		messageResource.setBasename("classpath:i18n/messages");
		messageResource.setDefaultEncoding("UTF-8");
		return messageResource;
	}
	
	@Override //interceptor cap nhat lai locale 
	public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor localInceptor = new LocaleChangeInterceptor(); //Xu ly cac thay doi locale tu phia nguoi dung
		localInceptor.setParamName("lang");
		
		registry.addInterceptor(localInceptor).addPathPatterns("/*");
		registry.addInterceptor(new LoginInterceptor());
	}
	
	// +++++++++++++++++++++++++++++++++Da ngon ngu end++++++++++++++++++++++++
	
	//+++++++++++++++++++++++++++++++++ Khai bao resource cho boostrap++++++++++++++
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		WebMvcConfigurer.super.addResourceHandlers(registry);
	}

	
}
