package ca._4976.destinationdeepspace.commands;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;

// This command allows joystick to drive the robot. It is always running
// except when interrupted by another command.

public class DriveWithJoystick extends Command {

    public DriveWithJoystick() { requires(Robot.drive); }

    @Override protected void execute() { Robot.drive.arcadeDrive(Robot.oi.driver); }

    @Override protected boolean isFinished() { return false; }

    @Override protected void end() { Robot.drive.stop(); }
}
