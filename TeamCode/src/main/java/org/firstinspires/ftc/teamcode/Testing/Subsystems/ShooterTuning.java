package org.firstinspires.ftc.teamcode.Testing.Subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@Config
public class ShooterTuning {

    private final DcMotorEx motor;
    private final ElapsedTime timer = new ElapsedTime();

    private boolean isShooting = false;
    private boolean firstRun = true;
    private double integralSum = 0;
    private double lastError = 0;

    public double currentVelo = 0;

    public static class PIDFshooterConfig {
        public double kP = 0.0002;
        public double kD = 0.0;
        public double kF = 0.000158;
        public double kS = 0.084;           // Static friction feedforward (usually 0.01 - 0.05)
        public double targetVelo = 4500.0;
    }

    public static PIDFshooterConfig sConfig = new PIDFshooterConfig();

    public ShooterTuning(HardwareMap hardwareMap) {
        motor = hardwareMap.get(DcMotorEx.class, "shooterMotor");
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        motor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void startShoot() {
        isShooting = true;
        firstRun = true;
        integralSum = 0;
        lastError = 0;
        timer.reset();
    }

    public void stopShoot() {
        isShooting = false;
        firstRun = true;
        integralSum = 0;
        lastError = 0;
        motor.setPower(0.0);
    }

    public void update() {
        currentVelo = motor.getVelocity();

        if (!isShooting) {
            motor.setPower(0.0);
            return;
        }

        double dt = timer.seconds();
        timer.reset();

        double error = sConfig.targetVelo - currentVelo;

        // 1. Prevent first-frame derivative spike
        double derivative = 0;
        if (firstRun) {
            firstRun = false;
        } else if (dt > 0) {
            derivative = (error - lastError) / dt;
        }
        lastError = error;

        // 3. Calculate PID + Velocity & Static Feedforward
        double p = sConfig.kP * error;
        double d = sConfig.kD * derivative;
        double f = (sConfig.kF * sConfig.targetVelo) + (sConfig.kS * Math.signum(sConfig.targetVelo));

        motor.setPower(p + d + f);
    }
}
