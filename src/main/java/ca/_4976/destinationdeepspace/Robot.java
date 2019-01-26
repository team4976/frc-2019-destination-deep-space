package ca._4976.destinationdeepspace;

import ca._4976.destinationdeepspace.subsystems.Drive;
import ca._4976.destinationdeepspace.subsystems.Shooter;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends TimedRobot {

    public static void main(String... args) {
        RobotBase.startRobot(Robot::new);
    }

    public static OI oi;
    public static Drive drive;
    public static Shooter shooter;

    private Scheduler scheduler;

    @Override
    public void robotInit() {
        oi = new OI();
        drive = new Drive();
        shooter = new Shooter();
        scheduler = Scheduler.getInstance();
    }

    @Override
    public void robotPeriodic(){
        scheduler.run();
    }

    @Override
    public void disabledInit() {
    }

    @Override
    public void autonomousInit() { }

    @Override
    public void teleopInit() { }

    @Override
    public void testInit() { }

    @Override
    public void disabledPeriodic() { }
    
    @Override
    public void autonomousPeriodic() { }

    @Override
    public void teleopPeriodic() { }

    @Override
    public void testPeriodic() { }
}