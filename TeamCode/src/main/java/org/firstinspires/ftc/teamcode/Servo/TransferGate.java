package org.firstinspires.ftc.teamcode.Servo;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


@Config
public class TransferGate {
    private Servo gateServo;

    public static class GateConfig {
        public double openPos = 0.9;
        public double closePos = 0.1;
    }

    public static GateConfig gConfig = new GateConfig();

    public TransferGate(HardwareMap hardwareMap) {
        gateServo = hardwareMap.get(Servo.class, "gate");
        gateServo.setDirection(Servo.Direction.REVERSE);
        gateServo.close();
    }

    public void GateOpen() {
        gateServo.setPosition(gConfig.openPos);
    }

    public void GateClose() {
        gateServo.setPosition(gConfig.closePos);
    }
}
