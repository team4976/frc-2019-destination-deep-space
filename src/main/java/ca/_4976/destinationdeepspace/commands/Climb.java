package ca._4976.destinationdeepspace.commands;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;

//This command causes the robot to enter an automatic platform-climbing
//mode. Teleop override will be allowed.

public class Climb extends Command {

    public Climb() { requires(Robot.climber); }

    @Override protected void execute() { Robot.climber.beginClimb(); }

    @Override protected boolean isFinished() { return false; }

    @Override protected void end() { Robot.climber.stopClimbing(); }
}
