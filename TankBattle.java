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

public class TankBattle extends JFrame {

    //private variable to controll the sound track
    private AudioClip soundTrack;
 
    public TankBattle(){
        
        	//create and add the panel that holds the game
		TankBattlePanel gamePanel = new TankBattlePanel();
		add(gamePanel);
		
		//create the thread that controls the animation of the game
		Thread gameThread = new Thread(gamePanel);
		gameThread.start();
		
		//make the panel focusable so the key listener will work
		gamePanel.setFocusable(true);
		
		//add sound track
		/**Evil March by Kevin MacLeod is licensed under a CC Attribution 3.0.
		http://incompetech.com/music/royalty-free/index.html?isrc=USUAN1100727
		*/
		URL urlOfSoundTrack = getClass().getResource("audio/Evil March.wav");
		soundTrack = Applet.newAudioClip(urlOfSoundTrack);
		soundTrack.loop();
    }
    //override the start and stop methods of the Applet class??
    public void start(){
            if (soundTrack != null) soundTrack.loop();
    }

    public void stop(){
            if (soundTrack != null) soundTrack.stop();
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        TankBattle frame = new TankBattle();
        frame.setTitle("TankBattle");
        frame.setSize(820,645);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
