package ca._4976.destinationdeepspace.commands.autoModules;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;
// The goal of this command is center the bot with the target
public class HorizontalCenter extends Command {
    @Override protected void execute() {
        // Centers
        Robot.vision.center();
    } //TODO: change these values

    //upon Reaching set point stop
    @Override
    protected boolean isFinished() {
        return Robot.vision.isCentered();
    }

    //stops the drive PID and re-enables user control
    @Override protected void end() { Robot.drive.setUserControlEnabled(true); Robot.drive.stop();}
}
