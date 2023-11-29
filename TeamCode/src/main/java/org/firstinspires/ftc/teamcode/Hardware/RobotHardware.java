package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.drive.DriveConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Nonnegative;


public class RobotHardware {

    // drivetrain
    public DcMotorEx leftFront;
    public DcMotorEx leftRear;
    public DcMotorEx rightFront;
    public DcMotorEx rightRear;

    // Imu
    public IMU imu;

    /**
     * HardwareMap storage.
     */
    private HardwareMap hardwareMap;
    private Telemetry telemetry;

    private double imuAngle ;

    /**
     * Voltage timer and voltage value.
     */
    private ElapsedTime voltageTimer = new ElapsedTime();
    private double voltage = 0.0;

    private static RobotHardware instance = null;
    public boolean enabled;

    public List<LynxModule> modules;
    private ArrayList<HSubsystem> subsystems;

    /**
     * Creating the singleton the first time, instantiating.
     */
    public static RobotHardware getInstance() {
        if (instance == null) {
            instance = new RobotHardware();
        }
        instance.enabled = true;
        return instance;
    }

    /**
     * Created at the start of every OpMode.
     *
     * @param hardwareMap The HardwareMap of the robot, storing all hardware devices
     * @param telemetry Saved for later in the event FTC Dashboard used
     */
    public void init(HardwareMap hardwareMap, Telemetry telemetry) {
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;


        voltage = hardwareMap.voltageSensor.iterator().next().getVoltage();

        this.subsystems = new ArrayList<>();
        modules = hardwareMap.getAll(LynxModule.class);
        modules.get(0).setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);
        modules.get(1).setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);


        imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                DriveConstants.LOGO_FACING_DIR, DriveConstants.USB_FACING_DIR));
        imu.initialize(parameters);

        // DRIVETRAIN
        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);

        leftRear = hardwareMap.get(DcMotorEx.class, "leftRear");
        leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftRear.setDirection(DcMotorSimple.Direction.FORWARD);

        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);

        rightRear = hardwareMap.get(DcMotorEx.class, "rightRear");
        rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRear.setDirection(DcMotorSimple.Direction.REVERSE);
    }

        public void read() {
//        imuAngle = imu.getAngularOrientation().firstAngle;
            for (HSubsystem subsystem : subsystems) {
                subsystem.read();
            }
        }

        public void write() {
            for (HSubsystem subsystem : subsystems) {
                subsystem.write();
            }
        }

        public void periodic() {
            if (voltageTimer.seconds() > 5) {
                voltageTimer.reset();
                voltage = hardwareMap.voltageSensor.iterator().next().getVoltage();
            }

            for (HSubsystem subsystem : subsystems) {
                subsystem.periodic();
            }
        }

        public void reset() {
            for (HSubsystem subsystem : subsystems) {
                subsystem.reset();
            }
        }

        public void clearBulkCache() {
            modules.get(0).clearBulkCache();
            modules.get(1).clearBulkCache();
        }

        public double getVoltage() {
            return voltage;
        }

        // TODO add offset
        // imuAngle - imuOffset;
        @Nonnegative
        public double getAngle() {
            return imuAngle;
        }

        public void addSubsystem(HSubsystem... subsystems) {
            this.subsystems.addAll(Arrays.asList(subsystems));
        }

        public void log(String data) {
            telemetry.addLine(data);
        }

        public void log(String data, Object input) {
            telemetry.addData(data, input.toString());
        }


    }
