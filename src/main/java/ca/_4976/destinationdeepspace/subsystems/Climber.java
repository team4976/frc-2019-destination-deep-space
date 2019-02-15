package ca._4976.destinationdeepspace.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

import static com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput;

public class Climber extends Subsystem {
    //Motor controller for the climbing leg
    public TalonSRX climberLeg = new TalonSRX(50);

    //Solenoid for locking the leg
    DoubleSolenoid climberLegLock = new DoubleSolenoid(0,1); //TODO: Change these values

    int climberLegTarget = -6250;

    public boolean isClimberLegDown = false;
    public boolean isLegLocked = false;

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
            if (climberLeg.getSelectedSensorPosition() < climberLegTarget) {
                climberLeg.set(PercentOutput, 0.1);
                return true;

            } else {
                return false;
            }
        }
    }

    //Moves the climber leg lock
    public boolean legLock() {
        //Unlocking
        if (isLegLocked) {
            climberLegLock.set(DoubleSolenoid.Value.kReverse); //TODO: Change these values
            }
        //Locking
        else {
            climberLegLock.set(DoubleSolenoid.Value.kForward); //TODO: Change these values
        }
        return false;
    }

    @Override
    protected void initDefaultCommand() {}
}