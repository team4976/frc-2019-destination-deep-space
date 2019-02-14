package ca._4976.destinationdeepspace.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Scale extends CommandGroup {

    public Scale(){
        addSequential(new TempIntakDown());
        addSequential(new ClimberLeg());
        addSequential(new ClimberLegLock());
        //addParallel(new DriveToEncoderPosition);
        addSequential(new TempIntakeUp()); //Parallel
        addSequential(new ClimberLegLock());
        addSequential(new ClimberLeg());
    }
}
