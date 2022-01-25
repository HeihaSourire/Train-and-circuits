package train;

/**
 * Représentation d'une gare. C'est une sous-classe de la classe {@link Element}.
 * Une gare est caractérisée par un nom et un nombre de quais (donc de trains
 * qu'elle est susceptible d'accueillir à un instant donné).
 * 
 * @author Fabien Dagnat <fabien.dagnat@imt-atlantique.fr>
 * @author Philippe Tanguy <philippe.tanguy@imt-atlantique.fr>
 */
public class Station extends Element {
	private final int size;

	public Station(String name, int size) {
		super(name);
		if(name == null || size <=0)
			throw new NullPointerException();
		this.size = size;
	}

	@Override
	public boolean isStation() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public void addNbTrain() {
		// TODO Auto-generated method stub
		super.nbTrain++;
	}
	
	public boolean invariant() {
		return nbTrain <= size;
	}
	
	@Override
	public synchronized void depart(Element posNext) {
		// TODO Auto-generated method stub

		super.nbTrain--;
		notifyAll();
	}
	
	@Override
	public synchronized void askAccess(Element posPrev, Direction dir) {
		// TODO Auto-generated method stub
		while (!invariant()) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		super.nbTrain++;

//		notifyAll();
	}
}
