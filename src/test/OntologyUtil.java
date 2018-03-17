package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.mindswap.pellet.jena.PelletReasonerFactory;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.Syntax;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.ResourceUtils;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

public class OntologyUtil{	
	/*
	 * Prepare model
	 */
	public static void prepareModel(OntModel model, String fileName)
	{
			model.read(fileName);
	        model.prepare();	
	}
	/*
	 * Load tat ca cac file owl trong thu muc vao model
	 */
	public static void loadAllOWL(OntModel model, String directory)
	{
		File dir=new File(directory);
		if (dir.isDirectory())
		{
			for (File file:dir.listFiles())
			{
				if (file.isDirectory())
				{
					loadAllOWL(model, file.getAbsolutePath());
				}
				else if (file.getName().endsWith(".owl")||file.getName().endsWith(".OWL"))
				{
					try {
						model.read("file:\\"+file.getAbsolutePath());						
					} catch (Exception e) {
					}				
				}
			}
		}
	}
	
	/*
	 * Lay ra tat ca cac gia tri cua cac thuoc tinh cua 1 individual ID cho truoc
	 */
	public static List getAllPropertyValueOfInstance(OntModel model, String ID) throws Exception
	{
		List result = new ArrayList();
		
		List<String[]> listDataProperty=new ArrayList();
		Map mapDataProperty = new HashMap();
		
		//Tao 2 temp de tach biet duoc data va object property trong 1 lan duyet
		List<String[]> listObjectProperty=new ArrayList();
		Map mapObjectProperty = new HashMap();
		
		
		Individual individual = model.getIndividual(ID);
		if (individual!=null)
		{
			OntClass oc = individual.getOntClass(true);//Neu khong co tham so, ket qua co the la 1 lop cha bat ky, nguy hiem
			ExtendedIterator iter = oc.listDeclaredProperties();

			while( iter.hasNext() ) {
				OntProperty op = (OntProperty) iter.next();
				String member[]=new String[3];
				member[0] = op.getLocalName();
				member[1] = op.getLabel("en");
				member[2] = op.getLabel("vn");
				if (member[1]==null)member[1] = member[0];//Neu khong co label, lay luon ten thuoc tinh
				if (member[2]==null)member[2] = member[0];
				
				if (op.isDatatypeProperty())
				{
					listDataProperty.add(member);
					
					NodeIterator value = individual.listPropertyValues(op.asProperty());
					
					List<String> tmpList=new ArrayList();//Do 1 thuoc tinh co nhieu gia tri
					while (value.hasNext())
					{
						String s=((Literal)value.next()).getString();
						tmpList.add(s);
					}
					
					mapDataProperty.put(op.getLocalName(), tmpList);
				}
				else
				{
					listObjectProperty.add(member);
					
					NodeIterator value = individual.listPropertyValues(op);
					
					List<String> tmpList=new ArrayList();//Do 1 thuoc tinh co nhieu gia tri
					
					while (value.hasNext())
					{
						String s=((Resource)value.next()).getLocalName();
						tmpList.add(s);
					}
					
					mapObjectProperty.put(op.getLocalName(), tmpList);
				}
			}
			
			result.add(listDataProperty);
			result.add(mapDataProperty);
			result.add(listObjectProperty);
			result.add(mapObjectProperty);
			
			return result;			
		}
		else
			return null;
	}

	public static List<List<String>> getValueProperty(OntModel model, String ID, List<String> property, Boolean full)throws Exception
	{
		List<List<String>> result=new ArrayList();
		Individual individual=model.getIndividual(ID);
		for(String s:property)
		{
			OntProperty ontProperty=model.getOntProperty(s);
			if (ontProperty.isDatatypeProperty())
			{
				NodeIterator value = individual.listPropertyValues(ontProperty.asProperty());
				
				List<String> tmpList=new ArrayList();//Do 1 thuoc tinh co nhieu gia tri
				while (value.hasNext())
				{
					String tmp=((Literal)value.next()).getString();
					tmpList.add(tmp);
				}
				result.add(tmpList);
			}
			else
			{
				NodeIterator value = individual.listPropertyValues(ontProperty);
				List<String> tmpList=new ArrayList();//Do 1 thuoc tinh co nhieu gia tri
				
				while (value.hasNext())
				{
					String tmp=full?((OntResource)value.next()).getURI():((OntResource)value.next()).getLocalName();
					tmpList.add(tmp);
				}
				result.add(tmpList);
			}
		}
		return result;
	}
	
	public static List<String> getDataProperties(OntModel model, String className, Boolean full) throws Exception {
		OntClass oc = model.getOntClass(className);
		List<String> classProps = new ArrayList();
		ExtendedIterator iter = oc.listDeclaredProperties();
		
		while( iter.hasNext() ) {
			OntProperty val = (OntProperty) iter.next();
			if (val.isDatatypeProperty())
				classProps.add(full?val.toString():val.getLocalName());
		}
		
		return classProps;
	}
	
