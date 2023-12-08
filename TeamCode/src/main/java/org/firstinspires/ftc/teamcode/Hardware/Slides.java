package org.firstinspires.ftc.teamcode.Hardware;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Config
public class Slides {
    private DcMotorEx leftLift;
    private DcMotorEx rightLift;

    private double LOW_POS = 0;
    private double MID_POS = 0;
    private double BACKDROP_POS = 0;
    private double HIGH_POS = 0;
    public void initialize(HardwareMap hardwareMap) {

        leftLift = hardwareMap.get(DcMotorEx.class,"leftLift");
        leftLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftLift.setDirection(DcMotorSimple.Direction.REVERSE);

        rightLift = hardwareMap.get(DcMotorEx.class, "rightLift");
        rightLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightLift.setDirection(DcMotorSimple.Direction.REVERSE);
    }
}
