package ca._4976.destinationdeepspace.subsystems;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CompressorSwitch extends Subsystem {
    @Override
    protected void initDefaultCommand() {
        Robot.compressor.stop();
    }
    public void compressorOn(){
        Robot.compressor.start();
    }
    public void compressorOff(){
        Robot.compressor.stop();
    }
}
