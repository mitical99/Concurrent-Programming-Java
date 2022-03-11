package rs.ac.bg.etf.kdp.barrierh20;

public class Test {

	public static void main(String[] args) {
		SharedData data = new SharedData();
		for (int i = 0; i < 10; i++) {
			Hydrogen h = new Hydrogen("H" + (i + 1), data);
			h.start();
		}
		for (int i = 0; i < 2; i++) {
			Oxygen o = new Oxygen("O" + (i+1),data);
			o.start();
		}
		for(int i=0;i<6;i++) {
			Hydrogen h = new Hydrogen("H" + (i + 11), data);
			h.start();
		}
		for (int i = 0; i < 4; i++) {
			Oxygen o = new Oxygen("O" + (i+11),data);
			o.start();
		}
	}

}
