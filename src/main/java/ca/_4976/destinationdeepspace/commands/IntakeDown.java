package ca._4976.destinationdeepspace.commands;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class IntakeDown extends Command {
    @Override
    protected void initialize(){
        Robot.intake.IntakeDown();
    }
    @Override
    protected boolean isFinished() {
        return false;
    }
    protected void end(){
        Robot.intake.hold();
    }
}
