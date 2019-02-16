package ca._4976.destinationdeepspace;

import ca._4976.destinationdeepspace.commands.*;
import ca._4976.destinationdeepspace.commands.autoModules.DriveForwardsFromGroundToLeftSide;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

// The operator interface of the robot, it has been simplified from the real
// robot to allow control with a single Xbox joystick. As a result, not all
// functionality from the real robot is available.

public final class OI {

    public Joystick driver = new Joystick(0);

    public Joystick operator = new Joystick(1);

    OI() {

        new JoystickButton(driver,4).whileHeld(new intakeFromGround());
       // new JoystickButton(driver, 1).whenPressed(new HP());

//        new JoystickButton(operator, 1).whenPressed(new intakeClimb());
//        new JoystickButton(operator, 2).whenPressed(new intakeHPPickup()); //TODO: Re add sensor based movemnt
//        new JoystickButton(operator, 4).whenPressed(new intakeHome());
        new JoystickButton(operator, 1).whileHeld(new TempIntakDown()); //1
        new JoystickButton(operator, 2).whenReleased(new TempIntakeStop());
        new JoystickButton(operator, 4).whileHeld(new TempIntakeUp());//4

        new JoystickButton(driver, 2).whenPressed(new shootRight());
        new JoystickButton(driver, 3).whenPressed(new shootLeft());

        new JoystickButton(driver, 8).whileHeld(new CompressorToggle());
        new JoystickButton(driver,1).whenPressed(new ClimberLeg()); //2,6
        new JoystickButton(driver,6).whenPressed(new ClimberLegLock());//3,5

        new JoystickButton(operator, 3).whenPressed(new DriveForwardsFromGroundToLeftSide());




    }
}