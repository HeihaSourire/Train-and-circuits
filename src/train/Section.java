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
	public synchronized void depart() {

		this.taken = false;
		notifyAll();
	}
	
	@Override
	public synchronized void askAccess() {

		while(taken == true) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		super.taken = true;
//		notifyAll();
	}
	
}
