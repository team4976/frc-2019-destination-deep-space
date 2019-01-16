package ca._4976.destinationdeepspace.subsystems;

import ca._4976.destinationdeepspace.commands.DriveWithJoystick;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

import static com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput;

public class Drive extends Subsystem {

    VictorSPX LF = new VictorSPX(4);
    VictorSPX RF = new VictorSPX(1);
    TalonSRX LB = new TalonSRX(2);
    TalonSRX RB = new TalonSRX(3);
    Encoder right = new Encoder(0,1);
    Encoder left = new Encoder(2, 3);

    double deadband = 0.10;
    double throttle, turn, leftOutput, rightOutput;

    public boolean userControlEnabled = true;

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
        LF.set(ControlMode.PercentOutput, leftOutput);
        LB.follow(LF);

        RF.set(ControlMode.PercentOutput, rightOutput);
        RB.follow(RF);
    }

    public void arcadeDrive(Joystick joy){

        double rightEncoder = -right.get();
        double leftEncoder = left.get();

        if (userControlEnabled) {
            throttle = joy.getRawAxis(3) - joy.getRawAxis(2);
            turn = joy.getRawAxis(0);

            throttle = applyDeadband(joy.getRawAxis(3) - joy.getRawAxis(2));
            turn = applyDeadband(joy.getRawAxis(0));

            leftOutput = regularize(throttle + turn);
            rightOutput = regularize(-throttle + turn);

            drive(leftOutput, rightOutput);
            System.out.println(rightEncoder+", "+leftEncoder);
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
