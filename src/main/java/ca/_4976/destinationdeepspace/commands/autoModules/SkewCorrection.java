package ca._4976.destinationdeepspace.commands.autoModules;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;
// The goal of this command is centerShooter the bot with the target
public class SkewCorrection extends Command {
    @Override protected void execute() {
        Robot.vision.skewCorrection();
    }

    //upon Reaching set point stop
    @Override
    protected boolean isFinished() {
        return Robot.vision.skewCorrection() || Robot.shooter.cancil;
    }

    //stops the drive PID and re-enables user control
    @Override protected void end() { Robot.drive.stop();}
}
