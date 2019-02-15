package ca._4976.destinationdeepspace.autonomous;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;

// The goal of this command is to drive foreward off the HAB until the vision system sees a target
//or if a specific encoder value is reached, both cases will stop the robot.

public class DriveForwardsFromPlatformToLeftSide extends Command {
    // Sets the camera servo motor to the right
    @Override
    protected void initialize() {
        Robot.vision.cameraRight();
    }

    //setting the encoder positions for the dive PID
    @Override
    protected void execute() {
        Robot.drive.driveToEncoderPos(0, 0);
    } //TODO: change these values

    //upon seeing a vision target, stops the robot
    @Override
    protected boolean isFinished() {
        return Robot.vision.stopWithVision();
    }

    //stops the drive PID and re-enables user control
    @Override
    protected void end() {
        Robot.drive.setUserControlEnabled(true);
        Robot.drive.stop();
    }
}