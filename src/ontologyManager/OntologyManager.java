package ontologyManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ontologyDataSructure.*;
import ontologyOperator.*;

import org.mindswap.pellet.jena.PelletReasonerFactory;

import cache.CacheData;

import com.clarkparsia.pellet.sparqldl.jena.SparqlDLExecutionFactory;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.Syntax;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.Ontology;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

public class OntologyManager {

	public ModelOperator modelOperator;
	public CacheData cacheData;
	
	private OntModel ontmodel;

	public ModelOperator getModelOperator() {
		return modelOperator;
	}

	public OntModel getOntmodel() {
		return ontmodel;
	}

	public void setOntmodel(OntModel ontmodel) {
		this.ontmodel = ontmodel;
	}

	/**
	 * Default Constructor
	 */
	public OntologyManager() {
		ontmodel = ModelFactory.createOntologyModel(PelletReasonerFactory.THE_SPEC);
		modelOperator = new ModelOperator(ontmodel);
		
		cacheData=new CacheData(this);
	}

	/**
	 * Constructor
	 * 
	 * @param ontology
	 *            Đọc ontology từ một file và read vào ontmodel
	 */
	public OntologyManager(String ontology) {
		super();
		ontmodel = ModelFactory.createOntologyModel(PelletReasonerFactory.THE_SPEC);
		ontmodel.read(ontology);
		ontmodel.prepare();
		ontmodel.setStrictMode(false);
		cacheData=new CacheData(this);
		modelOperator = new ModelOperator(ontmodel);
	}

	/**
	 * Constructor
	 * 
	 * @param ontology
	 *            Khởi tạo từ một OntModel
	 */
	public OntologyManager(OntModel newontmodel) {
		super();
		ontmodel = ModelFactory.createOntologyModel(PelletReasonerFactory.THE_SPEC);
		ontmodel = newontmodel;
		cacheData=new CacheData(this);
		modelOperator = new ModelOperator(ontmodel);
	}

	/**
	 * Doc vao 1 model khac
	 * @param subOntManager
	 */
	public void mergeWithModel(OntologyManager subOntManager, Boolean suppressReifications)
	{
		ontmodel.add(subOntManager.getOntmodel(), suppressReifications);
	}
	
	/**
	 * 
	 * @param classname
	 * @return : danh sách tên các instance của EnumeratedClass
	 */
	public ArrayList<String> listInstanceOfEnumeratedClass(String classname) {
		return cacheData.getClassOperator(classname).listInstanceOfEnumeratedClass();
	}

	/**
	 * Liệt kê các instance của một Class theo fullURI của Class
	 * 
	 * @param classname
	 * @return : danh sách tên các instance của Class
	 */
	public ArrayList<String> listAllClassInstance(String classname) {
		return cacheData.getClassOperator(classname).listAllClassInstance(true);
	}

	/**
	 * Liệt kê tên các instance của một Class theo LocalName của Class và cả các
	 * instance của SubClass đã có trên service
	 * 
	 * @param classname
	 * @return :danh sách tên các instance của Class và của SubClass
	 */
	public ArrayList<String> listAllRelatedInstance(String classname) {
		return cacheData.getClassOperator(classname).listAllClassInstance(false);
	}

	/**
	 * Liệt kê các property của một Class theo LocalName của Class Đã có trên
	 * service
	 * 
	 * @param classname
	 * @return : danh sách các BKProperty lưu trữ thông tin của các Property
	 */

	public ArrayList<BKProperty> listAllClassProperties(String classname) {
		return cacheData.getClassOperator(classname).getAllProperties(false);
	}

	/**
	 * Liệt kê các SubClass của một Class theo LocalName của Class Đã có trên
	 * service
	 * 
	 * @param classname
	 * @return : danh sách các BKCLass lưu trữ thông tin của các SubClass
	 */
	public ArrayList<BKClass> listSubClasses(String classname, Boolean direct) {
		return cacheData.getClassOperator(classname).getSubClasses(direct);
	}

	/**
	 * Liệt kê các SuperClass của một Class theo LocalName của Class Đã có trên
	 * service
	 * 
	 * @param classname
	 * @return : danh sách các BKClass lưu trữ thông tin của các SuperClass
	 */
	public ArrayList<BKClass> listSuperClasses(String classname) {
		return cacheData.getClassOperator(classname).getSuperClasses();
	}

	/**
	 * Lấy kiểu dữ liệu của một DatatypeProperty Đã có trên service
	 * 
	 * @param propertyname
	 * @return
	 */
	public String getPropertySpecificDataType(String propertyname) {
		return cacheData.getPropertyOperator(propertyname).getSpecificDataType();
	}

