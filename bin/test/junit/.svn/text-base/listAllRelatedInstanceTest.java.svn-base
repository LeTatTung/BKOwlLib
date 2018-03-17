package test.junit;

import ontologyManager.OntologyManager;
import junit.framework.TestCase;

public class listAllRelatedInstanceTest extends TestCase{
	
	private OntologyManager ontmethod;

	protected void setUp() throws Exception {

		ontmethod = new OntologyManager("file:D:/JavaSourceCodeOntology.owl");
	}
	
	public void testListAllRelatedInstance()
	{
		assertEquals("[Method1]", ontmethod.listAllRelatedInstance("Method").toString());
	}

}
