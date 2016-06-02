package com.kwp.simple.servlet.container;

import java.io.IOException;


class StaticResourceProcessor {

	public void process(Request request, Response response) {
		try{
			response.sendStaticResource();
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}

	


}
