package ca._4976.destinationdeepspace.commands;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class HP extends Command {
    //to eject hatch panel cylinders
    @Override
    protected void initialize(){
        Robot.intake.holdGear();
    }
    @Override
    protected boolean isFinished() {
        return true;
    }
}
