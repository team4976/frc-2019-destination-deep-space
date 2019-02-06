package ca._4976.destinationdeepspace.commands;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ClimberLeg  extends Command {

    @Override protected void execute() { Robot.climber.moveLeg();}

    @Override protected boolean isFinished() { return Robot.climber.moveLeg(); }
}