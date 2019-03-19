package ca._4976.destinationdeepspace.commands.autoModules;

import ca._4976.destinationdeepspace.commands.FireShooterLeft;
import ca._4976.destinationdeepspace.commands.rpmLeft;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AimShootLeft extends CommandGroup {

    public AimShootLeft(){
        addSequential(new HorizontalCenter());
//        addSequential(new SkewCorrection());
        addSequential(new Delay());
        addSequential(new rpmLeft());
        addSequential(new Delay());
        addSequential(new FireShooterLeft());
    }
}
