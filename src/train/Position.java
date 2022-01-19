package train;

/**
 * Représentation de la position d'un train dans le circuit. Une position
 * est caractérisée par deux valeurs :
 * <ol>
 *   <li>
 *     L'élément où se positionne le train : une gare (classe  {@link Station})
 *     ou une section de voie ferrée (classe {@link Section}).
 *   </li>
 *   <li>
 *     La direction qu'il prend (enumération {@link Direction}) : de gauche à
 *     droite ou de droite à gauche.
 *   </li>
 * </ol>
 * @author Fabien Dagnat <fabien.dagnat@imt-atlantique.fr> Modifié par Mayte
 *         Segarra 
 * @author Philippe Tanguy <philippe.tanguy@imt-atlantique.fr>
 *         
 * @version 0.3
 */
public class Position implements Cloneable {
	private Direction direction;
	private Element pos;
	private int posIndex;

	public Position(Element elt, Direction d) {
		if (elt == null || d == null)
			throw new NullPointerException();

		pos = elt;
		direction = d;
		if (direction == Direction.LR) {
			posIndex = 0;
		} else if (direction == Direction.RL){
			posIndex = pos.railway.railWayLength() - 1;
		}
		
	}

	@Override
	public Position clone() {
		try {
			return (Position) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void initialStation() {
		this.pos.addNbTrain();
	}
	
	public Element getPos() {
		return pos;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder(this.pos.toString());
		result.append(" going ");
		result.append(this.direction);
		return result.toString();
	}
	
	public boolean moveNext(String trainName) {
		
		Element nextElement = null;
		
		if (direction == Direction.LR) {
			nextElement = pos.railway.getElement(posIndex + 1);
		} else if (direction == Direction.RL){
			nextElement = pos.railway.getElement(posIndex - 1);
		}
		
		pos.depart(trainName, nextElement);
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (direction == Direction.LR) {
			posIndex++;
		} else if (direction == Direction.RL){
			posIndex--;
		}
		
		pos = nextElement;
//		pos.arrive(trainName);
		
		if (pos.isArrive()) {
			switch (direction) {
				case LR :
					direction = direction.RL;
					break;
				case RL :
					direction = direction.LR;
					break;
			}
			
			return true;
		}
		
		return false;
	}
	
	
}
