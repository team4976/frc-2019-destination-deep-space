package ca._4976.destinationdeepspace.commands.Intake;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class IntakeBall extends Command {
    @Override
    protected void initialize () {
        double speed = Robot.intakeRamp.intakeWheels.getMotorOutputPercent();
        if (speed < -0.5 || speed > 0.5){
            Robot.intakeRamp.runIntake(0);
        } else {
            Robot.intakeRamp.runIntake(1);
        }
    }
    @Override
    protected boolean isFinished() {
        return true;
    }
}
