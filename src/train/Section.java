package train;

/**
 * Représentation d'une section de voie ferrée. C'est une sous-classe de la
 * classe {@link Element}.
 *
 * @author Fabien Dagnat <fabien.dagnat@imt-atlantique.fr>
 * @author Philippe Tanguy <philippe.tanguy@imt-atlantique.fr>
 */
public class Section extends Element {
	
	
	public Section(String name) {
		super(name);
	}

	@Override
	public boolean isArrive() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public synchronized void arrive(String trainName) {
		// TODO Auto-generated method stub
//		while(taken) {
//			try {
//				wait();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		
		System.out.println(trainName + " left " + name);
		this.taken = false;
		notifyAll();
	}
	
	@Override
	public synchronized void depart(String trainName) {
		// TODO Auto-generated method stub
//		System.out.println(trainName + " is in " + name);
//		while (nextElement.taken) {
//			try {
//				wait();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//			}
//		}
//		nextElement.taken = true;
//		super.taken = false;
//		System.out.println(trainName + " from " + name + " to " + nextElement.toString());
//		notifyAll();
		while(taken == true) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println(trainName + " want to go " + name);
		super.taken = true;
//		notifyAll();
	}
	
}
