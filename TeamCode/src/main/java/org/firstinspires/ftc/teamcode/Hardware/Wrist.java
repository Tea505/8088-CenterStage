package org.firstinspires.ftc.teamcode.Hardware;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.LibraryFiles.Contraption;

@Config
public class Wrist extends Contraption {

    private static Servo Wrist;

    public static double WRIST_UP = 0;
    public static double WRIST_DOWN = 0.8;
    public Wrist(LinearOpMode opMode) {
        this.opMode = opMode;
    }

    @Override
    public void initialize(HardwareMap hwMap) {
        Wrist = hwMap.get(Servo.class, "Wrist");
    }

    public void loop(Gamepad gamepad) {
        if (gamepad.x) {
            // up
            Wrist.setPosition(WRIST_UP);
        } else if (gamepad.b) {
            // down
            Wrist.setPosition(WRIST_DOWN);
        } else if (gamepad.dpad_left) {
            Wrist.setPosition(0.15);
        }
    }
    public static void WristUp() {
        Wrist.setPosition(0);
    }
    public static void Wristdown(){
        Wrist.setPosition(WRIST_DOWN);
    }

}
