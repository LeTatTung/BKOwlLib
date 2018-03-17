package test.junit;

import ontologyManager.OntologyManager;
import junit.framework.TestCase;

public class listAllClassInstanceTest extends TestCase {
	
	private OntologyManager ontmethod;

	protected void setUp() throws Exception {

		ontmethod = new OntologyManager("file:D:/JavaSourceCodeOntology.owl");
	}
	
	public void testListAllClassInstance()
	{
		assertEquals("[Method1]", ontmethod.listAllClassInstance("SoftwareComponent").toString());
	}
}
