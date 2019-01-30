package ca._4976.destinationdeepspace.subsystems;

import ca._4976.destinationdeepspace.Robot;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import javafx.scene.transform.Rotate;

public class Shooter extends Subsystem {

    NetworkTable shooter = NetworkTableInstance.getDefault().getTable("Shooter");
    DoubleSolenoid rightBananna = new DoubleSolenoid(10,0,1);
    DoubleSolenoid leftBananna = new DoubleSolenoid(10,2,3);
    DoubleSolenoid hood = new DoubleSolenoid(10,4,5);
    TalonSRX rightShooter = new TalonSRX(7);
    TalonSRX leftShooter = new TalonSRX(1);
    DigitalInput checkIfBall = new DigitalInput(0);//sensor to determine if a ball is in the shooter

    //A whole bunch of flags
    boolean isThereABall = checkIfBall.get();
    boolean hoodFlag = false;
    boolean right = false;
    boolean left = false;
    boolean shootingHigh = false;

    //Jakes complicated value
    public double replaceWithJakesVisionToSpeedOfMotorCalculationVariable;

    @Override
    protected void initDefaultCommand() {

    }
    public void areYouShootingHigh(){
        shootingHigh = true;
    }
    public void shootHighRight(){
        rightShooter.set(ControlMode.PercentOutput, replaceWithJakesVisionToSpeedOfMotorCalculationVariable);
        rightBananna.set(DoubleSolenoid.Value.kForward);
        right = true;
        leftBananna.set(DoubleSolenoid.Value.kForward);
        left = true;
        if(hoodFlag)hood.set(DoubleSolenoid.Value.kReverse);
        hoodFlag = false;
        Timer.delay(1.0);//timer to be used if no sensor is on the shooter
        //if(!isThereABall)Robot.shooter.reset();
        Robot.shooter.reset();
    }
    public void shootLowRight(){
        if(shootingHigh)Robot.shooter.shootHighRight();
        else {
            rightBananna.set(DoubleSolenoid.Value.kForward);
            right = true;
            rightShooter.set(ControlMode.PercentOutput, -replaceWithJakesVisionToSpeedOfMotorCalculationVariable);
            Timer.delay(1.0);
            //if (!isThereABall) Robot.shooter.reset();
            Robot.shooter.reset();
        }
    }
    public void shootHighLeft(){
        leftShooter.set(ControlMode.PercentOutput, -replaceWithJakesVisionToSpeedOfMotorCalculationVariable);
        rightBananna.set(DoubleSolenoid.Value.kForward);
        right = true;
        leftBananna.set(DoubleSolenoid.Value.kForward);
        left = true;
        if(!hoodFlag)hood.set(DoubleSolenoid.Value.kForward);
        hoodFlag = true;
        Timer.delay(1.0);
        //if(!isThereABall)Robot.shooter.reset();
        Robot.shooter.reset();
    }
    public void shootLowLeft(){
        if(shootingHigh)Robot.shooter.shootHighLeft();
        else {
            leftBananna.set(DoubleSolenoid.Value.kForward);
            left = true;
            leftShooter.set(ControlMode.PercentOutput, replaceWithJakesVisionToSpeedOfMotorCalculationVariable);
            Timer.delay(1.0);
            //if (!isThereABall) Robot.shooter.reset();
            Robot.shooter.reset();
        }
    }
    public void reset(){
        rightShooter.set(ControlMode.PercentOutput, 0.0);
        leftShooter.set(ControlMode.PercentOutput, 0.0);
        if(left&&right){
            leftBananna.set(DoubleSolenoid.Value.kReverse);
            left = false;
            rightBananna.set(DoubleSolenoid.Value.kReverse);
            right = false;
        }
        else if(left&&!right){
            leftBananna.set(DoubleSolenoid.Value.kReverse);
            left = false;
        }
        else if(!left&&right){
            rightBananna.set(DoubleSolenoid.Value.kReverse);
            right = false;
        }
        if(shootingHigh)shootingHigh = false;
    }
}