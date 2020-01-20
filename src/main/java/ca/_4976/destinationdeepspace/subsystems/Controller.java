package ca._4976.destinationdeepspace.subsystems;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Controller extends Subsystem {

    public void rumble(Joystick joy, int rumbleValue) {
        joy.setRumble(GenericHID.RumbleType.kLeftRumble, rumbleValue);
        joy.setRumble(GenericHID.RumbleType.kRightRumble, rumbleValue);
    }

    public boolean axisIsHeldDown(Joystick joy, int axis){
        double joyVal = joy.getRawAxis(axis);
        return joyVal > 0.5 || joyVal < -0.5;
    }

    public boolean povIsHeldDown(Joystick joy, int pov){
        return joy.getPOV() == pov;
    }

    @Override
    protected void initDefaultCommand() { }
}
