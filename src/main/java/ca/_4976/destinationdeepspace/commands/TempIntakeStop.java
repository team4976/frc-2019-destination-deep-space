package ca._4976.destinationdeepspace.commands;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class TempIntakeStop extends Command {
    @Override
    protected void initialize(){
        Robot.intake.pickupPosition();
    }
    @Override
    protected boolean isFinished() {
        System.out.println(Robot.intake.cherrySensor.get());
        return !Robot.intake.cherrySensor.get();
    }
    protected void end(){
        Robot.intake.hold();
    }
}
