package ca._4976.destinationdeepspace.commands;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class DisablePID extends Command {

    public DisablePID() {}

    @Override protected void initialize() { Robot.drive.disablePID();}

    @Override protected boolean isFinished() { return true; }

}