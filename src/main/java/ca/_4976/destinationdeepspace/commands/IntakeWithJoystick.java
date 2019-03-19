package ca._4976.destinationdeepspace.commands;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;

// This command allows joystick to move the intake. It is always running
// except when interrupted by another command.

public class IntakeWithJoystick extends Command {

    public IntakeWithJoystick() { requires(Robot.intake); }

    @Override protected void execute() { Robot.intake.moveIntakeArmWithJoystick(Robot.oi.driver); }

    @Override protected boolean isFinished() { return false; }

}