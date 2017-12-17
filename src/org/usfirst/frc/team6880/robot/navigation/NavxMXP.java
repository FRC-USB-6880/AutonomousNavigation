package org.usfirst.frc.team6880.robot.navigation;

import org.usfirst.frc.team6880.robot.FRC6880Robot;
import org.usfirst.frc.team6880.robot.util.ClipRange;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Timer;

public class NavxMXP implements Gyro {
	FRC6880Robot robot=null;
	public AHRS navx_device=null;
	private static final double KP=0.2, KI=0.3;
	
	public NavxMXP(FRC6880Robot robot)
	{
		this.robot = robot;
		this.navx_device = new AHRS(SPI.Port.kMXP);
		
		if(navx_device.isConnected())
			System.out.println("frc6880: navxMXP is connected");
		else
			System.out.println("frc6880: navxMXP is not connected");
		
		if(navx_device.isCalibrating())
		{
			System.out.println("frc6880: navxMXP still callibrating");
			Timer.delay(.02);
		}
		
		navx_device.zeroYaw();
	}
	
	@Override
	public double getYaw()
	{	
		return (double) navx_device.getYaw();
	}
	
	@Override
	public double getPitch()
	{	
		return (double) navx_device.getPitch();
	}
	
	@Override
	public double getRoll()
	{
		return (double) navx_device.getRoll();
	}
	
	@Override
	public void goStraightPID(boolean driveBackwards, double heading, double speed)
	{
		double error=0.0, correction=0.0;
		double leftSpeed, rightSpeed;
		error = getYaw() - heading;
		
		correction = KP*error / 2;
		speed = ClipRange.clip(speed, 0.25, 0.75);
		leftSpeed = ClipRange.clip(speed-correction, 0.0, 1.0);
		rightSpeed = ClipRange.clip(speed+correction, 0.0, 1.0);
		
		if(driveBackwards)
		{
			leftSpeed *= -1;
			rightSpeed *= -1;
		}
		
		robot.driveSys.tankDrive(leftSpeed, rightSpeed);
	}
	
}
