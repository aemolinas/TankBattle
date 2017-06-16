/*
 * The mail class for the TankBattle game
 */
package tankbattle;
/**
 *
 * Created by Amar Molinas
 */
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Polygon.*;
import java.net.URL;
import java.util.Locale;
import javax.swing.*;
import javax.swing.event.*;


class TankBattlePanel extends JPanel implements Runnable{
	
    //variables
    /** consider making this an object**/
    private boolean playGame = false;
    /** consider making this an object**/
    private String player1 = "Player 1"; // blue player
    private String player2 = "Player 2"; // red player

    // if  turn is false player1's turn true player2's turn
    private boolean turn = false; /*make part of panel object*/

    private int tank1Armor, tank2Armor; //When armor = 0 player loses. 

    private boolean xHitObject = false; // used to detect if shot hit frame
    private boolean yHitObject = false; // used to detect if shot hit frame

    private Dimension panelSize = null; // the Dimensions of the panel
    private Thread gameThread = null; // declares a thread to run the game

    /*Now Defined by Tank.getAngle()*/
    private double theta1 = 0;// the angle of the turret of tank1
    private double theta2 = 0;// the angle of the turret of tank2

    /*Now Defined by Tank.getTurret(), Tanks.getHull(), Tank.getGun()*/
    //defines the starting  of tank1
    private int xTurret1 = 10, yTurret1 = 580;
    private int[] xGun1 = {20, 25};
    private int[] yGun1 = {590, 570};
    private int[] xTank1 = {0, 5, 40, 35};
    private int[] yTank1 = {600, 590, 690, 500};
    
    /*int[] turret1 = {10, 580};
    int[][] gun1 = {{20, 25}, {590, 570}};
    int[][] tank1 = {{0,5, 40, 35}, {600, 590, 690, 500}};*/

    //defines the starting position of tank2
    private int xTurret2 = 770, yTurret2 = 780;
    private int[] xGun2 = {780, 780};
    private int[] yGun2 = {790,570};
    private int[] xTank2 = {800,795, 760, 765};
    private int[] yTank2 = {600, 590, 590, 600};
    /*int[] turret2 = {770, 780};
    int[][] gun2 = {{780, 780}, {790, 570}};
    int[][] tank2 = {{800, 795, 760, 765}, {600, 590, 590, 600}};*/

    //defines the position of the barrier in the center
    private int[] xBarrier = {360, 380, 420, 440};
    private int[] yBarrier = {600, 525, 525, 600};

    //player 1 shot position before it is fired
    private int x1Position = xGun1[0]-5, y1Position = yGun1[0];

    //player 2 shot postition before it is fired
    private int x2Position = xGun2[0], y2Position = yGun2[0]; 

    /*Now Defined by Shot.getAngle() Shot.getVelocity(?*/
    // set the rate of change of x and y when thread wakes
    private int x1Velocity = 0, y1Velocity = 25; 
    private int x2Velocity = 0, y2Velocity = 25; 
    private int x1GunAngle = 0, y1GunAngle = 25; 
    private int x2GunAngle = 0, y2GunAngle = 25; 

    private int gravity = 0; //pull of gravity on the shot in the y direction

    private boolean shoot = false; //when shoot is false player not shot yet

    public TankBattlePanel(){

        setBackground(Color.black);
        setForeground(Color.magenta);
        addKeyListener(new TankBattlePanel.tankKeyListener());
        Tank tank1 = new Tank();
        Tank tank2 = new Tank();


    }//Close TankBattlePanel()	

