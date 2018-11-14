package org.usfirst.frc.team2473.robot.subsystems;

import org.usfirst.frc.team2473.framework.Devices;
import org.usfirst.frc.team2473.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {

	WPI_TalonSRX frontRight;
	WPI_TalonSRX frontLeft;
	WPI_TalonSRX backRight;
	WPI_TalonSRX backLeft;
	
	public Drivetrain( ) {
		frontRight = Devices.getInstance().getTalon(RobotMap.FRONT_RIGHT);
		frontLeft = Devices.getInstance().getTalon(RobotMap.FRONT_LEFT);
		backRight = Devices.getInstance().getTalon(RobotMap.BACK_RIGHT);
		backLeft = Devices.getInstance().getTalon(RobotMap.BACK_LEFT);
	}
	
	public void resetEncoders() {
		frontRight.setSelectedSensorPosition(0, 0, 5);
		frontLeft.setSelectedSensorPosition(0, 0, 5);
		backRight.setSelectedSensorPosition(0, 0, 5);
		backLeft.setSelectedSensorPosition(0, 0, 5);
	}
	
	private void driveTalon(WPI_TalonSRX t, double pow) {
		double p = pow;
		if (pow > 1) p = 1;
		else if (pow < -1) p = -1;
		else if (pow < 0.15 && pow > 0) p = 0.15;
		else if (pow > -0.15 && pow < 0) p = -0.15;
		t.set(p);
		
		System.out.println(t.getDeviceID()+" - " + p);
	}
	
	public void driveLeft(double pow) {
		driveTalon(frontLeft, pow);
		driveTalon(backLeft, pow);
	}
	
	public void driveRight(double pow) {
		driveTalon(frontRight, pow);
		driveTalon(backRight, pow);
	}
	
	public void tankDrive(double leftPow, double rightPow) {
		driveLeft(leftPow);
		driveRight(-rightPow);
	}
	
	public int getEncoderCount(int id) {
		if (id == RobotMap.FRONT_RIGHT) return Math.abs(frontRight.getSelectedSensorPosition(0));
		else if (id == RobotMap.FRONT_LEFT) return Math.abs(frontLeft.getSelectedSensorPosition(0));
		else if (id == RobotMap.BACK_RIGHT) return Math.abs(backRight.getSelectedSensorPosition(0));
		else if (id == RobotMap.BACK_LEFT) return Math.abs(backLeft.getSelectedSensorPosition(0));
		return 0;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
}

