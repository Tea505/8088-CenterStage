package org.firstinspires.ftc.teamcode.Hardware;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.LibraryFiles.Contraption;
@Config
public class Climber extends Contraption {
    DcMotor LeftClimber, RightClimber;

    public Climber(LinearOpMode opMode) {
        this.opMode = opMode;
    }

    @Override
    public void initialize(HardwareMap hwMap) {
        RightClimber = hwMap.get(DcMotorEx.class, "rightArm");
        LeftClimber = hwMap.get(DcMotorEx.class, "leftArm");
    }

    public void loop(Gamepad gamepad) {
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
    }
}
