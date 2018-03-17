package ontologyDataSructure;

import com.hp.hpl.jena.ontology.Individual;

public class BKIndividual {
	private String localName;
	
	private String URI;
	
	private String enComment;
	
	private String enLabel;
	
	private String vnComment;
	
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
	
	public BKIndividual(){
		
	}
	
	public BKIndividual(Individual individual){
		if (individual != null)
		{
		this.localName = individual.getLocalName();
		this.URI = individual.getURI();
		this.enComment = individual.getComment("en");
		this.vnComment = individual.getComment("vn");
		this.enLabel = individual.getLabel("en");
		this.vnLabel = individual.getLabel("vn");
		}
	}
}
