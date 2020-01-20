package ca._4976.destinationdeepspace.commands.Vision;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class HorizontalCenterLeftCam extends Command {
    @Override protected void initialize() {
        Robot.drive.setUserControl(false);
    }

    @Override protected void execute() {
        Robot.vision.centerShooterPID(false);
        Robot.vision.toggleLEDOn(false);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override protected void end() {
        Robot.drive.setUserControl(true);
        Robot.vision.toggleLEDOff(false);
        Robot.drive.stop();}
}
