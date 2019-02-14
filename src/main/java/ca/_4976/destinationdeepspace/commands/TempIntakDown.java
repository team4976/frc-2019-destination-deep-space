package ca._4976.destinationdeepspace.commands;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class TempIntakDown extends Command {
    @Override
    protected void initialize(){
        Robot.intake.tempintakeDown();
    }
    @Override
    protected boolean isFinished() {
        return false;
        //return Robot.intake.intakeArm.getOutputCurrent() > 0.125;
    }
    protected void end(){
        Robot.intake.hold();
    }
}
