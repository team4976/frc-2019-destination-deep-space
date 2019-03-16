package ca._4976.destinationdeepspace.commands.autoModules;

import ca._4976.destinationdeepspace.commands.FireShooterRight;
import ca._4976.destinationdeepspace.commands.rpmRight;
import edu.wpi.first.wpilibj.command.CommandGroup;

//Executes the sequence of commands required to aim rev and fire the shooter to the Right
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
