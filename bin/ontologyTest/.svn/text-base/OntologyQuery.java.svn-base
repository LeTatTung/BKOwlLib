package ontologyTest;

import java.util.ArrayList;

import ontologyManager.OntologyManager;

public class OntologyQuery {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OntologyManager ontManager = new OntologyManager("file:C:/JavaDocumentOntology6.owl");
	
		String queryString="";
		/*queryString ="PREFIX SEC: <http://hut.edu.vn/JavaSourceCodeOntology.owl#> "
					 +"PREFIX DOC: <http://www.semanticweb.org/ontologies/2010/3/JavaDocumentOntology.owl#>"
					 +"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" 
					 +"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
                	 +" PREFIX fn: <http://www.w3.org/2005/xpath-functions#>"
                	 +"PREFIX owl: <http://www.w3.org/2002/07/owl#>" 
                	 +"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"
                	 +"SELECT DISTINCT ?X ?label  ?Y ?labelSource ?created ?content"
                	 +" WHERE"
						+"{"
						+"?X rdf:type DOC:Image."
						+"?Y rdf:type SEC:Method."
						+"?X rdfs:label ?label."
						+"?X DOC:createdAt ?created."
						+"?X DOC:hasTextContent ?content."
						+"?X DOC:hasRelationSource ?Y."
						+"?Y rdfs:label ?labelSource"
						+"}";
		ArrayList<ArrayList<String>> test = ontManager.SparqlResultList(queryString);*/
		
		
		//Test query rule
		
		queryString ="PREFIX SEC: <http://hut.edu.vn/ontology/sourcecode#>  "
					+"PREFIX DOC: <http://hut.edu.vn/ontology/document#>"
					+"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" 
					+"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
					+"PREFIX fn: <http://www.w3.org/2005/xpath-functions#>"
					+"PREFIX owl: <http://www.w3.org/2002/07/owl#>" 
					+"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"
					+"SELECT DISTINCT ?X "
					+" WHERE"
					+"{"
					+"?X  DOC:hasDirectType 'SourceFile'.  "
					+"?X  rdf:type SEC:SourceFile.  "
					+"?X  SEC:hasMethod<http://hut.edu.vn/ontology/sourcecode#R.ttt.ttt.views.ViewLabelProvider.getType3>." 
					
					
					+"}";
		
		
		
		ArrayList<ArrayList<String>> test = ontManager.SparqlResultListLocalName(queryString);
		System.out.println("Pass done");
		
	}

}
