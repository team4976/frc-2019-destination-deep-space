package ca._4976.destinationdeepspace.commands;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;

//This initiates a climb command if both the right bumper and
//'A' button are being held down.

public class ClimbButtonPress extends Command {

    public ClimbButtonPress() { requires(Robot.climber); }

    @Override protected void execute() {
        if (Robot.bumperDown){
            Robot.climber.beginClimb();
        }
    }

    @Override protected boolean isFinished() { return false; }

    @Override protected void end() { }
}
