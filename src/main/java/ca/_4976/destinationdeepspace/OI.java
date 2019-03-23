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
        new JoystickButton(driver, 9).whenPressed(new ShiftGear());
        new JoystickButton(driver, 1).whenPressed(new IntakeToBallLevel());
        new JoystickButton(driver, 5).whenPressed(new intakeFromGround());
        new JoystickButton(driver, 4).whenPressed(new HP());
        new JoystickButton(driver, 4).whenReleased(new HPRelease());
        new JoystickButton(driver, 3).whenPressed(new HPDeliveryLevel());



        new JoystickButton(operator, 8).whileHeld(new CompressorToggle());
        new JoystickButton(operator, 2).whenPressed(new RevShooterRight());
        new JoystickButton(operator, 3).whenPressed(new RevShooterLeft());

        new JoystickButton(operator, 6).whenPressed(new ToggleClimb());

        //new JoystickButton(operator, 7).whenPressed(new DriveShootTest());
        new JoystickButton(operator, 7).whenPressed(new IntakeReverse());

//        new JoystickButton(operator, 4).whileHeld(new StoptheShooterAim()); TODO: Dont need this right now as we disables the camera
        new JoystickButton(operator, 4).whenPressed(new FireShooterPistons());
        new JoystickButton(operator, 5).whenPressed(new IntakeOverride());

        new JoystickButton(operator, 1).whenPressed(new switchShooterControlmode());
    }
}
