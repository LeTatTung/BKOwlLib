package test;

import java.util.ArrayList;
import java.util.List;

import ontologyDataSructure.BKClass;
import ontologyDataSructure.BKIndividualProperty;
import ontologyDataSructure.BKProperty;
import ontologyDataSructure.LanguageEnum;
import ontologyManager.OntologyManager;

public class FinalTest {
	public static final String SEC_NAMESPACE="http://hut.edu.vn/ontology/sourcecode#";
	public static final String DOC_NAMESPACE="http://hut.edu.vn/ontology/document#";
	
	public static String prefix =   
		  "\nPREFIX SEC: <"+SEC_NAMESPACE+"> "
		+ "\nPREFIX DOC: <"+DOC_NAMESPACE+"> "
		+ "\nPREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
		+ "\nPREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
		+ "\nPREFIX fn: <http://www.w3.org/2005/xpath-functions#> " 
		+ "\nPREFIX owl: <http://www.w3.org/2002/07/owl#> "
		+ "\nPREFIX xsd: <http://www.w3.org/2001/XMLSchema#>";

	private static OntologyManager ontManager = new OntologyManager("file:/C:/workspace/Data/JavaDocumentOntology9.owl");
	
	public static void printList(ArrayList<String> list)
	{
		if (list.isEmpty())
			System.out.println("list empty");
		else
			for (String s: list)
				System.out.println(s);
	}
	public static void printList(List<String> list)
	{
		if (list.isEmpty())
			System.out.println("list empty");
		else
			for (String s: list)
				System.out.println(s);
	}
	
	
	public static void testClassOperator()
	{
		System.out.println("Library 1");
		printList(ontManager.listAllClassInstance("http://hut.edu.vn/ontology/sourcecode#Library"));
		System.out.println("Library all");
		printList(ontManager.listAllRelatedInstance("http://hut.edu.vn/ontology/sourcecode#Library"));
		
		System.out.println("Software component 1");
		printList(ontManager.listAllClassInstance("http://hut.edu.vn/ontology/sourcecode#SoftwareComponent"));
		System.out.println("Software component all");
		printList(ontManager.listAllRelatedInstance("http://hut.edu.vn/ontology/sourcecode#SoftwareComponent"));
		
		System.out.println("Library Property");
		for (BKProperty bk:ontManager.listAllClassProperties("http://hut.edu.vn/ontology/sourcecode#Library"))
		{
			System.out.println(bk.getURI());
		}
		System.out.println("Software Component Property");
		for (BKProperty bk:ontManager.listAllClassProperties("http://hut.edu.vn/ontology/sourcecode#SoftwareComponent"))
		{
			System.out.println(bk.getURI());
		}
		
		System.out.println("superClass of Library");
		for (BKClass bk:ontManager.listSuperClasses("http://hut.edu.vn/ontology/sourcecode#Library"))
		{
			System.out.println(bk.getURI());
		}
		
		System.out.println("subclass of Software Component");
		for (BKClass bk:ontManager.listSubClasses("http://hut.edu.vn/ontology/sourcecode#SoftwareComponent", false))
		{
			System.out.println(bk.getURI());
		}		
	}
	
	public static void testPropertyOperator()
	{
		System.out.println("type of lastModifiedAt");
		System.out.println(ontManager.getPropertySpecificDataType("http://hut.edu.vn/ontology/sourcecode#lastModifiedAt"));
		
		System.out.println("datatype of hasPackage");
		System.out.println(ontManager.getPropertySpecificDataType("http://hut.edu.vn/ontology/sourcecode#hasPackage"));
		
		
		System.out.println("List domain of packageOf");
		printList(ontManager.getObjectPropertyDomains("http://hut.edu.vn/ontology/sourcecode#packageOf"));
		System.out.println("List domain of hasPackage");
		printList(ontManager.getObjectPropertyDomains("http://hut.edu.vn/ontology/sourcecode#hasPackage"));
		
		System.out.println("List range of packageOf");
		printList(ontManager.getObjectPropertyRanges("http://hut.edu.vn/ontology/sourcecode#packageOf"));
		System.out.println("List range of hasPackage");
		printList(ontManager.getObjectPropertyRanges("http://hut.edu.vn/ontology/sourcecode#hasPackage"));
		
		
		System.out.println("Get domain of a property (BKresource, just 1)");
		System.out.println(ontManager.getDomain("http://hut.edu.vn/ontology/sourcecode#packageOf").getURI());
		
		System.out.println("superProperties of seeAlso");
		for (BKProperty bk:ontManager.listSuperProperties("http://hut.edu.vn/ontology/sourcecode#seeAlso"))
		{
			System.out.println(bk.getURI());
		}
		
		System.out.println("subProperties of since");
		for (BKProperty bk:ontManager.listSubProperties("http://hut.edu.vn/ontology/sourcecode#since"))
		{
			System.out.println(bk.getURI());
		}
	}
	
