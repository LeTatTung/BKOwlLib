package ontologyTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntProperty;

import ontologyDataSructure.LanguageEnum;
import ontologyManager.OntologyManager;
import ontologyOperator.ModelOperator;

public class OntologyTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {	
		OntologyManager ontManager = new OntologyManager("file:C:/JavaDocumentOntology6.owl");
	    ontManager.createInstance("Method1.tt", "Method");
	    ontManager.createInstance("Class1", "Class");
	    ontManager.addLabelForResource("Class1", LanguageEnum.EN, "CLASS THU 1");
	    ontManager.createInstance("Class2", "Class");
	    ontManager.createInstance("Class3", "Class");
	    ontManager.createInstance("Class4", "Class");
		//Individual individual = ontManager.getIndividual("Class1");
		//ontManager.createInstance("Method1", "Method");
		ontManager.createInstance("Method2", "Method");
		ontManager.createInstance("AAAAA", "Comment");
		
		//Them gia tri thuoc tinh cho instan can biet prefix cua no
		String prefix_source =  "http://hut.edu.vn/ontology/sourcecode#";
		ontManager.addObjectProperty(prefix_source+"hasComment", prefix_source+"AAAAA", prefix_source+"Method1.tt");
		ontManager.addObjectProperty(prefix_source+"returnType", prefix_source+"Class1", prefix_source+"Method1.tt");    
		ontManager.addObjectProperty(prefix_source+"returnType", prefix_source+"Class2", prefix_source+"Method1.tt");  
		ontManager.addObjectProperty(prefix_source+"returnType", prefix_source+"Class3", prefix_source+"Method1.tt");  
		ontManager.addObjectProperty(prefix_source+"returnType", prefix_source+"Class4", prefix_source+"Method1.tt");  
		
		ontManager.addObjectProperty(prefix_source+"hasMethod", prefix_source+"Method1.tt", prefix_source+"Class1");
		ontManager.addObjectProperty(prefix_source+"hasMethod", prefix_source+"Method2", prefix_source+"Class1");		
		ontManager.addDatatypePropertyForIndividual("numMethods", "4", prefix_source+"Class1");
		ontManager.createInstance(prefix_source+"void", prefix_source+"JavaPrimaryType");
		
	    
		
		
	   
	    //OntProperty property = modelOperator.getOntPropertyByName("hasMethod");
	    //System.out.println(property.getLocalName());
	    //System.out.println(individual.getLocalName());
	    
	    //Doan dung map
	    /*Map<String, ArrayList<String>> result = ontManager.getPropertiesOfIndividual("Class1");
	    for (String key : result.keySet())
	    {
	        System.out.println(key);
	        System.out.println(result.get(key));
	    }*/
		String prefix_doc = "http://hut.edu.vn/ontology/document#";
	    
		ontManager.createInstance("Image1", "Image");
		ontManager.addDatatypePropertyForIndividual("hasTextContent", "comment here", prefix_doc+"Image1");
		ontManager.addObjectProperty(prefix_doc+"hasRelationSource", prefix_source+"Class1", prefix_doc+"Image1");
	  
		
		
		//Lay gia tri cac thuoc tinh
		List Result =  ontManager.getValueOfSpecificPropertyForIndividual(prefix_source+"Class1","hasMethod");
	  //Test get lable
	   String labelClass = ontManager.getLabelFromResource("Class1",LanguageEnum.EN);
	   System.out.println("Label cua class1: "+labelClass );
	   
	   
	   ontManager.writeToOWL("C:\\test11.owl");
	   ModelOperator modelOperator = new ModelOperator(ontManager.getOntmodel());
	   System.out.println(Result.size());
	   System.out.println("Pass done");
    } 
}
