package ca._4976.destinationdeepspace.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

import static com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput;

public class Climber extends Subsystem {
    //Motor controller for the climbing leg
    public TalonSRX climberLeg = new TalonSRX(50);

    public boolean isClimberLegDown = false;
    private DigitalInput climberIsDown = new DigitalInput(5);

    //Moves the climber leg
    public boolean moveLeg() {
        //Going up
        if (isClimberLegDown){
            climberLeg.set(PercentOutput, -0.5);
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
            climberLeg.set(PercentOutput, 0.5);
            System.out.println("Going down");
            int climberLegTarget = -6250;
            if (climberLeg.getSelectedSensorPosition() < climberLegTarget || climberIsDown.get()) {
                climberLeg.set(PercentOutput, 0.1);
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