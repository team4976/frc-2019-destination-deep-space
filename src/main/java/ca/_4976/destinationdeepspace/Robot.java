package ca._4976.destinationdeepspace;

import ca._4976.destinationdeepspace.subsystems.*;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
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
    public static Intake intake;
    public static Climber climber;

    private Scheduler scheduler;

    @Override
    public void robotInit() {
        oi = new OI();
        drive = new Drive();
        shooter = new Shooter();
        intake = new Intake();
        climber = new Climber();

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
    public void teleopPeriodic() {
        if (Robot.oi.driver.getPOV() == 0){
            System.out.println("uppy");
            Robot.shooter.areYouShootingHigh();
        }
        else if (Robot.oi.driver.getPOV() == 90){}
        else if (Robot.oi.driver.getPOV() == 180){}
        else if (Robot.oi.driver.getPOV() == 270){}

        //Dpad sensor for driver controller
        if (Robot.oi.operator.getPOV() == 0){}
        else if (Robot.oi.operator.getPOV() == 90){}
        else if (Robot.oi.operator.getPOV() == 180){}
        else if (Robot.oi.operator.getPOV() == 270){}
    }

    @Override
    public void testPeriodic() { }
}