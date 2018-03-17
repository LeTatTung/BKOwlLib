package ontologyOperator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


import ontologyDataSructure.*;

import org.apache.log4j.Logger;
import org.mindswap.pellet.jena.PelletReasonerFactory;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.ontology.Ontology;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.NsIterator;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.vocabulary.OWL;

/**
 * Cung cấp khả năng xử lý trên một Model
 * 
 * @author TRUNG
 * 
 */
public class ModelOperator {

	/**
	 * Model để xử lý
	 */
	private OntModel ontModel;

	/**
	 * Constructor
	 */
	public ModelOperator(OntModel ontModel) {
		this.ontModel = ontModel;
	}

	/**
	 * Tạo 1 submodel từ model đã cho
	 * 
	 * @return OntModel con
	 */
	public OntModel extractSubModel() {
		OntModel subModel = ModelFactory.createOntologyModel(PelletReasonerFactory.THE_SPEC);
		subModel.setNsPrefixes(ontModel.getNsPrefixMap());// Phải thiết lập
		// prefix cho an toàn, nếu để tự thiết lập rất nguy hiểm
		return subModel;
	}

	/**
	 * Trả về danh sách các Class của 1 ontModel.
	 * 
	 * @return ArrayList, với mỗi phần tử thuộc kiểu dữ liệu mô tả ngắn gọn cho
	 *         1 class
	 */
	public ArrayList<BKClass> listClass() {
		// Lấy ra danh sách các class, sắp xếp lại, trả về kết quả.
		ArrayList<String> list = new ArrayList<String>();
		Map<String, OntClass> map = new HashMap<String, OntClass>();

		ExtendedIterator<OntClass> extendedIterator = ontModel.listClasses();
		while (extendedIterator.hasNext()) {
			OntClass ontClass = extendedIterator.next();
			String s = ontClass.getLocalName();

			//Cần xử lý s khác null, do có những class giả, kiểu union, ...
			if (s != null) {			
				list.add(s);
				map.put(s, ontClass);
			}
		}
		Collections.sort(list);// Sap xep class

		ArrayList<BKClass> result = new ArrayList<BKClass>();
		for (String s : list) {
			result.add(new BKClass(map.get(s)));
		}

		return result;
	}

	/**
	 * Trả về danh sách các Property của một model
	 * 
	 * @return:ArrayList, mỗi phần tử là kiểu dữ liệu định kiểu trước lưu giữ
	 *                    thông tin cho Property
	 */
	public ArrayList<BKProperty> listProperties() {
		ArrayList<BKProperty> result = new ArrayList<BKProperty>();
		ArrayList<String> list = new ArrayList<String>();
		Map<String, OntProperty> map = new HashMap<String, OntProperty>();
		ExtendedIterator<OntProperty> tmpIterator = ontModel.listOntProperties();
		while (tmpIterator.hasNext()) {
			OntProperty tmpOntProperty = tmpIterator.next();
			String s = tmpOntProperty.getLocalName();
			list.add(s);
			map.put(s, tmpOntProperty);
		}
		Collections.sort(list);
		for (String s : list) {
			result.add(new BKProperty(map.get(s)));
		}
		return result;
	}

	/**
	 * Tìm kiếm một class trong model 
	 * 
	 * @param classtosearch
	 * @return: đối tượng MyClass lưu trữ thông tin của class tìm được
	 */
	public BKClass getClassByName(String classtosearch) {
		return new BKClass(this.getOntClassByName(classtosearch));
	}
	
	/**
	 * Tìm kiếm một class trong model theo localName
	 * 
	 * @param classtosearch
	 * @return: đối tượng MyClass lưu trữ thông tin của class tìm được
	 */
	public BKClass getClassByShortName(String classtosearch){
		if (classtosearch.equals("Thing"))
			return new BKClass(this.getOntClassByName("http://www.w3.org/2002/07/owl#Thing"));
		
		return new BKClass(this.getOntClassByShortName(classtosearch));			
	}

	/**
	 * Lấy ra một ontclass trong model, theo full URI
	 * 
	 * @param classtosearch
	 * @return: OntClass tìm được
	 */
	public OntClass getOntClassByName(String classtosearch) {
		return ontModel.getOntClass(classtosearch);
	}

	/**
	 * Tìm kiếm một ontclass trong model theo localName
	 * 
	 * @param classtosearch
	 * @return: OntClass tìm được
	 */
	public OntClass getOntClassByShortName(String classtosearch) {
		NsIterator lstUri = ontModel.listNameSpaces();
		while (lstUri.hasNext()) {
			String classBaseUri = lstUri.next()+ classtosearch;
			
			OntClass oc = ontModel.getOntClass(classBaseUri);
			if (oc != null)
				return oc;		
		}
		return null;
	}
	
