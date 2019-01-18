package ca._4976.destinationdeepspace.subsystems;

import ca._4976.destinationdeepspace.commands.DriveWithJoystick;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

import static com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput;

public class Drive extends Subsystem {

    TalonSRX LF = new TalonSRX(4);
    TalonSRX LB = new TalonSRX(1);
    TalonSRX RF = new TalonSRX(2);
    TalonSRX RB = new TalonSRX(3);

    double deadband = 0.10;
    double throttle, turn, leftOutput, rightOutput;

    public boolean userControlEnabled = true, visonOveride = false;

    public double applyDeadband(double x) {

        if (Math.abs(x) > deadband) {
            if (x > 0.0) {
                return (x - deadband) / (1.0 - deadband);
            }
            else {
                return (x + deadband) / (1.0 - deadband);
            }
        }
        else {
            return 0.0;
        }
    }

    public double regularize(double x){
        if (x > 1.0) {
            return 1.0;
        }
        else if (x < -1.0){
            return -1.0;
        }
        else {
            return x;
        }
    }

    public void drive(double leftOutput, double rightOutput){
        LF.set(PercentOutput, leftOutput);
        LB.set(PercentOutput, leftOutput);

        RF.set(PercentOutput, rightOutput);
        RB.set(PercentOutput, leftOutput);
    }

    public void arcadeDrive(Joystick joy){

        if (userControlEnabled) {
            throttle = applyDeadband(joy.getRawAxis(2) - joy.getRawAxis(3));
            turn = applyDeadband(joy.getRawAxis(0));

            if (visonOveride) {
                leftOutput = regularize(throttle);
                rightOutput = regularize(-throttle);
            }
            else {
                leftOutput = regularize(throttle + turn);
                rightOutput = regularize(-throttle + turn);
            }

            drive(leftOutput, rightOutput);
        }
    }

    public void stop(){
        drive(0, 0);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new DriveWithJoystick());
    }
}
