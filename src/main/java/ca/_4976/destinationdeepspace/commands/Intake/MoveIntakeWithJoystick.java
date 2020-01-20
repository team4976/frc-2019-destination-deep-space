package ca._4976.destinationdeepspace.commands.Intake;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class MoveIntakeWithJoystick extends Command {

    public MoveIntakeWithJoystick() { requires(Robot.intakeArm); }

    @Override protected void execute() { Robot.intakeArm.moveIntakeArmWithJoystick(Robot.oi.driver); }

    @Override protected boolean isFinished() { return false; }

}