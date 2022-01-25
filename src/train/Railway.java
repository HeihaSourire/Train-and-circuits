package train;


/**
 * Représentation d'un circuit constitué d'éléments de voie ferrée : gare ou
 * section de voie
 * 
 * @author Fabien Dagnat <fabien.dagnat@imt-atlantique.fr>
 * @author Philippe Tanguy <philippe.tanguy@imt-atlantique.fr>
 */
public class Railway {
	private final Element[] elements;
	private int nbTrainSurLige = 0;
	private Direction takenByDirection = null;

	public Railway(Element[] elements) {
		if(elements == null)
			throw new NullPointerException();
		
		this.elements = elements;
		for (Element e : elements)
			e.setRailway(this);
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		boolean first = true;
		for (Element e : this.elements) {
			if (first)
				first = false;
			else
				result.append("--");
			result.append(e);
		}
		return result.toString();
	}
	
	public Element getElement(int index) {
		if (index >=0 && index < elements.length) {
			return elements[index];
		}
		
		return null;
	}
	
	public int railWayLength() {
		return elements.length;
	}
	
	public boolean invariant(Direction dir) {
		return (nbTrainSurLige == 0) || (takenByDirection == dir);
	}
	
	public void askEnterLigne(Direction dir) {
		nbTrainSurLige++;
		takenByDirection = dir;
	}
	
	public void leaveLigne() {
		nbTrainSurLige--;
		takenByDirection = null;
	}
}
