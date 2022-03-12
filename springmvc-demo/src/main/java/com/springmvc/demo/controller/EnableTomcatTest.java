package com.springmvc.demo.controller;

import com.springmvc.demo.controller.config.AppConfiguration;
import org.apache.catalina.*;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.core.StandardWrapper;
import org.apache.catalina.startup.ContextConfig;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.File;
import java.io.IOException;

public class EnableTomcatTest {


	public static void main(String[] args) throws LifecycleException {

		String workspace = System.getProperty("user.dir");

		String webapp = new File(workspace,"src/main/webapp").getPath();

		Tomcat tomcat = new Tomcat();
		tomcat.setPort(8888);

		// 看源码发现,他只能设置一个service,直接拿默认的
		Service service = tomcat.getService();

//		// 创建连接器,并且添加对应的连接器,同时连接器指定端口
//		Connector connector = new Connector();
//		connector.setPort(8888);
//		service.addConnector(connector);
//
//		// 创建一个引擎,放入service中
		Engine engine = new StandardEngine();
		service.setContainer(engine);
		engine.setDefaultHost("localhost");
		engine.setName("myTomcat");
//
//		// 添加host
		Host host = new StandardHost();
		engine.addChild(host);
		host.setName("localhost");

		// 在对应的host下面创建一个 context 并制定他的工作路径,会加载该目录下的所有class文件,或者静态文件
		tomcat.getHost().setAppBase("webapp");
		StandardContext context = (StandardContext) tomcat.addContext(host, "/", webapp);

		// 初始化ContextConfig配置
		context.addLifecycleListener(new ContextConfig());


		WebApplicationContext webApplicationContext = webApplicationContext();

		// 创建一个servlet
		Wrapper myservlet = new StandardWrapper();
		myservlet.setServlet(new DispatcherServlet(webApplicationContext));
		myservlet.setName("srpingmvc");
		// 把servlet加入到contxt中
		context.addChild(myservlet);
		// 这里注意,要先添加到contxt中在映射路径,不然会空指针
		myservlet.addMapping("/*");

		context.addApplicationEventListener(new ContextLoaderListener(webApplicationContext));


		//tomcat启动
		tomcat.start();
		//保持主线程不退出
		tomcat.getServer().await();
	}


	public static void main1(String[] args) {

		// 创建内嵌的 Tomcat 对象
		String baseDir = new File(System.getProperty("java.io.tmpdir")).getAbsolutePath();
		Tomcat tomcat = new Tomcat();
		tomcat.setBaseDir(baseDir);
		tomcat.setPort(8081);
		// 编码为 UTF-8
		tomcat.getConnector().setProperty("URIEncoding", "UTF-8");
		// 连接超时，60 秒
		tomcat.getConnector().setProperty("connectionTimeout", "60000");
		tomcat.getConnector().setProperty("maxKeepAliveRequests", "-1");
		tomcat.getConnector().setProtocol("org.apache.coyote.http11.Http11NioProtocol");

		// 添加 DispatcherServlet 到 Tomcat 中
		Context context = tomcat.addContext("/", baseDir);
		WebApplicationContext webApplicationContext = webApplicationContext();
		DispatcherServlet dispatcherServlet = new DispatcherServlet(webApplicationContext);
		//dispatcherServlet.setContextConfigLocation("classpath:/WEB-INF/dispatcher-servlet.xml");
		Tomcat.addServlet(context, "defaultServletName", dispatcherServlet);
		context.addServletMapping("/*", "defaultServletName");

		// 启动 Tomcat
		try {
			tomcat.start();
			System.in.read();
		} catch (LifecycleException e) {
			throw new IllegalStateException("Failed to start tomcat server at " + e);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	private static WebApplicationContext webApplicationContext() {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(AppConfiguration.class);
		return context;
	}


}
