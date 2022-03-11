package rs.ac.bg.etf.kdp.lockdinningsavages;

public class Test {

	public static void main(String[] args) {
		SharedData data = new SharedData();
		Chef chef = new Chef("Chef",data);
		chef.start();
		for(int i=0;i < 15;i++) {
			Savage s = new Savage("S" + (i+1),data);
			s.start();
		}
	}

}
