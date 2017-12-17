package org.usfirst.frc.team6880.robot.navigation;

import org.usfirst.frc.team6880.robot.Robot;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Timer;

public class NavxMXP implements Gyro {
	Robot robot=null;
	public AHRS navx_device=null;
	
	public NavxMXP(Robot robot)
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
	
}
