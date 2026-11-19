//package org.firstinspires.ftc.teamcode.Servo;
//
//import com.qualcomm.robotcore.hardware.HardwareMap;
//import com.qualcomm.robotcore.hardware.Servo;
//
//public class FlowerRamp {
//
//    private Servo leftServo, rightServo;
//
//    public static class Config {
//        public double openPos = 0.8;
//        public double closePos = 0.0;
//    }
//
//    public static Config fConfig = new Config();
//
//    public FlowerRamp(HardwareMap hardwareMap) {
//        leftServo = hardwareMap.get(Servo.class, "left");
//        rightServo = hardwareMap.get(Servo.class, "right");
//
//        leftServo.setDirection(Servo.Direction.REVERSE);
//        rightServo.setDirection(Servo.Direction.REVERSE);
//    }
//
//    public void openPos() {
//        leftServo.setPosition(fConfig.openPos);
//        rightServo.setPosition(fConfig.openPos);
//    }
//
//    public void closePos() {
//        leftServo.setPosition(fConfig.closePos);
//        rightServo.setPosition(fConfig.closePos);
//    }
//}
