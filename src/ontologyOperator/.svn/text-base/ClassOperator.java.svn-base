package ontologyOperator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import ontologyDataSructure.BKClass;
import ontologyDataSructure.BKProperty;


import cache.CacheData;

import com.hp.hpl.jena.graph.query.regexptrees.Nothing;
import com.hp.hpl.jena.ontology.EnumeratedClass;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.vocabulary.OWL;

/**
 * Xử lý các thông tin trên OntClass của Ontology
 * 
 * @author TRUNGNT
 * 
 */
public class ClassOperator {

	/**
	 * Ghi log
	 */
	private Logger logger=Logger.getLogger(this.getClass());
	
	private ArrayList<BKProperty> listProperties;
	private ArrayList<BKProperty> listPropertiesDirect;
	
	private CacheData cacheData;
	
	/**
	 * Ontonlogy Class để xử lý và lấy thông tin
	 */
	private OntClass ontClass;

	public OntClass getOntClass() {
		return ontClass;
	}

	public void setOntClass(OntClass ontClass) {
		this.ontClass = ontClass;
	}

	public ClassOperator() {
	}

	/**
	 * Constructor
	 * 
	 * @param ontclass
	 *            Lấy OntClass về để xử lý thông tin
	 */
	public ClassOperator(OntClass ontclass, CacheData cacheData) {
		super();
		this.ontClass = ontclass;
		this.cacheData= cacheData;
	}

	/**
	 * Constructor
	 * 
	 * @param ontmodel
	 * @param classname
	 *            Lấy OntClass về từ một OntModel theo LocalName để xử lý thông tin
	 */
	public ClassOperator(OntModel ontmodel, String classname, CacheData cacheData) {
		super();
		this.cacheData = cacheData;

		ModelOperator modeloperator = new ModelOperator(ontmodel);
		if (modeloperator.getOntClassByName(classname) != null)
			this.ontClass = modeloperator.getOntClassByName(classname);
		
	}
	/**
	 * Liệt kê các Instance của một EnumeratedClass
	 * @return:Danh sách tên các Instance của EnumeratedClass
	 */
	public ArrayList<String> listInstanceOfEnumeratedClass()
	{
		ArrayList<String> result = new ArrayList<String>();
		if (ontClass != null && ontClass.isEnumeratedClass())
		{			
			ExtendedIterator<? extends OntResource> listinstance = ((EnumeratedClass)ontClass).listOneOf();
			while (listinstance.hasNext())
			{
				OntResource instance = listinstance.next();
				if (instance.isIndividual())
				{
					result.add(instance.getURI());
				}
			}			
		}
		return result;
	}
	
	/**
	 * Liệt kê các Instance của Ontology Class
	 * 
	 * @param: direct, chỉ cấp 1 hay toàn bộ
	 * @return:Danh sách tên các instance
	 */
	public ArrayList<String> listAllClassInstance(Boolean direct) {
		ArrayList<String> result = new ArrayList<String>();
		if (ontClass != null) {
			//Tham số true: chỉ lấy con trực tiếp
			ExtendedIterator<? extends OntResource> tmpIterator = ontClass.listInstances(direct);

			while (tmpIterator.hasNext()) {
				OntResource tmpresource = tmpIterator.next();
				if (tmpresource.isIndividual()) {
					result.add(tmpresource.getURI());
				}

			}
		}
		return result;
	}
	
	
	/**
	 * Liệt kê các Property của Ontology Class
	 * @param direct: trực tiếp hay không
	 * @return: List các BKProperty
	 */
	public ArrayList<BKProperty> getAllProperties(Boolean direct) {
		ArrayList<BKProperty> result = direct?listPropertiesDirect:listProperties;
		if (result == null)
		{
			// Lấy ra danh sách các property, sắp xếp lại, trả về kết quả. 
			ArrayList<String> list = new ArrayList<String>();
			Map<String, OntProperty> map = new HashMap<String, OntProperty>();
			result = new ArrayList<BKProperty>();
			if (ontClass != null) {
				ExtendedIterator<OntProperty> extendedIterator = ontClass.listDeclaredProperties(direct);
				while (extendedIterator.hasNext()) {
					OntProperty tmpProperty = extendedIterator.next();				
					if (tmpProperty != null) {
						String s = tmpProperty.getLocalName();
						list.add(s);
						map.put(s, tmpProperty);
					}
				}
				Collections.sort(list);
	
				for (String s : list) {
					result.add(new BKProperty(map.get(s)));
				}
			}

			//Tao lai lien ket doi tuong
			if (direct)
				listPropertiesDirect = result;
			else
				listProperties = result;
		}
		return result;
	}

	/**
	 * Liệt kê các subClass trực tiếp của Ont Class
	 * 
	 * @return Danh sách các đối tượng MyClass lưu trữ thông tin rút gọn của các
	 *         subClass tương ứng
	 */
	public ArrayList<BKClass> getSubClasses(Boolean direct) {
		ArrayList<BKClass> result = new ArrayList<BKClass>();
		ArrayList<String> list = new ArrayList<String>();
		Map<String, OntClass> map = new HashMap<String, OntClass>();
		if (ontClass != null) {
			ExtendedIterator<OntClass> tmpIterator = ontClass.listSubClasses(direct);
			while (tmpIterator.hasNext()) {
				OntClass tmpClass = tmpIterator.next();				
				if (tmpClass != null && !tmpClass.equals(OWL.Nothing)) {
					String s = tmpClass.getLocalName();
					list.add(s);
					map.put(s, tmpClass);
				}
			}

			Collections.sort(list);

			for (String s : list) {
				result.add(new BKClass(map.get(s)));
			}
		}
		return result;
	}

	/**
	 * Liệt kê các SuperClass trực tiếp của OntClass
	 * 
	 * @return Danh sách các đối tượng MyClass lưu trữ thông tin rút gọn của các
	 *         SuperClass tương ứng
	 */
	public ArrayList<BKClass> getSuperClasses() {
		ArrayList<BKClass> result = new ArrayList<BKClass>();
		ArrayList<String> list = new ArrayList<String>();
		Map<String, OntClass> map = new HashMap<String, OntClass>();
		if (ontClass != null) {
			ExtendedIterator<OntClass> tmpIterator = ontClass.listSuperClasses(true);
			while (tmpIterator.hasNext()) {
				OntClass tmpClass = tmpIterator.next();				
				if (tmpClass != null) {
					String s = tmpClass.getLocalName();
					list.add(s);
					map.put(s, tmpClass);
				}
			}

			Collections.sort(list);

			for (String s : list) {
				result.add(new BKClass(map.get(s)));
			}
		}
		return result;
	}
}