    @Override
    public void run(){ //overrides the run method of Runnable interface
        try{
            while(true){
                repaint();
                Thread.sleep(40);
                System.out.println(turn + "thread");// debugging code
            if(playGame == false){
                    turn = false;
                    shoot = false;
                    tank1Armor = 4;
                    tank2Armor = 4;
                    xHitObject = false;
                    yHitObject = false;
                    x1GunAngle = 0; y1GunAngle = 25;
                    x2GunAngle = 0;y2GunAngle = 25;
                    gravity = 0;
                    theta1 = 0;
                    theta2 = 0;
                    xTurret1 = 10; yTurret1 = 580;
                    xGun1[0] = 20; xGun1[1] = 20;
                    yGun1[0] = 590; yGun1[1] = 565;
                    xTank1[0] = 0; xTank1[1] = 5; 
                    xTank1[2] = 40; xTank1[3] = 35;
                    yTank1[0] = 600; yTank1[1] = 590;
                    yTank1[2] = 590; yTank1[3] = 600;
                    xTurret2 = 770; yTurret2 = 580;
                    xGun2[0] = 780; xGun2[1] = 780;
                    yGun2[0] = 590; yGun2[1] = 565;
                    xTank2[0] = 800; xTank2[1] = 795;
                    xTank2[2] = 760; xTank2[3] = 765;
                    yTank2[0] = 600; yTank2[1] = 590;
                    yTank2[2] = 590; yTank2[3] = 600;	
                    x1Position = xGun1[0]-5; y1Position = yGun1[0];
                    x2Position = xGun2[0]; y2Position = yGun2[0];
                }
                if(turn == false){
                    if(shoot == true){						
                            //move the ball and determine 
                            //if it hit an edge or the other tank
                            if(xHitObject == false  && 
                                    (x1Position >= 0 && x1Position <= 800)){
                                    x1Position += x1Velocity;
                            }
                            else{
                                    turn = true; // player control switches to player2					
                                    System.out.println(turn);// debugging code
                                    //xHitObject = (x1Position >= BOUNDARY);
                                    xHitObject = false;
                                    shoot = false;

                            }
                            if(yHitObject == false && 
                                    (y1Position >= 0 && y1Position <= 600)){
                                    y1Position -= (y1Velocity - gravity);
                                    gravity++;
                            }
                            else{
                                    turn = true;						
                                    System.out.println(turn);
                                    //yHitObject = (y1Position >= BOUNDARY);
                                    yHitObject = false;
                                    shoot = false;

                            }
                    }
                }
                if(turn == true){
                    if(shoot == true){						
                        //move the ball and determine 
                        //if it hit an edge or the other tank
                        if(xHitObject == false  && 
                                (x2Position >= 0 && x2Position <= 800)){
                                x2Position += x2Velocity;

                        }
                        else{
                                turn = false; //player control switches to first player					
                                System.out.println(turn);
                                xHitObject = false;
                                shoot = false;

                        }
                        if(yHitObject == false && 
                                (y2Position >= 0 && y2Position <= 600)){
                                y2Position -= (y2Velocity - gravity);
                                gravity++;
                        }
                        else{
                                turn = false;						
                                System.out.println(turn);
                                yHitObject = false;
                                shoot = false;

                        }
                    }
                }
                // determine if a tank is hit
                if((x1Position >= xTank2[2] && x1Position <= xTank2[0])
                    &&(y1Position >= yTank2[2] && y1Position <= yTank2[0])){
                                    tank2Armor--;
                }
                if((x2Position >= xTank1[0] && x2Position <= xTank1[3])
                    &&(y2Position >= yTank1[2] && y2Position <= yTank1[0])){
                                    tank1Armor--;	
                }
                //determine if the shot hit the wall. 
                if((x1Position >= xBarrier[1] && x1Position <= xBarrier[2])
                    && y1Position >= yBarrier[1]){
                    shoot = false;
                    turn = true;
                }
                if((x2Position >= xBarrier[1] && x2Position <= xBarrier[2])
                    && y2Position >= yBarrier[1]){
                    shoot = false;
                    turn = false;
                }
                if(tank1Armor <= 0 || tank2Armor <= 0){
                    playGame = false;
                }
                if(shoot == false){
                    gravity = 0; 
                    x1Position = xGun1[0]-5;
                    y1Position = yGun1[0];
                    x2Position = xGun2[0];
                    y2Position = yGun2[0];
                }
            }
        }
        catch(InterruptedException e){
        }
    }//Close run()

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        //Player 1 is the blue player
        g.setColor(Color.blue);

