package rs.ac.bg.etf.kdp.onelinebridge;


public class Car {
	protected Bridge bridge;
	int id;
	public Car(Bridge bridge,int id) {
		this.bridge=bridge;
		this.id=id;
	}
	public void crossing() {
		try {
			Thread.sleep((int)(Math.random()*2500));
		} catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	public void starting() {
		try {
			Thread.sleep((int)(Math.random() * 2500));
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}