	/*
	 * Lay ra cac data property, cung voi cac label tuong ung cua 1 class
	 */
	public static List<String[]> getDataPropertiesAndLabel(OntModel model, String className, Boolean full) throws Exception {
		OntClass oc = model.getOntClass(className);
		List<String[]> list = new ArrayList();
		ExtendedIterator iter = oc.listDeclaredProperties();
		
		while( iter.hasNext() ) {
			String member[]=new String[3];
			OntProperty val = (OntProperty) iter.next();
			if (val.isDatatypeProperty())
			{
				member[0] = full?val.toString():val.getLocalName();
				member[1] = val.getLabel("en");
				member[2] = val.getLabel("vn");
				if (member[1]==null)member[1] = val.getLocalName();//Neu khong co label, lay luon ten thuoc tinh
				if (member[2]==null)member[2] = val.getLocalName();
				list.add(member);
			}
		}
		return list;
	}
	
	public static List<String> getObjectProperties(OntModel model, String className, Boolean full) throws Exception {
		OntClass oc = model.getOntClass(className);
		List<String> classProps = new ArrayList();
		ExtendedIterator iter = oc.listDeclaredProperties();
		
		while( iter.hasNext() ) {
			OntProperty val = (OntProperty) iter.next();
			if (!val.isDatatypeProperty())
				classProps.add(full?val.toString():val.getLocalName());
		}
		
		return classProps;
	}
	
	/*
	 * Lay ra cac object property, cung voi cac label tuong ung cua 1 class
	 */
	public static List<String[]> getObjectPropertiesAndLabel(OntModel model, String className, Boolean full) throws Exception {
		OntClass oc = model.getOntClass(className);
		List<String[]> list = new ArrayList();
		ExtendedIterator iter = oc.listDeclaredProperties();
		
		while( iter.hasNext() ) {
			String member[]=new String[3];
			OntProperty val = (OntProperty) iter.next();
			if (!val.isDatatypeProperty())
			{
				member[0] = full?val.toString():val.getLocalName();
				member[1] = val.getLabel("en");
				member[2] = val.getLabel("vn");
				if (member[1]==null)member[1] = val.getLocalName();//Neu khong co label, lay luon ten thuoc tinh
				if (member[2]==null)member[2] = val.getLocalName();
				list.add(member);
			}
		}
		return list;
	}
	
	public static List<String> getAllProperties(OntModel model, String className, Boolean full) throws Exception {
		List<String> list=new ArrayList();
		Map map=new HashMap();
		
		OntClass oc = model.getOntClass(className);
		List<String> classProps = new ArrayList();
		ExtendedIterator iter = oc.listDeclaredProperties();
		
		while( iter.hasNext() ) {
			OntProperty property = (OntProperty) iter.next();
			String s=property.getLocalName();
			if (s!=null)
			{
				list.add(s);
				map.put(s, property);
			}
		}
		Collections.sort(list);
		
		for (String s:list)
		{
			OntProperty property=(OntProperty)map.get(s);
			classProps.add(full?property.toString():property.getLocalName());
		}		
		return classProps;
	}
	
	public static List<String[]> getAllProperties(OntModel model, String className) throws Exception {
		List<String[]> result = new ArrayList();
		OntClass oc = model.getOntClass(className);		
		ExtendedIterator iter = oc.listDeclaredProperties();
		
		String member[]=new String[2];
		while( iter.hasNext() ) {
			OntProperty val = (OntProperty) iter.next();
			member[0]=val.getLocalName();
			member[1]=val.getURI();
			result.add(member);
		}
		
		return result;
	}
	
	/*
	 * Lay ra cac property, cung voi cac label tuong ung cua 1 class
	 */
	public static List<String[]> getAllPropertiesAndLabel(OntModel model, String className, Boolean full) throws Exception {
		List<String> list=new ArrayList();
		Map map=new HashMap();
		
		OntClass oc = model.getOntClass(className);
		List<String[]> result = new ArrayList();
		ExtendedIterator iter = oc.listDeclaredProperties();
		
		while( iter.hasNext() ) {
			OntProperty property = (OntProperty) iter.next();
			String s=property.getLocalName();
			if (s!=null)
			{
				list.add(s);
				map.put(s, property);
			}
		}
		Collections.sort(list);
		
		for (String s:list)
		{
			OntProperty property=(OntProperty)map.get(s);
			String member[]=new String[3];

			member[0] = full?property.toString():property.getLocalName();
			member[1] = property.getLabel("en");
			member[2] = property.getLabel("vn");
			if (member[1]==null)member[1] = property.getLocalName();//Neu khong co label, lay luon ten thuoc tinh
			if (member[2]==null)member[2] = property.getLocalName();
			result.add(member);

		}
		return result;
	}
	

}
