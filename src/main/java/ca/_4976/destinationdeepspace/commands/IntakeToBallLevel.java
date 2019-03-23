package ca._4976.destinationdeepspace.commands;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class IntakeToBallLevel extends Command {
    @Override
    protected void initialize(){
        Robot.intake.pickupPosition();
        Robot.intake.changeuserControll(true);
//        Robot.intake.intakeArmToBall();
    }
    @Override
    protected boolean isFinished() {
        return Robot.intake.intakeArm.getSelectedSensorPosition()>-2280&&Robot.intake.intakeArm.getSelectedSensorPosition()<-2240;
//        return Robot.intake.intakeArmIsAtTargetBall();
    }
    @Override
    protected void end() {
        Robot.intake.stop();
        Robot.intake.changeuserControll(false);
    }
}
