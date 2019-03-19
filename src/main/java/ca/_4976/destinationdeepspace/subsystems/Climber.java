package ca._4976.destinationdeepspace.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

import static com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput;

public class Climber extends Subsystem {
    //Motor controller for the climbing leg
    public TalonSRX climberLeg = new TalonSRX(50);

    int climberLegTarget = -8000;

    public boolean isClimberLegDown = false;
    DigitalInput climberIsDown = new DigitalInput(5);
    public boolean isLegLocked = false;

    //Moves the climber leg
    public boolean moveLeg() {
        //Going up
        if (isClimberLegDown){
            climberLeg.set(PercentOutput, -0.8);
            System.out.println("Going up");
            if (climberLeg.getSelectedSensorPosition() >= 0){
                climberLeg.set(PercentOutput, 0);
                return true;
            }

            else {
                return false;
            }

        }
        //Going down
        else {
            climberLeg.set(PercentOutput, 0.8);
            System.out.println("Going down");
            if (climberLeg.getSelectedSensorPosition() < climberLegTarget || climberIsDown.get()) {
                climberLeg.set(PercentOutput, 0.0);
                return true;

            } else {
                return false;
            }
        }
    }

    @Override
    protected void initDefaultCommand() {}

    @Override
    public void initSendable(SendableBuilder builder){
        builder.addDoubleProperty("ClimberEncoder", () -> climberLeg.getSelectedSensorPosition(), (x) -> {});
    }
}