package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Robot;
import org.usfirst.frc.team2473.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraight extends Command {
	public static int K = 5000;
	private int prevTicksLeft;
	private int prevTicksRight;
	private int tickGoal;
	private double leftPower;
	private double rightPower;
    public DriveStraight(int ticks, double leftPow, double rightPow) {
        requires(Robot.drive);
        tickGoal = ticks;
        leftPower = leftPow;
        rightPower = rightPow;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drive.resetEncoders();
    	prevTicksRight = Robot.drive.getEncoderCount(RobotMap.FRONT_RIGHT);
    	prevTicksLeft = Robot.drive.getEncoderCount(RobotMap.BACK_LEFT);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	int currTicksRight = Robot.drive.getEncoderCount(RobotMap.FRONT_RIGHT);
    	double rightPow = 0;
    	if (tickGoal > currTicksRight) {
    		rightPow = rightPower;
    		int delta = currTicksRight - prevTicksRight;
    		
    		prevTicksRight = currTicksRight;
    	}
    	
    	int currClicksLeft = Robot.drive.getEncoderCount(RobotMap.BACK_LEFT);
    	double leftPow = 0;
    	if (tickGoal > currClicksLeft) {
    		leftPow = leftPower;
    		int delta = currClicksLeft - prevTicksLeft;
    		
    		prevTicksLeft = currClicksLeft;
    	}
    	
    	
    	Robot.drive.tankDrive(leftPow, rightPow);
    	System.out.println("BACK RIGHT " + Robot.drive.getEncoderCount(RobotMap.BACK_RIGHT));
		System.out.println("BACK LEFT " + Robot.drive.getEncoderCount(RobotMap.BACK_LEFT));
		System.out.println("FRONT RIGHT " + Robot.drive.getEncoderCount(RobotMap.FRONT_RIGHT));
		System.out.println("FRONT LEFT " + Robot.drive.getEncoderCount(RobotMap.FRONT_LEFT));
		System.out.println("DIFFERENCE " + (Robot.drive.getEncoderCount(RobotMap.BACK_LEFT)-Robot.drive.getEncoderCount(RobotMap.FRONT_RIGHT)));
		System.out.println("----------------------");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.drive.getEncoderCount(RobotMap.FRONT_RIGHT) > tickGoal && Robot.drive.getEncoderCount(RobotMap.BACK_LEFT) > tickGoal;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("---------FINALMENTE-------------");
    	System.out.println("BACK RIGHT " + Robot.drive.getEncoderCount(RobotMap.BACK_RIGHT));
		System.out.println("BACK LEFT " + Robot.drive.getEncoderCount(RobotMap.BACK_LEFT));
		System.out.println("FRONT RIGHT " + Robot.drive.getEncoderCount(RobotMap.FRONT_RIGHT));
		System.out.println("FRONT LEFT " + Robot.drive.getEncoderCount(RobotMap.FRONT_LEFT));
		System.out.println("DIFFERENCE " + (Robot.drive.getEncoderCount(RobotMap.BACK_LEFT)-Robot.drive.getEncoderCount(RobotMap.FRONT_RIGHT)));
		System.out.println("----------------------");
    	Robot.drive.tankDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
