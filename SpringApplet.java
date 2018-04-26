import java.awt.Graphics;
import java.awt.Color;
import java.util.Timer;


import javax.swing.JApplet;

public class SpringApplet extends JApplet {
	
	private SimEngine simengine;
	private SimTask simtask;
	private Timer timer;
	
	@Override
	public void init() {
		setSize(1366,662);
		simengine = new SimEngine(10, 10, 0.707, 250, new Vector2D(-10,300), new Vector2D(100,-100), new Vector2D(10,-40));
		simtask = new SimTask(0.1,this,simengine);
		timer = new Timer();
		timer.scheduleAtFixedRate(simtask, 1, 30);
	}

	@Override
	public void paint(Graphics g) {
		//czyszczenie okna
		g.clearRect(0, 0, getWidth(), getHeight());
		
		g.setColor(Color.LIGHT_GRAY);
		//rysowanie siatki
		int e = 30;
		int d = (int)(getWidth()* 1.0/e);
		for(int i = 0;i<=d;i++) {
			g.drawLine(i*d, 0, i*d, getHeight());
			g.drawLine(0, i*d, getWidth(), i*d);
		}
		
		
		
		g.translate(getWidth()/2, getHeight()/2);
	

		int xo = (int)simengine.getPunktZawieszenia().x;
		int yo = -(int)simengine.getPunktZawieszenia().y;
		
		g.translate(xo, yo);
		
		g.setColor(Color.ORANGE);
		//rysowanie punktu zawieszenia
		g.drawLine(-15, 0, 15, 0);
		for(int i = 0; i < 6;i++) {
			g.drawLine(-15 + i*5, 0, -10 + i*5, -5);
		}
		
		g.setColor(Color.BLACK);
		//rysowanie sprezyny oraz masy
		Vector2D ks = simengine.getPolozenie().normalizuj();
		
		int b = 41;
		int c = 5;
		
		Vector2D kw = simengine.getPolozenie().mnozPrzezStala(1.0/b);
		
		for(int i = 0;i <= b;i++) {
			if(i%2 == 0) {
				
				g.drawLine(	(int)(-(kw.x*(i)+c)*ks.y - kw.y*(i)*ks.x),
						-(int)((kw.x*(i)+c)*ks.x - kw.y*(i)*ks.y),
						(int)(-(kw.x*(i+1)-c)*ks.y - kw.y*(i+1)*ks.x),
						-(int)((kw.x*(i+1)-c)*ks.x - kw.y*(i+1)*ks.y) );
			
				if(i == b) {
					int xx = (int)(-(kw.x*(i+1)-c)*ks.y - kw.y*(i+1)*ks.x);
					int yy = -(int)((kw.x*(i+1)-c)*ks.x - kw.y*(i+1)*ks.y);
					g.setColor(Color.PINK);
					g.fillOval(xx-c,yy, 20, 20);
					
					g.setColor(Color.BLACK);
					g.drawString("m",xx,yy+14);
					
					xx = xx + c;
					yy = yy + 10;
					simengine.getsTlumienia().rysujWektor(g,Color.GREEN,xx,yy,"sila tlumienia");
					simengine.getsGrawitacji().rysujWektor(g,Color.BLUE,xx,yy,"sila grawitacji");
					simengine.getsSprezystosci().rysujWektor(g,Color.RED,xx,yy,"sila sprezystosci");
					simengine.getsWypadkowa().rysujWektor(g,Color.YELLOW,xx,yy,"sila wypadkowa");
				}
			
			}else if(!(i%2 == 0)){
			
				g.drawLine(	(int)(-(kw.x*(i+1)+c)*ks.y - kw.y*(i+1)*ks.x),
						-(int)((kw.x*(i+1)+c)*ks.x - kw.y*(i+1)*ks.y),
						(int)(-(kw.x*(i)-c)*ks.y - kw.y*(i)*ks.x),
						-(int)((kw.x*(i)-c)*ks.x - kw.y*(i)*ks.y) );
			
				if(i == b) {
					int xx = (int)(-(kw.x*(i)-c)*ks.y - kw.y*(i)*ks.x); 
					int yy = -(int)((kw.x*(i)-c)*ks.x - kw.y*(i)*ks.y);		
							
					g.setColor(Color.CYAN);
					g.fillOval(xx-c, yy, 20, 20);
					
					g.setColor(Color.BLACK);
					g.drawString("m", xx, yy+14);
					
					xx = xx + c;
					yy = yy + 10;
					simengine.getsTlumienia().rysujWektor(g,Color.GREEN,xx,yy,"sila tlumienia");
					simengine.getsGrawitacji().rysujWektor(g,Color.BLUE,xx,yy,"sila grawitacji");
					simengine.getsSprezystosci().rysujWektor(g,Color.RED,xx,yy,"sila sprezystosci");
					simengine.getsWypadkowa().rysujWektor(g,Color.YELLOW,xx,yy,"sila wypadkowa");
				}
			}
			
		}
	
	
	
	
	}
}