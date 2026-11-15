//package org.firstinspires.ftc.teamcode.Teleop.Subsystems;
//
//import com.qualcomm.robotcore.hardware.DcMotorEx;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//
//import org.firstinspires.ftc.teamcode.Vision.LimeLight;
//
//public class Turret {
//
//    private DcMotorEx motor;
//    private LimeLight limeLight;
//    private double maxSpin = 0;
//    private double minSpin = 0;
//
//    public Turret(HardwareMap hardwareMap) {
//        motor = hardwareMap.get(DcMotorEx.class, "turretMotor");
//
//        limeLight = new LimeLight(hardwareMap);
//        limeLight.pipelineSwitch(0);
//    }
//
//    public void moveTurret() {
//    }
//
//}
