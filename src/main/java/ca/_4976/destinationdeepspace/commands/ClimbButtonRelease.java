package ca._4976.destinationdeepspace.commands;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ClimbButtonRelease extends Command {

    public ClimbButtonRelease() { requires(Robot.climber); }

    @Override protected void execute() {
        if (Robot.bumperDown || Robot.isOtherwiseClimbing){
            Robot.climber.stopClimbing();
        }
    }

    @Override protected boolean isFinished() { return false; }

    @Override protected void end() { }
}
