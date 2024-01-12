package org.firstinspires.ftc.teamcode.Hardware;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.LibraryFiles.Contraption;
@Config
public class Intake extends Contraption {

    private Servo LeftClaw;
    private Servo RightClaw;

    public Intake(LinearOpMode opMode){
        this.opMode=opMode;
    }
    @Override
    public void initialize(HardwareMap hwMap) {

        LeftClaw = hwMap.get(Servo.class, "LeftClaw");
        RightClaw = hwMap.get(Servo.class, "RightClaw");

        RightClaw.setDirection(Servo.Direction.REVERSE);
        LeftClaw.setDirection(Servo.Direction.REVERSE);
    }
    public void loop(Gamepad gamepad){
        if (gamepad.left_bumper) {
            // open
            LeftClaw.setPosition(0.5);
        } else if (gamepad.left_trigger > 0) {
            // close
            LeftClaw.setPosition(.38);
        }
        // right claw
        if (gamepad.right_bumper) {
            // open
            RightClaw.setPosition(0.56);
        } else if (gamepad.right_trigger > 0) {
            // close
            RightClaw.setPosition(.66);
        }
    }
}
