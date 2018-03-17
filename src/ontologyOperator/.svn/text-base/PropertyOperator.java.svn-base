package ontologyOperator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import ontologyDataSructure.BKClass;
import ontologyDataSructure.BKProperty;
import ontologyDataSructure.BKResource;


import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.ontology.UnionClass;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.vocabulary.OWL;

/**
 * Lớp xử lý các thông tin trên Property
 * 
 * @author TRUNG
 * 
 */
public class PropertyOperator {
	/**
	 * Ontology Property cần để lấy thông tin
	 */
	private OntProperty ontProperty;
	
	/**
	 * Các tham số trong cache, tăng tốc độ
	 */
	private String dataType;
	private ArrayList<String> listRanges;
	private ArrayList<String> listDomains;

	/**
	 * 
	 * @param ontProperty: Ontology Property để lấy thông tin
	 */

	public OntProperty getOntProperty() {
		return ontProperty;
	}

	public void setOntProperty(OntProperty ontProperty) {
		this.ontProperty = ontProperty;
	}

	public PropertyOperator() {
	}

	/**
	 * Constructor
	 * 
	 * @param ontProperty
	 *            Lấy OntProperty về để xử lý thông tin
	 */
	public PropertyOperator(OntProperty ontProperty) {
		super();
		this.ontProperty = ontProperty;
	}

	/**
	 * Constructor
	 * 
	 * @param model
	 * @param propertyname
	 *            Lấy OntProperty từ một OntModel theo LocalName để xử lý thông tin
	 */
	public PropertyOperator(OntModel model, String propertyname) {
		super();
		ModelOperator modeloperator = new ModelOperator(model);
		if (modeloperator.getOntPropertyByName(propertyname) != null)
			this.ontProperty = modeloperator.getOntPropertyByName(propertyname);
		
	}

	/**
	 * Lấy ra kiểu của một thuộc tính datatype( VD: string, int, boolean)
	 * 
	 * @return null nếu không phải là thuộc tính datatype. Nếu không, trả về tên kiểu dữ liệu
	 */
	public String getSpecificDataType() {
		if (dataType == null)
		{
			if (this.ontProperty == null || this.ontProperty.isObjectProperty())
				dataType = null;
			else
			{
				OntResource ontResource = ontProperty.getRange();
				if (ontResource != null)
					dataType = ontProperty.getRange().getLocalName();
			}
		}
		
		return dataType;
	}

	/**
	 * Liệt kê danh sách các range có thể của một thuộc tính
	 * 
	 * @return List String các range của một thuộc tính (LocalName)
	 */
	public ArrayList<String> getListPropertyRanges()
	{
		if (listRanges == null)
		{
			listRanges = new ArrayList<String>();
			if (this.ontProperty == null)
				return listRanges;
			if (this.ontProperty.isDatatypeProperty()) return listRanges;
			
			//Ontology chỉ có 1 range duy nhất, thuộc loại Union các Class
			
			ExtendedIterator<? extends OntResource> iter = ontProperty.listRange();
			while( iter.hasNext() ) {
				OntClass tmpOC=(OntClass) iter.next();
				
				if (tmpOC.isUnionClass())
				{
					UnionClass uc= (tmpOC).asUnionClass();
					ExtendedIterator<? extends OntClass> i=uc.listSubClasses(true);
					while (i.hasNext()) {
						OntClass oc = i.next();
						if (!oc.equals(OWL.Nothing))
							listRanges.add(oc.getURI());
					}
				}
				else
				{
					listRanges.add(tmpOC.getURI());
				}
			}
			//Loai bo du thua va sap xep lai
			HashSet hashSet = new HashSet(listRanges);
			listRanges = new ArrayList(hashSet);
			Collections.sort(listRanges);
		}
		
		return listRanges;
		
	}
	
	/**
	 * Lấy Domain của Property
	 * @return trả về BKClass lưu trữ thông tin về OntClass là Domain của Property 
	 */
	public ArrayList<String> getListPropertyDomains()
	{
		if (listDomains == null)
		{
			listDomains = new ArrayList<String>();
			if (this.ontProperty == null)
				return listDomains;
			if (this.ontProperty.isDatatypeProperty()) return listDomains;
			
			//Ontology chỉ có 1 range duy nhất, thuộc loại Union các Class
			
			ExtendedIterator<? extends OntResource> iter = ontProperty.listDomain();
			while( iter.hasNext() ) {
				OntClass tmpOC=(OntClass) iter.next();
				
				if (tmpOC.isUnionClass())
				{
					UnionClass uc= (tmpOC).asUnionClass();
					ExtendedIterator<? extends OntClass> i=uc.listSubClasses(true);
					while (i.hasNext()) {
						OntClass oc = i.next();
						if (!oc.equals(OWL.Nothing))
							listDomains.add(oc.getURI());
					}
				}
				else
				{
					listDomains.add(tmpOC.getURI());
				}
			}
			
			//Loai bo du thua va sap xep lai
			HashSet hashSet = new HashSet(listDomains);
			listDomains = new ArrayList(hashSet);
			Collections.sort(listDomains);
		}
		
		return listDomains;
		
	}
	
	/**
	 * Lấy Domain của Property
	 * @return trả về BKClass lưu trữ thông tin về OntClass là Domain của Property 
	 */
	public BKResource getDomain()
	{
		return new BKResource((ontProperty.getDomain()));
		
	}

	/**
	 * Liệt kê danh sách các subProperty trực tiếp của Ont Property
	 * 
	 * @return Danh sách các đối tượng MyProperty lưu trữ thông tin rút gọn của các subProperty tương ứng
	 */
	public ArrayList<BKProperty> getSubProperties() {
		ArrayList<BKProperty> result = new ArrayList<BKProperty>();
		if (ontProperty != null) {
			ExtendedIterator<? extends OntProperty> tmpIterator = ontProperty.listSubProperties(true);

			while (tmpIterator.hasNext()) {
				OntProperty tmpProperty = tmpIterator.next();
				result.add(new BKProperty(tmpProperty));
			}
		}
		return result;
	}

	/**
	 * Liệt kê danh sách các superProperty trực tiếp của Ont Property
	 * 
	 * @return Danh sách các đối tượng MyProperty lưu trữ thông tin rút gọn của các superProperty tương ứng
	 */
	public ArrayList<BKProperty> getSuperProperties() {
		ArrayList<BKProperty> result = new ArrayList<BKProperty>();
		if (ontProperty != null) {
			ExtendedIterator<? extends OntProperty> tmpIterator = ontProperty.listSuperProperties(true);

			while (tmpIterator.hasNext()) {
				OntProperty tmpProperty = tmpIterator.next();
				result.add(new BKProperty(tmpProperty));
			}
		}
		return result;
	}
}
