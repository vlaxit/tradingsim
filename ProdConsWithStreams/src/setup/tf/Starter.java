package setup.tf;

import setup.tf.app1.input.JiraInputCreator;

public class Starter {

	public static void main(String[] args) {
		JiraInputCreator creator = new JiraInputCreator();
		Thread t = new Thread(creator);
		t.start();
	}
	
}
