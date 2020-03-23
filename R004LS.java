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
 * @author Le Sang - 1751010123
 */
public class R004LS extends Robot {
    /**
     * @param args the command line arguments
     */
    
    boolean enemy = false;
    int direction=1;
        
   @Override
   public void run()
   {
       setColors(Color.GREEN, Color.DARK_GRAY, Color.BLUE);
       turnGunRight(90);
       
       double maxBattleFieldDimension = Math.sqrt(getBattleFieldWidth() * getBattleFieldWidth() + getBattleFieldHeight() * getBattleFieldHeight());
       
       while(true) {
			// Thay thế 04 dòng kế tiếp với hành động/hành vi mà bạn muốn
                        ahead(maxBattleFieldDimension*direction);
                        if (!enemy)
                        {
                            turnGunRight(360);
                        }
                    }
   }
   
   @Override
   public void onScannedRobot(ScannedRobotEvent e)
   {
       stop();
       fire(1);
       ahead(30*direction); 
       fire(1);
       ahead(30*direction); 
       fire(1);
       ahead(30*direction); 
       enemy = false;
   }
   
   @Override
   public void onHitByBullet(HitByBulletEvent e)
   {
       turnRight(e.getBearing() + 90);
   }
   
   @Override
   public void onHitWall(HitWallEvent e) {
       if(direction==1)
            direction=-1;
        else
            direction=1;
    }
}
