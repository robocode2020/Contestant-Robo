package robots;

import robocode.*;
import java.awt.Color;
import java.util.Random;


public class R002VTD extends Robot{

    public void run() {

        setColors(Color.red,Color.blue,Color.green);

        while(true) {

           turnGunRight(360);
           move();
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {

        if(e.getDistance() < 150){
           fire(100);
           //fire(100);  
           e.getBearing();
        }
        else{
            fire(20);
        }
      

    }

    public void onHitByBullet(HitByBulletEvent e) {
        move();
    }

    public void onHitWall(HitWallEvent e) {
        turnRight(-e.getBearing());
    }

    public void onHitRobot(HitRobotEvent e){
        if (e.getBearing() > -90 && e.getBearing() <= 90){
            fire(50);
            move();
        }
    }

    public void onBulletHit(BulletHitEvent e){
       stop();
       fire(20);
    }
    

    public int getRanDom(int number){
        Random generator = new Random();
        return generator.nextInt(number);
    }
    public void move(){
        switch(getRanDom(4)){
            case 0: {
                turnAheadLeft(getRanDom(300),getRanDom(100));
                break;
            }
            case 3:{
                turnAheadRight(getRanDom(300),getRanDom(100));
                break;
            }
            case 1: {
                turnBackLeft(getRanDom(300),getRanDom(100));
                break;
            }
            case 2:{
                turnBackRight(getRanDom(400),getRanDom(100)); 
                break;
            }
        }
    }
    void turnAheadLeft(int toi , int degrees ){
        turnLeft(degrees);
        ahead(toi);
    }
    void turnAheadRight(int toi , int degrees ){
        turnRight(degrees);
        ahead(toi);
    }
    void turnBackRight(int lui , int degrees ){
        turnRight(degrees);
        ahead(lui);
    }
    void turnBackLeft(int lui , int degrees ){
        turnLeft(degrees);
        ahead(lui);
    }

}