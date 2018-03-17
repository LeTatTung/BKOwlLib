package test.junit;

import ontologyManager.OntologyManager;
import junit.framework.TestCase;

public class getObjectPropertyRangesTest extends TestCase{

	private OntologyManager ontmethod;

	protected void setUp() throws Exception {

		ontmethod = new OntologyManager("file:D:/JavaSourceCodeOntology.owl");
	}
	
	public void testgetObjectPropertyRanges()
	{
		assertEquals("[SoftwareComponent, MethodSignature, Constructor, SourceFile, Parameter, Method, JavaModifier, Class, Resource, Project, Variable, Workspace, Comment, Interface, Library, Package, AbstractClass, ValueParameter, ReferenceParameter]", ontmethod.getObjectPropertyRanges("contains").toString());
	}
	
}
