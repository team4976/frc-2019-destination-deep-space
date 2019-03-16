package ca._4976.destinationdeepspace.commands;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;

//Sets the rpm of the left shooter
public class rpmLeft extends Command {
    @Override
    protected void initialize(){
        Robot.shooter.rpmLeft();
    }
    @Override
    protected boolean isFinished() {
        return true;
    }
}
