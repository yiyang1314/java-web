package com.kdyzm.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class StudentHandler implements InvocationHandler {
	private Object obj;
	
	public StudentHandler(Object obj){
		this.obj=obj;
	}
	
	public Object getProxy(){
		return Proxy.newProxyInstance(
				obj.getClass().getClassLoader(), 
				obj.getClass().getInterfaces(),
				this);
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("调用了"+obj+"中的"+method+"参数为"+args+" error onliine：23");
		method.invoke(obj, args);
		System.out.println("执行成功!");
		return obj;
	}

}
