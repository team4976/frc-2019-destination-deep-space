package ca._4976.destinationdeepspace.commands;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;

//Changes the bots drive gear

public class ShiftGear extends Command {
    @Override
    protected void initialize(){
        Robot.drive.gearShift();
    }
    @Override
    protected boolean isFinished() {
        return true;
    }
    protected void end(){}
}
