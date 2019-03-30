package ca._4976.destinationdeepspace.commands;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class HPForksReverse extends Command {
    @Override
    protected void initialize(){
        Robot.intake.retractHatchPanelForks();
    }
    @Override
    protected boolean isFinished() {
        return true;
    }
}
