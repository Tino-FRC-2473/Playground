package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI { //this file is a means for storing and communicating with the driver input
	private Joystick throttle;
	private Joystick wheel;
	private Button slow;

	public OI() {
		throttle = new Joystick(2);		
		wheel = new Joystick(0);		
		slow = new JoystickButton(wheel, 0);
	}
	
	public Joystick getThrottle() {
		return throttle;
	}

	public Joystick getWheel() {
		return wheel;
	}

	public boolean getSlow() {
		return slow.get();
	}
}