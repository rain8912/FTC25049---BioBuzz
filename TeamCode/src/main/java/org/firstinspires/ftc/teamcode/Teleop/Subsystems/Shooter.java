package org.firstinspires.ftc.teamcode.Teleop.Subsystems;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.opencv.core.Mat;

//.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT) = keeps most speed if you drop power

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

//linear interpolation formula:
//  y = y1 + ((x-x1)/(x2-x1)) * (y2 - y1)

//    @Config
//    public class Shooter {
//
//        private DcMotorEx motor;
//
//        @Config
//        public static class PIDFshooterConfig {
//            public double kP = 0;
//            public double kV = 0.0;
//            public double kSF = 0.0;
//            public double targetVelo = 0;
//            public double currentVelo = 0;
//        }
//
//        public static PIDFshooterConfig sConfig = new PIDFshooterConfig();
//        public Shooter(HardwareMap hardwareMap) {
//            motor = hardwareMap.get(DcMotorEx.class, "shooterMotor");
//            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
//        }
//
//        public void startShoot() {
//            sConfig.currentVelo = motor.getVelocity();
//
//            double error = sConfig.targetVelo - sConfig.currentVelo;
//            double p = sConfig.kP * error;
//            double ff = sConfig.kV * sConfig.targetVelo + (sConfig.kSF * Math.signum(sConfig.targetVelo));
//
//            motor.setPower(p + ff);
////            motor.setPower(1.0);
//
//        }
//        public void stopShoot() {
//            motor.setPower(0.0);
//        }
//    }

@Config
public class Shooter {

    private final DcMotorEx motor;
    private final ElapsedTime timer = new ElapsedTime();

    public static double TICKS_PER_REV = 28.0;

    private boolean isShooting = false;
    private boolean firstRun = true;
    private double integralSum = 0;
    private double lastError = 0;

    public double currentVelo;
    public double rpmThreshold;


    public static class PIDFshooterConfig {
        public double kP = 0.0002;
        public double kD = 0.0;
        public double kF = 0.000158;
        public double kS = 0.084;              // Static friction feedforward (usually 0.01 - 0.05)
        public double targetVelo = 4500.0;
    }

    public static org.firstinspires.ftc.teamcode.Teleop.Subsystems.Shooter.PIDFshooterConfig sConfig = new org.firstinspires.ftc.teamcode.Teleop.Subsystems.Shooter.PIDFshooterConfig();

    public Shooter(HardwareMap hardwareMap) {
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

    public int getRawPosition() {
        return motor.getCurrentPosition();
    }

    public double getRPM() {
        return (motor.getVelocity() * 60.0) / TICKS_PER_REV;
    }

    public void update() {
        currentVelo = getRPM();

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

        // 2. Accumulate and clamp integral to prevent windup
        integralSum += error * dt;
//        integralSum = Range.clip(integralSum, -sConfig.maxIntegral, sConfig.maxIntegral);

        // 3. Calculate PID + Velocity & Static Feedforward
        double p = sConfig.kP * error;
//        double i = sConfig.kI * integralSum;
        double d = sConfig.kD * derivative;
        double f = (sConfig.kF * sConfig.targetVelo) + (sConfig.kS * Math.signum(sConfig.targetVelo));

        motor.setPower(p + d + f);
    }
}
