package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Hardware.TensorFlow;

@Autonomous (name = "TensorFlow test Java")
public class AutoTest extends LinearOpMode {

    TensorFlow tensorFlow;
    @Override
    public void runOpMode() {
        tensorFlow.init(hardwareMap);

        if (opModeInInit()) {
            telemetry.addData("Location: ", tensorFlow.getLocation());
            telemetry.update();

            waitForStart();
            if (opModeIsActive()) {
                while (opModeIsActive()) {
                    tensorFlow.telemetryDisplay(telemetry);
                    telemetry.update();
                }

            }
        }
    }
}
