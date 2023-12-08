package org.firstinspires.ftc.teamcode.Hardware;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class Intake {
    private Servo claw;
    public void initialize(HardwareMap hardwareMap) {
        claw = hardwareMap.get(Servo.class, "Claw");
    }

    public void loop(Gamepad gamepad1) {

        // test loop

    }

    public void telemetry(Telemetry telemetry) {
        telemetry.addData("Pos: ", claw.getPosition());
        if (claw.getPosition() == 0) {
            // display wa wa
        }   else {
            // blah blah
        }
    }
}
