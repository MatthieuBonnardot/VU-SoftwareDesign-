package nl.vu.cs.simbad.project;

import java.awt.Color;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import simbad.sim.Arch;
import simbad.sim.Box;
import simbad.sim.EnvironmentDescription;
import simbad.sim.Wall;



public class Environment  extends EnvironmentDescription{
	//Constructor
	public Environment() {
		// turn on the lights
        this.light1IsOn = true;
        this.light2IsOn = true;
        
        // enable the physics engine in order to have better physics effects on the objects
        this.setUsePhysics(true);
        
        // show the axes so that we know where things are
        this.showAxis(true);
        
        // Set the world size
        this.setWorldSize(25);
        
        
        
        Wall w1 = new Wall(new Vector3d(-12, 0, 0), 24, 2, this);
        w1.setColor(new Color3f(Color.GREEN));
        w1.rotate90(1);
        add(w1);
        
        Wall w2 = new Wall(new Vector3d(12, 0, 0), 24, 2, this);
        w2.setColor(new Color3f(Color.BLUE));
        w2.rotate90(1);
        add(w2);
        
        Wall w3 = new Wall(new Vector3d(0, 0, 12), 24, 2, this);
        w3.setColor(new Color3f(Color.CYAN));
        //w1.rotate90(1);
        add(w3);
        
        Wall w4 = new Wall(new Vector3d(0, 0, -12), 24, 2, this);
        w4.setColor(new Color3f(Color.RED));
        //w4.rotate90(1);
        add(w4);
        
        Box b1 = new Box(new Vector3d(-3, 0 , 3), new Vector3f(1, 1, 1), this);
        b1.setColor(new Color3f(Color.RED));
        add(b1);
        
        Box b2 = new Box(new Vector3d(3, 0 , -3), new Vector3f(1, 1, 1), this);
        b2.setColor(new Color3f(Color.ORANGE));
        add(b2);
        
        Box b3 = new Box(new Vector3d(0, 0, 0), new Vector3f(2, 2, 2), this);
        b3.setColor(new Color3f(Color.BLACK));
        add(b3);
        
        Box b4 = new Box(new Vector3d(7, 0, -6), new Vector3f(2, 2, 2), this);
        b4.setColor(new Color3f(Color.PINK));
        add(b4);
        
        Box b5 = new Box(new Vector3d(-7, 0, -7), new Vector3f(1, 1, 1), this);
        b5.setColor(new Color3f(Color.YELLOW));
        add(b5);
        
        Box b6 = new Box(new Vector3d(9, 0, 3), new Vector3f(1, 2, 1), this);
        b6.setColor(new Color3f(Color.GREEN));
        add(b6);
        
        Box b7 = new Box(new Vector3d(-10, 0, 5), new Vector3f(2, 2, 2), this);
        b7.setColor(new Color3f(Color.BLUE));
        add(b7);
        
        
        Wall interior = new Wall(new Vector3d(-2, 0, -6), 3, 1, this);
        interior.setColor(new Color3f(Color.MAGENTA));
        interior.rotateY(-1);
        add(interior);
        
        Arch arch = new Arch(new Vector3d(6, 0, 6), this);
        arch.rotateY(2);
        add(arch);
        
        
	}
}
