package org.usfirst.frc.team6880.robot;

import org.usfirst.frc.team6880.robot.attachments.Shooter;
import org.usfirst.frc.team6880.robot.driveTrain.DriveSystem;
import org.usfirst.frc.team6880.robot.navigation.Navigation;
import org.usfirst.frc.team6880.robot.util.ClipRange;
import org.usfirst.frc.team6880.robot.util.LogitechF310;

import edu.wpi.first.wpilibj.Joystick;

public class FRC6880Robot {
	Robot wpilibRobot=null;
	public Navigation navigation=null;
	public DriveSystem driveSys=null;
	LogitechF310 gamepad=null;
	//Shooter shooter=null;
	
	public FRC6880Robot(Robot wpilibRobot)
	{
		this.wpilibRobot = wpilibRobot;
		navigation = new Navigation(this);
		driveSys = new DriveSystem(this);
		//shooter = new Shooter();
		gamepad = new LogitechF310(0);
	}
	
	public boolean isEnabled()
	{
		return wpilibRobot.isEnabled();
	}
	
	public void runTeleOp()
	{
		double speed = ClipRange.clip(-gamepad.leftStickY(), -1.0, 1.0);
		double direction = ClipRange.clip(gamepad.rightStickX(), -1.0, 1.0);
		
		driveSys.drive(speed, direction);
		
//		if (gamepad.rightTrigger())
//			shooter.enable();
//		else
//			shooter.disable();
	}
	
	public void runAutonomous()
	{
		navigation.goStraightForDist(0.2, 12.0);
		navigation.turnDegrees(0.2, 90.0);
	}
}
