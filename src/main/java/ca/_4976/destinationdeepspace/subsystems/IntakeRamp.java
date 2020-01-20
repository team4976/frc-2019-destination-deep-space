package ca._4976.destinationdeepspace.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

import static com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput;

public class IntakeRamp extends Subsystem {
    public TalonSRX intakeWheels = new TalonSRX(39);
    TalonSRX conveyor = new TalonSRX(38);

    public void runIntake(int speed) {
        intakeWheels.set(PercentOutput, speed);
        conveyor.set(PercentOutput, speed);
    }

    @Override
    protected void initDefaultCommand() {

    }
}
