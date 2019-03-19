package ca._4976.destinationdeepspace.commands.autos;

import ca._4976.destinationdeepspace.commands.FireShooterRight;
import ca._4976.destinationdeepspace.commands.HPRelease;
import ca._4976.destinationdeepspace.commands.IsShootingHigh;
import ca._4976.destinationdeepspace.commands.autoModules.*;
import ca._4976.destinationdeepspace.commands.rpmRight;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class auto_1_8_HP_20 extends CommandGroup {

    public auto_1_8_HP_20(){
        addSequential(new DriveForwardsFromPlatformToLeftSide());
        addSequential(new Delay());
        addSequential(new HorizontalCenterShooter());
        addSequential(new Delay());
        addSequential(new TurnNinteyDegreesRight());
        addSequential(new Delay());
        addSequential(new DriveForHatch());
        addSequential(new Delay());
        addSequential(new HPRelease());
        addSequential(new Delay());
        addSequential(new DriveBackwardsABit());
        addSequential(new TurnFromEightToTwentyToGoBackwards());
        addSequential(new DriveBackwardsFromEightToTwenty());
    }
}
