/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robots;

import java.awt.Color;
import robocode.HitByBulletEvent;
import robocode.HitRobotEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;
import robocode.WinEvent;
import static robocode.util.Utils.normalRelativeAngleDegrees;

/**
 *
 * @author Nguyen Do Trong
 */
public class R005NDT extends Robot {
    boolean peek; 
    double moveAmount; 

	
    public void run() {
		// Set colors
        setBodyColor(new Color(0, 200, 0));
        setGunColor(Color.black);
        setRadarColor(Color.orange);
        setBulletColor(Color.pink);
        setScanColor(Color.cyan);

                   
	moveAmount = Math.max(getBattleFieldWidth(), getBattleFieldHeight());
		
	peek = false;

		
        turnLeft(getHeading() % 90);
	ahead(moveAmount);
		
	peek = true;
	turnGunRight(90);
	turnRight(90);

	while (true) {
			
            peek = true;
			
            ahead(moveAmount);
            turnGunRight(90);
            turnGunLeft(180);
            turnGunRight(90);
			
            peek = false;
			
            turnRight(90);
		}
    }

    public void onHitRobot(HitRobotEvent e) {
		
        if (e.getBearing() > -90 && e.getBearing() < 90) {
            back(100);
        }
        else {
            ahead(100);
        }
        this.run();
    }

	
    public void onScannedRobot(ScannedRobotEvent e) {
	// Calculate exact location of the robot
		double absoluteBearing = getHeading() + e.getBearing();
		double bearingFromGun = normalRelativeAngleDegrees(absoluteBearing - getGunHeading());

		// If it's close enough, fire!
		if (Math.abs(bearingFromGun) <= 3) {
			turnGunRight(bearingFromGun);
			// We check gun heat here, because calling fire()
			// uses a turn, which could cause us to lose track
			// of the other robot.
			if (getGunHeat() == 0) {
				fire(Math.min(3 - Math.abs(bearingFromGun), getEnergy() - .1));
			}
		} 
		
		else {
			turnGunRight(bearingFromGun);
		}
		
		if (bearingFromGun == 0) {
			scan();
		}

	if (peek) {
            scan();
	}
    }
   public void onHitByBullet(HitByBulletEvent e){
   turnRight(90);
   ahead(200);
   } 
    
    
    public void onWin(WinEvent e) {
	for (int i = 0; i < 50; i++) {
            turnRight(30);
            turnLeft(30);
            }
    }
}