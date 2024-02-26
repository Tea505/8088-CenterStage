package org.firstinspires.ftc.teamcode.Hardware;


import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.LibraryFiles.Contraption;



@Config
public class MecanumTeleOp extends Contraption {
    static DcMotor leftFront;
    static DcMotor leftRear;
    static DcMotor rightRear;
    static DcMotor rightFront;
 static double powerMultiplier= 1;
    public MecanumTeleOp(LinearOpMode opMode) {
        this.opMode = opMode;
    }
    @Override
    public void initialize(HardwareMap hwMap) {
        leftFront = hwMap.get(DcMotorEx.class, "LeftFrontWheel");
        leftRear = hwMap.get(DcMotorEx.class, "LeftRearWheel");
        rightRear = hwMap.get(DcMotorEx.class, "RightRearWheel");
        rightFront = hwMap.get(DcMotorEx.class, "RightFrontWheel");

        leftRear.setDirection(DcMotor.Direction.FORWARD);
        leftFront.setDirection(DcMotor.Direction.REVERSE);
        rightRear.setDirection(DcMotor.Direction.REVERSE);
        rightFront.setDirection(DcMotor.Direction.FORWARD);

        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
       public void loopish(Gamepad gamepad){
        double y = -gamepad.left_stick_y; // Remember, Y stick value is reversed
        double x = gamepad.left_stick_x * 1.1; // Counteract imperfect strafing
        double rx = gamepad.right_stick_x;

        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio,
        // but only if at least one is out of the range [-1, 1]
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double FLS = (y + x + rx) / denominator;
        double BLS = (y - x + rx) / denominator;
        double FRS = (y - x - rx) / denominator;
        double BRS = (y + x - rx) / denominator;

        leftFront.setPower(FLS * powerMultiplier);
        leftRear.setPower(BLS* powerMultiplier);
    rightFront.setPower(FRS* powerMultiplier);
        rightRear.setPower(BRS* powerMultiplier);

        if (gamepad.left_bumper) {
            powerMultiplier = 0.5;
        }   else {
            powerMultiplier = 1;
        }
    }
}
