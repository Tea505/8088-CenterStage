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
    DcMotor leftArm, rightArm;
    public Lift(LinearOpMode opMode) {
        this.opMode = opMode;
    }
    @Override
    public void initialize(HardwareMap hwMap) {
        rightArm = hwMap.get(DcMotorEx.class, "rightArm");
        leftArm = hwMap.get(DcMotorEx.class, "leftArm");
    }

    public void loop(Gamepad gamepad) {
        if (gamepad.a) {
            // up
            leftArm.setPower(0);
            rightArm.setPower(0);
        } else if (gamepad.b) {
            // down
            rightArm.setPower(0);
            leftArm.setPower(0);
        }
    }
}
