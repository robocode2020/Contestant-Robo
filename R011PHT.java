package robots;
import robocode.*;
import java.awt.Color;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * HTRobot - a robot by HoaiThanh
 */
public class R011PHT extends Robot
{
	double degreesGuess = 90, dt; 
	int direction = 1; // huong
	/**
	 * run: HTRobot's default behavior
	 */
	public void run() {
	
		setBodyColor(new Color(204, 153, 0));
        setGunColor(new Color(153, 153, 102));
        setRadarColor(new Color(204, 204, 0));
        setScanColor(Color.black);
        setBulletColor(Color.red);
		
		while(true){
			if(getOthers() != 1){  //khi con nhieu hon 1 robot tren chien truong
				back(150);
			} else {
				ahead((dt-100)*direction);
			}
			turnGunRight(360);  //quet 
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		if(getOthers() != 1) {
			fire(3);
			scan();  //neu muc tieu van dung yen 1 cho thi ban tiep
			degreesGuess = e.getBearing() +90;
			turnRight(degreesGuess);  //ne
		} else {
			fire(3);
			scan();
			degreesGuess = e.getBearing() + 90;
			turnRight(degreesGuess);
			dt = e.getDistance();
			if (e.getBearing() > -90 && e.getBearing() <= 90) {
				direction = -1;
        	} else {
				direction = 1;
        	}
		}
		
		
	}

	//Khi dung trung 1 robot khac
	public void onHitRobot(HitRobotEvent event) {
		if (event.getBearing() > -90 && event.getBearing() <= 90) { 
			turnRight(event.getBearing() +90);
           	back(100);
        } else {
			turnRight(event.getBearing() +90);
           	ahead(100);
        }
   	}
	
	//khi robot dung tuong 
	public void onHitWall(HitWallEvent event) {
       direction = -1;
    }	
}