        //Player 1 shot
        g.fillOval(x1Position, y1Position, 5, 5);

        //draw the score board
        g.setFont(new Font("Trebuchet MS", 0, 20));
        g.drawString(player1, 10, 25);
        g.setFont(new Font("Trebuchet MS", 0, 15));
        g.drawString(String.format("Armor: %2d", tank1Armor ), 10, 45);

        //draw player 1 gun
        g.drawLine(xGun1[0], yGun1[0], xGun1[1], yGun1[1]); 

         //draw player 1 turret
        g.fillOval(xTurret1, yTurret1, 20, 20);

        // draw player 1 tank body
        Polygon tank1 = new Polygon();		
        tank1.addPoint(xTank1[0], yTank1[0]);
        tank1.addPoint(xTank1[1], yTank1[1]);
        tank1.addPoint(xTank1[2], yTank1[2]);
        tank1.addPoint(xTank1[3], yTank1[3]);
        g.fillPolygon(tank1);

        //player 2 is the red player
        g.setColor(Color.red);

        //Player 1 shot
        g.fillOval(x2Position, y2Position, 5, 5);

        //draw the score board for player 2
        g.setFont(new Font("Trebuchet MS", 0, 20));
        g.drawString(player2, 720, 25);
        g.setFont(new Font("Trebuchet MS", 0, 15));
        g.drawString(String.format("Armor: %2d", tank2Armor ), 720, 45);


        //draw player 2 gun
        g.drawLine(xGun2[0], yGun2[0], xGun2[1], yGun2[1]); 

         //draw player 2 turret
        g.fillOval(xTurret2, yTurret2, 20, 20);

        // draw player 2tank
        Polygon tank2 = new Polygon();		
        tank2.addPoint(xTank2[0], yTank2[0]);
        tank2.addPoint(xTank2[1], yTank2[1]);
        tank2.addPoint(xTank2[2], yTank2[2]);
        tank2.addPoint(xTank2[3], yTank2[3]);
        g.fillPolygon(tank2);

        //draw the barrier
        g.setColor(Color.green);
        Polygon barrier = new Polygon();		
        barrier.addPoint(xBarrier[0], yBarrier[0]);
        barrier.addPoint(xBarrier[1], yBarrier[1]);
        barrier.addPoint(xBarrier[2], yBarrier[2]);
        barrier.addPoint(xBarrier[3], yBarrier[3]);
        g.fillPolygon(barrier);	

        //print message if there is a winner
        if(tank2Armor <= 0 ){
            playGame = false;
            g.drawString
                    (String.format("%s Wins!", player1), 200, 200); 		

        }
        else
            if(tank1Armor <= 0 ){
                    playGame = false;
                    g.drawString
                            (String.format("%s Wins!", player2), 200, 200);

            }

