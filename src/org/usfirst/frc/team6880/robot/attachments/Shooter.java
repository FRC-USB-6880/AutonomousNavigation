package org.usfirst.frc.team6880.robot.attachments;

import edu.wpi.first.wpilibj.TalonSRX;

public class Shooter {
	TalonSRX shooterMotor=null;
	private final int ON_POWER=1, OFF_POWER=0;
	
	public Shooter()
	{
		shooterMotor = new TalonSRX(4);
	}
	
	public void enable()
	{
		shooterMotor.set(ON_POWER);
	}
	
	public void disable()
	{
		shooterMotor.set(OFF_POWER);
	}
}
