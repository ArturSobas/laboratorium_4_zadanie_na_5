import java.util.TimerTask;

public class SimTask extends TimerTask{

	private SimEngine simengine;
	private SpringApplet springapplet;
	private double krokCzas;
	
	//konstruktor
	
	SimTask(double krokCzas, SpringApplet s, SimEngine e){
		this.krokCzas = krokCzas;
		this.simengine = e;
		this.springapplet = s;
	}
	
	
	@Override
	public void run() {
		springapplet.repaint();
		simengine.symulacja(krokCzas);
		
	}

}
