package ca._4976.destinationdeepspace.subsystems;

import ca._4976.destinationdeepspace.Robot;
import ca._4976.destinationdeepspace.commands.autoModules.AimShootLeft;
import ca._4976.destinationdeepspace.commands.autoModules.AimShootRight;
import ca._4976.destinationdeepspace.commands.autoModules.ShootNoVisionLeft;
import ca._4976.destinationdeepspace.commands.autoModules.ShootNoVisionRight;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

import static com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput;
import static com.ctre.phoenix.motorcontrol.ControlMode.Velocity;
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

    public Shooter() {
        rightShooter.setSensorPhase(true);
        leftShooter.setSensorPhase(true);
    }


    //If shooting high
    boolean shootingHigh = false;

    //Stops the aim
    public boolean cancil = false;

    public boolean manCont = true;

    boolean rightLeft = false;
    //Jakes complicated value
    public double distanceInInch = 97, rpm = 0, Rpm = 12000;//TODO: Change the value acording to the vision code with the actual rpm target
    // Max distance 97 min distance 26

    @Override
    protected void initDefaultCommand() {
        //Sets the start position
        RightBanana.set(false);
        LeftBanana.set(false);
        hood.set(false);
    }

    public void areYouShootingHigh(){
        shootingHigh = true;
    }

    //Fires the ba;ll out of the reved shooter
    public void FirePneumatics(){
        if (rightLeft){
            new ShootNoVisionRight().start();
        } else {
            new ShootNoVisionLeft().start();
        }
    }

    //Starts the wheels in motion for the shooter
    public void revShooter(boolean side) {
        if (manCont){
            rpm = 5000; //TODO: Change this to somthing valid
            if (side) {
                if (!shootingHigh) {
                    rightShooter.set(Velocity, -rpm);
                } else {
                    rightShooter.set(Velocity, 10000);
                }
                rightLeft = true;
//                new ShootNoVisionRight().start();
            }
            else{
                if (!shootingHigh) {
                    leftShooter.set(Velocity, rpm);
                } else {
                    leftShooter.set(Velocity, -10000);
                }
                rightLeft = false;
//                new ShootNoVisionLeft().start();
            }

        }
        else {
            rpm = ((((Robot.vision.distance * 39.37) - 26) * (9800)) / (71)) + 8300;
            //If side is true shoot right else shoot left
            if (side) {
                System.out.println();
                if (!shootingHigh) {
                    rightShooter.set(Velocity, -rpm);
                } else {
                    rightShooter.set(Velocity, 10000);
                }

                System.out.println(rightShooter.getMotorOutputPercent());
                if (!Robot.vision.hasTarget()) {
                    if (!shootingHigh) {
                        rightShooter.set(Velocity, -8800);
                    } else {
                        rightShooter.set(Velocity, 10000);
                    }
                    new ShootNoVisionRight().start();
                } else {
                    System.out.println("Shooter working");
                    new AimShootRight().start();
                }
            } else {
                if (!shootingHigh) {
                    leftShooter.set(Velocity, rpm);
                } else {
                    leftShooter.set(Velocity, -10000);
                }

                if (!Robot.vision.hasTarget()) {
                    if (!shootingHigh) {
                        leftShooter.set(Velocity, 8800);
                    } else {
                        leftShooter.set(Velocity, -10000);
                    }
                    new ShootNoVisionLeft().start();
                } else {
                    new AimShootLeft().start();
                }
            }
        }
    }

    public void swapControllMode(){
        manCont = !manCont;
    }

    public void rpmLeft() {
        rpm = ((((Robot.vision.distance * 39.37) - 26) * (9800)) / (71)) + 8300;
        rpm = rpm * 1.55;
        if (!shootingHigh){
            leftShooter.set(Velocity, rpm);
        }
        else {
            leftShooter.set(Velocity, -10000);
        }
    }

    public void rpmRight() {
        rpm = (((Math.abs(Robot.vision.distance * 39.37) - 26) * (9800)) / (71)) + 8300;
        rpm = rpm * 1.55;
        if (!shootingHigh){
            rightShooter.set(Velocity, -rpm);
        }
        else {
            rightShooter.set(Velocity, 10000);
        }
    }

    //Shoots the ball to the right high
    public void shootHighRight(){
        //Delay used to get the shooter up to speed
        hood.set(true);
        Timer.delay(0.5);
        //Set all the timgs to shoot right
        RightBanana.set(true);
        right = true;
        LeftBanana.set(true);
        left = true;
        //Delay used to fire ball before reset
        Timer.delay(1);
        //Resets the shooter
        Robot.shooter.reset();
    }

    //SHoots low to the right
    public void shootLowRight(){
        //Catch if shooting high
        if(!shootingHigh)Robot.shooter.shootHighRight();
        //Else continue shooting low
            //Sets the right bannan to shoot low right
            RightBanana.set(true);
            right = true;
            //Delay used to fire ball before reset
            Timer.delay(0.4);
            //Resets the shooter
            Robot.shooter.reset();
    }
    public void shootHighLeft(){
        //Sets the hood
        hood.set(false);
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
        if(!shootingHigh)Robot.shooter.shootHighLeft();
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
    //Used to stop the shooter from aiming
    public void noShootShoot(boolean want){
        cancil = want;
    }
}