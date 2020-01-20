package ca._4976.destinationdeepspace.commands.Shooter;

import ca._4976.destinationdeepspace.subsystems.Shooter;
import edu.wpi.first.wpilibj.command.Command;

public class FireBannanas extends Command {
    @Override
    protected void initialize(){
        if (Shooter.rightShoterWheel.getMotorOutputPercent() == 0){
            Shooter.moveLeftBannana(true);
        } else if (Shooter.leftShooterWheel.getMotorOutputPercent() == 0){
            Shooter.moveRightBannana(true);
        } else {
            Shooter.moveRightBannana(true);
            Shooter.moveLeftBannana(true);
        }
    }
    @Override
    protected boolean isFinished() {
        return true;
    }
}
