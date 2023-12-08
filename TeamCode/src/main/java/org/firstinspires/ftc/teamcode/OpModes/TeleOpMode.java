package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Hardware.FieldCentricMain;
import org.firstinspires.ftc.teamcode.Hardware.Intake;

@TeleOp (name = "FieldCentric TeleOp")
public class TeleOpMode extends LinearOpMode {

    private FieldCentricMain drivetrain = new FieldCentricMain(this);
    private Intake intake = new Intake();
    @Override
    public void runOpMode() {
        drivetrain.init(hardwareMap);
        intake.initialize(hardwareMap);

        waitForStart();
        if (opModeIsActive()) {
            while (opModeIsActive()) {
                drivetrain.FieldCentric(gamepad1);
                intake.loop(gamepad1);


                drivetrain.telemetry(telemetry);
                telemetry.update();
            }
        }
    }
}
