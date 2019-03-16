package ca._4976.destinationdeepspace.commands;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;

// The goal of this command is to fire the shooter pneumatics for the ball to fire to the left
public class FireShooterLeft extends Command {
    @Override
    protected void initialize(){
        Robot.shooter.shootLowLeft();
    }
    @Override
    protected boolean isFinished() {
        return true;
    }
}
