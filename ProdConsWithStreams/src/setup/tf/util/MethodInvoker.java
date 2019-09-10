package setup.tf.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodInvoker {

	private Object proxy;
	private Method method;
	private Object[] args;
	
	public MethodInvoker(Object proxy, Method method, Object[] args) {
		this.proxy = proxy;
		this.method = method;
		this.args = args;
	}
	
	public synchronized Object invoke() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InterruptedException {
		Thread.sleep(1);
		System.out.println("****"+method.getName());
		Object result = method.invoke(proxy, args);
		Thread.sleep(100);
		return result;
	}
	
}
