package ca._4976.destinationdeepspace.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem {

    NetworkTable intake = NetworkTableInstance.getDefault().getTable("Intake");

    @Override
    protected void initDefaultCommand() {

    }
}
