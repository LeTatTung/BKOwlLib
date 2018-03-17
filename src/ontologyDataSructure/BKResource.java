package ontologyDataSructure;

import com.hp.hpl.jena.ontology.OntResource;

/**
 * Lớp lưu trữ các thông tin rút gọn cho OntResource
 * 
 * @author TRUNG
 * 
 */
public class BKResource {

	/**
	 * Tên của OntResource
	 */
	private String localName;
	/**
	 * URI của OntResource
	 */
	private String URI;
	/**
	 * Comment tiếng Anh của OntResource
	 */
	private String enComment;
	/**
	 * Label tiếng Anh của OntResource
	 */
	private String enLabel;
	/**
	 * Comment tiếng Việt của OntResource
	 */
	private String vnComment;
	/**
	 * Label tiếng Việt của OntResource
	 */
	private String vnLabel;

	public String getLocalName() {
		return localName;
	}

	public void setLocalName(String localName) {
		this.localName = localName;
	}

	public String getURI() {
		return URI;
	}

	public void setURI(String uri) {
		URI = uri;
	}

	public String getEnComment() {
		return enComment;
	}

	public void setEnComment(String enComment) {
		this.enComment = enComment;
	}

	public String getEnLabel() {
		return enLabel;
	}

	public void setEnLabel(String enLabel) {
		this.enLabel = enLabel;
	}

	public String getVnComment() {
		return vnComment;
	}

	public void setVnComment(String vnComment) {
		this.vnComment = vnComment;
	}

	public String getVnLabel() {
		return vnLabel;
	}

	public void setVnLabel(String vnLabel) {
		this.vnLabel = vnLabel;
	}

	public BKResource() {

	}

	/**
	 * Constructor
	 * 
	 * @param resource:OntResource
	 *            gốc để lấy thông tin rút gọn về
	 */
	public BKResource(OntResource resource) {
		if (resource != null)
		{
		this.localName = resource.getLocalName();
		this.URI = resource.getURI();
		this.enComment = resource.getComment("en");
		this.vnComment = resource.getComment("vn");
		this.enLabel = resource.getLabel("en");
		this.vnLabel = resource.getLabel("vn");
		}
	}

}
