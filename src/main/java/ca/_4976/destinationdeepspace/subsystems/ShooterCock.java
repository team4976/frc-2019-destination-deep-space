package ca._4976.destinationdeepspace.subsystems;

import ca._4976.destinationdeepspace.Robot;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.buttons.POVButton;

public class ShooterCock {

    DoubleSolenoid shooterEngage = new DoubleSolenoid(0,1); //Solenoid that pushes ball into the shooter
    TalonSRX shootMeBabyOneMoreTime = new TalonSRX(5); //Motor that spins the shooting motor
    Timer shootTime = new Timer();

    public double shooterSpeed = 1.0;
    public double amountOfTimeShooterRuns = 2000;

    public void shootDaddy(){ //spins shooting mechanism based on pre decided amount
        shootTime.start();
        shootMeBabyOneMoreTime.set(ControlMode.PercentOutput, shooterSpeed);
        if(shootTime.get()<amountOfTimeShooterRuns)Robot.shooterCock.stopDaddy();
    }
    public void reverseDaddy(){
        shootMeBabyOneMoreTime.set(ControlMode.PercentOutput, -shooterSpeed);
    }
    public void stopDaddy(){ //resets timer and stops shooting motor
        shootMeBabyOneMoreTime.set(ControlMode.PercentOutput, 0.0);
        shootTime.stop();
        shootTime.reset();
        Robot.shooterCock.pullDaddy();
    }
    public void pushDaddy(){ //pushes the ball into the shooting mechanism + calls shooting mechanism
        shooterEngage.set(DoubleSolenoid.Value.kForward);
        Robot.shooterCock.shootDaddy();
    }
    public void pullDaddy(){
        shooterEngage.set(DoubleSolenoid.Value.kReverse);
    }
}