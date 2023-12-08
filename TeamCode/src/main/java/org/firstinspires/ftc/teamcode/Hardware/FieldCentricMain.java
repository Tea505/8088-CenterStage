package org.firstinspires.ftc.teamcode.Hardware;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@Config
public class FieldCentricMain {

    public double powerMultiplier = 1;
    public SampleMecanumDrive drive;
    IMU imu;
    public FieldCentricMain(LinearOpMode opMode) {}

    public void init(HardwareMap hardwareMap) {
        drive = new SampleMecanumDrive(hardwareMap);
        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                DriveConstants.LOGO_FACING_DIR, DriveConstants.USB_FACING_DIR));
        imu.initialize(parameters);
    }

    public void RobotCentric(Gamepad gamepad1) {
        double y = -gamepad1.left_stick_y; // Remember, this is reversed!
        double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
        double rx = gamepad1.right_stick_x;

        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio, but only when
        // at least one is out of the range [-1, 1]
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double leftFrontSpeed = (y + x + rx) / denominator;
        double leftRearSpeed = (y - x + rx) / denominator;
        double rightFrontSpeed = (y - x - rx) / denominator;
        double rightRearSpeed = (y + x - rx) / denominator;

        if (gamepad1.left_bumper) {
            powerMultiplier = 0.5;
        }   else {
            powerMultiplier = 1;
        }

        drive.setMotorPowers(leftFrontSpeed * powerMultiplier, leftRearSpeed * powerMultiplier,
                rightRearSpeed * powerMultiplier, rightFrontSpeed * powerMultiplier);

    }

    public void FieldCentric(Gamepad gamepad1) {
        double y = -gamepad1.left_stick_y; // Remember, this is reversed!
        double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
        double rx = gamepad1.right_stick_x;

        // This button choice was made so that it is hard to hit on accident,
        // it can be freely changed based on preference.
        // The equivalent button is start on Xbox-style controllers.
        if (gamepad1.options) {
            imu.resetYaw();
        }

        double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        // Rotate the movement direction counter to the bot's rotation
        double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
        double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio, but only when
        // at least one is out of the range [-1, 1]
        double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
        double leftFrontSpeed = (rotY + rotX + rx) / denominator;
        double leftRearSpeed = (rotY - rotX + rx) / denominator;
        double rightFrontSpeed = (rotY - rotX - rx) / denominator;
        double rightRearSpeed = (rotY + rotX - rx) / denominator;

        if (gamepad1.left_bumper) {
            powerMultiplier = 0.5;
        } else {
            powerMultiplier = 1;
        }

        drive.setMotorPowers(leftFrontSpeed * powerMultiplier, leftRearSpeed * powerMultiplier,
                rightRearSpeed * powerMultiplier, rightFrontSpeed * powerMultiplier);

    }

    public void telemetry(Telemetry telemetry) {
        drive.update();

        Pose2d poseEstimate = drive.getPoseEstimate();
        telemetry.addData("x", poseEstimate.getX());
        telemetry.addData("y", poseEstimate.getY());
        telemetry.addData("heading", poseEstimate.getHeading());
        telemetry.update();
    }
}
