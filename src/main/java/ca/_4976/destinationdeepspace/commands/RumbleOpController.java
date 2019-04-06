package ca._4976.destinationdeepspace.commands;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class RumbleOpController extends Command {
    @Override
    protected void initialize(){
        Robot.vision.rumbleController(Robot.oi.operator);
    }
    @Override
    protected boolean isFinished() {
        return true;
    }

    protected void end(){}
}
