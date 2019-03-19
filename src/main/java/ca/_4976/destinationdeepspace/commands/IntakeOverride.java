package ca._4976.destinationdeepspace.commands;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class IntakeOverride extends Command {
    //allows the intake to stop and regain regular control after an automation failure
    @Override
    protected void initialize(){
        Robot.intake.stop();
        Robot.intake.changeuserControll(false);
    }
    @Override
    protected boolean isFinished() {
        return true;
    }
}
