package ca._4976.destinationdeepspace;

import ca._4976.destinationdeepspace.commands.Reverse;
import ca._4976.destinationdeepspace.commands.Shoot;
import ca._4976.destinationdeepspace.commands.StopShooting;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public final class OI {

    public Joystick driver = new Joystick(0);

    public Joystick operator = new Joystick(1);

    OI() {

        new JoystickButton(driver, 1).whenPressed(new Shoot()); //press once, shoots ball
        new JoystickButton(driver, 2).whenPressed(new Reverse()); //hold down to reverse shooting motors
        new JoystickButton(driver, 3).whenPressed(new StopShooting()); //stop the motors

    }
}