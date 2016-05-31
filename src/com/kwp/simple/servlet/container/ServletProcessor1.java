package com.kwp.simple.servlet.container;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;



public class ServletProcessor1 {

	public void process(Request request, Response response) {
		String uri = request.getUri();
		String servletName = uri.substring(uri.lastIndexOf("/")+1);
		URLClassLoader loader = null;
		
		URL[] urls = new URL[1];
		URLStreamHandler streamHander = null;
		File classPath = new File(Constants.WEB_ROOT);
		String repository;
		try {
			repository = (new URL("file",null,classPath.getCanonicalPath() + File.separator)).toString();
			urls[0] = new URL(null,repository,streamHander);
			loader = new URLClassLoader(urls);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		Class myClass = null;
		try {
			myClass = loader.loadClass(servletName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Servlet servlet = null;
		try {
			servlet = (Servlet) myClass.newInstance();
			servlet.service((ServletRequest)request, (ServletResponse)response);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}
