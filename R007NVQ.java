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
 * @author Lenovo
 */
public class R007NVQ extends Robot{ //JuniorRobot /advancedRobot

   /**
	* Thực thi: hành động/hành vi mặc định của TenBanRobo
	*/
	public void run() {
		// Sự khởi đầu của Robot nên đặt ở đây
		setColors(Color.red,Color.blue,Color.green); // Thiết lập màu sắc: thân robo, nòng súng, radar
		// Vòng lặp chính của Robot
		while(true) {
			// Thay thế 04 dòng kế tiếp với hành động/hành vi mà bạn muốn
                        getX();
                        getY();
                        ahead(100);
                        turnGunRight(360);
                        back(100);
                }
        }
	/**
	* onScannedRobot: Robot của bạn làm gì khi phát hiện một robot khác
	*/
	public void onScannedRobot(ScannedRobotEvent e) {
		// Thay thế dòng kế tiếp với hành động/hành vi mà bạn muốn
                if (e.getDistance()<100){
                    fire(3);
                }else {
                    fire(1);
                }
	}
	/**
	* onHitByBullet: Robot của bạn làm gì khi bị trúng đạn
	*/
	public void onHitByBullet(HitByBulletEvent e) {
		// Thay thế dòng kế tiếp với hành động/hành vi mà bạn muốn
                turnRight(60);
	}
	/**
	* onHitWall: Robot của bạn làm gì khi bị đụng tường
	*/
	public void onHitWall(HitWallEvent e) {
		// Thay thế dòng kế tiếp với hành động/hành vi mà bạn muốn
                back(200);
                turnRight(90);
                ahead(100);
                
	}
        public void onBulletHit (BulletHitEvent event){
                //Đạn trúng Robot đối phương
                
        }
        public void onHitRobot (HitRobotEvent e){
                //Va vào Robot đối phương
                turnGunRight(360);
                back(200);
                turnRight(90);
        }
        public void onBulletMissed (BulletMissedEvent event){
                //đạn không trúng đối phương
                
        }
}