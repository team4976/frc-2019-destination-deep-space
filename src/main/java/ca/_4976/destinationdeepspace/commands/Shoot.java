package ca._4976.destinationdeepspace.commands;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class Shoot extends Command {
    @Override
    protected void initialize(){
        Robot.shooterCock.shootDaddy();
    }
    @Override
    protected boolean isFinished() {
        return false;
    }
}
