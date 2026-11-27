package org.firstinspires.ftc.teamcode.Servo;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class FlowerRamp {

    private Servo leftServo, rightServo;

    public static class Config {
        public double upPos = 0.8;
        public double downPos = 0.0;
    }

    public static Config fConfig = new Config();

    public FlowerRamp(HardwareMap hardwareMap) {
        leftServo = hardwareMap.get(Servo.class, "left");
        rightServo = hardwareMap.get(Servo.class, "right");

        leftServo.setDirection(Servo.Direction.REVERSE);
        rightServo.setDirection(Servo.Direction.REVERSE);
    }

    public void OpenPos() {
        leftServo.setPosition(fConfig.upPos);
        rightServo.setPosition(fConfig.upPos);
    }

    public void DownPos() {
        leftServo.setPosition(fConfig.downPos);
        rightServo.setPosition(fConfig.downPos);
    }
}
