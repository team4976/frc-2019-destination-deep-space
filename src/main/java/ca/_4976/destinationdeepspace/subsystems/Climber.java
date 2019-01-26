package ca._4976.destinationdeepspace.subsystems;

import ca._4976.destinationdeepspace.Robot;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem {

    NetworkTable climber = NetworkTableInstance.getDefault().getTable("Climber");

    //Left drive motor controllers
    TalonSRX LF = new TalonSRX(4);
    TalonSRX LB = new TalonSRX(1);

    //Right drive motor controllers
    TalonSRX RF = new TalonSRX(2);
    TalonSRX RB = new TalonSRX(3);

    //Intake motor controllers
    TalonSRX intake1 = new TalonSRX(5);
    TalonSRX intake2 = new TalonSRX(6);

    //The motor controlling the foot
    TalonSRX footMotor = new TalonSRX(6);

    //Limit switches
    DigitalInput chassisLimitSwitch = new DigitalInput(1);
    DigitalInput lowerFootLimitSwitch = new DigitalInput(2);
    DigitalInput upperFootLimitSwitch = new DigitalInput(3);

    //The encoder with which encoder values will be obtained
    Encoder distanceEncoder = new Encoder(0,1,false,Encoder.EncodingType.k4X);

    @Override
    protected void initDefaultCommand() {}

    public void beginClimb(){
        intakeSpin();
        while (true){
            if (chassisLimitSwitch.get()) {
                intakeStop();
                footDrop();
            }
        }
    }

    public void footDrop(){
        footMotor.set(ControlMode.PercentOutput, 0.6);
        while (true){
            if (lowerFootLimitSwitch.get()){
                footStop();
                driveALittle();
            }
        }
    }

    public void footReverse(){
        footMotor.set(ControlMode.PercentOutput, - 0.6);
        while (true){
            if (upperFootLimitSwitch.get()){
                footStop();
                driveALittleMore();
            }
        }
    }

    public void driveALittle(){
        drive();
        while (true){
            //ADD A REAL VALUE HERE...
            if (distanceEncoder.get() == 5){
                stopDriving();
                footReverse();
            }
        }
    }

    public void driveALittleMore(){
        drive();
        while (true){
            //ADD A REAL VALUE HERE...
            if (distanceEncoder.get() == 1){
                stopDriving();
            }
        }
    }

    public void drive(){
        RF.set(ControlMode.PercentOutput, 0.5);
        RB.set(ControlMode.PercentOutput, 0.5);

        LF.set(ControlMode.PercentOutput, 0.5);
        LB.set(ControlMode.PercentOutput, 0.5);
    }

    public void stopDriving(){
        RF.set(ControlMode.PercentOutput, 0.0);
        RB.set(ControlMode.PercentOutput, 0.0);

        LF.set(ControlMode.PercentOutput, 0.0);
        LB.set(ControlMode.PercentOutput, 0.0);
    }

    public void footStop(){
        footMotor.set(ControlMode.PercentOutput, 0.0);
    }

    public void intakeSpin(){
        intake1.set(ControlMode.PercentOutput, 0.6);
        intake2.set(ControlMode.PercentOutput, 0.6);
    }

    public void intakeStop(){
        intake1.set(ControlMode.PercentOutput, 0.0);
        intake2.set(ControlMode.PercentOutput, 0.0);
    }

    public void stopClimbing(){

    }
}
