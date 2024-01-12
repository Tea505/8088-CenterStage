package org.firstinspires.ftc.teamcode.Hardware;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.LibraryFiles.Contraption;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@Config
public class DriveTrain extends Contraption {

    public SampleMecanumDrive drive;

    public DriveTrain(LinearOpMode opMode) {
        this.opMode=opMode;
    }
    @Override
    public void initialize(HardwareMap hwMap) {
    drive = new SampleMecanumDrive(hwMap);

    }

    public void loop(Gamepad gamepad) {

        double vertical = gamepad.left_stick_y;
        double horizontal = gamepad.left_stick_x;
        double pivot = gamepad.right_stick_x;

        double FLS = (vertical - horizontal) - pivot;
        double BLS = (vertical + horizontal) - pivot;
        double BRS = (vertical - horizontal) + pivot;
        double FRS = vertical + horizontal + pivot;

        drive.setMotorPowers(FLS,BLS,BRS,FRS );
    }
}
