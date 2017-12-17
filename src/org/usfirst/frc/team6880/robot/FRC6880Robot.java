package org.usfirst.frc.team6880.robot;

import org.usfirst.frc.team6880.robot.navigation.Navigation;

public class FRC6880Robot {
	Robot wpilibRobot=null;
	Navigation navigation=null;
	
	public FRC6880Robot(Robot wpilibRobot)
	{
		this.wpilibRobot = wpilibRobot;
		this.navigation = new Navigation(this);
	}
	
	
}
