package ca._4976.destinationdeepspace.subsystems;

import ca._4976.destinationdeepspace.Robot;
import ca._4976.destinationdeepspace.commands.DriveWithJoystick;
import ca._4976.destinationdeepspace.commands.IntakeWithJoystick;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
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
    VictorSPX intakeArmSlave = new VictorSPX(42);
    TalonSRX intakeMotor1 = new TalonSRX(39);
    TalonSRX intakeMotor2 = new TalonSRX(38);
    public Encoder intakeEncoder = new Encoder(3,4);
    // The deadband percentage value
    public DigitalInput intakeLimitSwitch = new DigitalInput(2);
    private double deadband = 0.25;
    private boolean disableJoystik; //diasbles oystick when arm to ball position is active
    private boolean setPoint = false;
    boolean hp = false;
    private int intakeHighPosition = -60;//TODO set real value
    private int intakeLowPosition = -1200;
    public boolean runnig = false;
    private boolean noUserControll = false;
    boolean controlledSlam; //determines the speed while either normal control or climbing

    @Override
    protected void initDefaultCommand() {
        hatchPanelPickUp.set(false);
//        intakeArmSlave.follow(intakeArm);
        setDefaultCommand(new IntakeWithJoystick());
        controlledSlam = false;
    }
    public void pickUpBall(){
        if (!runnig) { //checks to see if the intake is running
            intakeMotor1.set(PercentOutput, 1);
            intakeMotor2.set(PercentOutput, 1);
        }
       else {
            intakeMotor1.set(PercentOutput, 0.0);
            intakeMotor2.set(PercentOutput, 0);
        }
    }
    public void holdGear(){
        hatchPanelPickUp.set(true);
    } //redundant
    public void releaseGear(){
        hatchPanelPickUp.set(false);
    }
    //TODO: Figure out why the intake encoder is returning values that do not make sense while the arm is not moving
    //runs the intake to a position close to the ideal position for cargo
    public void pickupPosition(){
        if(intakeEncoder.get()<-1040){//up
            intakeArm.set(PercentOutput, 0.25);
            intakeArmSlave.set(PercentOutput, -0.25);
        }
        else if(intakeEncoder.get()>-1020){//down
            intakeArm.set(PercentOutput, -0.05);
            intakeArmSlave.set(PercentOutput, 0.05);
        }
    }
    //runs the intake to a position close to the ideal position for hatch panels
    public void HPPositiion(){
        if(intakeEncoder.get()<-740){
            intakeArm.set(PercentOutput, 0.25);
            intakeArmSlave.set(PercentOutput, -0.25);
        }
        else if(intakeEncoder.get()>-720){
            intakeArm.set(PercentOutput, -0.05);
            intakeArmSlave.set(PercentOutput, 0.05);
        }
    }
    public void stop() {
        intakeArm.set(PercentOutput, 0);
        intakeArmSlave.set(PercentOutput, -0);
    }
    public void changeuserControll(boolean x) {
        noUserControll = x;
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
    //controls the deciding boolean which controls the change of the speed while either climbing or for regular use
    //rumbles both controllers when in climbing mode to let the drivers both be aware of when this is on
    public void climbOrNormal(){
        controlledSlam = !controlledSlam;
        if (controlledSlam) {
            Robot.oi.operator.setRumble(GenericHID.RumbleType.kLeftRumble, 1.0);
            Robot.oi.operator.setRumble(GenericHID.RumbleType.kRightRumble, 1.0);
            Robot.oi.driver.setRumble(GenericHID.RumbleType.kLeftRumble, 1.0);
            Robot.oi.driver.setRumble(GenericHID.RumbleType.kRightRumble, 1.0);
        }
        else{
            Robot.oi.operator.setRumble(GenericHID.RumbleType.kLeftRumble, 0.0);
            Robot.oi.operator.setRumble(GenericHID.RumbleType.kRightRumble, 0.0);
            Robot.oi.driver.setRumble(GenericHID.RumbleType.kLeftRumble, 0.0);
            Robot.oi.driver.setRumble(GenericHID.RumbleType.kRightRumble, 0.0);
        }
    }
    public void resetIntakeEncoder(){
        intakeEncoder.reset();
    }
    // Moves the intake arm based on joystick inputs

    public void moveIntakeArmWithJoystick(Joystick joy){
        if (noUserControll){

        }
        else if (applyDeadband(joy.getRawAxis(5)) == 0){
//            double holdingSpeed = Math.abs(intakeEncoder.get()/-1000)-0.89;
            intakeArm.set(PercentOutput, 0.1);
            intakeArmSlave.set(PercentOutput, -0.1);
        }
        else if (!setPoint && !controlledSlam){
             //If the intake is above the maximum position it will only go down
            //TODO: Do no use until reason for encoder skewing numbers is fixed
//            if (intakeEncoder.get() >= intakeHighPosition){
//                intakeArm.set(PercentOutput, -Math.abs(applyDeadband(joy.getRawAxis(5))));
//                intakeArmSlave.set(PercentOutput, Math.abs(applyDeadband(joy.getRawAxis(5))));
//            }
            //Moves the  intake normaly
//            else {
                intakeArm.set(PercentOutput, -0.3 * (applyDeadband(joy.getRawAxis(5))));
                intakeArmSlave.set(PercentOutput, 0.3 * (applyDeadband(joy.getRawAxis(5))));
//            }
        }
        //removes limiter on intake in order to climb
        else if(!setPoint && controlledSlam){
            intakeArm.set(PercentOutput, -(applyDeadband(joy.getRawAxis(5))));
            intakeArmSlave.set(PercentOutput, (applyDeadband(joy.getRawAxis(5))));
        }
    }
}