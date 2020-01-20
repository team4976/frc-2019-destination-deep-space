package ca._4976.destinationdeepspace.subsystems;

import ca._4976.destinationdeepspace.commands.HatchPanel.HoldHatchPanel;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class HatchPanelMech extends Subsystem {
    Solenoid hatchPanelGripper = new Solenoid(40, 2);
    Solenoid hatchPanelPistons = new Solenoid(40, 5);

    public void gripPanel(boolean grip) { hatchPanelGripper.set(grip); }
    public void pushPanel(boolean push) { hatchPanelPistons.set(push); }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new HoldHatchPanel());
    }
}
