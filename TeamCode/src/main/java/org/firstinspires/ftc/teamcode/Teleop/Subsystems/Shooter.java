    package org.firstinspires.ftc.teamcode.Teleop.Subsystems;
    import com.acmerobotics.dashboard.config.Config;
    import com.qualcomm.robotcore.hardware.DcMotor;
    import com.qualcomm.robotcore.hardware.DcMotorEx;
    import com.qualcomm.robotcore.hardware.HardwareMap;

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

    @Config
    public class Shooter {

        private DcMotorEx motor;

        public double targetVelo = 0;
        public double targetAccel = 0;

        @Config
        public static class PIDFshooterConfig {
            public double kP = 0;
            public double kV = 0.0;
            public double kSF = 0.0;
            public double kA = 0.0;

            public static double currentVelo;
            public double sgn = 0;
        }

        public static PIDFshooterConfig sConfig = new PIDFshooterConfig();
        public Shooter(HardwareMap hardwareMap) {
            motor = hardwareMap.get(DcMotorEx.class, "shooterMotor");
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        }

        public void startShoot() {
//            sConfig.currentVelo = motor.getVelocity();
//
//            if (targetVelo > 0) {
//                sConfig.sgn = 1.0;
//            } else if (targetVelo < 0) {
//                sConfig.sgn = -1.0;
//            }
//
//            double error = targetVelo - sConfig.currentVelo;
//            double p = sConfig.kP * error;
//            double ff = (sConfig.kV * targetVelo) + (sConfig.kA * targetAccel) + (sConfig.kSF * sConfig.sgn);
//
//            motor.setPower(p + ff);
            motor.setPower(1.0);

        }
        public void stopshoot() {
            motor.setPower(0.0);
        }
    }