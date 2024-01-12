package org.firstinspires.ftc.teamcode.Hardware;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.LibraryFiles.Contraption;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@Config
public class DriveTrain extends Contraption {

    public SampleMecanumDrive drive;
    public double powerMultiplier = 1;
    public DriveTrain(LinearOpMode opMode) {
        this.opMode = opMode;
    }
    @Override
    public void initialize(HardwareMap hwMap) {
        drive = new SampleMecanumDrive(hwMap);
        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void loop(Gamepad gamepad) {

        double vertical = gamepad.left_stick_y;
        double horizontal = gamepad.left_stick_x;
        double pivot = gamepad.right_stick_x;

        double FLS = (vertical - horizontal) - pivot;
        double BLS = (vertical + horizontal) - pivot;
        double BRS = (vertical - horizontal) + pivot;
        double FRS = vertical + horizontal + pivot;

        drive.setMotorPowers(FLS * powerMultiplier,BLS * powerMultiplier,
                BRS * powerMultiplier,FRS * powerMultiplier );

        if (gamepad.left_bumper) {
            powerMultiplier = 0.5;
        }   else {
            powerMultiplier = 1;
        }

    }
}
