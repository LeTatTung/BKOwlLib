package ontologyDataSructure;

import com.hp.hpl.jena.ontology.OntClass;

public class BKClass extends BKResource {

	/**
	 * Class có subclass hay không ?
	 */
	private boolean hasSubClass;

	/**
	 * Class có superclass hay không ?
	 */
	private boolean hasSuperClass;

	public boolean isHasSubClass() {
		return hasSubClass;
	}

	public void setHasSubClass(boolean hasSubClass) {
		this.hasSubClass = hasSubClass;
	}

	public boolean isHasSuperClass() {
		return hasSuperClass;
	}

	public void setHasSuperClass(boolean hasSuperClass) {
		this.hasSuperClass = hasSuperClass;
	}

	public BKClass() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param ontclass:
	 *            ontclass để lấy thông tin về
	 */
	public BKClass(OntClass ontclass) {
		super(ontclass);
		this.hasSubClass = ontclass.hasSubClass();
		this.hasSuperClass = ontclass.hasSuperClass();
	}
}
