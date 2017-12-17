package org.usfirst.frc.team6880.robot.navigation;

public interface Gyro {
	double getYaw();
	double getPitch();
	double getRoll();
	void goStraightPID(boolean driveBackwards, double heading, double speed);
}
