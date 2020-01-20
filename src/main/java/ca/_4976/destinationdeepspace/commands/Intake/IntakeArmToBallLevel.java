package ca._4976.destinationdeepspace.commands.Intake;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class IntakeArmToBallLevel extends Command {
    protected void initialize(){
        Robot.intakeArm.moveToSetPoint(2550);
    }
    @Override
    protected boolean isFinished() {
        return true;
    }
}
