package tankbattle;

/** Shot class
 *  created by Amar Molinas
 *	
 *	This class stores data about the projectile fired by
 *	the tank.
 */
 
 public class Shot{
	
	/** variables */
	String type = "";
	int[] position = new int[2];
	int[] velocity = new int[2];
	
	/** constructors */
	public Shot(){
	}
	
	/** constructors */
	public Shot(String type, int[] position, int[] velocity){
		this.type = type;
		this.position = position;
		this.velocity = velocity;
	}
	
	/** accessors */
	public String getType(){
		return type;
	}
	
	/** mutators */
	public void setType(String type){
		this.type = type;
	}
	
 }