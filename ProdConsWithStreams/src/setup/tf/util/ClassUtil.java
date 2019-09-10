package setup.tf.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;


public class ClassUtil {
	
	private static final Random RANDOM = new Random();

	public static MethodInvoker genereteRandomMethodInvoker(
			Object proxy) {
		
		Method[] methods = proxy.getClass().getDeclaredMethods();
		List<Method> list = Arrays.asList(methods);
		Method method = methods[RANDOM.nextInt(methods.length)];
		while(method.getName().startsWith("lambda")) {
			method = methods[RANDOM.nextInt(methods.length)];
		}
		
		return new MethodInvoker(proxy, method, createRandomArgs(method));
	}
	
	private static Object[] createRandomArgs(Method method) {
		Class[] paramTypes = method.getParameterTypes();
		List<Object> result = new ArrayList<>();
		for(Class paramType : paramTypes) {
			result.add(createRandomInstance(paramType));
		}
		return result.toArray(new Object[result.size()]);
	}
	
	private static <T> T createRandomInstance(Class<?> recordClass) {

		if(String.class.equals(recordClass)) {
			return (T) UUID.randomUUID().toString();
		} else if (Long.class.equals(recordClass) || long.class.equals(recordClass)) {
			return (T) new Long(ThreadLocalRandom.current().nextLong());
		} else if (Integer.class.equals(recordClass) || int.class.equals(recordClass)) {
			return (T) Integer.valueOf(ThreadLocalRandom.current().nextInt());
		}
		
	    Constructor<?>[] constructors = recordClass.getConstructors();
	    Constructor<?> maxParamConstructor = constructors[0];

	    for (Constructor<?> check : constructors) {
	        if (check.getParameterCount() > maxParamConstructor.getParameterCount())
	            maxParamConstructor = check;
	    }

	    if (maxParamConstructor.getParameterCount() > 0) {

	        Type[] types = maxParamConstructor.getGenericParameterTypes();
	        Object[] params = new Object[types.length];

	        for (int index = 0; index < types.length; index++) {
	            params[index] = getRandom(types[index].getTypeName());
	        }

	        try {
				return (T) maxParamConstructor.newInstance(params);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return null;
			} 
	    } else
			try {
				return (T) recordClass.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				return null;
			}
	}

	private static Object getRandom(String type) {

	    switch (type) {

	        //TODO add others
	    	case "java.lang.Integer":
	    	case "int":
            return ThreadLocalRandom.current().nextInt();
	        case "java.lang.Long":
	            return ThreadLocalRandom.current().nextLong();
	        case "java.lang.Boolean":
	            return ThreadLocalRandom.current().nextBoolean();
	        case "java.lang.String":
	            return UUID.randomUUID().toString();
	        case "java.lang.CharSequence":
	            return UUID.randomUUID().toString();
	        case "java.lang.Double":
	            return ThreadLocalRandom.current().nextDouble();
	        case "byte[]":
	        	return new byte[ThreadLocalRandom.current().nextInt(5)];

	        default:
			try {
				return createRandomInstance(Class.forName(type));
			} catch (ClassNotFoundException e) {
				return type;
			}
	    }
	}
}
