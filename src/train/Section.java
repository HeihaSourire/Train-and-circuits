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
	public boolean isStation() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean invariant() {
		return taken == false;
	}
	
	@Override
	public synchronized void depart(Element posNext) {
		if(posNext.isStation()) {
			railway.leaveLigne();
		}
		
		this.taken = false;
		notifyAll();
	}
	
	@Override
	public synchronized void askAccess(Element posPrev, Direction dir) {
		//Pour Station -> Section, il faut déterminer si il n'y a pas l'interblocage
		if(posPrev.isStation()) {
			while(!railway.invariant(dir)) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			railway.askEnterLigne(dir);
		}

		while(!invariant()) {
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
