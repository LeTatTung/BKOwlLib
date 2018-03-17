package ontologyDataSructure;

import com.hp.hpl.jena.ontology.OntProperty;

/**
 * Lớp lưu trữ các thông tin rút gọn cho một Ont Property
 * 
 * @author TRUNG
 * 
 */
public class BKProperty extends BKResource {

	/**
	 * Property có phải là DatatypeProperty không?
	 */
	private boolean DatatypeProperty;

	/**
	 * Property có phải là ObjectProperty không?
	 */
	private boolean ObjectProperty;

	/**
	 * Property có sub Property không?
	 */
	private boolean hasSubProperty;

	/**
	 * Property có super Property không?
	 */
	private boolean hasSuperProperty;

	public boolean isDatatypeProperty() {
		return DatatypeProperty;
	}

	public void setDatatypeProperty(boolean datatypeProperty) {
		DatatypeProperty = datatypeProperty;
	}

	public boolean isObjectProperty() {
		return ObjectProperty;
	}

	public boolean isHasSubProperty() {
		return hasSubProperty;
	}

	public void setHasSubProperty(boolean hasSubProperty) {
		this.hasSubProperty = hasSubProperty;
	}

	public boolean isHasSuperProperty() {
		return hasSuperProperty;
	}

	public void setHasSuperProperty(boolean hasSuperProperty) {
		this.hasSuperProperty = hasSuperProperty;
	}

	public void setObjectProperty(boolean objectProperty) {
		ObjectProperty = objectProperty;
	}

	public BKProperty() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param ontproperty:OntProperty
	 *            để lấy thông tin về
	 */
	public BKProperty(OntProperty ontproperty) {
		super(ontproperty);
		this.DatatypeProperty = ontproperty.isDatatypeProperty();
		this.ObjectProperty = ontproperty.isObjectProperty();
		if (ontproperty.listSubProperties(true).toList().isEmpty()) 
		{
			this.hasSubProperty = false;

		} 
		else
			this.hasSubProperty = true;

		if (ontproperty.listSuperProperties(true).toList().isEmpty()) 
		{
			this.hasSuperProperty = false;
		} 
		else
			this.hasSuperProperty = true;

	}
}
