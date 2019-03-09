package ca._4976.destinationdeepspace.subsystems;

import ca._4976.destinationdeepspace.Robot;
import ca._4976.destinationdeepspace.commands.DriveWithJoystick;
import ca._4976.destinationdeepspace.commands.IntakeWithJoystick;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;

import static com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput;
//TODO: add sensor based movment
public class Intake extends Subsystem {

    NetworkTable intake = NetworkTableInstance.getDefault().getTable("Intake");

    Solenoid hatchPanelPickUp = new Solenoid(40,2);
    TalonSRX intakeArm = new TalonSRX(43);
    TalonSRX intakeArmSlave = new TalonSRX(42);
    TalonSRX intakeMotor1 = new TalonSRX(39);
    TalonSRX intakeMotor2 = new TalonSRX(38);
    public Encoder intakeEncoder = new Encoder(3,4);
    // The deadband percentage value
    public DigitalInput intakeLimitSwitch = new DigitalInput(2);
    private double deadband = 0.15;
    private boolean disableJoystik; //diasbles oystick when arm to ball position is active
    private boolean setPoint = false;
    boolean hp = false;
    private int intakeLowPosition = -500;//TODO set real value
    @Override
    protected void initDefaultCommand() {
        hatchPanelPickUp.set(true);
//        intakeArmSlave.follow(intakeArm);
        setDefaultCommand(new IntakeWithJoystick());
    }
    public void choose(){
        if (hp){
            Robot.intake.releaseGear();
            hp = false;
        }
        else{
            Robot.intake.holdGear();
            hp = true;
        }
    }
    public void pickUpBall(){
        intakeMotor1.set(PercentOutput, 1);
        intakeMotor2.set(PercentOutput, 1);
    }
    public void end(){
        intakeMotor1.set(PercentOutput, 0.0);
        intakeMotor2.set(PercentOutput, 0.0);
    }
    public void holdGear(){
        hatchPanelPickUp.set(true);
    }
    public void releaseGear(){
        hatchPanelPickUp.set(false);
    }
    public void pickupPosition(){
        disableJoystik = true;
            intakeArm.set(PercentOutput, -0.15);
            intakeArmSlave.set(PercentOutput, 0.15);
    }
    public void hold() {
        disableJoystik = false;
        intakeArm.set(PercentOutput, 0.06);
        intakeArmSlave.set(PercentOutput, -0.06);
    }
    // Applies the deadband to the joystick outputs
    public double applyDeadband(double x) {
        if (Math.abs(x) > deadband) {
            disableJoystik = false;
            if (x > 0.0) {
                return (x - deadband) / (1.0 - deadband);
            } else {
                return (x + deadband) / (1.0 - deadband);
            }
        } else {
            return 0.0;
        }
    }
    public void resetIntakeEncoder(){
        intakeEncoder.reset();
    }
    // Moves the intake arm based on joystick inputs
    public void moveIntakeArmWithJoystick(Joystick joy){
        if(disableJoystik){
            return;
        }
        if (applyDeadband(joy.getRawAxis(5)) == 0){
            intakeArm.set(PercentOutput, 0.06);
            intakeArmSlave.set(PercentOutput, -0.06);
        }
        else if (!setPoint){
            // If the intake is below the minimum position it will only go up
            if (intakeEncoder.get() <= intakeLowPosition){
                intakeArm.set(PercentOutput, Math.abs(applyDeadband(joy.getRawAxis(5))));
                intakeArmSlave.set(PercentOutput, -Math.abs(applyDeadband(joy.getRawAxis(5))));
            }
            //Moves the  intake normaly
            else {
                intakeArm.set(PercentOutput, applyDeadband(joy.getRawAxis(5)));
                intakeArmSlave.set(PercentOutput, -applyDeadband(joy.getRawAxis(5)));
            }
        }
    }
}