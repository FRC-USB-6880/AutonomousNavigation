package org.usfirst.frc.team6880.robot.driveTrain;

import org.usfirst.frc.team6880.robot.FRC6880Robot;
import org.usfirst.frc.team6880.robot.util.ClipRange;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;

public class DriveSystem {
	FRC6880Robot robot=null;
	VictorSP motorL1;
	VictorSP motorL2;
	VictorSP motorR1;
	VictorSP motorR2;
	Encoder leftEnc;
	Encoder rightEnc;
	private static final double WHEEL_DIAMETER = 6.0;
	private static final double WHEEL_CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER;
	public static final double DRIVE_TRAIN_RADIUS = 27.6772 / 2;
	private static final double DISTANCE_PER_PULSE = WHEEL_CIRCUMFERENCE / 360.0;
	
	public DriveSystem(FRC6880Robot robot)
	{
		this.robot = robot;
		
		motorL1 = new VictorSP(0);
		motorL2 = new VictorSP(1);
		motorR1 = new VictorSP(2);
		motorR2 = new VictorSP(3);
		motorR1.setInverted(true);
		motorR2.setInverted(true);
		
		leftEnc = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
		leftEnc.setDistancePerPulse(DISTANCE_PER_PULSE);
		rightEnc = new Encoder(2, 3, true, Encoder.EncodingType.k4X);
		rightEnc.setDistancePerPulse(DISTANCE_PER_PULSE);
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
	
	public void resetEncoders()
	{
		leftEnc.reset();
		rightEnc.reset();
	}
	
	public double getLeftEncoderPos()
	{
		return leftEnc.getDistance();
	}
	
	public double getRightEncoderPos()
	{
		return rightEnc.getDistance();
	}
}
