package ca._4976.destinationdeepspace.commands.Climber;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class MoveClimberWithJoystick extends Command {

    public MoveClimberWithJoystick() { requires(Robot.climber); }

    @Override protected void execute() { Robot.climber.moveClimberWithJoystick(Robot.oi.operator); }

    @Override protected boolean isFinished() { return false; }

}