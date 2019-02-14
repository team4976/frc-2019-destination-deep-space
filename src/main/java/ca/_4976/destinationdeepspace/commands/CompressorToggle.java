package ca._4976.destinationdeepspace.commands;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class CompressorToggle extends Command {
    @Override
    protected void initialize(){
        Robot.compressorSwitch.compressorOn();
    }
    @Override
    protected boolean isFinished() {
        return false;
    }
    @Override
    protected void end(){
        Robot.compressorSwitch.compressorOff();
    }
}
