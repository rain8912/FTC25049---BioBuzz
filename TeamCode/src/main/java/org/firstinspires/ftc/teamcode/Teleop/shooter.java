//    package org.firstinspires.ftc.teamcode.Teleop;
//    import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//    import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//    import com.qualcomm.robotcore.hardware.DcMotor;
//    import com.qualcomm.robotcore.hardware.DcMotorSimple;
//    import com.qualcomm.robotcore.hardware.HardwareMap;
//    public class shooter {
//
//        private DcMotor leftMotor, rightMotor;
//
//        public shooter(HardwareMap HwMap) {
//            leftMotor = HwMap.get(DcMotor.class, "leftMotor");
//            rightMotor = HwMap.get(DcMotor.class, "rightMotor");
//
//            leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
//        }
//
//        public void startshoot() {
//            leftMotor.setPower(0.1);
//            rightMotor.setPower(0.1);
//        }
//
//        public void stopshoot() {
//            leftMotor.setPower(0.0);
//            rightMotor.setPower(0.0);
//        }
//    }
