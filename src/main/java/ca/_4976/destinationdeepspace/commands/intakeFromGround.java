package ca._4976.destinationdeepspace.commands;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class intakeFromGround extends Command {
    @Override
    protected void initialize(){
        Robot.intake.pickUpBall();
    }
    @Override
    protected boolean isFinished() {
        return false;
    }
    protected void end(){
        Robot.intake.end();
    }
}