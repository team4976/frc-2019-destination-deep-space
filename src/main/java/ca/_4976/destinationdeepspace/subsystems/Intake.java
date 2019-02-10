package ca._4976.destinationdeepspace.subsystems;

import ca._4976.destinationdeepspace.Robot;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

import static com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput;
//TODO: add sensor based movment
public class Intake extends PIDSubsystem {

    NetworkTable intake = NetworkTableInstance.getDefault().getTable("Intake");

    DoubleSolenoid hatchPanelPickUp = new DoubleSolenoid(6,7);
    boolean HP = true;
    TalonSRX intakeArm = new TalonSRX(43);
    TalonSRX intakeArmSlave = new TalonSRX(42);
    AnalogInput cherrySensor = new AnalogInput(2);
    TalonSRX intakeMotor1 = new TalonSRX(39);
    TalonSRX intakeMotor2 = new TalonSRX(38);
    double intakeSetpoint;

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
    public void climb(){

    }
    public void disablePID(){intakeController.disable();}
    public void setSetpointHome(){
        intakeSetpoint = 172; intakeController.enable();
        }
    public void setSetpointHP(){intakeSetpoint = 83; intakeController.enable();}
    public void setSetpointClimb(){
        intakeSetpoint = 17; intakeController.enable();
        }
    public void moveIntake(double output){
        intakeArm.set(PercentOutput, output);
    }
    public PIDController intakeController;
    private final PIDOutput intakeOutput = this::usePIDOutput;
    public Intake(String name, double p, double i, double d){
        super(name, p, i, d);
        intakeController = new PIDController(p, i, d, cherrySensor, intakeOutput);
        intakeController.setSetpoint(intakeSetpoint);
    }
    @Override
    protected double returnPIDInput() {
        return cherrySensor.getVoltage()*36;
    }

    @Override
    protected void usePIDOutput(double output) {
        moveIntake(output);
    }

    public void tempintakeDown(){
        intakeArm.set(PercentOutput, 0.3);
        intakeArmSlave.set(PercentOutput, -0.3);
    }
    public void tempIntakeUp() {
        intakeArm.set(PercentOutput, -0.3);
        intakeArmSlave.set(PercentOutput, 0.3);
    }
    public void tempIntakeStop() {
        intakeArm.set(PercentOutput, 0);
        intakeArmSlave.set(PercentOutput, 0);
    }
}