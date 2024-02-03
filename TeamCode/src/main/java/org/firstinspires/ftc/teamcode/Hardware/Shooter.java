package org.firstinspires.ftc.teamcode.Hardware;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.LibraryFiles.Contraption;
@Config
public class Shooter extends Contraption {
    public static Servo Shooter;
    public Shooter(LinearOpMode opMode) {
        this.opMode = opMode;
    }
    @Override
    public void initialize(HardwareMap hwMap) {
        Shooter = hwMap.get(Servo.class, "SHOOTER");
    }
    public void loop(Gamepad gamepad) {
        while(gamepad.dpad_up){
            Shooter.setPosition(1);
        }
    }
}
