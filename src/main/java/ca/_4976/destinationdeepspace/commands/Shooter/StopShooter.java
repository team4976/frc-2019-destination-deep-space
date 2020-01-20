package ca._4976.destinationdeepspace.commands.Shooter;

import ca._4976.destinationdeepspace.subsystems.Shooter;
import edu.wpi.first.wpilibj.command.Command;

public class StopShooter extends Command {
    @Override
    protected void initialize(){
        Shooter.stopShooter();
    }
    @Override
    protected boolean isFinished() {
        return true;
    }
}
