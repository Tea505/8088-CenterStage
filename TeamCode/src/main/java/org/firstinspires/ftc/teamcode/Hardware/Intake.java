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

    public static double LEFTCLAW_CLOSE_POS = 0.38;
    public static double LEFTCLAW_OPEN_POS = 0.5;

    public static double RIGHTCLAW_CLOSE_POS = 0.66;
    public static double RIGHTCLAW_OPEN_POS = 0.56;

    public Intake(LinearOpMode opMode){
        this.opMode=opMode;
    }
    @Override
    public void initialize(HardwareMap hwMap) {

        LeftClaw = hwMap.get(Servo.class, "LeftClaw");
        RightClaw = hwMap.get(Servo.class, "RightClaw");

        RightClaw.setDirection(Servo.Direction.REVERSE);
        LeftClaw.setDirection(Servo.Direction.REVERSE);

        ClawInit();
    }
    public void loop(Gamepad gamepad){
        if (gamepad.left_bumper) {
            // open
            // LeftClaw.setPosition(0.5);
            LeftClaw.setPosition(LEFTCLAW_OPEN_POS);
        } else if (gamepad.left_trigger > 0) {
            // close
            // LeftClaw.setPosition(.38);
            LeftClaw.setPosition(LEFTCLAW_CLOSE_POS);
        }
        // right claw
        if (gamepad.right_bumper) {
            // open
            // RightClaw.setPosition(0.56);
            RightClaw.setPosition(RIGHTCLAW_OPEN_POS);
        } else if (gamepad.right_trigger > 0) {
            // close
            // RightClaw.setPosition(.66);
            RightClaw.setPosition(RIGHTCLAW_CLOSE_POS);
        }
    }
    public void ClawInit() {
        LeftClaw.setPosition(LEFTCLAW_CLOSE_POS);
        RightClaw.setPosition(RIGHTCLAW_CLOSE_POS);
    }
}
