/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robots;
import java.awt.Color;
import robocode.*;
/**
 *
 * @author HOA
 */
public class R006NQH extends Robot {
    /**
	* Thực thi: hành động/hành vi mặc định của TenBanRobo
	*/
	public void run() {
		// Sự khởi đầu của Robot nên đặt ở đây
		setColors(Color.black,Color.red,Color.blue); // Thiết lập màu sắc: thân robo, nòng súng, radar
		// Vòng lặp chính của Robot
		while(true) {
			// Thay thế 04 dòng kế tiếp với hành động/hành vi mà bạn muốn
                        ahead(100);
                        turnGunRight(360);
                        back(100);
                        turnGunRight(360);                   
		}
	}
        
        public void onScannedRobot(ScannedRobotEvent e) {
            if (e.getDistance() <= 100) {
                fire(5);
                back(100);
            } else {
                fire(3);
            } 
        }
        
        public void onHitByBullet(HitByBulletEvent e) {
            back(100);
            turnRight(45);
            ahead(50);
        }
        
        public void onHitWall(HitWallEvent e) {
            back(20);
            turnRight(90);
        }
        
        public void onHitRobot(HitRobotEvent e) {
            stop();
            fire(10);
            back(100);
        }
        
        public void onBulletMissed(BulletMissedEvent event) {
            resume();
        }
        
        public void onBulletHit(BulletHitEvent event) {
            fire(10);
        }
}
