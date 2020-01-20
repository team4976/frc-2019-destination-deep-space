package ca._4976.destinationdeepspace.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

import static com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput;
import static com.ctre.phoenix.motorcontrol.ControlMode.Velocity;

public class Shooter extends Subsystem {
    public static TalonSRX leftShooterWheel = new TalonSRX(47);
    public static TalonSRX rightShoterWheel = new TalonSRX(48);

    private static Solenoid leftBannana = new Solenoid(40, 1);
    private static Solenoid rightBannana = new Solenoid(40, 0);

    private static Solenoid hood = new Solenoid(40, 3);

    private static int highRPM = 6250;
    private static int highAssistRPM = 2000;
    private static int lowRPM = -8500;

    public Shooter() {
        rightShoterWheel.setSensorPhase(true);
        leftShooterWheel.setSensorPhase(true);

    }

    public static void revShooterRight(boolean highShot) {
        if (highShot){
            rightShoterWheel.set(Velocity, highRPM);
            leftShooterWheel.set(Velocity, highAssistRPM);
        } else {
            rightShoterWheel.set(Velocity, lowRPM);
        }
    }
 //Hood true right
    public static void revShooterLeft(boolean highShot) {
        if (highShot){
            leftShooterWheel.set(Velocity, highRPM);
            rightShoterWheel.set(Velocity, highAssistRPM);
        } else {
            leftShooterWheel.set(Velocity, lowRPM);
        }
    }

    public static void moveHood(boolean setting){
        hood.set(setting);
    }

    public static void moveLeftBannana(boolean setting){
        leftBannana.set(setting);
    }

    public static void moveRightBannana(boolean setting){
        rightBannana.set(setting);
    }

    public static void stopShooter(){
        leftShooterWheel.set(PercentOutput, 0);
        rightShoterWheel.set(PercentOutput, 0);
        leftBannana.set(false);
        rightBannana.set(false);
    }

    @Override
    protected void initDefaultCommand() {
        rightShoterWheel.setInverted(true);
        leftBannana.set(false);
        rightBannana.set(false);
        hood.set(true);
    }
}