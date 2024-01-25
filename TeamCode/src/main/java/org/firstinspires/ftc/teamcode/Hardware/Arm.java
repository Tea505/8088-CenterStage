package org.firstinspires.ftc.teamcode.Hardware;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.LibraryFiles.Contraption;
@Config
public class Arm extends Contraption {

    private Servo LeftArm, RightArm;

    public static double ARM_UP = 1;
    public static double ARM_DOWN = 0;
    public Arm(LinearOpMode opMode) {
        this.opMode = opMode;
    }

    @Override
    public void initialize(HardwareMap hwMap) {

        LeftArm = hardwareMap.get(Servo.class, "LeftArm");
        RightArm = hardwareMap.get(Servo.class, "RightArm");

    }

    public void loop(Gamepad gamepad) {
        if (gamepad2.left_bumper) {
            // up
            LeftArm.setPosition(0);
            RightArm.setPosition(0.7);
        } else if (gamepad2.right_bumper) {
            // down
            LeftArm.setPosition(0.7);
            RightArm.setPosition(0);
        }
    }
}