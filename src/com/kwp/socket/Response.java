package com.kwp.socket;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

public class Response {

	private static final int BUFFER_SIZE = 1024;
	Request request;
	OutputStream output;
	
	public Response(OutputStream output) {
		this.output = output;
	}

	public void setRequest(Request request) {
		this.request = request;
		
	}

	public void sendStaticResource() throws IOException {
		byte[] bytes = new byte[BUFFER_SIZE];
		FileInputStream fis = null;
			
		try {
			File file = new File(HttpServer.WEB_ROOT, request.getUri());
			if(file.exists()){
				
				fis = new FileInputStream(file);
				
				int ch = fis.read(bytes,0,BUFFER_SIZE);
				while(ch != -1){
					output.write(bytes,0,ch);
					ch = fis.read(bytes,0,BUFFER_SIZE);
				}
			}else{
				String errorMessage = "Http/1.1 404 File Not Found\r\n" + 
						"Content-Type: text/html\r\n" +
						"Content-Type: text/html\r\n" +
						"Content-Type: text/html\r\n";
				output.write(errorMessage.getBytes());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.toString());
		}finally{
			if(fis != null){
				fis.close();
			}
		}
		
	}


}
