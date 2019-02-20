package nl.vu.cs.simbad.project;

import javax.vecmath.Vector3d;
import simbad.gui.Simbad;



public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// request antialising so that diagonal lines are not "stairy"
        System.setProperty("j3d.implicitAntialiasing", "true");
        
        // Instance of environment
        Environment environment = new Environment();
 
        // create instances of robot
        Robot robot1 = new Robot(new Vector3d(-5, 0, 5), "Robot-1");
        Robot robot2 = new Robot(new Vector3d(2,  0, 2), "Robot-2");
        Robot robot3 = new Robot(new Vector3d(-2,  0, -2), "Robot-3");
        // Add robots into environment
		environment.add(robot1);
		environment.add(robot2);
		environment.add(robot3);
		
		
 
        //Create instance of simbad simulator
      	Simbad frame = new Simbad(environment, false);
      	frame.update(frame.getGraphics());  
      	
	}

}
