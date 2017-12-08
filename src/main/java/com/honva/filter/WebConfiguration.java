package com.honva.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 
 * @author honva
 * 自定义拦截器
 */
@Component
public class WebConfiguration {
	/**
	 * 远程客户端访问的ip拦截器
	 */
	@Bean
	public RemoteIpFilter remoteIpFilter(){
		RemoteIpFilter  ipFilter= new RemoteIpFilter();
//		System.out.println("ipFilter.getProtocolHeader():"+ipFilter.getProtocolHeader());
//		System.out.println("ipFilter.getHttpsServerPort():"+ipFilter.getHttpsServerPort());
//		System.out.println("ipFilter.getPortHeader():"+ipFilter.getPortHeader());
//		System.out.println("ipFilter.getRemoteIpHeader():"+ipFilter.getRemoteIpHeader());
//		System.out.println("ipFilter.getHttpsServerPort():"+ipFilter.getHttpsServerPort());
		return ipFilter;
	}
	
	@Bean
	public FilterRegistrationBean teBean(){
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.setFilter(new MyFilter());
		bean.addUrlPatterns("/*");
		bean.addInitParameter("param", "value");
		bean.setName("MyFilter");
		
		bean.setOrder(1);
		return bean;
	}
	
	public class MyFilter implements Filter{

		@Override
		public void init(FilterConfig filterConfig) throws ServletException {
			String parameter = filterConfig.getInitParameter("param");
			System.out.println("parameter:"+parameter);
		}

		@Override
		public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
				throws IOException, ServletException {
			HttpServletRequest  req = (HttpServletRequest)request;
			System.out.println("this is MyFilter,url :"+req.getRequestURI());
//			System.out.println("this is RemoteAddr,url :"+req.getRemoteAddr());
//			System.out.println("this is RemoteHost,url :"+req.getRemoteHost());
			chain.doFilter(request, response); 
		}

		@Override
		public void destroy() {
				System.out.println("myFilter destoring");
		}

		
	}
}
