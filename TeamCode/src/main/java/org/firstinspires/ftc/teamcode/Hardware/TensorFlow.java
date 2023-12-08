package org.firstinspires.ftc.teamcode.Hardware;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.JavaUtil;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;

import java.util.List;

@Config
public class TensorFlow {

    public boolean USE_WEBCAM;
    TfodProcessor myTfodProcessor;
    VisionPortal myVisionPortal;
    Side location = Side.RIGHT;

    public void init(HardwareMap hardwareMap) {
        USE_WEBCAM = true;

        TfodProcessor.Builder myTfodProcessorBuilder;
        VisionPortal.Builder myVisionPortalBuilder;

        // TfodProcessor.Builder
        myTfodProcessorBuilder = new TfodProcessor.Builder();
        myTfodProcessor = myTfodProcessorBuilder.build();
        myVisionPortalBuilder = new VisionPortal.Builder();

        // TODO: Change Custom Model for these guys
        // Define custom Model
        // myTfodProcessorBuilder.setModelFileName("Red_Prop.tflite");

        // Set the full ordered list of labels the model is trained to recognize
        myTfodProcessorBuilder.setModelLabels(JavaUtil.createListWith("Team Prop", null));
        // Set the aspect ratio for the images used when the model was created.
        myTfodProcessorBuilder.setModelAspectRatio(16 / 9);
        if (USE_WEBCAM) {
            // Use a webcam.
            myVisionPortalBuilder.setCamera(hardwareMap.get(WebcamName.class, "Webcam"));
        } else {
            // Use the device's back camera.
            myVisionPortalBuilder.setCamera(BuiltinCameraDirection.BACK);
        }
        // Add myTfodProcessor to the VisionPortal.Builder.
        myVisionPortalBuilder.addProcessor(myTfodProcessor);
        // Set magnification
        // myTfodProcessor.setZoom(1);
        // Create a VisionPortal by calling build.
        myVisionPortal = myVisionPortalBuilder.build();
    }

    public int telemetryDisplay(Telemetry telemetry) {
        List<Recognition> myTfodRecognitions;
        // default location is right == 3
        int scan_loc = 3;
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

                if (x <= 250) {
                    // left
                    scan_loc = 1;
                    location = Side.LEFT;
                } else if (x <= 600) {
                    // center
                    scan_loc = 2;
                    location = Side.CENTER;
                } else {
                    // right
                    scan_loc = 3;
                    location = Side.RIGHT;
                }
                // Display size
                // Display the size of detection boundary for the recognition
                telemetry.addData("- Size", JavaUtil.formatNumber(myTfodRecognition.getWidth(), 0) + " x " + JavaUtil.formatNumber(myTfodRecognition.getHeight(), 0));
            }
        }

        return scan_loc;
    }

    public void stopStreaming() {
        myVisionPortal.stopStreaming();
    }
    public Side getLocation() {
        return this.location;
    }
}