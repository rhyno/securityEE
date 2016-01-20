package tuto.securityEE_ejb_credential;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import tuto.securityCommonEjb.HelloWorld;

@Stateless
@Remote (HelloWorld.class)
public class HelloWorldImpl implements HelloWorld{

	@Override
	public String sayHello() {
		return "hello world";
	}

}
