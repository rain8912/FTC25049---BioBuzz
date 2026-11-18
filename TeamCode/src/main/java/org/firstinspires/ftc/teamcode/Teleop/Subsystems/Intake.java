package org.firstinspires.ftc.teamcode.Teleop.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {

    private DcMotorEx frontMotor, backMotor;

    public Intake(HardwareMap hardwareMap) {
        frontMotor = hardwareMap.get(DcMotorEx.class, "frontIntake");
        backMotor = hardwareMap.get(DcMotorEx.class, "backIntake");

        frontMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        frontMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        backMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    public void StartIntake() {
        frontMotor.setPower(1.0);
        backMotor.setPower(-1.0);
    }
    public void StopIntake() {
        frontMotor.setPower(0.0);
        backMotor.setPower(0.0);
    }


    public void StartOuttake() {
        frontMotor.setPower(-1.0);
        backMotor.setPower(1.0);
    }
}
