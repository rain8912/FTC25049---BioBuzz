package org.firstinspires.ftc.teamcode.Teleop.Subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

//Mecanum wheels are mounted 45 deg
//y = forward/backward
//x = strafing left/right
//Rx = rotating in place

//FLP = y + x + Rx
//BLP = y - x + Rx
//FRP = y - x - Rx
//BRP = y + x - Rx

//Power Normalization: scale all 4 proportionally using denominator
//Denom = max( |y| + |x| + |Rx|, 1)

//Fieldcentric:
//Rotated Strafe x' = Xcos(0) + Ysin(0)
//Rotated Forward y' = Ycos(0) - Xsin(0)

//Auton Encoder Distance: Ticks = ((Target Distance) / (pi * wheel diameter)) * ticks per revolution


//Error = target velo - currentvelo
//Output PID = (Kp * error) + (Ki * sum of prev error) + (Kd * ROC error)
//P = Kp * error
//  = Kp * (target velo - currentvelo)
//I = Ki * sum of prev error
//  = Ki * (error sum n-1 + error * total time)
//  = Ki * (error sum n-1 + ((target velo - currentvelo)(dt))
//D = Kd * ROC error
//  = Kd * ((Current error - prev error) / (total time))
//  = Kd * ((Current error - prev error) / (dt))

//Output PIDF = (Kp * error) + (Ki * sum of prev error) + (Kd * ROC error) + Uff
//Uff = universal feedforward
//    = Kv + Ka + Kf + Kg
//    = (Kv * target velo) + (Ka * target accel) + (Ksf * sgn(target velo))

//sgn = signum function(sign function)
//sgn x = {1 if x > 0 | 0 if x = 0 | -1 if x < 0}

@Config
public class MechDrive {

    private DcMotorEx frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor;
    private GoBildaPinpointDriver deadwheel;
//    private IMU imu;
//
//    public double targetVelo = 0;
//    public double targetAccel = 0;
//
//    public static class PIDFdriveConfig {
//        public double kP = 0;
//        public double kV = 0;
//        public double kA = 0;
//        public double kS = 0;
//
//        public double maxPitch = 5.0;
//        public double minPitch = -5.0;
//        public double currentPitch;
//        public double sgn = 0;
//    }
//
//    public static PIDFdriveConfig dConfig = new PIDFdriveConfig();

    public MechDrive(HardwareMap hardwareMap) {
        frontLeftMotor = hardwareMap.get(DcMotorEx.class, "frontLeftMotor");
        frontRightMotor = hardwareMap.get(DcMotorEx.class,"frontRightMotor");
        backLeftMotor = hardwareMap.get(DcMotorEx.class, "backLeftMotor");
        backRightMotor = hardwareMap.get(DcMotorEx.class, "backRightMotor");

//        imu = hardwareMap.get(IMU.class, "imu");
//        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP, RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
//        imu.initialize(parameters);

        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

//        deadwheel = hardwareMap.get(GoBildaPinpointDriver.class, "wheel");
//        deadwheel.setOffsets(0,0, DistanceUnit.METER);
//        deadwheel.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD, GoBildaPinpointDriver.EncoderDirection.REVERSED);
    }

    public void drive(Gamepad gamepad1) {
//        double targetVelo = -gamepad1.left_stick_y;
        double y = -gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x * 1.1;
        double rx = gamepad1.right_stick_x;

//        dConfig.currentPitch = imu.getRobotYawPitchRollAngles().getPitch(AngleUnit.DEGREES);
//
//        if (targetVelo > 0) {
//            dConfig.sgn = 1.0;
//        } else if (targetVelo < 0) {
//            dConfig.sgn = -1.0;
//        } else {
//            dConfig.sgn = 0.0;
//        }
//
//        double error = 0.0 - dConfig.currentPitch;
//        double p = dConfig.kP * error;
//        double ff = (dConfig.kV * targetVelo) + (dConfig.kA * targetAccel) + (dConfig.kS * dConfig.sgn);
//
//        double y = p + ff;
//
//        if (dConfig.currentPitch > dConfig.maxPitch && y < 0) {
//            y = -0.3;
//        } else if (dConfig.currentPitch < dConfig.minPitch && y > 0) {
//            y = 0.3;
//        }

        double denom = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);

        double frontLeftPower = (y + x + rx) / denom;
        double frontRightPower = (y - x - rx) / denom;
        double backLeftPower = (y - x + rx) / denom;
        double backRightPower = (y + x - rx) / denom;

        frontLeftMotor.setPower(frontLeftPower);
        frontRightMotor.setPower(frontRightPower);
        backLeftMotor.setPower(backLeftPower);
        backRightMotor.setPower(backRightPower);
    }
}