	/**
	 * Tìm kiếm một Property trong model theo tên
	 * 
	 * @param propertytosearch
	 * @return : đối tượng MyProperty lưu trữ thông tin của property tìm được
	 */
	public BKProperty getPropertyByName(String propertytosearch) {
		return new BKProperty(this.getOntPropertyByName(propertytosearch));

	}
	
	/**
	 * Tìm kiếm một Property trong model theo localName
	 * 
	 * @param propertytosearch
	 * @return : đối tượng MyProperty lưu trữ thông tin của property tìm được
	 */
	public BKProperty getPropertyByShortName(String propertytosearch) {
		return new BKProperty(this.getOntPropertyByShortName(propertytosearch));
	}

	/**
	 * Tìm kiếm một ontProperty trong model theo tên
	 * 
	 * @param propertytosearch
	 * @return : OntProperty tìm được
	 */
	public OntProperty getOntPropertyByName(String propertytosearch) {
		return ontModel.getOntProperty(propertytosearch);
	}
	
	/**
	 * Tìm kiếm một ontProperty trong model theo localName
	 * 
	 * @param propertytosearch
	 * @return : OntProperty tìm được
	 */
	public OntProperty getOntPropertyByShortName(String propertytosearch) {
		NsIterator lstUri = ontModel.listNameSpaces();
		while (lstUri.hasNext()) {
			String propertyBaseUri = lstUri.next() + propertytosearch;
			OntProperty op = ontModel.getOntProperty(propertyBaseUri);
			if (op != null)
				return op;
		}
		return null;
	}
	
	public Individual getIndividualByName(String individualToSearch){
		return ontModel.getIndividual(individualToSearch);
	}
	
	public Individual getIndividualByShortName(String individualToSearch){
		
		NsIterator lstUri = ontModel.listNameSpaces();
		while (lstUri.hasNext()) {
			String individualBaseUri = lstUri.next() + individualToSearch;
			Individual ind = ontModel.getIndividual(individualBaseUri);
			if (ind != null)
				return ind;
		}			
		return null;
	}
	
	public BKIndividual getIndividualInfoByName(String individualName) {
		return new BKIndividual(this.getIndividualByName(individualName));
	}

	public BKIndividual getIndividualInfoByShortName(String individualName) {
		return new BKIndividual(this.getIndividualByShortName(individualName));
	}
	
	
	public void addLabelForResource(String resourceID, LanguageEnum languageType, String label){
		OntResource ontResource = null;
		if (getOntClassByName(resourceID) != null) ontResource = getOntClassByName(resourceID);
		if (getOntPropertyByName(resourceID) != null) ontResource = getOntPropertyByName(resourceID);
		if (getIndividualByName(resourceID) != null) ontResource = getIndividualByName(resourceID);
		
		if (ontResource != null)
		{
		
			switch (languageType)
		{
		case DE : ontResource.addLabel(label, "de"); 
		break;
		case EN : ontResource.addLabel(label, "en"); 
		break;
		case ES : ontResource.addLabel(label, "es");
		break;
		case FR : ontResource.addLabel(label, "fr");
		break;
		case PT : ontResource.addLabel(label, "pt");
		break;
		}
		
		}
		
	}
	
	public String getLabelFromResource(String resourceID, LanguageEnum languageType){
		OntResource ontResource = null;
		String result = "";
		if (getOntClassByName(resourceID) != null) ontResource = getOntClassByName(resourceID);
		if (getOntPropertyByName(resourceID) != null) ontResource = getOntPropertyByName(resourceID);
		if (getIndividualByName(resourceID) != null) ontResource = getIndividualByName(resourceID);
		
		if (ontResource != null){
		
			switch (languageType)
		{
		case DE : result = ontResource.getLabel("de"); 
		break;
		case EN : result = ontResource.getLabel("en"); 
		break;
		case ES : result = ontResource.getLabel("es");
		break;
		case FR : result = ontResource.getLabel("fr");
		break;
		case PT : result = ontResource.getLabel("pt");
		break;
		}
		
		}
		
		return result;
	}
	
	/**
	 * Ghi model ra 1 file
	 * 
	 * @param fileName:
	 *            Tên file OWL tương ứng
	 */
	public void writeToOWL(String fileName) {
		try {
			File file = new File(fileName);

			File path = file.getParentFile();
			if (!path.exists())
				path.mkdirs();

			FileOutputStream out = new FileOutputStream(file);

			ontModel.write(out, "RDF/XML-ABBREV");

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		}
	}

}
