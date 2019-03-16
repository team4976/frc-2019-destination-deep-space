package ca._4976.destinationdeepspace.subsystems;

import ca._4976.destinationdeepspace.Robot;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

import static com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput;
import static com.ctre.phoenix.motorcontrol.ControlMode.Velocity;
public class Shooter extends Subsystem {

    //Lift the bottom panel of the shooter
    private Solenoid LeftBanana = new Solenoid(40, 1);
    private Solenoid RightBanana = new Solenoid(40, 0);

    //Shoot left or right by switching hood pos while shooting high
    private Solenoid hood = new Solenoid(40, 3);

    //Shooter talons
    private TalonSRX rightShooter = new TalonSRX(48);
    private TalonSRX leftShooter = new TalonSRX(47);

    //Left and right for the bananas
    private boolean right = false;
    private boolean left = false;

    //If shooting high
    private boolean shootingHigh = false;

    //rpm values for the different ranges
    private double rpmLowMid = 8000; //TODO: Set the rpm values once tested
    private double rpmMidHigh = 12000; //TODO: Set the rpm values once tested
    private double rpmNoVision = 10000; //TODO: Set the rpm values once tested

    //Mid distance to calibrate rpm with
    private double middle = 50; //TODO: Set the mid distance once calculated
    // Max distance 97 min distance 26

    @Override
    protected void initDefaultCommand() {
        //Sets the start position
        RightBanana.set(false);
        LeftBanana.set(false);
        hood.set(true);
    }

    // Tells the bot to shoot high
    public void areYouShootingHigh(){
        shootingHigh = true;
    }

    // Sets the rpm for the left shooter
    public void rpmLeft() {
        //Checks to see if the robot can see a target
        if (Robot.vision.hasTarget()) {
            // Sets the close range rpm
            if (Robot.vision.readXValue() < middle) {
                //Checks to see if were shooting high or low
                if (shootingHigh){
                    leftShooter.set(Velocity, rpmLowMid);
                }
                else {
                    leftShooter.set(Velocity, -rpmLowMid);
                }
            }
            // Sets the long range rpm
            else if (Robot.vision.readXValue() > middle) {
                //Checks to see if were shooting high or low
                if (shootingHigh){
                    leftShooter.set(Velocity, rpmMidHigh);
                }
                else {
                    leftShooter.set(Velocity, -rpmMidHigh);
                }
            }
        }
        //Robot cant see a target
        else {
            //Checks to see if the bot is shooting high or low
            if (shootingHigh){
                leftShooter.set(Velocity, rpmNoVision);
            }
            else {
                leftShooter.set(Velocity, -rpmNoVision);
            }
        }
    }

    //Sets the rpm for the right shooter
    public void rpmRight() {
        //Checks to see if the robot can see a target
        if (Robot.vision.hasTarget()) {
            // Sets the close range rpm
            if (Robot.vision.readXValue() < middle) {
                //Checks to see if the robot is shooting high
                if (shootingHigh){
                    rightShooter.set(Velocity, -rpmLowMid);
                }
                else {
                    rightShooter.set(Velocity, rpmLowMid);
                }
            }
            //Sets the long range rpm
            else if (Robot.vision.readXValue() > middle) {
                //Checks to see if the robot is shooting high or not
                if (shootingHigh){
                    rightShooter.set(Velocity, -rpmMidHigh);
                }
                else {
                    rightShooter.set(Velocity, rpmMidHigh);
                }
            }
        }
        // Robot cant see a target
        else {
            //Checks to see if the Robot is shooting high or low
            if (shootingHigh){
                rightShooter.set(Velocity, -rpmNoVision);
            }
            else {
                rightShooter.set(Velocity, rpmNoVision);
            }
        }
    }

    //Shoots the ball to the right high
    private void shootHighRight(){
        //Delay used to get the shooter up to speed
        hood.set(false);
        Timer.delay(0.4);
        //Set all the timgs to shoot right
        RightBanana.set(true);
        right = true;
        LeftBanana.set(true);
        left = true;
        //Delay used to fire ball before reset
        Timer.delay(0.4);
        //Resets the shooter
        Robot.shooter.reset();
    }

    //SHoots low to the right
    public void shootLowRight(){
        //Catch if shooting high
        if(shootingHigh)Robot.shooter.shootHighRight();
        //Else continue shooting low
            //Sets the right bannan to shoot low right
            RightBanana.set(true);
            right = true;
            //Delay used to fire ball before reset
            Timer.delay(0.4);
            //Resets the shooter
            Robot.shooter.reset();
    }
    private void shootHighLeft(){
        //Sets the hood
        hood.set(true);
        Timer.delay(0.4);
        //Sets all of the bannas to shoot hight left
        RightBanana.set(true);
        right = true;
        LeftBanana.set(true);
        left = true;
        //Delay used to fire ball before reset
        Timer.delay(0.4);
        //Resets the shooter
        Robot.shooter.reset();
    }

    //Shoots low and to the left
    public void shootLowLeft(){
        //Catch if shooting high
        if(shootingHigh)Robot.shooter.shootHighLeft();
        //Else continues shooting low
        else {
            //Sets left bannan to shoot left low
            LeftBanana.set(true);
            left = true;
          //Delay used to shoot ball before reset
            Timer.delay(0.4);
            //Resets the shooter
            Robot.shooter.reset();
        }
    }

    //Resets the hood pos along with the rest of the shooter
    private void reset(){
        //Resets the motors
        rightShooter.set(PercentOutput, 0.0);
        leftShooter.set(PercentOutput, 0.0);
        //Drops bananas down from up position
        if(left && right){
            LeftBanana.set(false);
            left = false;
            RightBanana.set(false);
            right = false;
        }
        //Resets only left banana
        else if(left){
            LeftBanana.set(false);
            left = false;
        }
        //Resets only Right banana
        else if(right){
            RightBanana.set(false);
            right = false;
        }
        //Sets shooting high to false
        if(shootingHigh){
            shootingHigh = false;
        }
    }
}