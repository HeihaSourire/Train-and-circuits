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
	public boolean isArrive() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public void addNbTrain() {
		// TODO Auto-generated method stub
		super.nbTrain++;
	}
	
	@Override
	public synchronized void arrive(String trainName) {
		// TODO Auto-generated method stub
		while(nbTrain > size) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(trainName + " arrive " + name);
		super.nbTrain++;
	}
	
	@Override
	public synchronized void depart(String trainName, Element nextElement) {
		// TODO Auto-generated method stub
		while (nextElement.taken == true) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		nextElement.taken = true;
		super.nbTrain--;
		System.out.println(trainName + " depart " + name);
		notifyAll();
	}
}
