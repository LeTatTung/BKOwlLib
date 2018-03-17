package cache;

import java.util.HashMap;
import java.util.Map;

import ontologyManager.OntologyManager;
import ontologyOperator.ClassOperator;
import ontologyOperator.PropertyOperator;

public class CacheData {

	private Map<String, ClassOperator> classOperatorMap = new HashMap<String, ClassOperator>();
	private Map<String, PropertyOperator> propertyOperatorMap = new HashMap<String, PropertyOperator>();
	private Map<String, String> classShortFullMap = new HashMap<String, String>();
	private Map<String, String> propertyShortFullMap = new HashMap<String, String>();
	
	private OntologyManager ontManager;

	public CacheData(OntologyManager ontManager)
	{
		this.ontManager = ontManager;
	}
	
	/**
	 * Lay ra ClassOperator theo full URI, lay xong add luon connection short-full cua Class
	 * @param key
	 * @return
	 */
	public ClassOperator getClassOperator(String key)
	{
		ClassOperator result = classOperatorMap.get(key);

		if (result == null)
		{
			result = new ClassOperator(this.ontManager.getOntmodel(),key, this);
			classOperatorMap.put(key, result);
		}
		
		//add luon connection short-full cua Class
		classShortFullMap.put(result.getOntClass().getLocalName(), key);
		return result;
	}
	
	public ClassOperator getClassOperatorByShortName(String key)
	{
		String s = classShortFullMap.get(key);
		
		//Neu chua thay, tuc la class tuong ung chua add vao cache, lay ra chinh xac ten roi add luon class vao cace
		//Khi add class vao cache, thu duoc ca full, short
		if (s == null)
		{
			String fullURI = ontManager.getModelOperator().getClassByShortName(key).getURI();
			getClassOperator(fullURI);
		}
		return getClassOperator(classShortFullMap.get(key));
	}
	
	/**
	 * Lay ra PropertyOperator theo full URI, lay xong add luon connection short-full cua Property 
	 * @param key
	 * @return
	 */
	public PropertyOperator getPropertyOperator(String key)
	{
		PropertyOperator result = propertyOperatorMap.get(key);

		if (result == null)
		{
			result = new PropertyOperator(this.ontManager.getOntmodel(),key);
			propertyOperatorMap.put(key, result);
		}

		//add luon connection short-full cua Property
		propertyShortFullMap.put(result.getOntProperty().getLocalName(), key);
		return result;
	}
	
	public PropertyOperator getPropertyOperatorByShortName(String key)
	{
		String s = propertyShortFullMap.get(key);
		
		//Neu chua thay, tuc la class tuong ung chua add vao cache, lay ra chinh xac ten roi add luon class vao cace
		//Khi add class vao cache, thu duoc ca full, short
		if (s == null)
		{
			String fullURI = ontManager.getModelOperator().getPropertyByShortName(key).getURI();
			getPropertyOperator(fullURI);
		}
		
		return getPropertyOperator(propertyShortFullMap.get(key));
	}
}
