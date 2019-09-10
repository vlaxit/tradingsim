package setup.tf.app1;

import java.util.List;

public interface JiraApi {

	void addUser(String name, Integer role);
	
	void removeUserByName(String name);
	
	List<User> getAllUsers();
	
	void changeUserName(String oldName, String newName);
	
	
}
