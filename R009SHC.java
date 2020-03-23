/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robots;
import robocode.JuniorRobot;
import java.awt.Color;
import static robocode.util.Utils.normalRelativeAngleDegrees;
/**
 *
 * @author Shishi
 */
public class R009SHC extends JuniorRobot{

    public void run() {
		setColors(red,green,blue,yellow,white);
		while(true) {
                        turnGunRight(360);
			ahead(150);
			turnGunRight(360);
			back(100);
		}
	}
	public void onScannedRobot() {
		turnGunTo(scannedAngle);
		fire(2);
	}
	public void onHitByBullet() {
		turnAheadLeft(100, 90 - hitByBulletBearing);
	}
	public void onHitWall() {
		back(200);
		turnRight(35);
	}
    
}