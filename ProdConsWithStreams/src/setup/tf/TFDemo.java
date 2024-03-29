package setup.tf;

import org.tensorflow.Graph;
import org.tensorflow.Session;
import org.tensorflow.Tensor;
import org.tensorflow.TensorFlow;

import setup.tf.app1.Jira;
import setup.tf.app1.JiraApi;
import setup.tf.app1.SimpleUser;
import setup.tf.app1.User;

public class TFDemo {

	public static void main(String[] args) throws Exception {
	    try (Graph g = new Graph()) {
	      final String value = "Hello from " + TensorFlow.version();
	      final JiraApi jira = new Jira();
	      int[] values = {1,2,3};

	      // Construct the computation graph with a single operation, a constant
	      // named "MyConst" with a value "value".
	      try (Tensor t = Tensor.create(values)) {
	        // The Java API doesn't yet include convenience functions for adding operations.
	        g.opBuilder("Const", "MyConst").setAttr("dtype", t.dataType()).setAttr("value", t).build();
	        
	      }

	      // Execute the "MyConst" operation in a Session.
	      try (Session s = new Session(g);
	           Tensor output = s.runner().fetch("MyConst").run().get(0)) {
	        System.out.println(new String(output.bytesValue(), "UTF-8"));
	      }
	    }
	  }	
}