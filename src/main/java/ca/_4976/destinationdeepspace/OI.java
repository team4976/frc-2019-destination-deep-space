package ca._4976.destinationdeepspace;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

// The operator interface of the robot, it has been simplified from the real
// robot to allow control with a single Xbox joystick. As a result, not all
// functionality from the real robot is available.

public final class OI {

    public Joystick driver = new Joystick(0);

    public Joystick operator = new Joystick(1);

    OI() {

        //Dpad sensor for operator controller
        if (operator.getPOV() == 0){}
        else if (operator.getPOV() == 90){}
        else if (operator.getPOV() == 180){}
        else if (operator.getPOV() == 270){}

        //Dpad sensor for driver controller
        if (driver.getPOV() == 0){}
        else if (driver.getPOV() == 90){}
        else if (driver.getPOV() == 180){}
        else if (driver.getPOV() == 270){}
    }
}