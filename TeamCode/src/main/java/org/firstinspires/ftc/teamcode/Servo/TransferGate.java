package org.firstinspires.ftc.teamcode.Servo;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class TransferGate {
    private Servo gateServo;

    public static class GateConfig {
        public double startPos = 0.6;
        public double openPos = -0.4;
    }

    public static GateConfig gConfig = new GateConfig();

    public TransferGate(HardwareMap hardwareMap) {
        gateServo = hardwareMap.get(Servo.class, "gate");
        gateServo.close();
    }

    public void GateOpen() {
        gateServo.setPosition(gConfig.openPos);
    }

    public void GateClose() {
        gateServo.setPosition(gConfig.startPos);
    }
}
