package ca._4976.destinationdeepspace.commands;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;
// The goal of this command is to fire the shooter pneumatics for the ball to fire to the right
public class FireShooterRight extends Command {
    @Override
    protected void initialize(){
        System.out.println("Delay Finished");
        Robot.shooter.shootLowRight();
    }
    @Override
    protected boolean isFinished() {
        return true;
    }
}
