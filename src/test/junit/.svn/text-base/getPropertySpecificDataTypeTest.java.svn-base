package test.junit;

import ontologyManager.OntologyManager;
import junit.framework.TestCase;

public class getPropertySpecificDataTypeTest extends TestCase{

	private OntologyManager ontmethod;

	protected void setUp() throws Exception {

		ontmethod = new OntologyManager("file:D:/JavaSourceCodeOntology.owl");
	}
	
	public void testgetPropertySpecificDataType()
	{
		assertEquals("string", ontmethod.getPropertySpecificDataType("fullPath"));
	}
	
}
