package nl.vu.cs.simbad.project;

import java.awt.image.BufferedImage;
import java.awt.Color;

import javax.vecmath.Vector3d;
import simbad.sim.Agent;
import simbad.sim.CameraSensor;
import simbad.sim.RangeSensorBelt;
import simbad.sim.RobotFactory;

public class Robot extends Agent {
	
	RangeSensorBelt sonar;
	RangeSensorBelt bumper;
	CameraSensor camera;
	
	
	double translationalVelocity = 0.5;
	double rotationalVelocity = 0;
	double rotationalVelocityFactor = Math.PI / 8;

	public Robot(Vector3d position, String name) {
        super(position, name);
		// TODO Auto-generated constructor stub
        // Add sonar sensor
        sonar = RobotFactory.addSonarBeltSensor(this, 8);
        // Add bumper sensor
        bumper = RobotFactory.addBumperBeltSensor(this, 8);
        // Add camera sensor
        camera = RobotFactory.addCameraSensor(this);
        
       
	}
	
	private void distance() {
		// Get distance from sonar in every 20 frames
		if(getCounter() % 20 == 0) {
			for(int i = 0; i < sonar.getNumSensors(); i++) {
				double range = sonar.getMeasurement(i); 
                boolean hit = sonar.hasHit(i);
                double maxrange = sonar.getMaxRange();
                System.out.println("Sonar: "+ i + " max range: " + maxrange +
                "  measured range: "+range+ "  has hit something: "+hit);
			}
		}
	}
	
	private void hit() {
		// detects hit from bumper in every 20 frames
		if(getCounter() % 20 == 0) {
			for(int i = 0; i < bumper.getNumSensors(); i++) {
				 
	             boolean hit = bumper.hasHit(i);
	             System.out.println("Bumper: " + i 
	                + " has hit something:"+hit);
			}
		}
	}
	private void move() {
		// set speed 0.5 m/s
		this.setTranslationalVelocity(0.5);
		// change the orientation
		if(getCounter() % 100 == 0) {
			this.setRotationalVelocity(Math.PI /8 * (0.5 - Math.random()));
			
		}
	}
	
	private void rotate_right() {
		this.setTranslationalVelocity(-0.5);
		
		this.setRotationalVelocity(-(Math.PI /4));

	}
	
	private void rotate_left() {
		this.setTranslationalVelocity(-0.5);
		this.setRotationalVelocity(Math.PI /4);
	}

	@Override
	protected void initBehavior() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void performBehavior() {
		// TODO Auto-generated method stub
		distance();
		BufferedImage cameraimage = camera.createCompatibleImage();
		
		if ((sonar.hasHit(0) && sonar.hasHit(1)) || (sonar.hasHit(7) && sonar.hasHit(0))) {
		      // reads the three front quadrants
		      double left = sonar.getFrontLeftQuadrantMeasurement();
		      double right = sonar.getFrontRightQuadrantMeasurement();
		      double front = sonar.getFrontQuadrantMeasurement();
		      // if obstacle near
		      if ((front < 0.9) || (left < 0.9) || (right < 0.9)) {  
		    	camera.copyVisionImage(cameraimage);
		    	
		    	Color rgb = new Color (cameraimage.getRGB(50, 50));
		    	System.out.println("The captured image has: RED: " + rgb.getRed() + " GREEN: " + rgb.getGreen() + " BLUE: " + rgb.getBlue() );
		    	
		    	
		    	if (left < right) {
		    		rotate_right();
		    	}
		    	else {
		    		rotate_left();
		    	}
		      }
		      else {
		    	  move();
		      }
		}else if(this.collisionDetected()) {
			hit();
			System.out.println("Collision");
			for (int i=0; i<= 4; i++) {
				if (bumper.hasHit(i)) {
					rotate_right();
				}
			}
			for (int i=5; i<= 8; i++) {
				if (bumper.hasHit(i)) {
					rotate_left();
				}
			}
			
		}else if (this.anOtherAgentIsVeryNear()){
			rotate_right();
			
		}else {
			//System.out.println("No Obstacles");
			//move the robots
			move();
		}
		
	
	}
}
