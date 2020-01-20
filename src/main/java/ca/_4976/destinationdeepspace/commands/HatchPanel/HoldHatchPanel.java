package ca._4976.destinationdeepspace.commands.HatchPanel;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;


public class HoldHatchPanel extends Command {

    public HoldHatchPanel() { requires(Robot.hatchPanelMech); }

    @Override protected void execute() {
        if (Robot.controller.axisIsHeldDown(Robot.oi.operator, 3)) Robot.hatchPanelMech.gripPanel(true);
        else Robot.hatchPanelMech.gripPanel(false);

        if (Robot.controller.axisIsHeldDown(Robot.oi.operator, 2)) Robot.hatchPanelMech.pushPanel(true);
        else Robot.hatchPanelMech.pushPanel(false);
    }

    @Override protected boolean isFinished() { return false; }

}