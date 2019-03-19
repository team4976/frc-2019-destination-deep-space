package ca._4976.destinationdeepspace.commands;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class HPDeliveryLevel extends Command {
    @Override protected void initialize(){
        Robot.intake.HPPositiion();
        Robot.intake.changeuserControll(true);
    }
    @Override
    protected boolean isFinished() {
        return Robot.intake.intakeEncoder.get()<-705&&Robot.intake.intakeEncoder.get()>-730;
    }
    protected void end(){
        Robot.intake.stop();
        Robot.intake.changeuserControll(false);
    }
}
