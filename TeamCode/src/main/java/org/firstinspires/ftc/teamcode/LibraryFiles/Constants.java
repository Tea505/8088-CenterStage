package org.firstinspires.ftc.teamcode.LibraryFiles;

import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryAccelerationConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryVelocityConstraint;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

public class Constants {
    public enum Side {
        LEFT,
        CENTER,
        RIGHT
    }
    public static final TrajectoryVelocityConstraint MaxVel = SampleMecanumDrive.getVelocityConstraint(
            100, Math.toRadians(180), Math.toRadians(180));

    public static final TrajectoryAccelerationConstraint MaxAccel = SampleMecanumDrive.getAccelerationConstraint
            (100);

}
