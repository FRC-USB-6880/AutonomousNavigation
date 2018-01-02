package org.usfirst.frc.team6880.robot.navigation;

import org.usfirst.frc.team6880.robot.FRC6880Robot;
import org.usfirst.frc.team6880.robot.util.ClipRange;

import edu.wpi.first.wpilibj.Timer;

/**
 * Top-level navigation class containing all navigation algorithms. This class is initialized in {@link FRC6880Robot.java}.
 * @author pb8xe
 * @version 0.1
 *
 */
public class Navigation {
	Gyro gyro=null;
	FRC6880Robot robot=null;
	private static final double GYRO_KP=0.2, GYRO_KI=0.3;
	
	public Navigation(FRC6880Robot robot)
	{
		this.robot = robot;		//Initialize FRC6880Robot local object
		this.gyro = new NavxMXP(robot);		//Initialize Gyro interfaced object as a NavxMXP gyro
		
		System.out.println("frc6880: curYaw:" + gyro.getYaw() + ", curPitch:" + gyro.getPitch() + ", curRoll:" + gyro.getRoll());
	}
	
	/**
	 * This method maintains a yaw while driving for a period of time.
	 * @see goStraightPID
	 * @param speed Speed of robot, from -1.0 to 1.0.
	 * @param seconds Time for robot to drive straight, in seconds.
	 */
	public void goStraightForTime(double speed, double seconds)
	{
		double startingYaw = gyro.getYaw();
		
		boolean backwards = speed<0 ? true : false;		//If speed is negative, true, else false
		speed = Math.abs(speed);
		
		double startTime = Timer.getFPGATimestamp();
		double elapsedTime = Timer.getFPGATimestamp() - startTime;
		while(elapsedTime < seconds && robot.isEnabled())
		{
			goStraightPID(speed, backwards, startingYaw);
			elapsedTime = Timer.getFPGATimestamp() - startTime;
		}
	}
	
	/**
	 * This method maintains a yaw while driving to a specified distance.
	 * @see goStraightPID
	 * @param speed Speed of robot, from -1.0 to 1.0.
	 * @param distance Distance the robot should drive straight for, in inches.
	 */
	public void goStraightForDist(double speed, double distance)
	{
		double startingYaw = gyro.getYaw();
		boolean backwards = speed < 0 ? true : false;		//If speed is negative, true, else false
		speed = Math.abs(speed);
		
		robot.driveSys.resetEncoders();
		
		double avgDist = (robot.driveSys.getLeftEncoderPos() + robot.driveSys.getRightEncoderPos()) / 2;
		while(avgDist < distance && robot.isEnabled())
		{
			goStraightPID(speed, backwards, startingYaw);
//			robot.driveSys.drive(speed, 0.0);
			avgDist = (robot.driveSys.getLeftEncoderPos() + robot.driveSys.getRightEncoderPos()) / 2;
			System.out.println(avgDist);
		}
		robot.driveSys.tankDrive(0.0, 0.0);
	}
	
	/**
	 * This method applies a PID correction to the {@link DriveSystem#tankDrive} to maintain a heading, 
	 * based on the private constants {@code GYRO_KP}, {@code GYRO_KI}, and {@code GYRO_KD}.
	 * @param speed Speed of robot, from 0.0 to 1.0.
	 * @param driveBackwards True if robot should drive backwards, false if robot should drive forwards.
	 * @param heading Heading that the robot must maintain, from -180.0 to 180.0.
	 */
	public void goStraightPID(double speed, boolean driveBackwards, double heading)
	{
		double error=0.0, correction=0.0;
		double leftSpeed, rightSpeed;
		
		//Proportional: ( kP * error[in degrees of offset from target heading] ) / 2[to allow correction to be applied to 2 sides of drivetrain]
		error = gyro.getYaw() - heading;
		correction = GYRO_KP*error / 2;
		
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
	
	/**
	 * This method turns the robot a specified number of degrees.
	 * @param speed Speed of robot, from 0.0 to 1.0.
	 * @param angle Angle the robot must turn. Negative angle turns the robot left, positive angle turns the robot right.
	 */
	public void turnDegrees(double speed, double angle)
	{
		double leftSpeed = angle<0 ? -speed : speed;
		double rightSpeed = angle<0 ? speed : -speed;
		double targetYaw = gyro.getYaw() + angle;
		double curYaw = gyro.getYaw();
		
		while(curYaw != targetYaw && robot.isEnabled())
		{
			robot.driveSys.tankDrive(leftSpeed, rightSpeed);
			Timer.delay(0.005);
		}
		
//		robot.driveSys.resetEncoders();
//		double arcLength = Math.toRadians(angle)*robot.driveSys.DRIVE_TRAIN_RADIUS;
//		double avgDist = (Math.abs(robot.driveSys.getLeftEncoderPos()) + Math.abs(robot.driveSys.getRightEncoderPos())) / 2;
//		while(avgDist < arcLength && robot.isEnabled())
//		{
//			robot.driveSys.tankDrive(speed, -speed);
//			avgDist = (Math.abs(robot.driveSys.getLeftEncoderPos()) + Math.abs(robot.driveSys.getRightEncoderPos())) / 2;
//		}
		
		robot.driveSys.tankDrive(0.0, 0.0);
	}
	
	/**
	 * This method turns the robot to a specified heading, regardless of current heading.
	 * @param speed Speed of robot, from 0.0 to 1.0.
	 * @param heading Heading the robot must turn to, from -180.0 to 180.0.
	 */
	public void setRobotOrientation(double speed, double heading)
	{
//		double startingYaw = gyro.getYaw();
//		double angleToTurn = startingYaw + heading;
//		if(Math.abs(angleToTurn) > 180)
//		{
//			double leftPower = (angleToTurn < 0) ? speed : -speed;
//			double rightPower = (angleToTurn < 0) ? -speed : speed;
//		}
//		else
//		{
//			
//		}
		double curYaw = gyro.getYaw();
		while(curYaw != heading && robot.isEnabled())
		{
			robot.driveSys.tankDrive(speed, -speed);
		}
	}
}
