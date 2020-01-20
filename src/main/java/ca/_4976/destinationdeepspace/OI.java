package ca._4976.destinationdeepspace;

import ca._4976.destinationdeepspace.commands.Compressor.CompressorToggle;
import ca._4976.destinationdeepspace.commands.DriveTrain.ShiftGear;
import ca._4976.destinationdeepspace.commands.Intake.*;
import ca._4976.destinationdeepspace.commands.Shooter.FireSequence;
import ca._4976.destinationdeepspace.commands.Shooter.RevShooterLeft;
import ca._4976.destinationdeepspace.commands.Shooter.RevShooterRight;
import ca._4976.destinationdeepspace.commands.Shooter.StopShooter;
import ca._4976.destinationdeepspace.commands.Vision.HorizontalCenterLeftCam;
import ca._4976.destinationdeepspace.commands.Vision.HorizontalCenterRightCam;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;

// The operator interface of the robot, it has been simplified from the real
// robot to allow control with Xbox joysticks. As a result, not all
// functionality from the real robot is available.

public final class OI {

    public Joystick driver = new Joystick(0);

    public Joystick operator = new Joystick(1);

    OI() {
        new JoystickButton(driver, 1).whenPressed(new IntakeArmToBallLevel());
        new JoystickButton(driver, 3).whenPressed(new IntakeArmToHatchLevel());
        new JoystickButton(driver, 5).whenPressed(new IntakeBall());
        new JoystickButton(driver, 9).whenPressed(new ShiftGear());
        new POVButton(driver, 90).whileHeld(new HorizontalCenterRightCam());
        new POVButton(driver, 270).whileHeld(new HorizontalCenterLeftCam());

        new JoystickButton(operator, 2).whenPressed(new RevShooterRight());
        new JoystickButton(operator, 2).whenReleased(new FireSequence());
        new JoystickButton(operator, 3).whenPressed(new RevShooterLeft());
        new JoystickButton(operator, 3).whenReleased(new FireSequence());
        new JoystickButton(operator, 5).whenPressed(new StopShooter());
        new JoystickButton(operator, 6).whenPressed(new ToggleRentalMode());
        new JoystickButton(operator, 7).whenPressed(new ReverseIntake());
        new JoystickButton(operator, 8).whenPressed(new CompressorToggle());
    }
}
