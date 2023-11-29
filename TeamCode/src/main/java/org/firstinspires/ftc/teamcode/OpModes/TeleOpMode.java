package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Hardware.FieldCentricMain;

@TeleOp (name = "FieldCentricMain")
public class TeleOpMode extends LinearOpMode {

    FieldCentricMain drivetrain = new FieldCentricMain();
    @Override
    public void runOpMode() {
        drivetrain.init(hardwareMap, telemetry);

        waitForStart();
        if (opModeIsActive()) {
            while (opModeIsActive()) {

                drivetrain.FieldCentric(gamepad1);
                drivetrain.telemetry(telemetry);
                telemetry.update();
            }
        }
    }
}
