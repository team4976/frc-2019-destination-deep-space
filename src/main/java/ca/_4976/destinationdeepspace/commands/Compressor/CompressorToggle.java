package ca._4976.destinationdeepspace.commands.Compressor;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class CompressorToggle extends Command {
    boolean flag = true;
    @Override
    protected void initialize(){
        if (flag) {
            Robot.compressor.compressorOff();
            flag = !flag;
        }
        else {
            Robot.compressor.compressorOn();
            flag = !flag;
        }
    }
    @Override
    protected boolean isFinished() {
        return true;
    }
}
