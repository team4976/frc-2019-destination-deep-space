package ca._4976.destinationdeepspace.commands;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ToggleClimb extends Command {
    @Override
    protected void initialize(){
        Robot.intake.climbOrNormal();
    }
    @Override
    protected boolean isFinished() {
        return true;
    }
}
