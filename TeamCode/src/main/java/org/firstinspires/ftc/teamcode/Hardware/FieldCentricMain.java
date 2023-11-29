package org.firstinspires.ftc.teamcode.Hardware;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;


import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@Config
public class FieldCentricMain {

    public double powerMultiplier = 1;
    private final RobotHardware robot = RobotHardware.getInstance();
    double[] ws = new double[4];
    public FieldCentricMain() {}

    public void init(HardwareMap hardwareMap, Telemetry telemetry) {
        robot.init(hardwareMap, telemetry);
    }

    public void FieldCentric(Gamepad gamepad1) {
        double y = -gamepad1.left_stick_y; // Remember, this is reversed!
        double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
        double rx = gamepad1.right_stick_x;

        // This button choice was made so that it is hard to hit on accident,
        // it can be freely changed based on preference.
        // The equivalent button is start on Xbox-style controllers.
        if (gamepad1.options) {
            robot.imu.resetYaw();
        }

        double [] wheelspeeds = new double[4];

        double botHeading = robot.imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

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

        wheelspeeds[0] = leftFrontSpeed * powerMultiplier;
        wheelspeeds[1] = leftRearSpeed * powerMultiplier;
        wheelspeeds[2] = rightFrontSpeed * powerMultiplier;
        wheelspeeds[3] = rightRearSpeed * powerMultiplier;

        ws[0] = wheelspeeds[0]; // left front
        ws[1] = wheelspeeds[1]; // left rear
        ws[2] = wheelspeeds[2]; // right front
        ws[3] = wheelspeeds[3]; // right rear

        robot.leftFront.setPower(ws[0]);
        robot.leftRear.setPower(ws[1]);
        robot.rightFront.setPower(ws[2]);
        robot.rightRear.setPower(ws[3]);
    }

    public void telemetry(Telemetry telemetry) {

        telemetry.addData("leftFront power", robot.leftFront.getCurrentPosition());
        telemetry.addData("leftRear power", robot.leftRear.getCurrentPosition());
        telemetry.addData("rightFront power", robot.rightFront.getCurrentPosition());
        telemetry.addData("rightRear power", robot.rightRear.getCurrentPosition());
    }


}
