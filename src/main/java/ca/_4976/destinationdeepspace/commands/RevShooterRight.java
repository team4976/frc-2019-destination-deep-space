package ca._4976.destinationdeepspace.commands;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class RevShooterRight extends Command {
    @Override
    protected void initialize(){
        Robot.shooter.revShooter(true);
        System.out.println("Reving");
    }
    @Override
    protected boolean isFinished() {
        return true;
    }
}
