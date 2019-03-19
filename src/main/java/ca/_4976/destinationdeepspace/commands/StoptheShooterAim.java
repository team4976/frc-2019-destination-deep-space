package ca._4976.destinationdeepspace.commands;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;

//Stops te shooter aiming function

public class StoptheShooterAim extends Command {
    @Override
    protected void initialize(){
        //Sest the variable to true
        Robot.shooter.noShootShoot(true);
    }
    @Override
    protected boolean isFinished() {
        return false;
    }
    protected void end(){
        //Set the variable to false
        Robot.shooter.noShootShoot(false);
    }
}
