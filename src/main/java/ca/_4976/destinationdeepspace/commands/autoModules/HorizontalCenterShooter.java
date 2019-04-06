package ca._4976.destinationdeepspace.commands.autoModules;

import ca._4976.destinationdeepspace.Robot;
import ca._4976.destinationdeepspace.commands.OpControllerRumbleGroup;
import edu.wpi.first.wpilibj.command.Command;
// The goal of this command is center the shooter with the target
public class HorizontalCenterShooter extends Command {
    @Override protected void execute() {
        // Centers
        Robot.vision.centerShooter();
    }

    //upon Reaching set point stop
    @Override
    protected boolean isFinished()
    {
        return Robot.vision.isCenteredShooter() || Robot.shooter.cancil;
    }

    //stops the drive PID and re-enables user control
    @Override protected void end() {
        new OpControllerRumbleGroup().start();
        Robot.drive.setUserControlEnabled(true); Robot.drive.stop();}
}
