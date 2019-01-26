package ca._4976.destinationdeepspace.subsystems;



import edu.wpi.first.networktables.NetworkTableInstance;

import edu.wpi.first.wpilibj.Sendable;

import edu.wpi.first.wpilibj.command.Subsystem;

import edu.wpi.first.networktables.NetworkTable;

import edu.wpi.first.networktables.NetworkTableEntry;



public class Vision extends Subsystem implements Sendable {



    NetworkTable visionValues = NetworkTableInstance.getDefault().getTable("limelight");

    NetworkTableEntry tx = visionValues.getEntry("tx");

    NetworkTableEntry ty = visionValues.getEntry("ty");

    NetworkTableEntry ta = visionValues.getEntry("ta");



    @Override

    protected void initDefaultCommand() {}



    public void periodicRead (){

        double x = tx.getDouble(0.0);

        double y = ty.getDouble(0.0);

        double area = ta.getDouble(0.0);

        //System.out.println("X Value: " + x);

        //System.out.println("Y Value: " + y);

        //System.out.println("A Value: " + area);

        if(x > 3){

            System.out.println("Move left");

        } else if (x < -3){

            System.out.println("Move right");

        } else if (x < 3 && x > -3){

            System.out.println("Centered horizontally");

        }



        if(y > 3){

            System.out.println("Move down");

        } else if(y < -3){

            System.out.println("Move up");

        } else if(y < 3 && y > -3){

            System.out.println("Centered vertically");

        }

    }

}