	/**
	 * Lấy Range của một ObjectProperty Đã có trên service
	 * 
	 * @param propertyname
	 * @return
	 */

	public ArrayList<String> getObjectPropertyRanges(String propertyname) {
		return cacheData.getPropertyOperator(propertyname).getListPropertyRanges();
	}
	/**
	 * Lấy domain của 1 ObjectProperty
	 * @param propertyname
	 * @return
	 */
	public ArrayList<String> getObjectPropertyDomains(String propertyname) {
		return cacheData.getPropertyOperator(propertyname).getListPropertyDomains();
	}
	
	/**
	 * Lấy về Domain của một property Đã có trên service
	 * 
	 * @param propertyname
	 * @return
	 */
	public BKResource getDomain(String propertyname) {
		return cacheData.getPropertyOperator(propertyname).getDomain();
	}
	
	
	/**
	 * Liệt kê các SubProperty của một Property Đã có trên service
	 * 
	 * @param propertyname
	 * @return : danh sách các BKProperty lưu trữ thông tin của các SubProperty
	 */
	public ArrayList<BKProperty> listSubProperties(String propertyname) {
		return cacheData.getPropertyOperator(propertyname).getSubProperties();
	}

	/**
	 * Liệt kê các SuperProperty của một Property Đã có trên service
	 * 
	 * @param propertyname
	 * @return : danh sách các BKProperty lưu trữ thông tin của một
	 *         SuperProperty
	 */
	public ArrayList<BKProperty> listSuperProperties(String propertyname) {
		return cacheData.getPropertyOperator(propertyname).getSuperProperties();
	}

	/**
	 * Liệt kê các Class của một ontology Đã có trên service
	 * 
	 * @return : danh sách các BKClass lưu trữ thông tin của các Class
	 */
	public ArrayList<BKClass> listClasses() {

		return modelOperator.listClass();

	}

	/**
	 * Liệt kê các Property của một ontology Đã có trên service
	 * 
	 * @return : danh sách các BKProperty lưu trữ thông tin của các Property
	 */
	public ArrayList<BKProperty> listProperties() {
		return modelOperator.listProperties();
	}

	/**
	 * Ghi ontology ra một file Đã có trên service
	 * 
	 * @param fileName :
	 *            tên file để ghi ontology
	 */
	public void writeToOWL(String fileName) {
		modelOperator.writeToOWL(fileName);
	}

	/**
	 * Lấy một Class trong ontology theo LocalName Đã có trên service
	 * 
	 * @param classtosearch
	 * @return
	 */
	public BKClass getClassByName(String classtosearch) {
		return new BKClass(cacheData.getClassOperator(classtosearch).getOntClass());
	}

	public BKClass getClassByShortName(String classtosearch) {
		return new BKClass(cacheData.getClassOperatorByShortName(classtosearch).getOntClass());
	}

	/**
	 * Lấy một Property trong ontology theo LocalName Đã có trên service
	 * 
	 * @param propertytosearch
	 * @return
	 */
	public BKProperty getPropertyByName(String propertytosearch) {
		return new BKProperty(cacheData.getPropertyOperator(propertytosearch).getOntProperty());
	}

	public BKProperty getPropertyByShortName(String propertytosearch) {
		return new BKProperty(cacheData.getPropertyOperatorByShortName(propertytosearch).getOntProperty());
	}
	
	
	/**
	 * Thêm label cho một Resource trong model Đã có trên service
	 * 
	 * @param resourceID
	 * @param languageType
	 * @param label
	 */

	public void addLabelForResource(String resourceID, LanguageEnum languageType, String label) {

		modelOperator.addLabelForResource(resourceID, languageType, label);
	}

	/**
	 * Lấy về label của một Resource trong model Đã có trên service
	 * 
	 * @param resourceID
	 * @param languageType
	 * @return
	 */
	public String getLabelFromResource(String resourceID, LanguageEnum languageType) {

		return modelOperator.getLabelFromResource(resourceID, languageType);
	}

	
	/**
	 * Tao them 1 instance, api tu no da check luon neu ton tai, tra ve individual do luon
	 * @param instancename fullURI
	 * @param classname fullURI
	 * @return
	 */
	public IndividualOperator createInstance(String instancename, String classname) {
		OntClass oc = cacheData.getClassOperator(classname).getOntClass();
		if (oc == null)
			return null;
		else {
			Individual individual = oc.createIndividual(instancename);
			if (individual != null)
				return new IndividualOperator(individual);
			else
				return null;
		}

	}

