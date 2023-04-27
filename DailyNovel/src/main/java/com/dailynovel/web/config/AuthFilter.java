package com.dailynovel.web.config;

import java.io.IOException;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

//@Component
public class AuthFilter implements Filter {

	private static String[] permitUrls = {

			"/admin/**", "/member/**"

	}; //

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httprequest = (HttpServletRequest) request;
		String uri = httprequest.getRequestURI();

		String urL = httprequest.getRequestURL().toString();


		chain.doFilter(request, response);

	}

}
