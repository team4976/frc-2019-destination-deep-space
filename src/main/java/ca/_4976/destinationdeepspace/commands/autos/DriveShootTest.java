package ca._4976.destinationdeepspace.commands.autos;

import ca._4976.destinationdeepspace.commands.FireShooterRight;
import ca._4976.destinationdeepspace.commands.IsShootingHigh;
import ca._4976.destinationdeepspace.commands.autoModules.*;
import ca._4976.destinationdeepspace.commands.rpmRight;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveShootTest extends CommandGroup {

    public DriveShootTest(){
        addSequential(new DriveForwardsFromGroundToLeftSide());
        addSequential(new Delay());
        addSequential(new HorizontalCenterShooter());
        addSequential(new Delay());
//        addSequential(new SkewCorrection());
        addSequential(new IsShootingHigh());
        addSequential(new rpmRight());
        addSequential(new Delay());
        addSequential(new FireShooterRight());
    }
}
