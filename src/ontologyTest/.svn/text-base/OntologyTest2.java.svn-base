package ontologyTest;

import java.util.ArrayList;
import java.util.List;

import ontologyDataSructure.BKProperty;
import ontologyDataSructure.LanguageEnum;
import ontologyManager.OntologyManager;
import ontologyOperator.ModelOperator;

public class OntologyTest2 {
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
		//String prefix_source =  "http://hut.edu.vn/ontology/sourcecode#";
		ontManager.addObjectProperty("hasComment", "AAAAA", "Method1.tt");
		ontManager.addObjectProperty("returnType", "Class1", "Method1.tt");    
		ontManager.addObjectProperty("returnType", "Class2", "Method1.tt");  
		ontManager.addObjectProperty("returnType", "Class3", "Method1.tt");  
		ontManager.addObjectProperty("returnType", "Class4", "Method1.tt");  
		
		ontManager.addObjectProperty("hasMethod", "Method1.tt", "Class1");
		ontManager.addObjectProperty("hasMethod", "Method2", "Class1");		
		ontManager.addDatatypePropertyForIndividual("numMethods", "4", "Class1");
		ontManager.createInstance("void", "JavaPrimaryType");
		
	    
		
		
	   
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
		//String prefix_doc = "http://hut.edu.vn/ontology/document#";
	    
		ontManager.createInstance("Image1", "Image");
		ontManager.addDatatypePropertyForIndividual("hasTextContent", "comment here", "Image1");
		ontManager.addObjectProperty("hasRelationSource", "Class1", "Image1");
	  
		
		
		//Lay gia tri cac thuoc tinh
		List Result =  ontManager.getValueOfSpecificPropertyForIndividual("Class1","hasMethod");
	  //Test get lable
	   String labelClass = ontManager.getLabelFromResource("Class1",LanguageEnum.EN);
	   System.out.println("Label cua class1: "+labelClass );
	   
	   
	   ArrayList<BKProperty> listPropertyofClass = ontManager.listAllClassProperties("Comment");
	   
	   //ontManager.writeToOWL("C:\\test12.owl");
	   ModelOperator modelOperator = new ModelOperator(ontManager.getOntmodel());
	  
	   ArrayList<String> checkValue = ontManager.getValueOfSpecificPropertyForIndividual("aaaa", "version");
	   
	   System.out.println(Result.size());
	   System.out.println("Pass done");
    }
}
