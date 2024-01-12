package org.firstinspires.ftc.teamcode.Hardware;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.LibraryFiles.Contraption;

@Config
public class Wrist extends Contraption {

    private Servo Wrist;

    public static double WRIST_UP = 0.7;
    public static double WRIST_DOWN = 0;
    public Wrist(LinearOpMode opMode) {
        this.opMode = opMode;
    }

    @Override
    public void initialize(HardwareMap hwMap) {
        Wrist = hwMap.get(Servo.class, "Wrist");
    }

    public void loop(Gamepad gamepad) {
        if (gamepad.y) {
            // up
            // Wrist.setPosition(.7);
            Wrist.setPosition(WRIST_UP);
        } else if (gamepad.x) {
            // down
            // Wrist.setPosition(0);
            Wrist.setPosition(WRIST_DOWN);
        }
    }
}
