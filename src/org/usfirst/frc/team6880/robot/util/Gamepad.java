package org.usfirst.frc.team6880.robot.util;

import edu.wpi.first.wpilibj.Joystick;

public class Gamepad extends Joystick{
	// Gamepad axis ports
	private static final int AXIS_LEFT_X = 1;
	private static final int AXIS_LEFT_Y = 2;
	private static final int AXIS_SHOULDER = 3;
	private static final int AXIS_RIGHT_X = 4;
	private static final int AXIS_RIGHT_Y = 5;
	private static final int AXIS_DPAD = 6;
	
	// Gamepad buttons
	private static final int BUTTON_A = 2;
	private static final int BUTTON_B = 3;
	private static final int BUTTON_X = 1;
	private static final int BUTTON_Y = 4;
	private static final int BUTTON_SHOULDER_LEFT = 5;
	private static final int BUTTON_SHOULDER_RIGHT = 6;
	private static final int BUTTON_TRIGGER_LEFT = 7;
	private static final int BUTTON_TRIGGER_RIGHT = 8;
	private static final int BUTTON_BACK = 9;
	private static final int BUTTON_START = 10;
	private static final int BUTTON_LEFT_STICK = 11;
	private static final int BUTTON_RIGHT_STICK = 12;
	private static final int BUTTON_MODE = -1;
	private static final int BUTTON_LOGITECH = -1;
	
	public Gamepad(int portNum)
	{
		super(portNum);
	}
	
	public double leftStickX()
	{
		return getRawAxis(AXIS_LEFT_X);
	}
	
	public double leftStickY()
	{
		return getRawAxis(AXIS_LEFT_Y);
	}
	
	public double rightStickX()
	{
		return getRawAxis(AXIS_RIGHT_X);
	}
	
	public double rightStickY()
	{
		return getRawAxis(AXIS_RIGHT_Y);
	}
	
	public boolean dpadRight()
	{
		return (getRawAxis(AXIS_DPAD) < -0.5);
	}
	
	public boolean dpadLeft()
	{
		return (getRawAxis(AXIS_DPAD) > 0.5);
	}
	
	public boolean a()
	{
		return getRawButton(BUTTON_A);
	}
	
	public boolean b()
	{
		return getRawButton(BUTTON_B);
	}
	
	public boolean x()
	{
		return getRawButton(BUTTON_X);
	}
	
	public boolean y()
	{
		return getRawButton(BUTTON_Y);
	}
	
	public boolean leftBumper()
	{
		return getRawButton(BUTTON_SHOULDER_LEFT);
	}
	
	public boolean rightBumper()
	{
		return getRawButton(BUTTON_SHOULDER_RIGHT);
	}
	
	public boolean leftTrigger()
	{
		return getRawButton(BUTTON_TRIGGER_LEFT);
	}
	
	public boolean rightTrigger()
	{
		return getRawButton(BUTTON_TRIGGER_RIGHT);
	}
	
	public boolean leftStickButton()
	{
		return getRawButton(BUTTON_LEFT_STICK);
	}
	
	public boolean rightStickButton()
	{
		return getRawButton(BUTTON_RIGHT_STICK);
	}
	
	public boolean back()
	{
		return getRawButton(BUTTON_BACK);
	}
	
	public boolean start()
	{
		return getRawButton(BUTTON_START);
	}
	
	public boolean mode()
	{
		return getRawButton(BUTTON_MODE);
	}
	
	public boolean home()
	{
		return getRawButton(BUTTON_LOGITECH);
	}
}
