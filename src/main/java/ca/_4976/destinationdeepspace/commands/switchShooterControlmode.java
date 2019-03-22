package ca._4976.destinationdeepspace.commands;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class switchShooterControlmode extends Command {
    //allows the intake to stop and regain regular control after an automation failure
    @Override
    protected void initialize(){
        Robot.shooter.swapControllMode();
    }
    @Override
    protected boolean isFinished() {
        return true;
    }
}
