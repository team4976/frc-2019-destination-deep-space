package ca._4976.destinationdeepspace.commands.Vision;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class HorizontalCenterRightCam extends Command {
    @Override protected void initialize() {
        Robot.drive.setUserControl(false);
    }

    @Override protected void execute() {
        Robot.vision.centerShooterPID(true);
        Robot.vision.toggleLEDOn(true);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override protected void end() {
        Robot.drive.setUserControl(true);
        Robot.vision.toggleLEDOff(true);
        Robot.drive.stop();}
}
