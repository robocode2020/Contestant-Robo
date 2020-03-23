
package robots;
import robocode.*;
import java.awt.*;
import robocode.util.Utils;

/**
 * Do Minh Nhat
 * Tracker Robot
 * 
 */
public class R013DMN extends AdvancedRobot {
    int dir = 1;//Huong di chuyen
    public void run() {
        setAdjustRadarForRobotTurn(true);
        setAdjustGunForRobotTurn(true); 
        setBodyColor(new Color(70, 77, 106));
        setGunColor(new Color(70, 77, 106));
        setRadarColor(new Color(70, 77, 106));
        setScanColor(new Color(70, 77, 106));
        setBulletColor(new Color(70, 77, 106));
        do{
            turnRadarRightRadians(Double.POSITIVE_INFINITY);
        }while(true);
    }

   
    public void onScannedRobot(ScannedRobotEvent e) {
        double realBearing = e.getBearingRadians() + getHeadingRadians();//Lay Goc
        double lastV = e.getVelocity();//Lay toc do enemy
        setTurnRadarLeftRadians(getRadarTurnRemainingRadians());//Khoa Radar
        
        if (e.getDistance() > 150) {
            setTurnGunRightRadians(Utils.normalRelativeAngle(realBearing - getGunHeadingRadians()));//CHinh goc quay sung
            setTurnRightRadians(Utils.normalRelativeAngle(realBearing - getHeadingRadians() + lastV/getVelocity()));//Di chuyen den vi tri da doan truoc: lastV/getVelocity()
            setAhead((e.getDistance() - 150)*dir);//Giu khoang cach 150px voi enemy
            setFire(2);
        }
        else{
            setTurnGunRightRadians(Utils.normalRelativeAngle(realBearing - getGunHeadingRadians()));
            setTurnLeft(-90 - e.getBearing()); //Xoay xe vuong goc voi enemy
            setAhead((e.getDistance() - 150)*dir);
            setFire(3);//fire
        }
    }
    public void onHitWall(HitWallEvent e){
        dir = -dir;
    }
   
    public void onHitRobot(HitRobotEvent e){
       if (e.getBearing() > -90 && e.getBearing() < 90) {
            back(100);
        } 
	else {
            ahead(100);
	}
    }
}