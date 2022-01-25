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
	
	public Element askAccess() {
		Element nextElement = null;
		
		if (direction == Direction.LR) {
			posIndex++;
			nextElement = pos.railway.getElement(posIndex);
		} else if (direction == Direction.RL){
			posIndex--;
			nextElement = pos.railway.getElement(posIndex);
		}
		
		nextElement.askAccess(pos, direction);
		return nextElement;
	}
	
	public boolean moveNext(String trainName) {
		
//		System.out.println(trainName + " is in" + pos.name);
		
		Element nextElement = askAccess();
		System.out.println(trainName + " leaves " + pos.name + " to go to " + nextElement.name);
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		pos.depart(nextElement);
		pos = nextElement;
		System.out.println(trainName + " arrive at " + pos.name);
		
//		if (direction == Direction.LR) {
//			posIndex++;
//		} else if (direction == Direction.RL){
//			posIndex--;
//		}
		
		if (pos.isStation()) {
			switch (direction) {
				case LR :
					direction = direction.RL;
					break;
				case RL :
					direction = direction.LR;
					break;
			}
			System.out.println(trainName + " se retourne");
			return true;
		}
		
		return false;
	}
	
	
}
