package ca._4976.destinationdeepspace.commands;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class shootLeft extends Command {
    @Override
    protected void initialize(){
        Robot.shooter.shootHighLeft();
    }
    @Override
    protected boolean isFinished() {
        return true;
    }
}
