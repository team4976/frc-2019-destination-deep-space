package ca._4976.destinationdeepspace.subsystems;

import ca._4976.destinationdeepspace.Robot;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
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
    Encoder distanceEncoder = new Encoder(0,1,false, Encoder.EncodingType.k4X);

    @Override
    protected void initDefaultCommand() {}

    //Starts the auto-climb sequence
    public void beginClimb(){
        Robot.isOtherwiseClimbing = true;
        intakeSpin();
        while (true){
            if (chassisLimitSwitch.get()) {
                intakeStop();
                footDrop();
            }
        }
    }

    //Drops the foot
    public void footDrop(){
        footMotor.set(ControlMode.PercentOutput, 0.6);
        while (true){
            if (lowerFootLimitSwitch.get()){
                footStop();
                driveALittle();
            }
        }
    }

    //Retracts the foot
    public void footReverse(){
        footMotor.set(ControlMode.PercentOutput, - 0.6);
        while (true){
            if (upperFootLimitSwitch.get()){
                footStop();
                driveALittleMore();
            }
        }
    }

    //Drives a distance equivalent to the length of the bot
    public void driveALittle(){
        drive();
        while (true){
            if (distanceEncoder.get() == 224){
                stopDriving();
                footReverse();
            }
        }
    }

    //Drives a distance equivalent to the width of the foot,
    //to push the robot onto the platform.
    public void driveALittleMore(){
        drive();
        while (true){
            if (distanceEncoder.get() == 17){
                stopDriving();
            }
        }
    }

    //Drives the bot
    public void drive(){
        RF.set(ControlMode.PercentOutput, 0.5);
        RB.set(ControlMode.PercentOutput, 0.5);

        LF.set(ControlMode.PercentOutput, 0.5);
        LB.set(ControlMode.PercentOutput, 0.5);
    }

    //Stops the bot from moving
    public void stopDriving(){
        RF.set(ControlMode.PercentOutput, 0.0);
        RB.set(ControlMode.PercentOutput, 0.0);

        LF.set(ControlMode.PercentOutput, 0.0);
        LB.set(ControlMode.PercentOutput, 0.0);
    }

    //Stops the foot from moving more than it should
    public void footStop(){
        footMotor.set(ControlMode.PercentOutput, 0.0);
    }

    //Spins both intake motors forward
    public void intakeSpin(){
        intake1.set(ControlMode.PercentOutput, 0.6);
        intake2.set(ControlMode.PercentOutput, 0.6);
    }

    //Stops the intake motors from spinning
    public void intakeStop(){
        intake1.set(ControlMode.PercentOutput, 0.0);
        intake2.set(ControlMode.PercentOutput, 0.0);
    }

    //Driver override
    public void stopClimbing(){
        intake1.set(ControlMode.PercentOutput, 0.0);
        intake2.set(ControlMode.PercentOutput, 0.0);

        RF.set(ControlMode.PercentOutput, 0.0);
        RB.set(ControlMode.PercentOutput, 0.0);

        LF.set(ControlMode.PercentOutput, 0.0);
        LB.set(ControlMode.PercentOutput, 0.0);

        Robot.isOtherwiseClimbing = false;
    }

}
