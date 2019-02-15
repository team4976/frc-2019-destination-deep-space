package ca._4976.destinationdeepspace.subsystems;

import ca._4976.destinationdeepspace.Robot;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;

import static com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput;
//TODO: add sensor based movment
public class Intake extends Subsystem {

    NetworkTable intake = NetworkTableInstance.getDefault().getTable("Intake");

    DoubleSolenoid hatchPanelPickUp = new DoubleSolenoid(6,7);
    boolean HP = true;
    public TalonSRX intakeArm = new TalonSRX(43);
    TalonSRX intakeArmSlave = new TalonSRX(42);
    public DigitalInput cherrySensor = new DigitalInput(2);
    TalonSRX intakeMotor1 = new TalonSRX(39);
    TalonSRX intakeMotor2 = new TalonSRX(38);
    boolean position = true;

    @Override
    protected void initDefaultCommand() {
        hatchPanelPickUp.set(DoubleSolenoid.Value.kForward);
//        intakeArmSlave.follow(intakeArm);
    }
    public void choose(){
        if(HP) Robot.intake.releaseGear();HP = false;
        if(!HP)Robot.intake.holdGear();HP = true;
    }
    public void pickUpBall(){
        intakeMotor1.set(PercentOutput, -1);
        intakeMotor2.set(PercentOutput, 1);
    }
    public void end(){
        intakeMotor1.set(PercentOutput, 0.0);
        intakeMotor2.set(PercentOutput, 0.0);
    }
    public void holdGear(){
        hatchPanelPickUp.set(DoubleSolenoid.Value.kForward);
    }
    public void releaseGear(){
        hatchPanelPickUp.set(DoubleSolenoid.Value.kReverse);
    }

    public void tempintakeDown(){
        intakeArm.set(PercentOutput, 0.8);
        intakeArmSlave.set(PercentOutput, -0.8);
    }
    public void tempIntakeUp() {
        intakeArm.set(PercentOutput, -0.3);
        intakeArmSlave.set(PercentOutput, 0.3);
    }
    public void tempIntakeStop() {
        intakeArm.set(PercentOutput, 0);
        intakeArmSlave.set(PercentOutput, 0);
    }
    public void homePosition(){
        intakeArm.set(PercentOutput, -0.3);
        intakeArmSlave.set(PercentOutput, 0.3);
        position = true;
    }
    public void pickupPosition(){
        if(!position) {
            intakeArm.set(PercentOutput, -0.3);
            intakeArmSlave.set(PercentOutput, 0.3);
        }
        else if(position){
            intakeArm.set(PercentOutput, 0.3);
            intakeArmSlave.set(PercentOutput, -0.3);
        }
    }
    public void climbPostition(){
        intakeArm.set(PercentOutput, 0.3);
        intakeArmSlave.set(PercentOutput, -0.3);
        position = false;
    }
    public void hold() {
        intakeArm.set(PercentOutput, -0.04);
        intakeArmSlave.set(PercentOutput, 0.04);
    }
}