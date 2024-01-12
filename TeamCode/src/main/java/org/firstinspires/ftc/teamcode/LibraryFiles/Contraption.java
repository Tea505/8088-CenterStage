package org.firstinspires.ftc.teamcode.LibraryFiles;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public abstract class Contraption {

    protected LinearOpMode opMode;

    public abstract void initialize(HardwareMap hwMap);

    public void loop(Gamepad gamepad) {}


    public void loop(Gamepad gamepad1, Gamepad gamepad2) {}

    public void telemetry(Telemetry telemetry) {}

}
