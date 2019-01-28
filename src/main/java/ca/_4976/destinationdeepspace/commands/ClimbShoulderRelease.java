package ca._4976.destinationdeepspace.commands;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;

//A command that is called when the right bumper is released,
//which indicates the release of the right bumper to the rest
//of the code.
public class ClimbShoulderRelease extends Command {

    public ClimbShoulderRelease() { requires(Robot.climber); }

    @Override protected void execute() { Robot.bumperDown = false; }

    @Override protected boolean isFinished() { return false; }

    @Override protected void end() { }
}
