package org.firstinspires.ftc.teamcode.OpModes;

import static org.firstinspires.ftc.teamcode.LibraryFiles.Constants.MaxAccel;
import static org.firstinspires.ftc.teamcode.LibraryFiles.Constants.MaxVel;
import static org.firstinspires.ftc.teamcode.LibraryFiles.Constants.Side.CENTER;
import static org.firstinspires.ftc.teamcode.LibraryFiles.Constants.Side.LEFT;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.JavaUtil;
import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.LibraryFiles.Constants;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;

import java.util.List;

public class BlueBack extends LinearOpMode {

    public VisionPortal myVisionPortal;
    public TfodProcessor myTfodProcessor;
    public boolean USE_WEBCAM;
    public int scan_loc;
    public double proplocation;
    Constants.Side side;
    SampleMecanumDrive drive;

    @Override
    public void runOpMode() throws InterruptedException {
        USE_WEBCAM = true;
        initTfod();

        while (!isStarted()) {
            proplocation = Tfod_location();
            telemetry.addData("location: ", proplocation);
            telemetry.update();
        }

        waitForStart();
        myVisionPortal.close();
    }


    /**
     * Describe this function...
     */
    private int Tfod_location() {
        List<Recognition> myTfodRecognitions;
        Recognition myTfodRecognition;
        float x;
        float y;

        // Get a list of recognitions from TFOD.
        myTfodRecognitions = myTfodProcessor.getRecognitions();
        telemetry.addData("# Objects Detected", JavaUtil.listLength(myTfodRecognitions));
        if (JavaUtil.listLength(myTfodRecognitions) == 0) {
            scan_loc = 3;
        } else {
            // Iterate through list and call a function to display info for each recognized object.
            for (Recognition myTfodRecognition_item : myTfodRecognitions) {
                myTfodRecognition = myTfodRecognition_item;
                // Display info about the recognition.
                telemetry.addLine("");
                // Display label and confidence.
                // Display the label and confidence for the recognition.
                telemetry.addData("Image", myTfodRecognition.getLabel() + " (" + JavaUtil.formatNumber(myTfodRecognition.getConfidence() * 100, 0) + " % Conf.)");
                // Display position.
                x = (myTfodRecognition.getLeft() + myTfodRecognition.getRight()) / 2;
                y = (myTfodRecognition.getTop() + myTfodRecognition.getBottom()) / 2;
                // Display the position of the center of the detection boundary for the recognition
                telemetry.addData("- Position", JavaUtil.formatNumber(x, 0) + ", " + JavaUtil.formatNumber(y, 0));
                if (x >= 100) {
                    telemetry.addLine("CENTERED");
                    scan_loc = 1;
                } else if (x <= 80) {
                    telemetry.addLine("LEFT");
                    scan_loc = 2;
                } else {
                    scan_loc = 3;
                }
                // Display size
                // Display the size of detection boundary for the recognition
                telemetry.addData("- Size", JavaUtil.formatNumber(myTfodRecognition.getWidth(), 0) + " x " + JavaUtil.formatNumber(myTfodRecognition.getHeight(), 0));
            }

        }
        return scan_loc;
    }

    private void initTfod() {
        TfodProcessor.Builder myTfodProcessorBuilder;
        VisionPortal.Builder myVisionPortalBuilder;

        // First, create a TfodProcessor.Builder.
        myTfodProcessorBuilder = new TfodProcessor.Builder();
        // Set the name of the file where the model can be found.
        myTfodProcessorBuilder.setModelFileName("8088_red_model_metadata.tflite");
        // Set the full ordered list of labels the model is trained to recognize.
        myTfodProcessorBuilder.setModelLabels(JavaUtil.createListWith("None", "Red Prop"));
        // Set the aspect ratio for the images used when the model was created.
        myTfodProcessorBuilder.setModelAspectRatio(16 / 9);
        // Create a TfodProcessor by calling build.
        myTfodProcessor = myTfodProcessorBuilder.build();
        // Next, create a VisionPortal.Builder and set attributes related to the camera.
        myVisionPortalBuilder = new VisionPortal.Builder();
        if (USE_WEBCAM) {
            // Use a webcam.
            myVisionPortalBuilder.setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"));
        } else {
            // Use the device's back camera.
            myVisionPortalBuilder.setCamera(BuiltinCameraDirection.BACK);
        }
        // Add myTfodProcessor to the VisionPortal.Builder.
        myVisionPortalBuilder.addProcessor(myTfodProcessor);
        // Create a VisionPortal by calling build.
        myVisionPortal = myVisionPortalBuilder.build();

        drive = new SampleMecanumDrive(hardwareMap);

        Pose2d startPose = new Pose2d(0, 0, Math.toRadians(0));

        drive.setPoseEstimate(startPose);
        // these have not been tested they are just base line
        TrajectorySequence Centered = drive.trajectorySequenceBuilder(startPose)
                .setConstraints(MaxVel, MaxAccel)

                .forward(29)
                //open one claw here
                .waitSeconds(2)
                // need refining
                .back(4)
                .turn(Math.toRadians(-90))
                .back(62)
                //going at add spline later
                .waitSeconds(2)
                .strafeRight(20)
                .build();

        TrajectorySequence Left = drive.trajectorySequenceBuilder(startPose)
                .setConstraints(MaxVel, MaxAccel)

                .forward(29)
                .turn(Math.toRadians(-90))
                //open one claw here
                .waitSeconds(2)
                .back(62)
                //score thingy here
                .strafeLeft(22)
                .build();

        TrajectorySequence Right = drive.trajectorySequenceBuilder(startPose)
                .setConstraints(MaxVel, MaxAccel)

                .forward(29)
                .turn(Math.toRadians(-90))
                //open one Claw here
                .waitSeconds(2)
                .back(5)
                .strafeRight(15)
                //Going to do spline on actual field for auccarcy
                .back(62)
                .strafeRight(8)
                //scoring thingy
                .build();

        if (side == CENTER) {
            drive.followTrajectorySequence(Centered);
        } else if (side == LEFT) {
            drive.followTrajectorySequence(Left);
        } else {
            drive.followTrajectorySequence(Right);

        }


    }
}
