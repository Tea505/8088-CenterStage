package org.firstinspires.ftc.teamcode.Hardware;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.LibraryFiles.Contraption;

@Config
public class Lift extends Contraption {
    public static DcMotor LeftLift, RightLift;
    public Lift(LinearOpMode opMode) {
        this.opMode = opMode;
    }
    @Override
    public void initialize(HardwareMap hwMap) {
        RightLift = hwMap.get(DcMotorEx.class, "RightLift");
        LeftLift = hwMap.get(DcMotorEx.class, "LeftLift");

        RightLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LeftLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        RightLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LeftLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        RightLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LeftLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void loop(Gamepad gamepad) {
        if (gamepad.left_stick_y>0) {
            // up
            LeftLift.setPower(-1);
            RightLift.setPower(1);
        } else if (gamepad.left_stick_y<0) {
            // down
            LeftLift.setPower(1);
            RightLift.setPower(-1);
        } else {
            LeftLift.setPower(0);
            RightLift.setPower(0);
        }
    }

    public void autoMove(int TargetPosition, double Power) {
        RightLift.setTargetPosition(TargetPosition);
        LeftLift.setTargetPosition(TargetPosition);

        RightLift.setPower(Power);
        LeftLift.setPower(Power);

        LeftLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RightLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}
