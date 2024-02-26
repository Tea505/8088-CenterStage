package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Hardware.Arm;
import org.firstinspires.ftc.teamcode.Hardware.Climber;
import org.firstinspires.ftc.teamcode.Hardware.DriveTrain;
import org.firstinspires.ftc.teamcode.Hardware.Intake;
import org.firstinspires.ftc.teamcode.Hardware.Lift;
import org.firstinspires.ftc.teamcode.Hardware.MecanumTeleOp;
import org.firstinspires.ftc.teamcode.Hardware.Shooter;
import org.firstinspires.ftc.teamcode.Hardware.Wrist;

@TeleOp(name = "8088Teleop2024")
public class Teleop extends LinearOpMode {
    private MecanumTeleOp drive = new MecanumTeleOp(this);
    private Intake intake = new Intake(this);
    private Arm arm = new Arm(this);
    private Wrist wrist = new Wrist(this);
    private Climber climber = new Climber(this);
     private Lift lift =new Lift(this);
     private Shooter shooter = new Shooter(this);
    @Override
    public void runOpMode(){
        drive.initialize(hardwareMap);
        intake.initialize(hardwareMap);
        arm.initialize(hardwareMap);
        wrist.initialize(hardwareMap);
        climber.initialize(hardwareMap);
        lift.initialize(hardwareMap);
        shooter.initialize(hardwareMap);


        waitForStart();
        if (opModeIsActive()) {
            while (opModeIsActive()) {
                drive.loopish(gamepad1);
                intake.loop(gamepad1);
                arm.loop(gamepad2);
                wrist.loop(gamepad2);
                climber.loop(gamepad2);
                lift.loop(gamepad2);
                shooter.loop(gamepad1);
                intake.loop2(gamepad1);
            }
        }
    }
}

