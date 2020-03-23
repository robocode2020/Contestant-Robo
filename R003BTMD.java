package robots;

import robocode.*;
import java.awt.Color;
import robocode.AdvancedRobot;

public class R003BTMD extends AdvancedRobot {
    boolean movingForward;
    
    @Override
    public void run() {
                setColors(Color.pink, Color.pink, Color.pink);
		setAdjustRadarForGunTurn(true);
		setTurnRadarRight(1000); 
		execute();
		while (true) {
                        setAhead(40000);
			movingForward = true;
                        setTurnRight(90);
                        waitFor(new TurnCompleteCondition(this));
                        setTurnLeft(180);
                        waitFor(new TurnCompleteCondition(this));
                        setTurnRight(180);
			waitFor(new TurnCompleteCondition(this));
			if (getRadarTurnRemaining() == 0)
				setTurnRadarRight(1);

			execute();
		}
	}

	public void onScannedRobot(ScannedRobotEvent e) {
		setTurnRight(e.getBearing());

		if (Math.abs(getTurnRemaining()) < 10) {
			if (e.getDistance() > 200) {
				setAhead(e.getDistance() / 2);
			}
			
			if (e.getDistance() < 100) {
				setBack(e.getDistance() * 2);
			}
			setFire(2.0);
		}
		setTurnRadarRight(getHeading() - getRadarHeading() + e.getBearing());
	}
    @Override
	public void onHitWall(HitWallEvent e) {
		reverseDirection();
	}
        public void reverseDirection() {
		if (movingForward) {
			setBack(40000);
			movingForward = false;
		} else {
			setAhead(40000);
			movingForward = true;
		}
	}
    @Override
         public void onHitRobot(HitRobotEvent e) {
		if (e.getBearing() > -90 && e.getBearing() < 90) {
			back(100);
		} 
		else {
			ahead(100);
		}
	}   
}