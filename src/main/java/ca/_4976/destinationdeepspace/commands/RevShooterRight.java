package ca._4976.destinationdeepspace.commands;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class RevShooterRight extends Command {
    @Override
    protected void initialize(){
        if (Robot.oi.operator.getPOV() == 180) {
            Robot.shooter.areYouShootingHigh();
        }
        Robot.shooter.hood.set(true);
        Robot.vision.cameraRight();
        Robot.shooter.revShooter(true);
    }
    @Override
    protected boolean isFinished() {
        return true;
    }
}
