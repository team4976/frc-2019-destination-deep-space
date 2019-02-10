package ca._4976.destinationdeepspace;

import ca._4976.destinationdeepspace.commands.shootLeft;
import ca._4976.destinationdeepspace.commands.shootRight;
import edu.wpi.first.wpilibj.Joystick;

// The operator interface of the robot, it has been simplified from the real
// robot to allow control with a single Xbox joystick. As a result, not all
// functionality from the real robot is available.

public final class OI {

    public Joystick driver = new Joystick(0);

    public Joystick operator = new Joystick(1);

    OI() {
//        new JoystickButton(driver, 2).whenPressed(new shootRight());
//        new JoystickButton(driver, 3).whenPressed(new shootLeft());
        //Dpad sensor for operator controller

    }
}