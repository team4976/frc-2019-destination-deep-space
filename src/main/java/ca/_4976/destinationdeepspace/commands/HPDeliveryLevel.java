package ca._4976.destinationdeepspace.commands;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class HPDeliveryLevel extends Command {
    @Override protected void initialize(){
        Robot.intake.HPPositiion();
        Robot.intake.changeuserControll(true);
//        Robot.intake.intakeArmToHachPanel();
    }
    @Override
    protected boolean isFinished() {
        return Robot.intake.intakeArm.getSelectedSensorPosition()<-2227+5 && Robot.intake.intakeArm.getSelectedSensorPosition()>-2227-25;
//        return Robot.intake.intakeArmIsAtTargetHatch();
    }
    protected void end(){
        Robot.intake.stop();
        Robot.intake.changeuserControll(false);
    }
}
