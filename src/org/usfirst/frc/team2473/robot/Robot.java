package org.usfirst.frc.team2473.robot;


import org.usfirst.frc.team2473.robot.commands.DriveStraight;
import org.usfirst.frc.team2473.robot.subsystems.Drivetrain;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends TimedRobot {
	public static Drivetrain drive;
	public static OI oi;
	
	
	@Override
	public void robotInit() {
		//autoSystem = new AutonomousSubsystem();
		
		oi = new OI();
		drive = new Drivetrain();
		
		UsbCamera camera0 = CameraServer.getInstance().startAutomaticCapture("Cube View", 0);
		camera0.setBrightness(75);
		camera0.setResolution(640, 480);
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture("Front View", 1);
		camera.setBrightness(75);
		camera.setResolution(640, 480);
	}

	@Override
	public void disabledInit() {
		
		Scheduler.getInstance().removeAll();
		
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		System.out.println("Starting drive straight...");
		(new DriveStraight(8000, 0.4, 0.41)).start();
		System.out.println("Drive straight finished");
		
		
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		
	}

	@Override
	public void teleopInit() {

	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() {
	}
}