	public static void testModelOperator()
	{
		System.out.println("List All Class");
		for (BKClass bk:ontManager.listClasses())
		{
			System.out.println(bk.getURI());
		}	
		
		System.out.println("List All Properties");
		for (BKProperty bk:ontManager.listProperties())
		{
			System.out.println(bk.getURI());
		}
		
		System.out.println("getClass by URI");
		System.out.println(ontManager.getClassByName("http://hut.edu.vn/ontology/sourcecode#Library").getURI());
		System.out.println(ontManager.getClassByName("http://hut.edu.vn/ontology/document#Person").getURI());
		
		System.out.println("getClass by shortName");
		System.out.println(ontManager.getClassByShortName("Library").getURI());
		System.out.println(ontManager.getClassByShortName("Person").getURI());
		
		System.out.println("getProperty by URI");
		System.out.println(ontManager.getPropertyByName("http://hut.edu.vn/ontology/sourcecode#hasPackage").getURI());
		System.out.println(ontManager.getPropertyByName("http://hut.edu.vn/ontology/document#seeAlso").getURI());
		
		System.out.println("getProperty by shortName");
		System.out.println(ontManager.getPropertyByShortName("hasPackage").getURI());
		System.out.println(ontManager.getPropertyByShortName("seeAlso").getURI());
		
		System.out.println("add label for haspackage");
		ontManager.addLabelForResource("http://hut.edu.vn/ontology/sourcecode#hasPackage", LanguageEnum.EN, "test thanh cong nay");
		
		System.out.println("get label for haspackage");
		System.out.println(ontManager.getLabelFromResource("http://hut.edu.vn/ontology/sourcecode#hasPackage", LanguageEnum.EN));
	
	}
	
	public static void testIndividualOperator()
	{
		System.out.println("Create instance abc of Class Library");
		ontManager.createInstance("lib", "http://hut.edu.vn/ontology/sourcecode#Library");
		ontManager.createInstance("pack", "http://hut.edu.vn/ontology/sourcecode#Package");
		ontManager.createInstance("src", "http://hut.edu.vn/ontology/sourcecode#FolderSourceCode");
		
		System.out.println("Library 1");
		printList(ontManager.listAllClassInstance("http://hut.edu.vn/ontology/sourcecode#Library"));
		System.out.println("Library all");
		printList(ontManager.listAllRelatedInstance("http://hut.edu.vn/ontology/sourcecode#Library"));	
		
		System.out.println("Get Class of a Individual");
		System.out.println(ontManager.getClassOfIndividual("lib"));
		
		System.out.println("Add property data");
		ontManager.addDatatypePropertyForIndividual("http://hut.edu.vn/ontology/sourcecode#hasDirectType", "Library", "lib");
		ontManager.addDatatypePropertyForIndividual("http://hut.edu.vn/ontology/sourcecode#hasDirectType", "MNP", "lib");
		ontManager.addDatatypePropertyForIndividual("http://hut.edu.vn/ontology/sourcecode#hasDirectType", "Package", "pack");
		ontManager.addDatatypePropertyForIndividual("http://hut.edu.vn/ontology/sourcecode#hasDirectType", "FolderSourceCode", "src");
		
		System.out.println("Add object property data");
		ontManager.addObjectProperty("http://hut.edu.vn/ontology/sourcecode#hasPackage", "pack", "lib");
		ontManager.addObjectProperty("http://hut.edu.vn/ontology/sourcecode#hasPackage", "pack", "src");
		
		System.out.println("Get all property data");
		for (BKIndividualProperty bk: ontManager.getValuesOfIndividual("pack"))
		{
			System.out.println(bk.getProperty());
			printList(bk.getListValue());
		}
		
		System.out.println("Get one property data");
		printList(ontManager.getValueOfSpecificPropertyForIndividual("lib", "http://hut.edu.vn/ontology/sourcecode#hasDirectType"));
		System.out.println("Get one property data");
		printList(ontManager.getValueOfSpecificPropertyForIndividual("pack", "http://hut.edu.vn/ontology/sourcecode#packageOf"));
		
		System.out.println("Get individual by name");
		System.out.println(ontManager.getIndividualInfoByName("lib").getURI());
		
		System.out.println("Check individual exist");
		System.out.println(ontManager.checkexitsIndividual("lib"));
		System.out.println(ontManager.checkexitsIndividual("lib1234"));
		
		
		System.out.println("List individual");
		printList(ontManager.listAllRelatedInstance("http://hut.edu.vn/ontology/sourcecode#Library"));	
		System.out.println("Execute remove Individual");
		ontManager.removeIndividual("lib");
		System.out.println("List lai individual");
		printList(ontManager.listAllRelatedInstance("http://hut.edu.vn/ontology/sourcecode#Library"));	
	}
	
	public static void otherTest()
	{
		printList(ontManager.getBaseUri());
	}
	
	public static void testSparql()
	{
		String query = prefix +
		"\nSELECT ?x  ?y where"+
		"\n{"+
		"\n ?x rdf:type SEC:Method."+
		
		"\n}";
		System.out.println(ontManager.SparqlResultList(query));
	}
	public static void main(String[] args) {
//		testClassOperator();	
//		testPropertyOperator();
//		testModelOperator();
//		testIndividualOperator();
//		otherTest();	
		testSparql();
	}

}
