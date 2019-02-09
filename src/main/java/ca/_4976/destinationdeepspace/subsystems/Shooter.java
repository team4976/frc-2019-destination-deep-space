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

    //Lift the bottom panel of the shooter
    DoubleSolenoid rightBananna = new DoubleSolenoid(10,1,0);
    DoubleSolenoid leftBananna = new DoubleSolenoid(10,3,2);

    //Shoot left or right by switching hood pos while shooting high
    DoubleSolenoid hood = new DoubleSolenoid(10,4,5);

    //Shooter talons
    TalonSRX rightShooter = new TalonSRX(7);
    TalonSRX leftShooter = new TalonSRX(8);

    //Is the hood left or right
    boolean hoodFlag = false;

    //Left and right for the bannanas
    boolean right = false;
    boolean left = false;

    //If shooting high
    boolean shootingHigh = false;

    //Jakes complicated value
    public double replaceWithJakesVisionToSpeedOfMotorCalculationVariable=0.8;

    @Override
    protected void initDefaultCommand() {
        //Sets the start position
        rightBananna.set(DoubleSolenoid.Value.kReverse);
        leftBananna.set(DoubleSolenoid.Value.kReverse);
        hood.set(DoubleSolenoid.Value.kForward);
    }

    public void areYouShootingHigh(){
        shootingHigh = true;
    }

    //Shoots the ball to the right high
    public void shootHighRight(){
        //Set speed
        rightShooter.set(ControlMode.PercentOutput, replaceWithJakesVisionToSpeedOfMotorCalculationVariable);//TODO: Change the value acording to the vision code
        leftShooter.set(ControlMode.PercentOutput, -replaceWithJakesVisionToSpeedOfMotorCalculationVariable);//TODO: Change the value acording to the vision code
        //Delay used to get the shooter up to speed
        if(hoodFlag)hood.set(DoubleSolenoid.Value.kReverse);
        hoodFlag = false;

        Timer.delay(1.0);
        //Set all the timgs to shoot right

        rightBananna.set(DoubleSolenoid.Value.kForward);
        right = true;
        leftBananna.set(DoubleSolenoid.Value.kForward);
        left = true;


        //Delay used to fire ball before reset
        Timer.delay(1.0);
        //Resets the shooter
        Robot.shooter.reset();
    }

    //SHoots low to the right
    public void shootLowRight(){
        //Catch if shooting high
        if(shootingHigh)Robot.shooter.shootHighRight();
        //Else continue shooting low
        else {
            //Sets the speed
            rightShooter.set(ControlMode.PercentOutput, replaceWithJakesVisionToSpeedOfMotorCalculationVariable);//TODO: Change the value acording to the vision code
           //Delay used to get the shooter up to speed
            Timer.delay(1.0);
            //Sets the right bannan to shoot low right
            rightBananna.set(DoubleSolenoid.Value.kForward);
            right = true;
            //Delay used to fire ball before reset
            Timer.delay(1.0);
            //Resets the shooter
            Robot.shooter.reset();
        }
    }
    public void shootHighLeft(){
        //Sets the speed
        leftShooter.set(ControlMode.PercentOutput, -replaceWithJakesVisionToSpeedOfMotorCalculationVariable);//TODO: Change the value acording to the vision code
        rightShooter.set(ControlMode.PercentOutput, replaceWithJakesVisionToSpeedOfMotorCalculationVariable);//TODO: Change the value acording to the vision code\
        //Sets the hood
        if(!hoodFlag)hood.set(DoubleSolenoid.Value.kForward);
        hoodFlag = true;
        //Delay used to spin motor before shoot
        Timer.delay(1.0);
        //Sets all of the bannas to shoot hight left
        rightBananna.set(DoubleSolenoid.Value.kForward);
        right = true;
        leftBananna.set(DoubleSolenoid.Value.kForward);
        left = true;

        //Delay used to fire ball before reset
        Timer.delay(1.0);
        //Resets the shooter
        Robot.shooter.reset();
    }

    //Shoots low and to the left
    public void shootLowLeft(){
        //Catch if shooting high
        if(shootingHigh)Robot.shooter.shootHighLeft();
        //Else continues shooting low
        else {
            //Sets the speed
            leftShooter.set(ControlMode.PercentOutput, replaceWithJakesVisionToSpeedOfMotorCalculationVariable);//TODO: Change the value acording to the vision code\
            Timer.delay(1.0);
            //Sets left bannan to shoot left low
            leftBananna.set(DoubleSolenoid.Value.kForward);
            left = true;
          //Delay used to shoot ball before reset
            Timer.delay(1.0);
            //Resets the shooter
            Robot.shooter.reset();
        }
    }

    //Resets the hood pos along with the rest of the shooter
    public void reset(){
        //Resets the motors
        rightShooter.set(ControlMode.PercentOutput, 0.0);
        leftShooter.set(ControlMode.PercentOutput, 0.0);

        //Drops bannanas down from up position
        if(left && right){
            leftBananna.set(DoubleSolenoid.Value.kReverse);
            left = false;
            rightBananna.set(DoubleSolenoid.Value.kReverse);
            right = false;
        }

        //Resets only left banan
        else if(left&&!right){
            leftBananna.set(DoubleSolenoid.Value.kReverse);
            left = false;
        }

        //Resets only ight banan
        else if(!left&&right){
            rightBananna.set(DoubleSolenoid.Value.kReverse);
            right = false;
        }

        //Sets shooting high to false
        if(shootingHigh){
            shootingHigh = false;
        }
    }
}