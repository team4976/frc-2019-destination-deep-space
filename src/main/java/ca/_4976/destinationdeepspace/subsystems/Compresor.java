package ca._4976.destinationdeepspace.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Compresor extends Subsystem {
    private Compressor compressor = new Compressor(40);

    public void compressorOn(){
        compressor.start();
    }
    public void compressorOff(){
        compressor.stop();
    }

    @Override
    protected void initDefaultCommand() {

    }
}
