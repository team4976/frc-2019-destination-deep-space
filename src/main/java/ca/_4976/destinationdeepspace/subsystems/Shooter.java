package ca._4976.destinationdeepspace.subsystems;

import ca._4976.destinationdeepspace.Robot;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {

    NetworkTable shooter = NetworkTableInstance.getDefault().getTable("Shooter");

    @Override
    protected void initDefaultCommand() {

    }
}