        if(playGame == false){
            g.drawString(String.format("Press SHIFT to play"), 300, 80);
            g.drawString
                    (String.format("Use LEFT and RIGHT arrows to move"), 300, 100);
            g.drawString
                    (String.format("Use UP and DOWN arrows to aim"), 300, 120);
            g.drawString(String.format("Press ENTER to Shoot"), 300, 140);
        }
    }//Close paintComponent

    public class tankKeyListener extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            if(turn == false){// controls player 1tank
                switch (e.getKeyCode()){
                    case KeyEvent.VK_ENTER: 
                        x1Velocity = x1GunAngle;
                        y1Velocity = y1GunAngle;
                        shoot = true;
                        break;
                    case KeyEvent.VK_SHIFT: playGame = true;
                        break;
                    case KeyEvent.VK_RIGHT:
                        if(xTank1[0] < 320){
                            xGun1[0] += 20;
                            xGun1[1] += 20;
                            xTurret1 += 20;

                            for(int i = 0; i < 4; i++){
                                xTank1[i] += 20;
                            }
                        }
                        break;
                    case KeyEvent.VK_LEFT: 
                        if(xTank1[0] > 0){
                            xGun1[0] -= 20;
                            xGun1[1] -= 20;
                            xTurret1 -= 20;
                            for(int i = 0; i < 4; i++){
                                xTank1[i] -= 20;
                            }
                        }
                        break;
                    case KeyEvent.VK_UP:
                        theta1--;
                        xGun1[1] = (int)(xGun1[0] + 25 * 
                            Math.sin(theta1 * (2 * Math.PI/360)));
                        yGun1[1] = (int)(yGun1[0] - 25 * 
                            Math.cos(theta1 * (2 * Math.PI/360)));
                        x1GunAngle = (int)(25 * 
                            Math.sin(theta1 * (2 * Math.PI/360)));
                        y1GunAngle = (int)(25 * 
                            Math.cos(theta1 * (2 * Math.PI/360)));
                        break;
                    case KeyEvent.VK_DOWN: 
                        theta1++;
                        xGun1[1] = (int)(xGun1[0] + 25 * 
                            Math.sin(theta1 * (2 * Math.PI/360)));
                        yGun1[1] = (int)(yGun1[0] - 25 * 
                            Math.cos(theta1 * (2 * Math.PI/360)));
                        x1GunAngle = (int)(25 * 
                            Math.sin(theta1 * (2 * Math.PI/360)));
                        y1GunAngle = (int)(25 * 
                            Math.cos(theta1 * (2 * Math.PI/360)));
                        break;
                    default:;
                }
            }
            else
                if(turn == true){// controls player 2 tank
                        switch (e.getKeyCode()){
                    case KeyEvent.VK_ENTER: 
                        x2Velocity = x2GunAngle;
                        y2Velocity = y2GunAngle;
                        shoot = true;
                        break;						
                    case KeyEvent.VK_SHIFT: playGame = true; 
                        break;
                    case KeyEvent.VK_RIGHT:
                        if(xTank2[0] < 800){
                            xGun2[0] += 20;
                            xGun2[1] += 20;
                            xTurret2 += 20;
                            for(int i = 0; i < 4; i++){
                                xTank2[i] += 20;
                            }
                        }
                        break;
                    case KeyEvent.VK_LEFT: 
                        if(xTank2[0] > 480){
                            xGun2[0] -= 20;
                            xGun2[1] -= 20;
                            xTurret2 -= 20;
                            for(int i = 0; i < 4; i++){
                                    xTank2[i] -= 20;
                            }
                        }
                        break;
                    case KeyEvent.VK_UP:
                        theta2++;
                        xGun2[1] = (int)(xGun2[0] + 25 * 
                            Math.sin(theta2 * (2 * Math.PI/360)));
                        yGun2[1] = (int)(yGun2[0] - 25 * 
                            Math.cos(theta2 * (2 * Math.PI/360)));
                        x2GunAngle = (int)(25 * 
                            Math.sin(theta2 * (2 * Math.PI/360)));
                        y2GunAngle = (int)(25 * 
                            Math.cos(theta2 * (2 * Math.PI/360)));
                        break;
                    case KeyEvent.VK_DOWN: 
                        theta2--;
                        xGun2[1] = (int)(xGun2[0] + 25 * 
                            Math.sin(theta2 * (2 * Math.PI/360)));
                        yGun2[1] = (int)(yGun2[0] - 25 * 
                            Math.cos(theta2 * (2 * Math.PI/360)));
                        x2GunAngle = (int)(25 * 
                            Math.sin(theta2 * (2 * Math.PI/360)));
                        y2GunAngle = (int)(25 * 
                            Math.cos(theta2 * (2 * Math.PI/360)));
                        break;
                    default:;
                    }
                }
            repaint();	
        }//close keyPressed()
    }//close tankKeyListener
}// close TankBattlePanel