	/**
	 * Tạo thêm instance cho ontology và
	 * ghi vào submodel,trả về false nếu không tạo đuợc, trả về true nếu tạo
	 * thành công
	 */
	public boolean createInstanceInSubModel(String instancename, String classname, OntModel submodel) {
		OntClass classToAdd = modelOperator.getOntClassByName(classname);
		if (classToAdd == null)
			return false;
		else {
			if (modelOperator.getIndividualByName(instancename) != null)
				return false;
			
			submodel.createIndividual(instancename, classToAdd);
			classToAdd.createIndividual(instancename);
			return true;
		}
	}


	/**
	 * Lấy về tên class trực tiếp của một individual
	 * 
	 * @param individualName
	 * @return
	 */
	public String getClassOfIndividual(String individualName) {
		Individual individual = modelOperator.getIndividualByName(individualName);
		if (individual != null) {
			return individual.getOntClass(true).getURI();
		} else
			return null;
	}
	
	
	
	/**
	 * Thêm một giá trị thuộc tính kiểu Datatype cho Individual Đã có trên
	 * service
	 * 
	 * @param propertyname
	 * @param value
	 * @param individualname
	 */
	public void addDatatypePropertyForIndividual(String propertyname,String value, String individualname) {
		IndividualOperator individualoperator = new IndividualOperator(this.ontmodel, individualname, cacheData);
		individualoperator.addDatatypePropertyForIndividual( cacheData.getPropertyOperator(propertyname), value);
	}

	
	public void addDatatypePropertyForIndividual(String propertyname,String value, IndividualOperator individualoperator) {
		individualoperator.addDatatypePropertyForIndividual( cacheData.getPropertyOperator(propertyname), value);
	}
	
	/**
	 * Thêm một ObjectProperty cho Individual Đã có trên service
	 * 
	 * @param property
	 * @param individualValue
	 * @param individualname
	 */
	public void addObjectProperty(String property, String individualValue,String individualname) {
		IndividualOperator individualoperator = new IndividualOperator(this.ontmodel, individualname, cacheData);
		individualoperator.addObjectProperty(property,individualValue);
	}
	
	public void addObjectProperty(String property, IndividualOperator individualOperatorValue,IndividualOperator individualoperator) {
		individualoperator.addObjectProperty(property,individualOperatorValue);
	}
	
	/**
	 * Lấy về tất cả các giá trị cho 1 instance, sắp xếp thuộc tính có giá trị trc, abc sau
	 * @param instanceID
	 * @return
	 */
	public ArrayList<BKIndividualProperty> getValuesOfIndividual(String instanceID)
	{
		IndividualOperator individualOperator  = new IndividualOperator(modelOperator.getIndividualByName(instanceID));
		return individualOperator.getValuesOfIndividual();
	}

	/**
	 * Lay ve gia tri mot property xac dinh cua mot individual 
	 * @param instanceID
	 * @param propertyName
	 * @return
	 */
	public ArrayList<String> getValueOfSpecificPropertyForIndividual(String instanceID, String propertyName) {
		IndividualOperator individualOperator  = new IndividualOperator(ontmodel, instanceID, cacheData);
		return individualOperator.getValueOfSpecificPropertyForIndividual(propertyName);
	}

	public BKIndividual getIndividualInfoByName(String individualName) {
		return modelOperator.getIndividualInfoByName(individualName);
	}

	public BKIndividual getIndividualInfoByShortName(String individualName) {
		return modelOperator.getIndividualInfoByShortName(individualName);
	}
	
	public boolean checkexitsIndividual(String individualID) {
		if (modelOperator.getIndividualByName(individualID) != null)
			return true;
		return false;
	}


	/**
	 * Xóa một individual trong Ontmodel
	 * 
	 * @param individualName
	 */
	public void removeIndividual(String individualName) {
		Individual individual = modelOperator.getIndividualByName(individualName);
		if (individual != null) {
			ontmodel.removeAll(individual, null, null);
			ontmodel.removeAll(null, null, individual);
		}
	}
	

	/**
	 * Thực hiện câu truy vấn Sparql Đã có trên service
	 * 
	 * @param querystring
	 * @return Danh sách các ArrayList<String>,trong đó ArrayList đầu tiên chứa
	 *         danh sách các biến, các ArrayList tiếp theo chứa các bộ giá trị
	 */
	public ArrayList<ArrayList<String>> SparqlResultList(String queryString) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		Query query = QueryFactory.create(queryString);

