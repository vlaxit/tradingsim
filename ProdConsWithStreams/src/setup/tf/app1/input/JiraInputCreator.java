package setup.tf.app1.input;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import setup.tf.app1.Jira;
import setup.tf.app1.JiraApi;
import setup.tf.util.ClassUtil;
import setup.tf.util.MethodInvoker;
import setup.tf.util.SimpleInvocationHandler;

public class JiraInputCreator implements Runnable{

	private JiraApi jiraApi;
	
	
	private Stream<MethodInvoker> createInvocationHandlerStream(
			Object proxy) {
		return Stream.iterate(create(proxy), i-> create(proxy));

	}
	
	private synchronized MethodInvoker create(Object proxy) {
		return ClassUtil.genereteRandomMethodInvoker(proxy);
	}

	@Override
	public void run() {
		JiraApi jira = new Jira();
		Stream<MethodInvoker> stream = createInvocationHandlerStream(jira);
//		stream.map(i -> {
//			try {
//				return i.invoke();
//			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
//					| InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			return i;
//		});
		List<MethodInvoker> collect = stream
				  .limit(100)
				  .collect(Collectors.toList());

		collect.stream().forEach(i -> {
			try {
				i.invoke();
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
}
