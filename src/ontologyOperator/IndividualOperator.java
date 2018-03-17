package ontologyOperator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


import ontologyDataSructure.BKIndividualProperty;

import cache.CacheData;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

/**
 * Xử lý thông tin trên Individual
 * 
 * @author TRUNG
 * 
 */
public class IndividualOperator {

	private CacheData cacheData;
	
	private OntModel ontModel;
	
	/**
	 * Individual cần để xử lý
	 */
	private Individual individual;

	public Individual getIndividual() {
		return individual;
	}

	public void setIndividual(Individual individual) {
		this.individual = individual;
	}

	/**
	 * Constructor
	 * 
	 * @param individual:Individual
	 *            để xử lý
	 */
	public IndividualOperator(Individual individual) {
		super();
		this.individual = individual;
	}

	/**
	 * Constructor
	 * 
	 * @param model
	 * @param individualname
	 *            Lấy Individual về từ một OntModel theo LocalName để xử lý
	 */
	public IndividualOperator(OntModel model, String individualname, CacheData cacheData) {
		super();
		this.cacheData = cacheData;
		this.ontModel = model;
		
		ModelOperator modelOperator = new ModelOperator(model);
		Individual tmpIndividual = modelOperator
				.getIndividualByName(individualname);
		if (tmpIndividual != null)
			this.individual = tmpIndividual;
	}

	/**
	 * Thêm một giá trị của một thuộc tính (Kiểu datatype) cho 1 individual
	 * 
	 * @param property:
	 *            thuộc tính quan tâm
	 * @param value:
	 *            giá trị cho thuộc tính
	 */
		public void addDatatypePropertyForIndividual(PropertyOperator property, String value) {
		String type = property.getSpecificDataType();
		if (type != null) {
			if (type.equals("string"))
			{
				if (!XSDDatatype.XSDstring.isValid(value))
					return;
				individual.addProperty(property.getOntProperty(), value, XSDDatatype.XSDstring);
			}
			else if (type.equals("date"))
			{
				if (!XSDDatatype.XSDdate.isValid(value))
					return;
				individual.addProperty(property.getOntProperty(), value, XSDDatatype.XSDdate);
			}			
			else if (type.equals("dateTime"))
			{
				if (!XSDDatatype.XSDdateTime.isValid(value))
					return;
				individual.addProperty(property.getOntProperty(), value, XSDDatatype.XSDdateTime);
			}
			else if (type.equals("float"))
			{
				if (!XSDDatatype.XSDfloat.isValid(value))
					return;
				individual.addProperty(property.getOntProperty(), value, XSDDatatype.XSDfloat);
			}
			else if (type.equals("int"))
			{
				if (!XSDDatatype.XSDint.isValid(value))
					return;
				individual.addProperty(property.getOntProperty(), value, XSDDatatype.XSDint);
			}
			else if (type.equals("integer"))
			{
				if (!XSDDatatype.XSDinteger.isValid(value))
					return;
				individual.addProperty(property.getOntProperty(), value, XSDDatatype.XSDinteger);
			}
			else if (type.equals("positiveInteger"))
			{
				if (!XSDDatatype.XSDpositiveInteger.isValid(value))
					return;
				individual.addProperty(property.getOntProperty(), value, XSDDatatype.XSDpositiveInteger);
			}
			else if (type.equals("nonPositiveInteger"))
			{
				if (!XSDDatatype.XSDnonPositiveInteger.isValid(value))
					return;
				individual.addProperty(property.getOntProperty(), value, XSDDatatype.XSDnonPositiveInteger);
			}
			else if (type.equals("negativeInteger"))
			{
				if (!XSDDatatype.XSDnegativeInteger.isValid(value))
					return;
				individual.addProperty(property.getOntProperty(), value, XSDDatatype.XSDnegativeInteger);
			}
			else if (type.equals("nonNegativeInteger"))
			{
				if (!XSDDatatype.XSDnonNegativeInteger.isValid(value))
					return;
				individual.addProperty(property.getOntProperty(), value, XSDDatatype.XSDnonNegativeInteger);
			}
			else if (type.equals("double"))
			{
				if (!XSDDatatype.XSDdouble.isValid(value))
					return;
				individual.addProperty(property.getOntProperty(), value, XSDDatatype.XSDdouble);
			}
			else if (type.equals("boolean"))
			{
				if (!XSDDatatype.XSDboolean.isValid(value))
					return;
				individual.addProperty(property.getOntProperty(), value, XSDDatatype.XSDboolean);
			}
			else
				// Neu tao bang cach nay thi khong can if else, nhung indi lai
				// co 2
				// gia tri cho 1 thuoc tinh (bi loi)
				individual.addProperty(property.getOntProperty(), value, new XSDDatatype(type));
		}
	}
	
