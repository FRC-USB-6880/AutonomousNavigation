package org.usfirst.frc.team6880.robot.driveTrain;

import org.usfirst.frc.team6880.robot.FRC6880Robot;
import org.usfirst.frc.team6880.robot.util.ClipRange;

import edu.wpi.first.wpilibj.VictorSP;

public class DriveSystem {
	FRC6880Robot robot=null;
	VictorSP motorL1;
	VictorSP motorL2;
	VictorSP motorR1;
	VictorSP motorR2;
	
	public DriveSystem(FRC6880Robot robot)
	{
		this.robot = robot;
		
		motorL1 = new VictorSP(0);
		motorL2 = new VictorSP(1);
		motorR1 = new VictorSP(2);
		motorR2 = new VictorSP(3);
	}
	
	public void drive(double speed, double direction)
	{	
		double leftPower = ClipRange.clip(speed + direction, -1.0, 1.0);
		double rightPower = ClipRange.clip(speed - direction, -1.0, 1.0);
		
		motorL1.set(leftPower);
		motorL2.set(leftPower);
		motorR1.set(rightPower);
		motorR1.set(rightPower);
	}
	
	public void tankDrive(double leftPower, double rightPower)
	{
		leftPower = ClipRange.clip(leftPower, -1.0, 1.0);
		rightPower = ClipRange.clip(rightPower, -1.0, 1.0);
		
		motorL1.set(leftPower);
		motorL2.set(leftPower);
		motorR1.set(rightPower);
		motorR2.set(rightPower);
	}
}
