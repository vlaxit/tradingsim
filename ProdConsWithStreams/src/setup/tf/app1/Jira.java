package setup.tf.app1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Jira implements JiraApi{

	private ConcurrentHashMap<String, Integer> users = new ConcurrentHashMap<>();
	private Logger LOG = Logger.getLogger(Jira.class.getSimpleName());
	
	@Override
	public synchronized void addUser(String name, Integer role) {
		LOG.info("Add user " + name + " with role " + role);
		users.put(name, role);		
		LOG.info("Added user " + name + " with role " + role);
	}

	@Override
	public synchronized void removeUserByName(String name) {
		LOG.info("Remove user " + name);
		users.remove(name);
		LOG.info("User " + name + " removed");
	}

	@Override
	public synchronized List<User> getAllUsers() {
		LOG.info("List all users");
		return users.keySet().stream().map(i->{System.out.println(i);return new SimpleUser(i, 0);}).collect(Collectors.toList());
	}

	@Override
	public synchronized void changeUserName(String oldName, String newName) {
		LOG.info("Change username for user " + oldName + " to " + newName);
		Integer role = users.get(oldName);
		if(null != role) {
			users.remove(oldName);
			users.put(newName, role);
			LOG.info("User name changed for user " + oldName + " to " + newName);
		}
	}

}
