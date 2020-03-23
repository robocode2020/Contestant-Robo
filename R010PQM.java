/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robots;
import java.awt.Color;
import java.awt.geom.Point2D;
import robocode.*;
import robocode.util.Utils;
/**
 *
 * @author ADMIN
 */
public class R010PQM extends AdvancedRobot{
    static double BULLET_POWER = 3;
    static double BULLET_DAMAGE = BULLET_POWER*4;
    static double BULLET_SPEED=20-3*BULLET_POWER;
    double enemyDistance;
    double enemyAbsoluteBearing;
    static double oldEnemyHeading;
    static double dir=1;
    static double enemyEnergy;
    public void run() {
		// Sự khởi đầu của Robot nên đặt ở đây
		setColors(Color.pink,Color.pink,Color.pink); // Thiết lập màu sắc: thân robo, nòng súng, radar
		// Vòng lặp chính của Robot
		while(true) {
		// Sự khởi đầu của Robot nên đặt ở đây
		setColors(Color.pink,Color.pink,Color.pink); // Thiết lập màu sắc: thân robo, nòng súng, radar
                setAdjustRadarForGunTurn(true);
                setAdjustGunForRobotTurn(true);
		// Vòng lặp chính của Robot
                do {
                    turnRadarRightRadians(Double.POSITIVE_INFINITY); 
                } while (true);
		}
	}
	/**
	* onScannedRobot: Robot của bạn làm gì khi phát hiện một robot khác
	*/
	public void onScannedRobot(ScannedRobotEvent e) {
		// Thay thế dòng kế tiếp với hành động/hành vi mà bạn muốn
                double absoluteBearing = getHeadingRadians() + e.getBearingRadians();
                double turn=absoluteBearing+Math.PI/2;
                turn-=0.5*dir;
                setTurnRightRadians(Utils.normalRelativeAngle(turn-getHeadingRadians()));
              	if(enemyEnergy>(enemyEnergy=e.getEnergy())){
			if(Math.random()>500/e.getDistance()){
				dir = -dir;
			}
		}
                int num = getOthers();
                setMaxVelocity(400/getTurnRemaining());
		setAhead(100*dir);
                enemyDistance = e.getDistance();
                double enemyHeading = e.getHeadingRadians();
		double enemyHeadingChange = enemyHeading - oldEnemyHeading;
		oldEnemyHeading = enemyHeading;
                double deltaTime = 0;
                double predictedX = getX()+e.getDistance()*Math.sin(absoluteBearing);
		double predictedY = getY()+e.getDistance()*Math.cos(absoluteBearing);
                while((++deltaTime) * BULLET_SPEED <  Point2D.Double.distance(getX(), getY(), predictedX, predictedY)){	
			predictedX += Math.sin(enemyHeading) * e.getVelocity();
			predictedY += Math.cos(enemyHeading) * e.getVelocity();
			enemyHeading += enemyHeadingChange;
			predictedX=Math.max(Math.min(predictedX,getBattleFieldWidth()-18),18);
			predictedY=Math.max(Math.min(predictedY,getBattleFieldHeight()-18),18);
 
		}
		double aim = Utils.normalAbsoluteAngle(Math.atan2(  predictedX - getX(), predictedY - getY()));
                setTurnGunRightRadians(Utils.normalRelativeAngle(aim - getGunHeadingRadians()));
                setTurnRadarRightRadians(Utils.normalRelativeAngle(absoluteBearing - getRadarHeadingRadians()));
                if(num > 1){
                    BULLET_POWER = 3;
                }
                else{
                    BULLET_POWER = Math.min(400 / enemyDistance, 3);
                }
                fire(BULLET_POWER);
	}

	/**
	* onHitByBullet: Robot của bạn làm gì khi bị trúng đạn
	*/
	public void onHitByBullet(HitByBulletEvent e) {
		// Thay thế dòng kế tiếp với hành động/hành vi mà bạn muốn
	}
	/**
	* onHitWall: Robot của bạn làm gì khi bị đụng tường
	*/
	public void onHitWall(HitWallEvent e) {
		// Thay thế dòng kế tiếp với hành động/hành vi mà bạn muốn
		dir = -dir;
	}
        public void onBulletHit(BulletHitEvent e){
            enemyEnergy-=BULLET_DAMAGE;
        }
        public  void onHitRobotEvent(HitRobotEvent e){
            double absoluteBearing = getHeadingRadians() + e.getBearingRadians();            
                stop();
                setTurnGunRightRadians(Utils.normalRelativeAngle(absoluteBearing - getGunHeadingRadians()));
                setTurnRadarRightRadians(Utils.normalRelativeAngle(absoluteBearing - getRadarHeadingRadians()));
                fire(3);
        }
}
