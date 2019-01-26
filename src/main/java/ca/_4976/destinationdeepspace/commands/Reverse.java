package ca._4976.destinationdeepspace.commands;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class Reverse extends Command {
    @Override
    protected void execute(){
        Robot.shooter.reverseDaddy();
    }
    @Override
    protected boolean isFinished() {
        return false;
    }
}
