package org.usfirst.frc.team6880.robot.navigation;

import org.usfirst.frc.team6880.robot.FRC6880Robot;

import edu.wpi.first.wpilibj.Timer;

public class Navigation {
	Gyro gyro=null;
	FRC6880Robot robot=null;
	
	public Navigation(FRC6880Robot robot)
	{
		this.robot = robot;
		this.gyro = new NavxMXP(robot);
		
		System.out.println("frc6880: curYaw:" + gyro.getYaw() + ", curPitch:" + gyro.getPitch() + ", curRoll:" + gyro.getRoll());
	}
	
	public void goStraightForTime(double speed, double seconds)
	{
		double startingYaw = gyro.getYaw();
		
		boolean backwards = speed<0 ? true : false;
		speed = Math.abs(speed);
		
		double startTime = Timer.getFPGATimestamp();
		double elapsedTime = Timer.getFPGATimestamp() - startTime;
		while(elapsedTime < seconds && robot.isEnabled())
		{
			gyro.goStraightPID(backwards, startingYaw, speed);
			elapsedTime = Timer.getFPGATimestamp() - startTime;
		}
	}
}
