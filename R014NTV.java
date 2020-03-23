package robots;
import java.awt.Color;
import robocode.*;
import static robocode.util.Utils.normalRelativeAngleDegrees;

/**
 *
 * @author Nguyen Truong Vu
 */
public class R014NTV extends Robot
{
	 //Thực thi: hành động/hành vi mặc định của TenBanRobo
        boolean peek; // Don't turn if there's a robot there
	double moveAmount; // How much to move
	public void run() 
        {
		// Sự khởi đầu của Robot nên đặt ở đây
		setColors(Color.yellow,Color.blue,Color.pink,Color.orange,Color.green); // Thiết lập màu sắc: thân robo, nòng súng, radar, đạn, scan
		// Set colors
		//setBodyColor(Color.yellow);
		//setGunColor(Color.red);
		//setRadarColor(Color.pink);
		//setBulletColor(Color.orange);
		//setScanColor(Color.green);
    
		// Vòng lặp chính của Robot
            // Initialize moveAmount to the maximum possible for this battlefield.
		moveAmount = Math.max(getBattleFieldWidth(), getBattleFieldHeight());
		// Initialize peek to false
		peek = false;

		// turnLeft to face a wall.
		// getHeading() % 90 means the remainder of
		// getHeading() divided by 90.
		turnLeft(getHeading() % 90);
		ahead(moveAmount);
		// Turn the gun to turn right 90 degrees.
		peek = true;
                //turnGunRight(360);        
		turnRight(90);

                // Thay thế 04 dòng kế tiếp với hành động/hành vi mà bạn muốn
		while (true) {
			// Look before we turn when ahead() completes.
			peek = true;
			// Move up the wall
			ahead(moveAmount);
                       // turnGunRight(90);
			// Don't look now
			peek = false;
			// Turn to the next wall
			turnRight(90);
                       // turnGunRight(90);
		}
	}
	/**
	* onScannedRobot: Robot của bạn làm gì khi phát hiện một robot khác
	*/
	public void onScannedRobot(ScannedRobotEvent e) 
        {
		// Thay thế dòng kế tiếp với hành động/hành vi mà bạn muốn
		if (e.getDistance() < 200 && getEnergy() > 50) 
                {
			fire(3);
		} // otherwise, fire 1.
		else 
                {
			fire(1);
		}
	}
	/**
	* onHitByBullet: Robot của bạn làm gì khi bị trúng đạn
	*/
	/*public void onHitByBullet(HitByBulletEvent e) 
       {
            // Thay thế dòng kế tiếp với hành động/hành vi mà bạn muốn
        
	}*/
	/**
	* onHitWall: Robot của bạn làm gì khi bị đụng tường
	*/
//	public void onHitWall(HitWallEvent e) 
//        {
            // Thay thế dòng kế tiếp với hành động/hành vi mà bạn muốn

//         }
        public void onHitRobot(HitRobotEvent e)
        {
            if (e.getBearing() >= 0) {
                int turnDirection = 1;
		} else {
                int turnDirection = -1;
		}
		turnRight(e.getBearing());

		// Determine a shot that won't kill the robot...
		// We want to ram him instead for bonus points
		if (e.getEnergy() > 16) {
			fire(3);
		} else if (e.getEnergy() > 10) {
			fire(2);
		} else if (e.getEnergy() > 4) {
			fire(1);
		} else if (e.getEnergy() > 2) {
			fire(.5);
		} else if (e.getEnergy() > .4) {
			fire(.1);
		}
		ahead(40);
                // Ram him again!
        }
        public void onBulletMissed(BulletMissedEvent event) 
        {
            scan();
            fire(1);
            out.println("Drat, I missed.");
        }

    private void turnRadar(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}