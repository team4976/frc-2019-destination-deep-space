package ca._4976.destinationdeepspace.subsystems;

import ca._4976.destinationdeepspace.Robot;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;

import static com.ctre.phoenix.motorcontrol.ControlMode.*;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;
public class Shooter extends Subsystem {

    //Lift the bottom panel of the shooter
    Solenoid LeftBanana = new Solenoid(40, 1);
    Solenoid RightBanana = new Solenoid(40, 0);

    //Shoot left or right by switching hood pos while shooting high
    Solenoid hood = new Solenoid(40, 3);

    //Shooter talons
    public TalonSRX rightShooter = new TalonSRX(48);
    public TalonSRX leftShooter = new TalonSRX(47);

    //Left and right for the bannanas
    boolean right = false;
    boolean left = false;

    //If shooting high
    boolean shootingHigh = false;

    //Jakes complicated value
    public double distanceInInch = 97, rpm = 0, Rpm = 12000;//TODO: Change the value acording to the vision code with the actual rpm target
    // Max distance 97 min distance 26

    @Override
    protected void initDefaultCommand() {
        //Sets the start position
        RightBanana.set(false);
        LeftBanana.set(false);
        hood.set(true);
    }

    public void areYouShootingHigh(){
        shootingHigh = true;
    }

    //Shoots the ball to the right high
    public void shootHighRight(){
        rpm = ((((Robot.vision.distance * 39.37) - 26) * (9800)) / (71)) + 8300;
        //Set speed
        rightShooter.set(Velocity, -rpm);
        Timer.delay(0.5);
        if (rightShooter.getMotorOutputPercent() < 0.2){
            rightShooter.set(Velocity, -13350);
        }
        //Delay used to get the shooter up to speed
        hood.set(false);
        Timer.delay(0.5);
        //Set all the timgs to shoot right
        RightBanana.set(true);
        right = true;
        LeftBanana.set(true);
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
            rightShooter.set(Velocity, Rpm);
           //Delay used to get the shooter up to speed
            Timer.delay(1.0);
            //Sets the right bannan to shoot low right
            RightBanana.set(true);
            right = true;
            //Delay used to fire ball before reset
            Timer.delay(1.0);
            //Resets the shooter
            Robot.shooter.reset();
        }
    }
    public void shootHighLeft(){
            rpm = ((((Robot.vision.distance * 39.37) - 26) * (9800)) / (71)) + 8300;
        //Sets the speed
//        leftShooter.set(Velocity, rpm);
        leftShooter.set(Velocity, rpm);
        Timer.delay(0.5);
        if (leftShooter.getMotorOutputPercent() < 0.2){
            leftShooter.set(Velocity, 13350);
        }
        //Sets the hood
        hood.set(true);
        //Delay used to spin motor before shoot
        Timer.delay(0.5);
        //Sets all of the bannas to shoot hight left
        RightBanana.set(true);
        right = true;
        LeftBanana.set(true);
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
            leftShooter.set(Velocity, -Rpm);
            Timer.delay(1.0);
            //Sets left bannan to shoot left low
            LeftBanana.set(true);
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
        rightShooter.set(PercentOutput, 0.0);
        leftShooter.set(PercentOutput, 0.0);
        //Drops bannanas down from up position
        if(left && right){
            LeftBanana.set(false);
            left = false;
            RightBanana.set(false);
            right = false;
        }
        //Resets only left banan
        else if(left&&!right){
            LeftBanana.set(false);
            left = false;
        }
        //Resets only ight banan
        else if(!left&&right){
            RightBanana.set(false);
            right = false;
        }
        //Sets shooting high to false
        if(shootingHigh){
            shootingHigh = false;
        }
    }
}