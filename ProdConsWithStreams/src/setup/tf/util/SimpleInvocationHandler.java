package setup.tf.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class SimpleInvocationHandler implements InvocationHandler{
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		return method.invoke(proxy, args);
	}

}
