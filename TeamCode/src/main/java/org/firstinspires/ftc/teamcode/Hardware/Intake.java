package org.firstinspires.ftc.teamcode.Hardware;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;

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

    public static double LEFTCLAW_CLOSE_POS = 0.7;
    public static double LEFTCLAW_OPEN_POS = 0.5;

    public static double RIGHTCLAW_CLOSE_POS = 0.3;
    public static double RIGHTCLAW_OPEN_POS = 0.5;

    public Intake(LinearOpMode opMode) {
        this.opMode = opMode;
    }

    @Override
    public void initialize(HardwareMap hwMap) {

        LeftClaw = hwMap.get(Servo.class, "LeftClaw");
        RightClaw = hwMap.get(Servo.class, "RightClaw");

        LeftClaw.setPosition(LEFTCLAW_CLOSE_POS);
        RightClaw.setPosition(RIGHTCLAW_CLOSE_POS);

    }

    public void loop(Gamepad gamepad) {
        if (gamepad.left_trigger > 0) {
            // close
            LeftClaw.setPosition(0.7);
            RightClaw.setPosition(0.3);
        }
        if (gamepad.right_trigger > 0) {
            // open
            LeftClaw.setPosition(0.5);
            RightClaw.setPosition(0.5);
        }
    }

    public void OpenLeft() {
        LeftClaw.setPosition(LEFTCLAW_OPEN_POS);
    }

    public void OpenRight() {
    RightClaw.setPosition(RIGHTCLAW_OPEN_POS);
    }

    public void OpenBoth() {
        LeftClaw.setPosition(LEFTCLAW_OPEN_POS);
        RightClaw.setPosition((RIGHTCLAW_OPEN_POS));
    }
}