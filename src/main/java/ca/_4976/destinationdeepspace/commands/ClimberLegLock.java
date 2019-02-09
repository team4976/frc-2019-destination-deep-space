package ca._4976.destinationdeepspace.commands;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ClimberLegLock extends Command {

    @Override protected void execute() { Robot.climber.legLock();}

    @Override protected boolean isFinished() { return Robot.climber.legLock(); }
}