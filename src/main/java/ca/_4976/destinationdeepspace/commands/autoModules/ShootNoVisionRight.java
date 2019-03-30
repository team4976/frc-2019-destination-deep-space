package ca._4976.destinationdeepspace.commands.autoModules;

import ca._4976.destinationdeepspace.commands.FireShooterLeft;
import ca._4976.destinationdeepspace.commands.FireShooterRight;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShootNoVisionRight extends CommandGroup {

    public ShootNoVisionRight(){
        addSequential(new FireShooterRight());
    }
}