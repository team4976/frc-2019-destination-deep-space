package ca._4976.destinationdeepspace.commands;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class FireShooterPistons extends Command {
    @Override
    protected void initialize(){
        Robot.shooter.FirePneumatics();
    }
    @Override
    protected boolean isFinished() {
        return true;
    }
}
