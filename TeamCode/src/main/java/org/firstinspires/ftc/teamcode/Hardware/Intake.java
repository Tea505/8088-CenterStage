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

    public static Servo LeftClaw;
    public static Servo RightClaw;

    public static double LEFTCLAW_CLOSE_POS = 0.75;
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

        //LeftClaw.setPosition(LEFTCLAW_CLOSE_POS);
        //RightClaw.setPosition(RIGHTCLAW_CLOSE_POS);

    }

    public void loop(Gamepad gamepad) {
        if (gamepad.left_trigger > 0) {
            // close
            RightClaw.setPosition(.35);
        }else if (gamepad.left_bumper)
            RightClaw.setPosition(0.5);
        }

    public void loop2(Gamepad gamepad){
        if (gamepad.right_trigger>0) {
            // open
            LeftClaw.setPosition(0.75);
        }else if(gamepad.right_bumper)
            LeftClaw.setPosition(0.5);
        }


    public static void OpenLeft() {
        LeftClaw.setPosition(LEFTCLAW_OPEN_POS);
    }

    public static void OpenRight() {
    RightClaw.setPosition(RIGHTCLAW_OPEN_POS);
    LeftClaw.setPosition(LEFTCLAW_CLOSE_POS);
    }

    public static void OpenBoth() {
        LeftClaw.setPosition(LEFTCLAW_OPEN_POS);
        RightClaw.setPosition((RIGHTCLAW_OPEN_POS));
    }
    public static void closeBoth(){
        LeftClaw.setPosition(LEFTCLAW_CLOSE_POS);
        RightClaw.setPosition(RIGHTCLAW_CLOSE_POS);
    }

    public static void closeLeft() {
        LeftClaw.setPosition(LEFTCLAW_CLOSE_POS);
    }

    public static void closeRight() {
        RightClaw.setPosition(RIGHTCLAW_CLOSE_POS);
    }
}
