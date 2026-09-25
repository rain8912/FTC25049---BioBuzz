///*
//package org.firstinspires.ftc.teamcode.Teleop;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//
////PID: Error = Setpoint - Current Position
////Output =
////(Kp x error) + (Ki x Sum of Previous Errors) + (Kd x Sum of Change of Error)
//
////Kp: Rise Time - decrease / Overshoot - increase / settling time - small change / s - s error - decrease
////Ki: Rise Time - decrease / Overshoot - increase / settling time - increase / s - s error - decrease
////Kd: Rise Time - small change / Overshoot - Decrease / settling time - decrease / s - s error - no change
//
////RPM =
//public class PID {
//
//    private DcMotor leftMotor, rightMotor;
//
//    private double Kp = 0.0;
//    private double Ki = 0.0;
//    private double Kp = 0.0;
//    private double setPoint
//
//    public PID(HardwareMap HwMap) {
//        leftMotor = HwMap.get(DcMotor.class, "leftMotor");
//        rightMotor = HwMap.get(DcMotor.class, "rightMotor");
//
//        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
//    }
//
//    public class PIDCalc {
//
//        double error = setPoint; // - current position
//        double P = Kp * error;
//        double I = Ki;
//    }
//
//    public void startshoot() {
//        leftMotor.setPower(0.1);
//        rightMotor.setPower(0.1);
//    }
//
//    public void stopshoot() {
//        leftMotor.setPower(0.0);
//        rightMotor.setPower(0.0);
//    }
//}
//
//
//*/
