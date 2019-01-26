package ca._4976.destinationdeepspace.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem {

    NetworkTable climber = NetworkTableInstance.getDefault().getTable("Climber");

    @Override
    protected void initDefaultCommand() {

    }
}
