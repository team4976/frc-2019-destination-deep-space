package ca._4976.destinationdeepspace.commands.autoModules;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;

// The goal of this command is to drive foreward off the HAB until
// a specific encoder value is reached

public class DriveForwardsFromTwoToThirteen extends Command {
    // Sets the camera servo motor to the right
    @Override
    protected void initialize() {
        Robot.vision.cameraLeft();
        Robot.drive.resetEncoders();
    }

    //setting the encoder positions for the dive PID
    @Override
    protected void execute() {
        Robot.drive.driveToEncoderPos(0,0);
    }

    //reaching encoder pos stop the bot
    @Override
    protected boolean isFinished() {
        return Robot.drive.isAtTarget();
    }

    //stops the drive PID and re-enables user control
    @Override
    protected void end() {
        Robot.drive.setUserControlEnabled(true);
        Robot.drive.stop();
    }
}