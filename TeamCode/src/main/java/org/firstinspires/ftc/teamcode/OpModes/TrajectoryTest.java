package org.firstinspires.ftc.teamcode.OpModes;

import static org.firstinspires.ftc.teamcode.LibraryFiles.Constants.Side.*;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.LibraryFiles.Constants;
import org.firstinspires.ftc.teamcode.LibraryFiles.Constants.Side;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

public class TrajectoryTest extends LinearOpMode {

    public SampleMecanumDrive drive;
    Constants.Side side;
    @Override
    public void runOpMode() throws InterruptedException {
        drive = new SampleMecanumDrive(hardwareMap);

        Pose2d startPose = new Pose2d(0, 0, Math.toRadians(0));

        drive.setPoseEstimate(startPose);

        TrajectorySequence Centered = drive.trajectorySequenceBuilder(startPose)
                .build();

        TrajectorySequence Left = drive.trajectorySequenceBuilder(startPose)
                        .build();
        TrajectorySequence Right = drive.trajectorySequenceBuilder(startPose)
                        .build();

        waitForStart();

        if (!isStopRequested())
            if (side == CENTER) {
                drive.followTrajectorySequence(Centered);
            } else if (side == LEFT) {
                drive.followTrajectorySequence(Left);
            }else{
                drive.followTrajectorySequence(Right);

            }

    }
}
