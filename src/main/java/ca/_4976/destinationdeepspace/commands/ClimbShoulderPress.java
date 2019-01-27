package ca._4976.destinationdeepspace.commands;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;

//The entire purpose of this file is to switch a boolean
//in the Robot file to 'true,' indicating that the right bumper
//is being pressed. There's probably a cleaner way to do this.

public class ClimbShoulderPress extends Command {

    public ClimbShoulderPress() { requires(Robot.climber); }

    @Override protected void execute() { Robot.bumperDown = true; }

    @Override protected boolean isFinished() { return false; }

    @Override protected void end() { }
}
