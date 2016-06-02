package com.kwp.test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import com.kwp.simple.servlet.container.Constants;

public class URLClassLoaderTest {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		
		URLClassLoader loader;
		try {
			URL[] urls = new URL[] { new URL("file:/"
					+ System.getProperty("user.dir") + "/") };

			loader = new URLClassLoader(urls);
			try {
				//Class myClass = loader.loadClass("com.kwp.test.Hello");  //包名
				Class myClass = loader.loadClass("com.kwp.servlets.PrimitiveServlet"); 
				Hello hello = (Hello) myClass.newInstance();
				System.out.println("Success!");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		

	}

}
