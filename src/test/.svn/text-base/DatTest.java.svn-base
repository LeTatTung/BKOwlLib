package test;

import java.util.ArrayList;
import java.util.List;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

import cache.CacheData;

import ontologyDataSructure.BKIndividualProperty;
import ontologyDataSructure.BKProperty;
import ontologyManager.OntologyManager;

public class DatTest {

	private static OntologyManager ontManager = new OntologyManager("file:/C:/workspace/Data/JavaSourceCodeOntology.owl");
	public static void printList(ArrayList<String> list)
	{
		if (list.isEmpty())
			System.out.println("list empty");
		else
			for (String s: list)
				System.out.println(s);
	}
	public static void listInstance()
	{
		
		
//		OntModel model=ontManager.getOntmodel();
//		ArrayList<String> list = new ArrayList();
//		OntClass ontC=model.getOntClass("http://hut.edu.vn/ontology/sourcecode#SoftwareComponent");
//		ExtendedIterator i=ontC.listInstances();
//		while( i.hasNext() ) {			
//			Resource res = (Resource) i.next();
//			list.add(res.getLocalName());
//		}
//		
//		printList(list);
		
		
//		printList(ontManager.listAllClassInstance("http://hut.edu.vn/ontology/sourcecode#Library"));
		
//		printList(ontManager.getValueOfSpecificPropertyForIndividual
//				("http://hut.edu.vn/ontology/sourcecode#java.awt.getImage", "http://hut.edu.vn/ontology/sourcecode#usesMethod"));
//		
//		printList(ontManager.getValueOfSpecificPropertyForIndividual
//				("http://hut.edu.vn/ontology/sourcecode#java.awt.getImage", "http://hut.edu.vn/ontology/sourcecode#hasDirectType"));
//		System.out.println(ontManager.getModelOperator().getOntClassByName("http://hut.edu.vn/ontology/sourcecode#Method").getLocalName());
	}

	
	public static void listProperties()
	{
		printList(ontManager.getObjectPropertyRanges("http://hut.edu.vn/ontology/sourcecode#packageOf"));
		
		OntClass ontClass = ontManager.getOntmodel().getOntClass("http://hut.edu.vn/ontology/sourcecode#FolderSourceCode");
		ExtendedIterator<OntProperty> extendedIterator = ontClass.listDeclaredProperties(false);
		while (extendedIterator.hasNext()) {
			OntProperty tmpProperty = extendedIterator.next();	
			System.out.println(tmpProperty.getURI());
		}
		
		OntProperty ontP = ontManager.getOntmodel().getOntProperty("http://hut.edu.vn/ontology/sourcecode#hasPackage");
		NodeIterator i = ontClass.listPropertyValues(ontP);
		while (i.hasNext()) {
			System.out.println("dfdfd");
			Individual tmpProperty = (Individual)(i.next());	
			System.out.println(tmpProperty.getURI());
		}
		
//		for (BKProperty property: ontManager.listAllClassProperties("http://hut.edu.vn/ontology/sourcecode#FolderSourceCode"))
//		{
//			System.out.println(property.getLocalName());
//		}
	}
	
	public static void listRange()
	{
//		printList(ontManager.getObjectPropertyRanges("http://hut.edu.vn/ontology/sourcecode#resourceOf"));
		
		OntProperty ontp=ontManager.getOntmodel().getOntProperty("http://hut.edu.vn/ontology/sourcecode#hasPackage");
		ExtendedIterator<? extends OntResource> extendedIterator =ontp.listDomain();
		
		while (extendedIterator.hasNext()) {
			System.out.println("jp");
			OntClass tmpProperty = (OntClass)extendedIterator.next();	
			System.out.println(tmpProperty.getURI());
		}
	}
	
	public static void listIndividualValues()
	{
		for (BKIndividualProperty bk:ontManager.
				getValuesOfIndividual("http://hut.edu.vn/ontology/sourcecode#R.jgraph.src"))
		{
			System.out.println("......................");
			System.out.println(bk.getProperty());
			printList(bk.getListValue());
		}
	}

	
	public static void main(String[] args) {
//		listInstance();
		listProperties();		
//		listRange();		
//		listIndividualValues();
	}
	


}
