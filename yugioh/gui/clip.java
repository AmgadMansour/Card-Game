package eg.edu.guc.yugioh.gui;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class clip {

    public static  void main(String[] args) throws Exception {
        URL url = new URL(
        		"Aerials-Lights_and_Motion.mp3");
        Clip clip = AudioSystem.getClip();
        AudioInputStream ais = AudioSystem.getAudioInputStream( url );
        clip.open(ais);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // A GUI element to prevent the Clip's daemon Thread 
                // from terminating at the end of the main()
                JOptionPane.showMessageDialog(null, "Close to exit!");
            }
        });
    }
}
