package org.firstinspires.ftc.teamcode.OpModes.Auto;

import static org.firstinspires.ftc.teamcode.LibraryFiles.Constants.MaxAccel;
import static org.firstinspires.ftc.teamcode.LibraryFiles.Constants.MaxVel;


import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.JavaUtil;
import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.Hardware.Arm;
import org.firstinspires.ftc.teamcode.Hardware.Intake;
import org.firstinspires.ftc.teamcode.Hardware.Lift;
import org.firstinspires.ftc.teamcode.Hardware.Wrist;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;

import java.util.List;
@Autonomous
public class BlueClose extends LinearOpMode {

    public VisionPortal myVisionPortal;
    public TfodProcessor myTfodProcessor;
    public SampleMecanumDrive drive;
    public Lift lift = new Lift(this);
    public Intake claw = new Intake(this);
    public Wrist wrist = new Wrist(this);
    public Arm arm = new Arm(this);

    public boolean USE_WEBCAM;
    public double proplocation;
    @Override
    public void runOpMode() throws InterruptedException {
        USE_WEBCAM = true;
        initTfod();

        drive = new SampleMecanumDrive(hardwareMap);
        lift.initialize(hardwareMap);
        claw.initialize(hardwareMap);
        wrist.initialize(hardwareMap);
        arm.initialize(hardwareMap);

        Pose2d startPose = new Pose2d(0, 0, Math.toRadians(0));
        drive.setPoseEstimate(startPose);

        while (!isStarted()) {
            proplocation = Tfod_location();
            telemetry.addData("location: ", proplocation);
            telemetry.update();
        }

        TrajectorySequence Centered = drive.trajectorySequenceBuilder(startPose)
                .setConstraints(MaxVel, MaxAccel)

                .addTemporalMarker(Intake::closeBoth)
                .forward(29)
                .addTemporalMarker(Intake::OpenLeft)
                // need refining
                .back(5)
                .addTemporalMarker(Intake::closeLeft)
                .turn(Math.toRadians(-90))
                .back(30)
                .addTemporalMarker(Arm::armup)
                .back(1)
                .strafeLeft(6)
                .waitSeconds(2)
                .addTemporalMarker(Intake::OpenBoth)
                .forward(2.5)
                .addTemporalMarker(Arm::armdown)
                .addTemporalMarker(Intake::closeBoth)
                .strafeRight(15)

                .build();
        TrajectorySequence Left = drive.trajectorySequenceBuilder(startPose)
                .setConstraints(MaxVel, MaxAccel)

                .addTemporalMarker(Intake::closeBoth)
                .splineToConstantHeading(new Vector2d(19.5,15.5), Math.toRadians(90))
                .addTemporalMarker(Intake::OpenLeft)
                .back(4)
                .turn(Math.toRadians(-90))
                .addTemporalMarker(Intake::closeBoth)
                .back(20)
                .strafeLeft(11)
                .addTemporalMarker(Arm::armup)
                .waitSeconds(2)
                .addTemporalMarker(Intake::OpenBoth)
                .forward(4.5)
                .addTemporalMarker(Arm::armdown)
                .addTemporalMarker(Intake::closeBoth)
                .strafeRight(10)
                .build();

        TrajectorySequence Right = drive.trajectorySequenceBuilder(startPose)
                .setConstraints(MaxVel, MaxAccel)

                .addTemporalMarker(Intake::closeBoth)
                .forward(31)
                .turn(Math.toRadians(-90))
                .forward(4)
                .addTemporalMarker(Intake::OpenRight)
                .back(34.5)
                .addTemporalMarker(Intake::closeBoth)
                .addTemporalMarker(Arm::armup)
                .waitSeconds(0.7)
                .addTemporalMarker(Wrist::Wristdown)
                .waitSeconds(1.8)
                .addTemporalMarker(Intake::OpenBoth)
                .waitSeconds(0.5)
                .forward(4)
                .addTemporalMarker(Arm::armdown)
                .addTemporalMarker(Wrist::WristUp)
                .addTemporalMarker(Intake::closeBoth)
                .strafeRight(18)
                .build();

        waitForStart();
        myVisionPortal.close();

        if (isStopRequested()) return;

        if (proplocation == 2) {
            drive.followTrajectorySequence(Centered);
        } else if (proplocation == 1) {
            drive.followTrajectorySequence(Left);
        } else {
            drive.followTrajectorySequence(Right);

        }
    }


    /**
     * Describe this function...
     */
    private double Tfod_location() {
        List<Recognition> myTfodRecognitions;
        Recognition myTfodRecognition;
        float x;
        float y;

        // Get a list of recognitions from TFOD.
        myTfodRecognitions = myTfodProcessor.getRecognitions();
        telemetry.addData("# Objects Detected", JavaUtil.listLength(myTfodRecognitions));
        if (JavaUtil.listLength(myTfodRecognitions) == 0) {
            proplocation = 0;
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
                if (x >= 130) {
                    proplocation = 2;
                    telemetry.addLine("CENTERED");
                } else if (x <= 130) {
                    proplocation = 1;
                    telemetry.addLine("LEFT");
                } else {
                    proplocation = 3;
                }
                // Display size
                // Display the size of detection boundary for the recognition
                telemetry.addData("- Size", JavaUtil.formatNumber(myTfodRecognition.getWidth(), 0) + " x " + JavaUtil.formatNumber(myTfodRecognition.getHeight(), 0));
            }

        }
        return proplocation;
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



    }
}
