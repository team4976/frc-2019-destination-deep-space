package ca._4976.destinationdeepspace.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drive extends Subsystem {

    NetworkTable drive = NetworkTableInstance.getDefault().getTable("Drive");

    @Override
    protected void initDefaultCommand() {

    }
}
