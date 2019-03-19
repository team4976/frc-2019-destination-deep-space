package ca._4976.destinationdeepspace.commands;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class IntakeToBallLevel extends Command {
    @Override
    protected void initialize(){
        Robot.intake.pickupPosition();
        Robot.intake.changeuserControll(true);
    }
    @Override
    protected boolean isFinished() {
        return Robot.intake.intakeEncoder.get()<-1000&&Robot.intake.intakeEncoder.get()>-1040;
//        return !Robot.intake.intakeLimitSwitch.get();
    }
    @Override
    protected void end() {
        Robot.intake.stop();
        Robot.intake.changeuserControll(false);
    }
}
