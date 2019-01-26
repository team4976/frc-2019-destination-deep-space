package ca._4976.destinationdeepspace.commands;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class EnablePID extends Command {

    public EnablePID() {}

    @Override protected void initialize() { Robot.drive.enablePID();}

    @Override protected boolean isFinished() { return true; }

}