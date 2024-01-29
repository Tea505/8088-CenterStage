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

    public static Servo LeftArm, RightArm;

    public static double LeftARM_UP = 0;
    public static double RightARM_UP = 0.7;
    public static double LeftARM_DOWN = 0.7;
    public static double RightARM_DOWM = 0;

    public Arm(LinearOpMode opMode) {
        this.opMode = opMode;
    }

    @Override
    public void initialize(HardwareMap hwMap) {
        LeftArm = hwMap.get(Servo.class, "LeftArm");
        RightArm = hwMap.get(Servo.class, "RightArm");
        //RightArm.setDirection(Servo.Direction.REVERSE);
    }

    public void loop(Gamepad gamepad) {
        if (gamepad.left_bumper) {
            // up
            LeftArm.setPosition(0);
            RightArm.setPosition(0.7);
        } else if (gamepad.right_bumper) {
            // down
            LeftArm.setPosition(.7);
            RightArm.setPosition(0);
        }
    }

    public static void armdown() {
        LeftArm.setPosition(LeftARM_UP);
        RightArm.setPosition(RightARM_UP);
    }
    public static void armup(){
        LeftArm.setPosition(LeftARM_DOWN);
        RightArm.setPosition(RightARM_DOWM);
    }

}