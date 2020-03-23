package robots;
import robocode.*;

public class R012LTH extends JuniorRobot
{
/**
 * Pre01*= run
 */
	public void run() 
	{
		setColors(green, black, blue);

		while (true) 
		{
			ahead(100); 
			turnGunRight(360);
			back(100);
			turnGunRight(360);
		}
	}
/**
 * Pre02*= onScannedRobot
 */
	public void onScannedRobot() 
	{
		turnGunTo(scannedAngle);
		fire(1);
	}
/**
 * Pre03*= onHitByBullet
 */
	public void onHitByBullet() 
	{
		turnAheadLeft(100, 90 - hitByBulletBearing);
	}

/**
 * Pre04*= onHitRobot
 */
	public void onHitRobot(HitRobotEvent event) {
			       if (event.getBearing() > -90 && event.getBearing() <= 90) {
			           back(100);
			       } else {
			           ahead(100);
			       }
			   }	
/**
 * Pre05*= onHitWall
 */
	public void onHitWall(HitWallEvent event) {
       out.println("Ouch, I hit a wall bearing " + event.getBearing() + " degrees.");
   }
/**
 * Pre06*= onBulletMissed
 */   
	public void onBulletMissed(BulletMissedEvent event) {
       out.println("Drat, I missed.");
   }

}