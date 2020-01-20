package ca._4976.destinationdeepspace.commands.Shooter;

import ca._4976.destinationdeepspace.subsystems.Shooter;
import edu.wpi.first.wpilibj.command.Command;

import static ca._4976.destinationdeepspace.Robot.controller;
import static ca._4976.destinationdeepspace.Robot.oi;

public class RevShooterRight extends Command {
    @Override
    protected void initialize(){
        if (!controller.povIsHeldDown(oi.operator, 180)){
            Shooter.revShooterRight(true);
            Shooter.moveHood(true);
        } else {
            Shooter.revShooterRight(false);
        }
    }
    @Override
    protected boolean isFinished() {
        return true;
    }
}
