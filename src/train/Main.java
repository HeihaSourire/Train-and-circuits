package train;

/**
 * @author Fabien Dagnat <fabien.dagnat@imt-atlantique.fr>
 */
public class Main {
	public static void main(String[] args) {
		Station A = new Station("GareA", 3);
		Station D = new Station("GareD", 3);
		Section AB = new Section("AB");
		Section BC = new Section("BC");
		Section CD = new Section("CD");
		Railway r = new Railway(new Element[] { A, AB, BC, CD, D });
		System.out.println("The railway is:");
		System.out.println("\t" + r);
		Position pLR = new Position(A, Direction.LR);
		Position pRL = new Position(D, Direction.RL);
		try {
			Train train1 = new Train("1", pLR);
			Thread t1 = new Thread(train1);
			Train train2 = new Train("2", pRL);
			Thread t2 = new Thread(train2);
			
			Train train3 = new Train("3", pLR);
			Thread t3 = new Thread(train3);
			System.out.println(train1);
			System.out.println(train2);
			System.out.println(train3);
				
			t1.start();
			t2.start();
			t3.start();

			
			
		} catch (BadPositionForTrainException e) {
			System.out.println("Le train " + e.getMessage());
		}
		

	}
}
