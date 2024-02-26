package org.firstinspires.ftc.teamcode.Hardware;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.trajectory.MarkerCallback;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.LibraryFiles.Contraption;

@Config
public class Lift extends Contraption {
    public static DcMotor LeftLift, RightLift;
    public static TouchSensor HighLimit, LowLimit;
    public Lift(LinearOpMode opMode) {
        this.opMode = opMode;
    }
    @Override
    public void initialize(HardwareMap hwMap) {
        RightLift = hwMap.get(DcMotorEx.class, "RightLift");
        LeftLift = hwMap.get(DcMotorEx.class, "LeftLift");

        RightLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LeftLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        RightLift.setDirection(DcMotorSimple.Direction.REVERSE);
        LeftLift.setDirection(DcMotorSimple.Direction.FORWARD);

        HighLimit = hwMap.get(TouchSensor.class,"HighLimit");
        LowLimit = hwMap.get(TouchSensor.class,"LowLimit");
    }
    public void autoinit(){
        RightLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LeftLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        RightLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LeftLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void loop(Gamepad gamepad) {
        if (gamepad.left_stick_y>0 && !HighLimit.isPressed()) {
            // up
            LeftLift.setPower(.8);
            RightLift.setPower(.8);
        } else if (gamepad.left_stick_y<0 && !LowLimit.isPressed()) {
            // down
            LeftLift.setPower(-.60);
            RightLift.setPower(-.60);
        } else {
            LeftLift.setPower(0);
            RightLift.setPower(0);
        }


    }



    public void autoMove(int LeftPos, int RightPos, double Power) {
        RightLift.setTargetPosition(RightPos);
        LeftLift.setTargetPosition(LeftPos);

        RightLift.setPower(Power);
        LeftLift.setPower(Power);

        LeftLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RightLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}
