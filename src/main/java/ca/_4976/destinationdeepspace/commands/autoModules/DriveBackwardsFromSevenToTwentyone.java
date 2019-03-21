package ca._4976.destinationdeepspace.commands.autoModules;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;

// The goal of this command is to drive from Twelve to twenty
public class DriveBackwardsFromSevenToTwentyone extends Command {
    @Override
    protected void initialize(){Robot.drive.resetEncoders();}
    //setting the encoder positions for the dive PID
    @Override protected void execute() { Robot.drive.driveToEncoderPos(0,0); } //TODO: change these values

    //upon Reaching set point stop
    @Override
    protected boolean isFinished() {
        return Robot.drive.isAtTarget();
    }

    //stops the drive PID and re-enables user control
    @Override protected void end() { Robot.drive.setUserControlEnabled(true); Robot.drive.stop();}
}
