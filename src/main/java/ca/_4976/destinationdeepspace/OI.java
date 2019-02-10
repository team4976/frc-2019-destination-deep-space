package ca._4976.destinationdeepspace;

import ca._4976.destinationdeepspace.commands.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
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
        new JoystickButton(driver, 1).whenPressed(new HP());

//        new JoystickButton(operator, 1).whenPressed(new intakeClimb());
//        new JoystickButton(operator, 2).whenPressed(new intakeHPPickup()); //TODO: Re add sensor based movemnt
//        new JoystickButton(operator, 4).whenPressed(new intakeHome());
        new JoystickButton(operator, 1).whenPressed(new TempIntakDown());
        new JoystickButton(operator, 1).whenReleased(new TempIntakeStop());
        new JoystickButton(operator, 4).whenPressed(new TempIntakeUp());
        new JoystickButton(operator, 4).whenReleased(new TempIntakeStop());

        new JoystickButton(driver, 2).whenPressed(new shootRight());
        new JoystickButton(driver, 3).whenPressed(new shootLeft());

        //Dpad sensor for operator controller
        if (operator.getPOV() == 0){
            new SetCameraForwards();
        }
        else if (operator.getPOV() == 90){
            new SetCameraRight();
        }

        else if (operator.getPOV() == 180){}
        else if (operator.getPOV() == 270){
            new SetCameraLeft();
        }

        //Dpad sensor for driver controller
        if (driver.getPOV() == 0){

            System.out.println("Dpa worked");
        }
        else if (driver.getPOV() == 90){}
        else if (driver.getPOV() == 180){}
        else if (driver.getPOV() == 270){}

    }
}