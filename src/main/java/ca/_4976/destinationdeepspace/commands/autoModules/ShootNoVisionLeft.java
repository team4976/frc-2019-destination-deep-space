package ca._4976.destinationdeepspace.commands.autoModules;

import ca._4976.destinationdeepspace.commands.FireShooterLeft;
import ca._4976.destinationdeepspace.commands.rpmLeft;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShootNoVisionLeft extends CommandGroup {

    public ShootNoVisionLeft(){
        addSequential(new Delay());
        addSequential(new FireShooterLeft());
    }
}