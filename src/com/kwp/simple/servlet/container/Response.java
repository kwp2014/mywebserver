package com.kwp.simple.servlet.container;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Locale;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;


class Response implements ServletResponse{

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
			File file = new File(Constants.WEB_ROOT, request.getUri());
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
						"Content-Length: 23\r\n" +
						"\r\n" +
						"<h1> File Not Found</h1>";
				output.write(errorMessage.getBytes());
			}
		} catch (IOException e) {
			System.out.println(e.toString());
		}finally{
			if(fis != null){
				fis.close();
			}
		}
		
	}
	
	@Override
	public String getCharacterEncoding() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getContentType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		boolean autoFlush = true;
		PrintWriter writer = new PrintWriter(output,autoFlush);
		return writer;
	}

	@Override
	public void setCharacterEncoding(String charset) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setContentLength(int len) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setContentType(String type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBufferSize(int size) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getBufferSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void flushBuffer() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resetBuffer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isCommitted() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLocale(Locale loc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Locale getLocale() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
