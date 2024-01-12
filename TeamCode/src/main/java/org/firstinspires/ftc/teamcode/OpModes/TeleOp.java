package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Hardware.Arm;
import org.firstinspires.ftc.teamcode.Hardware.DriveTrain;
import org.firstinspires.ftc.teamcode.Hardware.Intake;
import org.firstinspires.ftc.teamcode.Hardware.Wrist;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp

public class TeleOp extends LinearOpMode {

    public DriveTrain drive = new DriveTrain(this);
    public Intake intake = new Intake(this);

    public Arm arm = new Arm(this);
    public Wrist wrist = new Wrist(this);

    @Override
    public void runOpMode() throws InterruptedException {
        drive.initialize(hardwareMap);
        intake.initialize(hardwareMap);
        arm.initialize(hardwareMap);
        wrist.initialize(hardwareMap);

        waitForStart();
        if (opModeIsActive()) {
            while (opModeIsActive()) {
                drive.loop(gamepad1);
                intake.loop(gamepad1);
                arm.loop(gamepad1);
                wrist.loop(gamepad1);
            }
        }
    }
}
