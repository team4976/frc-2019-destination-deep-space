package ca._4976.destinationdeepspace.commands.autoModules;

import ca._4976.destinationdeepspace.commands.FireShooterLeft;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AimShootLeft extends CommandGroup {

    public AimShootLeft(){
        addSequential(new HorizontalCenter());
        addSequential(new SkewCorrection());
        addSequential(new FireShooterLeft());
    }
}
