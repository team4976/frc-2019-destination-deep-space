package ca._4976.destinationdeepspace.commands;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ResetEncoders extends Command {

    public ResetEncoders() {}

    @Override protected void initialize() { Robot.drive.resetEncoders();}

    @Override protected boolean isFinished() { return true; }
}
