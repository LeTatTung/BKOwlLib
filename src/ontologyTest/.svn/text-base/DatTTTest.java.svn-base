package ontologyTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntProperty;

import ontologyDataSructure.LanguageEnum;
import ontologyManager.OntologyManager;
import ontologyOperator.ModelOperator;

public class DatTTTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {	
		OntologyManager ontManager = new OntologyManager("file:C:/JavaSourceCodeOntology.owl");
	    ontManager.createInstance("DatSF", "SourceFile");
	    ontManager.createInstance("DatClass1", "Class");
	    ontManager.createInstance("DatClass2", "Class");
		
		//Them gia tri thuoc tinh cho instan can biet prefix cua no
		String prefix_source =  "http://hut.edu.vn/ontology/sourcecode#";
		ontManager.addObjectProperty("hasClass", "DatClass1", "DatSF");
		ontManager.addObjectProperty("hasClass", "DatClass2", "DatClass1");
		
		
		/*ontManager.createInstance("DatClass", "Class");
	    ontManager.createInstance("DatMethod", "Method");
	    ontManager.createInstance("DatComment", "Comment");
		

		ontManager.addObjectProperty("hasMethod", "DatMethod", "DatClass");
		ontManager.addObjectProperty("hasComment", "DatComment", "DatMethod");*/
		
		//ontManager.writeToOWL("C:\\test16.owl");
		System.out.println(ontManager.getValueOfSpecificPropertyForIndividual("DatSF", "hasClass"));
		
    } 
}
