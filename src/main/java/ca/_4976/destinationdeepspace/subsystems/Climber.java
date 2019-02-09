package ca._4976.destinationdeepspace.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

import static com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput;

public class Climber extends Subsystem {
    //Motor controller for the climbing leg
    TalonSRX climberLeg = new TalonSRX(5); //TODO: Change these values

    //Encoder for the back leg
    Encoder climberLegEncoder = new Encoder(5,6); //TODO: Change these values

    //Solenoid for locking the leg
    DoubleSolenoid climberLegLock = new DoubleSolenoid(0,1); //TODO: Change these values

    AnalogInput input = new AnalogInput(1);

    int climberLegTarget = 50; //TODO: Change these values

    public boolean isClimberLegDown = false;
    public boolean isLegLocked = false;

    //Moves the climber leg
    public boolean moveLeg() {
        //Going up
        if (isClimberLegDown){
            climberLeg.set(PercentOutput, -0.5); //TODO: Change these values
            if (climberLegEncoder.get() <= 0){
                isClimberLegDown = !isClimberLegDown;
                return true;
            }
            else {
                return false;
            }
        }
        //Going down
        else {
            climberLeg.set(PercentOutput, 0.5); //TODO: Change these values
            if (climberLegEncoder.get() > climberLegTarget) {
                isClimberLegDown = !isClimberLegDown;
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