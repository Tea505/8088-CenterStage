package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Hardware.Arm;
import org.firstinspires.ftc.teamcode.Hardware.DriveTrain;
import org.firstinspires.ftc.teamcode.Hardware.Intake;
import org.firstinspires.ftc.teamcode.Hardware.Lift;
import org.firstinspires.ftc.teamcode.Hardware.Wrist;

@TeleOp(name = "_8088Teleop2024 (Blocks to Java)")
public class _8088Teleop2024 extends LinearOpMode {

    private DcMotor LeftRearWheel;
    private DcMotor LeftFrontWheel;
    private DcMotor RightRearWheel;
    private DcMotor RightFrontWheel;
    private DcMotor LeftClimber;
    private DcMotor RightLift;
    private DcMotor LeftLift;
    private Servo LeftClaw;
    private Servo RightClaw;
    private Servo Wrist;
    private Servo LeftArm;
    private Servo RightArm;
    private DcMotor RightClimber;

    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        double powerMultiplier;
        float vertical;
        float horizontal;
        float pivot;
        float FLS;
        float BLS;
        float BRS;
        float FRS;

        LeftRearWheel = hardwareMap.get(DcMotor.class, "LeftRearWheel");
        LeftFrontWheel = hardwareMap.get(DcMotor.class, "LeftFrontWheel");
        RightRearWheel = hardwareMap.get(DcMotor.class, "RightRearWheel");
        RightFrontWheel = hardwareMap.get(DcMotor.class, "RightFrontWheel");

        LeftClimber = hardwareMap.get(DcMotor.class, "LeftClimber");
        RightClimber = hardwareMap.get(DcMotor.class, "RightClimber");

        RightLift = hardwareMap.get(DcMotor.class, "RightLift");
        LeftLift = hardwareMap.get(DcMotor.class, "LeftLift");

        LeftClaw = hardwareMap.get(Servo.class, "LeftClaw");
        RightClaw = hardwareMap.get(Servo.class, "RightClaw");

        Wrist = hardwareMap.get(Servo.class, "Wrist");

        LeftArm = hardwareMap.get(Servo.class, "LeftArm");
        RightArm = hardwareMap.get(Servo.class, "RightArm");



        LeftRearWheel.setDirection(DcMotor.Direction.FORWARD);
        LeftFrontWheel.setDirection(DcMotor.Direction.REVERSE);
        RightRearWheel.setDirection(DcMotor.Direction.REVERSE);
        RightFrontWheel.setDirection(DcMotor.Direction.FORWARD);

        LeftRearWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RightRearWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LeftFrontWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RightFrontWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        // Put initialization blocks here.
        LeftClimber.setDirection(DcMotor.Direction.REVERSE);
        RightLift.setDirection(DcMotor.Direction.REVERSE);
        LeftLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RightLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        powerMultiplier = 1;
        waitForStart();
        if (opModeIsActive()) {
            // Put run blocks here.
            while (opModeIsActive()) {
                // Put loop blocks here.
                // Mecanum Wheel Drivetrain control
                vertical = gamepad1.left_stick_y;
                horizontal = gamepad1.left_stick_x;
                pivot = gamepad1.right_stick_x;
                FLS = (vertical - horizontal) - pivot;
                BLS = (vertical + horizontal) - pivot;
                BRS = (vertical - horizontal) + pivot;
                FRS = vertical + horizontal + pivot;
                LeftFrontWheel.setPower(FLS * powerMultiplier);
                LeftRearWheel.setPower(BLS * powerMultiplier);
                RightRearWheel.setPower(BRS * powerMultiplier);
                RightFrontWheel.setPower(FRS * powerMultiplier);
                if (gamepad1.left_bumper) {
                    powerMultiplier = 0.5;
                } else {
                    powerMultiplier = 1;
                }
                // claw
                if (gamepad2.left_trigger > 0) {
                    // close
                    LeftClaw.setPosition(0.7);
                    RightClaw.setPosition(0.3);
                }
                if (gamepad2.right_trigger > 0) {
                    // open
                    LeftClaw.setPosition(0.5);
                    RightClaw.setPosition(0.5);
                }
                // lift
                if (gamepad2.left_stick_y < 0) {
                    // up
                    LeftLift.setPower(1);
                    RightLift.setPower(1);
                } else if (gamepad2.left_stick_y > 0) {
                    // down
                    LeftLift.setPower(-1);
                    RightLift.setPower(-1);
                } else {
                    LeftLift.setPower(0);
                    RightLift.setPower(0);
                }
                // wrist
                if (gamepad2.x) {
                    // up
                    Wrist.setPosition(0);
                } else if (gamepad2.b) {
                    // down
                    Wrist.setPosition(0.8);
                } else if (gamepad1.dpad_left) {
                    Wrist.setPosition(0.5);
                }
                // Arm
                if (gamepad2.left_bumper) {
                    // up
                    LeftArm.setPosition(0);
                    RightArm.setPosition(0.7);
                } else if (gamepad2.right_bumper) {
                    // down
                    LeftArm.setPosition(0.7);
                    RightArm.setPosition(0);
                }
                // climber
                if (gamepad2.dpad_up) {
                    LeftClimber.setPower(1);
                    RightClimber.setPower(1);
                } else if (gamepad2.dpad_down) {
                    LeftClimber.setPower(-1);
                    RightClimber.setPower(-1);
                } else {
                    LeftClimber.setPower(0);
                    RightClimber.setPower(0);
                }
                telemetry.update();
            }
        }
    }
}
