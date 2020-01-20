package ca._4976.destinationdeepspace.subsystems;

import ca._4976.destinationdeepspace.commands.Climber.MoveClimberWithJoystick;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

import static com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput;

public class Climber extends Subsystem {
    public TalonSRX climberLeg = new TalonSRX(50);

    public void moveClimberWithJoystick(Joystick joy) {
        climberLeg.set(PercentOutput, applyDeadband(joy.getRawAxis(5)));
    }

    public double applyDeadband(double x) {
        double deadband = 0.25;
        if (Math.abs(x) > deadband) {
            if (x > 0.0) {
                return (x - deadband) / (1.0 - deadband);
            } else {
                return (x + deadband) / (1.0 - deadband);
            }
        } else {
            return 0.0;
        }
    }

    @Override
    protected void initDefaultCommand() {
        climberLeg.setInverted(true);
        setDefaultCommand(new MoveClimberWithJoystick());
    }
}
