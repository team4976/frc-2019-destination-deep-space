package ca._4976.destinationdeepspace.commands;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class HPRelease extends Command {
    //to pull in hatch panel cylinders
    @Override
    protected void initialize(){
        Robot.intake.releaseGear();
    }
    @Override
    protected boolean isFinished() {
        return true;
    }
}
