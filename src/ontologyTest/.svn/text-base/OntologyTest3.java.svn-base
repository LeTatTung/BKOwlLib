package ontologyTest;

import java.util.List;

import ontologyDataSructure.LanguageEnum;
import ontologyManager.OntologyManager;
import ontologyOperator.ModelOperator;

public class OntologyTest3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {	
		OntologyManager ontManager = new OntologyManager("file:C:/JavaDocumentOntology.owl");
		String prefix_source =  "http://hut.edu.vn/ontology/sourcecode#";
		
		ontManager.createInstance(prefix_source+"Method1.tt", prefix_source+"Method");
	    ontManager.createInstance(prefix_source+"Class1", prefix_source+"Class");
	    ontManager.addLabelForResource(prefix_source+"Class1", LanguageEnum.EN, "CLASS THU 1");
	    ontManager.createInstance(prefix_source+"Class2", prefix_source+"Class");
	    ontManager.createInstance(prefix_source+"Class3", prefix_source+"Class");
	    ontManager.createInstance(prefix_source+"Class4", prefix_source+"Class");
		//Individual individual = ontManager.getIndividual("Class1");
		//ontManager.createInstance("Method1", "Method");
		ontManager.createInstance(prefix_source+"Method2", prefix_source+"Method");
		ontManager.createInstance(prefix_source+"AAAAA", prefix_source+"Comment");
		
		//Them gia tri thuoc tinh cho instan can biet prefix cua no
		//String prefix_source =  "http://hut.edu.vn/ontology/sourcecode#";
		ontManager.addObjectProperty(prefix_source+"hasComment", prefix_source+"AAAAA", prefix_source+"Method1.tt");
		ontManager.addObjectProperty(prefix_source+"returnType", prefix_source+"Class1", prefix_source+"Method1.tt");    
		ontManager.addObjectProperty(prefix_source+"returnType", prefix_source+"Class2", prefix_source+"Method1.tt");  
		ontManager.addObjectProperty(prefix_source+"returnType", prefix_source+"Class3", prefix_source+"Method1.tt");  
		ontManager.addObjectProperty(prefix_source+"returnType", prefix_source+"Class4", prefix_source+"Method1.tt");  
		
		ontManager.addObjectProperty(prefix_source+"hasMethod", prefix_source+"Method1.tt", prefix_source+"Class1");
		ontManager.addObjectProperty(prefix_source+"hasMethod", prefix_source+"Method2", prefix_source+"Class1");		
		ontManager.addDatatypePropertyForIndividual(prefix_source+"numMethods", "4", prefix_source+"Class1");
		ontManager.createInstance(prefix_source+"void", prefix_source+"JavaPrimaryType");
		
	    
		
		/*
	   
	    //OntProperty property = modelOperator.getOntPropertyByName("hasMethod");
	    //System.out.println(property.getLocalName());
	    //System.out.println(individual.getLocalName());
	    
	    //Doan dung map
	    Map<String, ArrayList<String>> result = ontManager.getPropertiesOfIndividual("Class1");
	    for (String key : result.keySet())
	    {
	        System.out.println(key);
	        System.out.println(result.get(key));
	    }
		String prefix_doc = "http://hut.edu.vn/ontology/document#";
	    
		ontManager.createInstance("Image1", "Image");
		ontManager.addDatatypePropertyForIndividual("hasTextContent", "comment here", prefix_doc+"Image1");
		ontManager.addObjectProperty(prefix_doc+"hasRelationSource", prefix_source+"Class1", prefix_doc+"Image1");
	  
		
		*/
		//Lay gia tri cac thuoc tinh
		List Result =  ontManager.getValueOfSpecificPropertyForIndividual(prefix_source+"Class1",prefix_source+"hasMethod");
	  //Test get lable
	   String labelClass = ontManager.getLabelFromResource(prefix_source+"Class1",LanguageEnum.EN);
	   System.out.println("Label cua class1: "+labelClass );
	   
	   
	   ontManager.writeToOWL("C:\\test10.owl");
	   ModelOperator modelOperator = new ModelOperator(ontManager.getOntmodel());
	   System.out.println(Result.size());
	   System.out.println("Pass done");
    } 
}
