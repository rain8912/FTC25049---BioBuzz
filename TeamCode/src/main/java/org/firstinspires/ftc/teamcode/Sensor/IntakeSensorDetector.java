//package org.firstinspires.ftc.teamcode.Sensor;
//
//import com.qualcomm.robotcore.hardware.HardwareMap;
//
//import org.firstinspires.ftc.robotcore.external.Telemetry;
//
////red, blue, green lowest/highest close to sensor w light: 0.0217/0.0262, 0.0105/0.0131, 0.0346/0.0428
////red, blue, green lowest/highest further from sensor w light: 0.0074/0.0079, 0.0046/0.0072, 0.0107/0.012
//
//public class IntakeSensorDetector {
//
//    private ColourBench cValues;
//
//    public IntakeSensorDetector(HardwareMap hardwareMap) {
//        cValues = new ColourBench();
//        cValues.init(hardwareMap);
//    }
//
//    public boolean detectedPollen(Telemetry telemetry) {
//        ColourBench.detectedPollen result = cValues.detectPollen(telemetry);
//
//        return result == ColourBench.detectedPollen.YELLOW;
//    }
//}
