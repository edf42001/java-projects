import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Arrays;
import javax.swing.JPanel;
import javax.swing.Timer;


public class GraphicsPanel extends JPanel implements KeyListener, MouseMotionListener{
	
	private Timer t;							 // The time is used to move objects at a consistent time interval.
	Cube[] cubes;
	double[] camera;
	double[] cameraVels;
	double[] cameraAngles;
	double[] cameraAngleVels;
	double[] cameraSensitivity;
	int[] mouse;
	int[] lastMouse;
	Robot r;
	boolean[] keys;
	int FOV;
	boolean openMenu;
	
	
	public GraphicsPanel(){
		setPreferredSize(new Dimension(800,800));    // Set these dimensions to the width 
		t = new Timer(25, new ClockListener(this));  // t is a time.  This object will call the ClockListener's
		 											 // action performed method every 10 milliseconds once the 
		 											 // time is started.
		t.start();
		this.setFocusable(true);					 // for keylistener
		this.addKeyListener(this);
		this.addMouseMotionListener(this);
		int nc = 15;
		cubes = new Cube[nc*nc];
		for(int i = 0; i<nc; i++){
			for(int j = 0; j<nc; j++){
				cubes[i*nc+j] = new Cube(i*2-nc,j*2-nc,0);
			}
		}
		camera = new double[] {-200,60,128};
		cameraAngles = new double[] {0,0};
		cameraAngleVels = new double[] {0,0};
		cameraSensitivity = new double[] {0.4, 0.38};
		mouse = new int[] {700,450};
		lastMouse = new int[] {700,450};
		keys = new boolean[5];
		cameraVels = new double[3];
		FOV = 70;
		try {
			r = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		r.mouseMove(700, 450);
		openMenu = false;
	}
	
	
	// method: paintComponent
	// description: This method will paint the items onto the graphics panel.  This method is called when the panel is
	//   			first rendered.  It can also be called by this.repaint()
	// parameters: Graphics g - This object is used to draw your images onto the graphics panel.
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, 800, 800);

		g2.setColor(Color.BLACK);
		for(Cube cube: cubes){
			cube.draw(g2);
		}
	}

	// method:clock
	// description: This method is called by the clocklistener every 5 milliseconds.  You should update the coordinates
	//				of one of your characters in this method so that it moves as time changes.  After you update the
	//				coordinates you should repaint the panel.
	public void clock(){
		if(!openMenu){
			double xVel = 0;
			double yVel = 0;
			if(keys[1]){//d
				xVel = -6;
			}else if(keys[3]){//g
				xVel = 6;
			}
			
			if(keys[2]){//f
				yVel = -9;
			}else if(keys[0]){//r
				 yVel = 9;
			}
			
			if(keys[4] && cameraVels[2]==0){
				cameraVels[2] = 22;
			}
			
			
			cameraVels[2]+=-2-cameraVels[2]*0.01;
			camera[2]+=cameraVels[2];
			
			if(camera[2]<=128){
				camera[2] = 128;
				cameraVels[2] = 0;
			}
			
	
		    if(xVel != 0 || yVel != 0){   
		    	//Rotate vel vector by the direction the player is looking
		    	double angle = Math.toRadians(90-cameraAngles[0]);
		        camera[0]-= xVel*Math.cos(angle)-yVel*Math.sin(angle);
		        camera[1]-= xVel*Math.sin(angle)+yVel*Math.cos(angle);
		    }
		    
			Point mouse = MouseInfo.getPointerInfo().getLocation();
			
			this.mouse[0] = (int) mouse.getX();
			this.mouse[1] = (int) mouse.getY();
//			lastMouse = new int[] {700,450};  // If the mouse is fixed to the center of the screen (Seel line 145 for details)
			for(int i = 0; i<2; i++){
			 cameraAngleVels[i] += (this.mouse[i]-lastMouse[i])*cameraSensitivity[i]*(i == 0?1:-1);//invert y direction
				        cameraAngles[i]+=cameraAngleVels[i];
				        cameraAngleVels[i]*=0.2;       
			}
			cameraAngles[1] = Math.max(Math.min(cameraAngles[1], 90), -90);

			for(Cube cube: cubes){
				cube.transform(camera, cameraAngles, FOV);
			}
			Arrays.sort(cubes);
			
			for(int i = 0; i<2; i++){
				lastMouse[i] = this.mouse[i];
			}
			this.repaint();

			// I originally used this robot to move the mouse to the center of the screen, keeping it in a fixed position
			// This doesn't seem to work on my version of linux, so I've disabled it
//			r.mouseMove(700, 450);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
		case KeyEvent.VK_W:
			keys[0] = true;
			break;
		case KeyEvent.VK_A:
			keys[1] = true;
			break;
		case KeyEvent.VK_S:
			keys[2] = true;
			break;
		case KeyEvent.VK_D:
			keys[3] = true;
			break;
		case KeyEvent.VK_SPACE:
			keys[4] = true;
			break;
		case KeyEvent.VK_SHIFT:
			break;
		case KeyEvent.VK_ESCAPE:
			openMenu = !openMenu;
			break;
		default:
			break;
		}
	}
		
	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()){
		case KeyEvent.VK_W:
			keys[0] = false;
			break;
		case KeyEvent.VK_A:
			keys[1] = false;
			break;
		case KeyEvent.VK_S:
			keys[2] = false;
			break;
		case KeyEvent.VK_D:
			keys[3] = false;
			break;
		case KeyEvent.VK_SPACE:
			keys[4] = false;
			break;
		case KeyEvent.VK_SHIFT:
			break;
		default:
			break;
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}
}
