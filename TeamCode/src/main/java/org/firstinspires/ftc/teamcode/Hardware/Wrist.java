package org.firstinspires.ftc.teamcode.Hardware;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.LibraryFiles.Contraption;

@Config
public class Wrist extends Contraption {

    public static Servo wrist;
    public static double WRIST_UP = 0;
    public static double WRIST_DOWN = 0.8;
    public Wrist(LinearOpMode opMode) {
        this.opMode = opMode;
    }

    @Override
    public void initialize(HardwareMap hwMap) {
        wrist = hwMap.get(Servo.class, "Wrist");
    }

    public void loop(Gamepad gamepad) {
        if (gamepad.x) {
            // up
            wrist.setPosition(WRIST_UP);
        } else if (gamepad.b) {
            // down
            wrist.setPosition(WRIST_DOWN);
        } else if (gamepad.dpad_left) {
            wrist.setPosition(0.15);
        }
    }
    public static void WristUp() {
        wrist.setPosition(WRIST_UP);
    }
    public static void Wristdown(){
        wrist.setPosition(WRIST_DOWN);
    }

}
