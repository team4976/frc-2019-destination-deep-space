package ca._4976.destinationdeepspace.commands;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;
// The goal of this command is to change the state of the hatch panel mechanism
public class HatchPanel extends Command {
    @Override
    protected void initialize(){
        Robot.intake.choose();
    }
    @Override
    protected boolean isFinished() {
        return true;
    }
}