		List<String> varlist = query.getResultVars();
		result.add((ArrayList<String>) varlist);
		QueryExecution qe = SparqlDLExecutionFactory.create(query,this.ontmodel);
		ResultSet rs = qe.execSelect();
		while (rs.hasNext()) {
			ArrayList<String> tmpresult = new ArrayList<String>();
			QuerySolution querySolution = rs.next();
			
			for (int i = 0; i < varlist.size(); i++) {
				String tmpvar = varlist.get(i);
				RDFNode node = querySolution.get(tmpvar);
				if (node != null)
					tmpresult.add(node.toString());
				else
					tmpresult.add(null);
			}

			result.add(tmpresult);
		}

		return result;

	}

	/**
	 * Thực hiện câu truy vấn Sparql Đã có trên service
	 * 
	 * @param querystring
	 * @return kết quả trả về là QueryResultMapData chứa danh sách các biến và
	 *         map, 1 map là các cặp biến và giá trị tuơng ứng
	 */

	public QueryResultMapData SparqlResultMap(String querystring) {
		QueryResultMapData result = new QueryResultMapData();
		Query query = QueryFactory.create(querystring);

		List<String> tmpvarlist = query.getResultVars();
		ArrayList<String> varlist = new ArrayList<String>();

		Map<String, ArrayList<String>> solutionmap = new HashMap<String, ArrayList<String>>();
		for (int i = 0; i < tmpvarlist.size(); i++) {
			varlist.add(tmpvarlist.get(i));
		}
		result.setVariableList(varlist);


		for (int i = 0; i < varlist.size(); i++) {
			QueryExecution qe = SparqlDLExecutionFactory.create(query, this.ontmodel);
			ResultSet rs = qe.execSelect();
			
			ArrayList<String> resultlist = new ArrayList<String>();
			String tmpvar = varlist.get(i);
			while (rs.hasNext()) {
				QuerySolution solution = rs.next();
				if (solution.contains(tmpvar)) {
					RDFNode node = solution.get(tmpvar);
					if (node!=null)
						if (!resultlist.contains(node.toString()))
							resultlist.add(node.toString());
				}
			}
			solutionmap.put(tmpvar, resultlist);
		}

		result.setResultMap(solutionmap);

		return result;
	}

	/**
	 * @param queryString
	 * @return Truy van tra ve localName
	 * @author KienCuong
	 */
	public ArrayList<ArrayList<String>> SparqlResultListLocalName(
			String queryString) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();

		QueryExecution qe = QueryExecutionFactory.create(queryString, Syntax.syntaxARQ, this.ontmodel);
		ResultSet rs = qe.execSelect();
		List<String> varlist = rs.getResultVars();
		String propertycContent;
		while (rs.hasNext()) {
			ArrayList<String> tmpresult = new ArrayList<String>();

			QuerySolution querySolution = rs.next();

			for (int i = 0; i < varlist.size(); i++) {
				String tmpvar = varlist.get(i);
				// tmpresult.add(querySolution.get(tmpvar).toString());

				// Phan lay cac thuoc tinh cua instance
				RDFNode node = querySolution.get(tmpvar);

				if (node != null) {
					if (node.isResource()) {
						String uri = ((Resource) node).getURI();
						propertycContent = uri.substring(uri.indexOf('#') + 1);
					} else if (node.isLiteral()) {
						Literal literal = (Literal) node;
						propertycContent = literal.getLexicalForm();
					} else {
						propertycContent = node.toString();
					}
				} else {
					propertycContent = "";
				}
				tmpresult.add(propertycContent);
			}

			result.add(tmpresult);
		}

		return result;

	}

	
	/**
	 * Lấy baseUri của Ontology đuợc load lên
	 */
	public ArrayList<String> getBaseUri() {
		ArrayList<String> result = new ArrayList<String>();
		ExtendedIterator<Ontology> ontology = this.ontmodel.listOntologies();
		while (ontology.hasNext()) {
			Ontology i = ontology.next();
			result.add(i.getURI());
		}
		return result;
	}

	/**
	 * Đọc thêm ontology vào ontmodel
	 */
	public void readOntology(String ontologyfilename) {

		this.ontmodel.read(ontologyfilename);
		this.ontmodel.prepare();
		this.ontmodel.setStrictMode(false);
	}
	

	/**
	 * Remove 1 file owl trong 1 model
	 */
	public void removeOwl(String uri) {
		this.ontmodel.removeLoadedImport(uri);
	}

}
