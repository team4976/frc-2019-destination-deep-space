package ca._4976.destinationdeepspace.commands;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;

//A command that is called when the 'A' button is released,
//which puts the driver back in control of the bot. As of now,
//the bumper must be continually held while releasing the 'A' button.

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
