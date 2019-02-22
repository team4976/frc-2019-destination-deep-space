package ca._4976.destinationdeepspace;

import ca._4976.destinationdeepspace.commands.*;
import ca._4976.destinationdeepspace.commands.autos.DriveShootTest;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

// The operator interface of the robot, it has been simplified from the real
// robot to allow control with a single Xbox joystick. As a result, not all
// functionality from the real robot is available.

public final class OI {

    public Joystick driver = new Joystick(0);

    public Joystick operator = new Joystick(1);

    OI() {
        new JoystickButton(operator, 8).whileHeld(new CompressorToggle());
        new JoystickButton(operator, 2).whenPressed(new shootRight());
        new JoystickButton(operator, 3).whenPressed(new shootLeft());
        new JoystickButton(operator, 1).whenPressed(new HP());

        new JoystickButton(driver,5).whileHeld(new intakeFromGround());
        new JoystickButton(driver,7).whenPressed(new ClimberLeg());
        new JoystickButton(driver, 9).whenPressed(new ShiftGear());
        new JoystickButton(driver, 2).whenPressed(new IntakeToBallLevel());
    }
}
