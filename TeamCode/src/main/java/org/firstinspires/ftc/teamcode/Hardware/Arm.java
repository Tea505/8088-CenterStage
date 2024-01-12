package org.firstinspires.ftc.teamcode.Hardware;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.LibraryFiles.Contraption;
@Config
public class Arm extends Contraption {

    private Servo Arm;

    public static double ARM_UP = 0.9;
    public static double ARM_DOWN = 0;
    public Arm(LinearOpMode opMode) {
        this.opMode = opMode;
    }

    @Override
    public void initialize(HardwareMap hwMap) {

        Arm = hwMap.get(Servo.class, "Arm");
        Arm.setDirection(Servo.Direction.REVERSE);

    }

    public void loop(Gamepad gamepad) {
        if (gamepad.a) {
            // up
            // Arm.setPosition(.9);
            Arm.setPosition(ARM_UP);
        } else if (gamepad.b) {
            // down
            // Arm.setPosition(0);
            Arm.setPosition(ARM_DOWN);
        }
    }
}