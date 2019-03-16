package ca._4976.destinationdeepspace.commands.autoModules;

import ca._4976.destinationdeepspace.commands.FireShooterRight;
import ca._4976.destinationdeepspace.commands.rpmLeft;
import ca._4976.destinationdeepspace.commands.rpmRight;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AimShootRight extends CommandGroup {

    public AimShootRight(){
//        addSequential(new HorizontalCenter());
//        addSequential(new SkewCorrection());
//        addSequential(new Delay());
        addSequential(new rpmRight());
        addSequential(new Delay());
        addSequential(new FireShooterRight());
    }
}
