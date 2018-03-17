package ontologyTest;

import ontologyManager.OntologyManager;

public class OntologyTestRule {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OntologyManager ontManager = new OntologyManager("file:C:/JavaSourceCodeOntology.owl");
	    ontManager.createInstance("DatSF", "SourceFile");
	    ontManager.createInstance("DatClass1", "Class");
	    ontManager.createInstance("DatClass2", "Class");
	    ontManager.createInstance("DatClass3", "Class");
	    ontManager.createInstance("Method1", "Method");
	    ontManager.createInstance("Method2", "Method");
		//Them gia tri thuoc tinh cho instan can biet prefix cua no
		String prefix_source =  "http://hut.edu.vn/ontology/sourcecode#";
		ontManager.addObjectProperty("hasClass", "DatClass1", "DatSF");
		ontManager.addObjectProperty("hasClass", "DatClass2", "DatClass1");
		ontManager.addObjectProperty("hasMethod", "Method2", "DatClass2");
		ontManager.addObjectProperty("hasClass", "DatClass3", "DatClass1");
		ontManager.addObjectProperty("hasMethod", "Method1", "DatClass3");
		System.out.println(ontManager.getValueOfSpecificPropertyForIndividual("DatSF", "hasMethod"));
	}

}