	/**
	 * Thêm một giá trị của một thuộc tính (Kiểu object) cho 1 individual
	 * 
	 * @param model:
	 *            ontModel chứa individual giá trị của individual quan tâm
	 * @param property:
	 *            tên thuộc tính quan tâm
	 * @param individualValue:
	 *            ID dạng string của individual làm giá trị thuộc tính của
	 *            individual quan tâm
	 */
	public void addObjectProperty(String propertyname, String individualValue) {
		Individual indVal = null;
		OntProperty Ontpro = null;
		ModelOperator modelOperator = new ModelOperator(ontModel);
		Ontpro = modelOperator.getOntPropertyByName(propertyname);
		indVal = modelOperator.getIndividualByName(individualValue);

		if (indVal != null && Ontpro != null) {
			individual.addProperty(Ontpro, indVal);
		}
	}
	
	public void addObjectProperty(String propertyname, IndividualOperator individualOperatorValue) {
		OntProperty Ontpro = null;
		ModelOperator modelOperator = new ModelOperator(ontModel);
		Ontpro = modelOperator.getOntPropertyByName(propertyname);

		if (individual !=null && individualOperatorValue != null && Ontpro != null) {
			individual.addProperty(Ontpro, individualOperatorValue.getIndividual());
		}
	}
	
	/**
	 * Lấy về tất cả các giá trị của Individual
	 * Sắp xếp kết quả trả về
	 * Thuộc tính có giá trị ưu tiên đứng trước, sau đó xếp theo a,b,c
	 * @return danh sách các kết quả, mỗi kết quả là 1 giá trị của thuộc tính
	 */
	public ArrayList<BKIndividualProperty> getValuesOfIndividual()
	{
		ArrayList<BKIndividualProperty> result = new ArrayList<BKIndividualProperty> ();
		
		//list các thuộc tính có giá trị
		ArrayList<String> listProperty1= new ArrayList<String>();
		//list các thuộc tính không có giá trị
		ArrayList<String> listProperty2= new ArrayList<String>();
		Map<String, BKIndividualProperty> map = new HashMap<String, BKIndividualProperty>();
		
		OntClass ontClass = individual.getOntClass(true);
		System.out.println(ontClass.getLocalName());
		ExtendedIterator<OntProperty> extendedIterator = ontClass.listDeclaredProperties(false);
		
		//Duyệt tất cả các thuộc tính
		while (extendedIterator.hasNext()) {
			OntProperty tmpProperty = extendedIterator.next();				
			if (tmpProperty != null) {
				System.out.println(tmpProperty.getLocalName());
				NodeIterator value = individual.listPropertyValues(tmpProperty);
				ArrayList<String> tmpList = new ArrayList<String>();
				
				//Duyệt tất cả các giá trị, cho vào list
				while (value.hasNext())
				{
					String s;
					if (tmpProperty.isDatatypeProperty())
						s=((Literal)value.next()).getString();
					else
						s=((Resource)value.next()).getURI();
					tmpList.add(s);
				}
				
				//add vào list để sắp xếp
				if (tmpList.isEmpty())
					listProperty2.add(tmpProperty.getLocalName());
				else
					listProperty1.add(tmpProperty.getLocalName());
				
				map.put(tmpProperty.getLocalName(),new BKIndividualProperty(tmpProperty.getURI(), tmpList));
			}
		}
		
		Collections.sort(listProperty1);
		Collections.sort(listProperty2);
		
		for (String s: listProperty1)
			result.add(map.get(s));
		for (String s: listProperty2)
			result.add(map.get(s));
		
		return result;
	}

	public ArrayList<String> getValueOfSpecificPropertyForIndividual(String propertyName)
	{
		ArrayList<String> result = new ArrayList<String>();		
		OntProperty ontProperty = ontModel.getOntProperty(propertyName);

		if ((ontProperty != null) && (individual != null)) {
			NodeIterator list = individual.listPropertyValues(ontProperty);			
			while (list.hasNext())
			{
				String s;
				if (ontProperty.isDatatypeProperty())
					s=((Literal)list.next()).getString();
				else
					s=((Resource)list.next()).getURI();
				result.add(s);
			}
		}
		
		return result;
	}
}
