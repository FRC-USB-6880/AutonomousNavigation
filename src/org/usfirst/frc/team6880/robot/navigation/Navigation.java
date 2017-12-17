package org.usfirst.frc.team6880.robot.navigation;

import org.usfirst.frc.team6880.robot.FRC6880Robot;

public class Navigation {
	Gyro gyro=null;
	FRC6880Robot robot=null;
	
	public Navigation(FRC6880Robot robot)
	{
		this.robot = robot;
		this.gyro = new NavxMXP(robot);
		
		System.out.println("frc6880: curYaw:" + gyro.getYaw() + ", curPitch:" + gyro.getPitch() + ", curRoll:" + gyro.getRoll());
	}
}
