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

    public double shooterSpeed = 1.0;

    public void shootDaddy(){ //spins shooting mechanism based on pre decided amount
        shootMeBabyOneMoreTime.set(ControlMode.PercentOutput, shooterSpeed);
        Robot.shooterCock.pushDaddy();
    }
    public void reverseDaddy(){
        shootMeBabyOneMoreTime.set(ControlMode.PercentOutput, -shooterSpeed);
    }
    public void stopDaddy(){ //resets timer and stops shooting motor
        shootMeBabyOneMoreTime.set(ControlMode.PercentOutput, 0.0);
        Robot.shooterCock.pullDaddy();
    }
    public void pushDaddy(){ //pushes the ball into the shooting mechanism + calls shooting mechanism
        shooterEngage.set(DoubleSolenoid.Value.kForward);
    }
    public void pullDaddy(){
        shooterEngage.set(DoubleSolenoid.Value.kReverse);
    }
}