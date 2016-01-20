package tuto.security_ejb_client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

import javax.naming.Context;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tuto.securityCommonEjb.HelloWorld;

@WebServlet(name = "helloWorldCaller", urlPatterns = { "/helloWorldCaller" })
public class HelloWorldCaller extends HttpServlet{

	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,  IOException{
		this.processRequest(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,  IOException{
		this.processRequest(req, resp);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            final Hashtable props = new Hashtable();
            // setup the ejb: namespace URL factory
            props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
            // create the InitialContext
            final Context context = new javax.naming.InitialContext(props);
 
            // Lookup the Greeter bean using the ejb: namespace syntax which is explained here https://docs.jboss.org/author/display/AS71/EJB+invocations+from+a+remote+client+using+JNDI
            final HelloWorld bean = (HelloWorld) context.lookup("ejb:" + "" + "/" + "securityeEE_ejb_credential" + "/" + "" + "/" + "HelloWorld" + "!" + HelloWorld.class.getName());
 
            // invoke on the bean
            final String hello = bean.sayHello();
 
            System.out.println("Received greeting: " + hello);
 
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
	
}
