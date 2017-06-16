/** Tank class
 *  created by Amar Molinas
 *	
 *	This class stores date for the individual tank objects for Tank Battle
 *	by Amar Molinas.
 */
package tankbattle;

import java.awt.Polygon.*;


 public class Tank{
 
    //private variables
    private String name		= "";	//name of tank
    private int armor		= 0; 	//tank armor

    //tank position variables
    /** The GunPower variables correspond the the x and y components of the
     *	triangle described by the gun barrel and it's angle with respect to
     *	the center of the turret. When the gun is fired the values of these 
     *	variables are transferred to the xVelocity and yVelocity variables
     *	so that the shot can be fired independent of the */
    private int[] gunPower = new int[2];

    /** The angle of the gun barrel, this also controls the amplitude
     * 	of the x an y components of the shot */
    private double angle = 0; 	

    /** A two dimensional array defines the positions of the end
     *	points of the gun. */
    private int[][] gun = {{0,0},{0,0}}; 

    /** center point of the turret */
    private int[] turret = new int [] {0,0};
    private int[][] hull = new int [][] {{0,0,0,0},{0,0,0,0}};

    /** default constructor */
    public Tank(){
    }

    /** constructor with arguments */
    public Tank(String name, int armor, double angle, int[][] gun,
        int[] turret, int[][] hull){
        this.name = name;
        this.armor = armor;
        this.angle = angle;
        this.gun = gun;
        this.turret = turret;
        this.hull = hull;	
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

    public int[] getTurret(){
            return turret;
    }

    public int[][] getHull(){
            return hull;
    }

    /** mutators */
    public void setName(String name){
            this.name = name;
    }

    public void setArmor(int armor){
            this.armor = armor;
    }

    public void setAngle(double angle){
            this.angle = angle;
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
 }