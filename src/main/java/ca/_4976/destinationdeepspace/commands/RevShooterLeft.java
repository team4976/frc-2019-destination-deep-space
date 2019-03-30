package ca._4976.destinationdeepspace.commands;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class RevShooterLeft extends Command {
    @Override
    protected void initialize(){
        if (Robot.oi.operator.getPOV() == 180) {
            Robot.shooter.areYouShootingHigh();
        }
        Robot.vision.cameraLeft();
        Robot.shooter.revShooter(false);
    }
    @Override
    protected boolean isFinished() {
        return true;
    }
}
