package ontologyOperator;

import java.util.ArrayList;

import ontologyDataSructure.*;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.NodeIterator;

/**
 * Lớp lưu trữ giá trị của Object Property của một thể hiện Individual
 * 
 * @author TRUNG
 * 
 */
public class ObjectPropertyValuesOfIndividual {

	ArrayList<BKClass> listOfObjectValues;

	public ArrayList<BKClass> getListOfObjectValues() {
		return listOfObjectValues;
	}

	public void setListOfObjectValues(ArrayList<BKClass> listOfObjectValues) {
		this.listOfObjectValues = listOfObjectValues;
	}

	public ObjectPropertyValuesOfIndividual(Individual individual,
			OntProperty ontproperty) {
		if (ontproperty.isObjectProperty()) {
			NodeIterator tmpIterator = individual.listPropertyValues(ontproperty);
			while (tmpIterator.hasNext()) {
				listOfObjectValues.add(new BKClass((OntClass) tmpIterator.next()));

			}
		}
	}

}
