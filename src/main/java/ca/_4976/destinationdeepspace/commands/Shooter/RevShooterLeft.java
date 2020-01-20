package ca._4976.destinationdeepspace.commands.Shooter;

import ca._4976.destinationdeepspace.subsystems.Shooter;
import edu.wpi.first.wpilibj.command.Command;

import static ca._4976.destinationdeepspace.Robot.controller;
import static ca._4976.destinationdeepspace.Robot.oi;

public class RevShooterLeft extends Command {
    @Override
    protected void initialize(){
        if (!controller.povIsHeldDown(oi.operator, 180)){
            Shooter.revShooterLeft(true);
            Shooter.moveHood(false);
        } else {
            Shooter.revShooterLeft(false);
        }
    }
    @Override
    protected boolean isFinished() {
        return true;
    }
}
