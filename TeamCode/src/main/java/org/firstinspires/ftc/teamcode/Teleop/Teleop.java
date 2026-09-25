package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Vision.LimeLight;

@TeleOp(name="Shooter ctrl test")
public class Teleop extends OpMode {
//    private shooter shooter;
//    private boolean RB = false;
//    private boolean shooterOn = false;
    private LimeLight limeLight;

    @Override
    public void init() {
//        shooter = new shooter(hardwareMap);
        limeLight = new LimeLight(hardwareMap);
    }

    public void loop() {
//        if(gamepad1.right_bumper && !RB) {
//            shooterOn = !shooterOn;
//
//            RB = true;
//
//            if (shooterOn) {
//                shooter.startshoot();
//            } else {
//                shooter.stopshoot();
//            }
//        }
//
//        if (!gamepad1.right_bumper) {
//            RB = false;
//        }

        telemetry.addData("Tag Count", limeLight.getTagCount());
        telemetry.addData("Target X", limeLight.aprilx());
        telemetry.addData("Target Y", limeLight.aprily());
        telemetry.addData("Target A", limeLight.aprila());

        telemetry.update();
    }
}