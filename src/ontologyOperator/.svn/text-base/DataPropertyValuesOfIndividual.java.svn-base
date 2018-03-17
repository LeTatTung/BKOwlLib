package ontologyOperator;

import java.util.ArrayList;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.NodeIterator;

/**
 * Lớp lưu trữ giá trị của DataProperty của một thể hiện individual
 * 
 * @author TRUNG
 * 
 */
public class DataPropertyValuesOfIndividual {

	/**
	 * Danh sách các giá trị của Property
	 */
	private ArrayList<String> listOfDataValues;

	public ArrayList<String> getListOfDataValues() {
		return listOfDataValues;
	}

	public void setListOfDataValues(ArrayList<String> listOfDataValues) {
		this.listOfDataValues = listOfDataValues;
	}

	/**
	 * Constructor
	 * 
	 * @param individual:Individual
	 *            cần lấy về các giá trị
	 * @param ontproperty:Property
	 *            cụ thể cần lấy về giá trị của Individual
	 */
	public DataPropertyValuesOfIndividual(Individual individual,
			OntProperty ontproperty) {

		if (ontproperty.isDatatypeProperty()) {
			listOfDataValues = new ArrayList<String>();
			NodeIterator tmpIterator = individual.listPropertyValues(ontproperty);
			while (tmpIterator.hasNext()) {
				listOfDataValues.add(((Literal) tmpIterator.next()).toString());
			}
		}
	}
}
