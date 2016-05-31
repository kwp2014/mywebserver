package com.kwp.simple.servlet.container;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * 构建一个简单的Servlet容器
 * 1 当第一次调用该servlet 需要载入该类并调用其init方法，仅此一次
 * 2 针对每一个request对象，创建一个ServletRequest对象实例和对应的ServletResponse对象实例
 * 作为service方法的参数，执行service方法
 * 3 当关闭servlet时，调用其destroy方法  卸载该servlet类
 * 
 * 步骤：
 * 1 等待http请求
 * 2 创建ServletRequest  ServletResponse对象
 * 3 根据请求的资源类型，分别调用静态资源处理对象和servlet对象：载入servlet类，调用service方法
 * 
 * @author Weiping
 *
 */
public class HttpServer1 {
	private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";
	
	private boolean shutdown = false;
	
	public static void main(String[] args) {
		HttpServer1 server = new HttpServer1();
		server.await();
	}

	public void await() {
		ServerSocket serverSocket = null;
		int port = 8080;
		try {
			serverSocket = new ServerSocket(port,1,
					InetAddress.getByName("127.0.0.1"));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		// loop for request
		while(!shutdown){
			Socket socket = null;
			InputStream input = null;
			OutputStream output = null;
			try{
				socket = serverSocket.accept();   // 方法一直等待中
				input = socket.getInputStream();
				output = socket.getOutputStream();
				
				Request request = new Request(input);
				request.parse();
				
				Response response = new Response(output);
				response.setRequest(request);
				
				// check if this is a request for a servlet or a static resource
				if(request.getUri().startsWith("/servlet/")){
					ServletProcessor1 processor = new ServletProcessor1();
					processor.process(request,response);
				}else{
					StaticResourceProcessor processor = new StaticResourceProcessor();
					processor.process(request,response);
				}

				// close the socket
				socket.close();
				shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
			}catch(Exception e){
				e.printStackTrace();
				continue;
			}
		}
		
	}
}
