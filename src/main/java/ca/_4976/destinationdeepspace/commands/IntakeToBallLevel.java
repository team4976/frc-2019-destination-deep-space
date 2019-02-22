package ca._4976.destinationdeepspace.commands;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class IntakeToBallLevel extends Command {
    @Override
    protected void initialize(){
        Robot.intake.pickupPosition();
        Robot.intake.isPosition(true);
    }
    @Override
    protected boolean isFinished() {
        return !Robot.intake.intakeLimitSwitch.get();
    }
    protected void end(){
        Robot.intake.isPosition(false); Robot.intake.hold();
    }
}
