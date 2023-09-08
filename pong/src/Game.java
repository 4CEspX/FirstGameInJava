import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;


public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 1L;

	public static final int WIDTH =640, HEIGHT =640;

	private boolean isRunning = false;
	public Handler handler;
	private Thread thread;
	private HUD hud;
	private Spawner spawner;
	private Random r = new Random();
	public static int Coins = 0;
	public static int Damage = 50;
	public static boolean Pause = false;
	public static boolean Start = false;
	public static boolean Controls = false;

	//metod spel startar spelet
	public Game() {

		//lägger till saker inom spelet
		handler = new Handler();
		spawner = new Spawner(handler,hud);

		this.addKeyListener(new KeyInput(handler));
		new Window(640, 640, "Qubus war", this);

		hud = new HUD();
		handler.addObject(new Player(300,300,ID.Player1,handler));



		//startar metoder
		HUD.timer();
		start();
	}

	//startmetod
	public void start() {
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}

	//slutmetod
	public void stop() {
		isRunning = false;
		try {
			thread.join();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

	//kör metod(stor loop som körs flera tusen gånger per sekund)
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(isRunning){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				delta--;
			}
			render();
			frames++;
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frames = 0;
			}
		}
		stop();
	}

	//metod tick får andra klasser att uppdateras
	public void tick() {
		if (Start == true) {
			if (Pause == false) {
				if (Controls == false) {
					handler.tick();
					hud.tick();
					spawner.tick();
				}
			}
		}
	}

	//metod render får andra klasser att visas
	public void render() {

		//skapar backgrund
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, 640, 640);

			handler.render(g);
			hud.render(g);

		g.dispose();
		bs.show();
	}


	//metod ger max och min värden till olika variabler
	public static int clamp(int var, int min, int max) {
		if (var >= max) {
			return var = max;
		} else if (var <= min) {
			return var = min;
		} else {
			return var;
		}
	}

	//main metod startar spelet när projektet körs
	public static void main(String args[]) {
		new Game();
	}
}
