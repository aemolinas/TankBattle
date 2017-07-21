/** Tank class
 *  created by Amar Molinas
 *	
 *	This class stores date for the individual tank objects for Tank Battle
 *	by Amar Molinas.
 */
package tankbattle;

import java.awt.Graphics;
import java.awt.Polygon;
import javax.swing.JPanel;
//import java.awt.Polygon.*;


 public class Tank extends JPanel{
 
    //private variables
    private String name	= "Default";	//name of tank
    private static int numberOfTanks = 0;
    private int tankID = 0;
    private int armor = 0; 	//tank armor

    //tank position variables
    /** The GunPower variables correspond the the x and y components of the
     *	triangle described by the gun barrel and it's angle with respect to
     *	the center of the turret. When the gun is fired the values of these 
     *	variables are transferred to the xVelocity and yVelocity variables
     *	so that the shot can be fired independent of the */
    private int[] gunPower = new int[2];

    /** The angle of the gun barrel, this also controls the amplitude
     * 	of the x an y components of the shot */
    private double angle = 45; 
    
    /** A one dimensional array defines the position of the shot before
     * it is fired */
    private int[] shot = {25,-30};

    /** A two dimensional array defines the positions of the end
     *	points of the gun. */
    private int[][] gunShape = {{20,-10},{41,-22}};
    private int[][] gun = {{20,-10},{41,-22}};
    /** center point of the turret */
    private int[] turretShape = new int [] {10,-20};
    private int[] turret = {0,0};
    private int[][] hullShape = new int [][] {{0,0},{5,-10},{40,-10},{35,0}};
    private int[][] hull = new int[][]{{0,0},{0,0},{0,0},{0,0}};
    private Polygon hullPoly = new Polygon();
    
    private int[] position = new int[] {0,0};

    /** default constructor */
    public Tank(){
        numberOfTanks++;
        tankID = numberOfTanks;
        if(tankID % 2 == 0){
            hullShape[0][0] += 5;
            hullShape[1][0] -= 5;
            hullShape[2][0] -= 5;
            hullShape[3][0] += 5;
            angle = angle + 90;
        }
    }

    public Tank (String playerName, int[] position){
        this.name = playerName;
        this.armor = 4;
        this.position = position;
        numberOfTanks++;
        tankID = numberOfTanks;
        if(tankID % 2 == 0){
            hullShape[0][0] += 5;
            hullShape[1][0] -= 5;
            hullShape[2][0] -= 5;
            hullShape[3][0] += 5;
            angle = angle + 90;
        }
    }
    
    public Tank (String playerName, int armor, double angle,
        int[] position){
        this.name = playerName;
        this.armor = armor;
        this.angle = angle;
        this.position = position;
        numberOfTanks++;
        tankID = numberOfTanks;
        if(tankID % 2 == 0){
            hullShape[0][0] += 5;
            hullShape[1][0] -= 5;
            hullShape[2][0] -= 5;
            hullShape[3][0] += 5;
            angle = angle + 90;
        }
    }

    /** constructor with all arguments */
    public Tank(String name, int armor, double angle, int[][] gun,
        int[] turret, int[][] hull, int[]shot, int[] position){
        this.name = name;
        this.armor = armor;
        this.angle = angle;
        this.gun = gun;
        this.turret = turret;
        this.hull = hull;
        this.shot = shot;
        this.position = position;
        numberOfTanks++;
        tankID = numberOfTanks;
        if(tankID % 2 == 0){
            hullShape[0][0] += 5;
            hullShape[1][0] -= 5;
            hullShape[2][0] -= 5;
            hullShape[3][0] += 5;
            angle = angle + 270;
        }
    }

    /** accessors */
    public String getName(){
            return name;
    }

    public int getArmor(){
            return armor;
    }

    public double getAngle(){
            return angle;
    }

    public int[][] getGun(){
            return gun;
    }
    public int[][] getGunShape(){
            return gunShape;
    }

    public int[] getTurret(){
            return turret;
    }

    public int[][] getHull(){
            return hull;
    }
    public int[] getShot(){
        return shot;
    }
    public Polygon getHullPoly(){
            return hullPoly;
    }
    public int[] getPosition(){
        return position;
    }

    /** mutators */
    public void setName(String name){
            this.name = name;
    }
    
    public void setArmor(int armor){
            this.armor = armor;
    }

    public void setAngle(double angle){
            this.angle = angle % 360;
    }

    public void setGun(int[][] gun){
            this.gun = gun;
    }

    public void setTurret(int[] turret){
            this.turret = turret;
    }

    public void setHull(int[][] hull){
            this.hull = hull;
    }	
    
    public void setShot(int[] shot){
        this.shot = shot;
    }
    
    public void setHullPoly(int hull[][]){
        for(int i = 0; i < hull.length; i++){
            hullPoly.addPoint(hull[i][0],hull[i][1]);
        }
    }
    
    //effector
    public void setPosition(int[] position){
        this.position = position;
        for (int i = 0; i < turret.length; i++){
            turret[i] = position[i] + turretShape[i];
        }
        
        
        for (int i = 0; i < gun.length; i++){
            gun[i][0] = position[0] + gunShape[i][0];
            gun[i][1] = position[1] + gunShape[i][1];
        }    
        setGunPosition(angle);
            
        for (int i = 0; i < hull.length; i++){
            hull[i][0] = position[0] + hullShape[i][0];
            hull[i][1] = position[1] + hullShape[i][1];
        }        
        setHullPoly(hull);
        shot[0] = gun[0][0]; shot[1] = gun[0][1];
    }
    
    public void setGunPosition(double angle){
        gunShape[1][0] = (int)(gun[0][0] - position[0]
            + 25 * Math.cos(angle * (2 * Math.PI/360)));
        gunShape[1][1] = (int)(gun[0][1] - position[1]
            - 25 * Math.sin(angle * (2 * Math.PI/360))); 
        gun[1][0] = position[0] + gunShape[1][0] ;
        gun[1][1] = position[1] + gunShape[1][1];
    }
    
    public void raiseGun(){
         setGunPosition(angle =  ++angle % 360); 
    }
    
    public void lowerGun(){
        setGunPosition(angle = --angle % 360);
    }
    
    
    public int returnTankID(){
        return tankID;
    }
    
    public static int returnNumberOfTanks(){
            return numberOfTanks;
    }

    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.fillPolygon(getHullPoly());
    }
    
    public void paintHull(Graphics g){
        g.fillPolygon(getHullPoly());
    }
 }