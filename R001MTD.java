package robots;

import robocode.*;
import java.awt.*;

public class R001MTD extends AdvancedRobot {
    int moveDirection = 1;	//move ahead
    
    public void run() {

        setBodyColor(new Color(221,160,221));
		setGunColor(new Color(186,85,211));
        setRadarColor(new Color(173,255,47));
        setScanColor(new Color(124,252,0));
        setBulletColor(new Color(244,164,96));
		
		// Keep gun & radar independent with robot
        setAdjustGunForRobotTurn(true);
		setAdjustRadarForGunTurn(true);
		turnRadarRight(Double.POSITIVE_INFINITY);	//keep turning radar right
    }

    public void onScannedRobot(ScannedRobotEvent e) {
		setScanColor(new Color(255,0,255));
        double absBearing = e.getBearing() + getHeading();	//enemies absolute bearing
        double gunTurnAmount;	//amount to turn our gun
        setTurnRadarLeft(getRadarTurnRemaining());	//lock on the radar

       if(Math.random() > 0.5) {
           setMaxVelocity((12 * Math.random()) + 12);	//random speed
       }
        
       if (e.getDistance() > 150) {		//far enemy
            gunTurnAmount = robocode.util.Utils.normalRelativeAngleDegrees(absBearing - getGunHeading() );
            setTurnGunRight(gunTurnAmount);
            setTurnRight(robocode.util.Utils.normalRelativeAngleDegrees(absBearing - getHeading()) );
            setAhead((e.getDistance() - 140) * moveDirection);
            smartFire(e);
        }
        else {	//near enemy 
            gunTurnAmount = robocode.util.Utils.normalRelativeAngleDegrees(absBearing - getGunHeading());
            setTurnGunRight(gunTurnAmount);
            setTurnLeft(-90 - e.getBearing());
            setAhead((e.getDistance() - 140) * moveDirection);
            smartFire(e);
        }
    }
    public void onHitWall(HitWallEvent e){
        moveDirection = -moveDirection;	//reverse direction when hit wall
    }

    public void smartFire(ScannedRobotEvent e) {
        if (e.getDistance() > 400) {
            fire(1);
        }
        else if (e.getDistance() > 200) {
            fire(2);
        }
        else {
            fire(3);
        }
    }

    public void onWin(WinEvent e) {
			setBodyColor(new Color(50,205,50));
			setRadarColor(new Color(60,179,113));
			setScanColor(new Color(0,255,255));    
    }
}