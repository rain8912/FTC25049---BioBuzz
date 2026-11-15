//package org.firstinspires.ftc.teamcode.Sensor;
//
//import com.qualcomm.robotcore.hardware.HardwareMap;
//import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
//import com.qualcomm.robotcore.hardware.NormalizedRGBA;
//
//import org.firstinspires.ftc.robotcore.external.Telemetry;
//
//public class ColourBench {
//
//    NormalizedColorSensor colourSensor;
//
//    public enum detectedPollen {
//
//        RED,
//        BLUE,
//        YELLOW,
//        UNKNOWN
//
//    }
//
//    public void init(HardwareMap hardwareMap) {
//        colourSensor = hardwareMap.get(NormalizedColorSensor.class, "color");
//    }
//
//    public detectedPollen detectPollen(Telemetry telemetry) {
//        NormalizedRGBA colours = colourSensor.getNormalizedColors();
//
//        float red, blue, green;
//        red = colours.red / colours.alpha;
//        blue = colours.blue / colours.alpha;
//        green = colours.green / colours.alpha;
//
//        telemetry.addData("red", red);
//        telemetry.addData("blue", blue);
//        telemetry.addData("green", green);
//
////red, blue, green lowest/highest close to sensor w light: 0.0217/0.0262, 0.0105/0.0131, 0.0346/0.0428
////red, blue, green lowest/highest further from sensor w light: 0.0074/0.0079, 0.0046/0.0072, 0.0107/0.012
//
//        //values are inconsistent
////        if (red >= 0.0217 && red <= 0.0262 && (blue >= 0.0105 && blue <= 0.0131 && (green >= 0.0346 && green <= 0.0428))) {
////            return detectedPollen.YELLOW;
////        }
//
//        boolean isBrightEnough = colours.alpha > 0.005;
//        boolean isPollen = (green > red) && (red > blue);
//        boolean matchRatio = (blue > 0) && (green/blue >= 1.5);
//
//        if (isBrightEnough && isPollen && matchRatio) {
//            return detectedPollen.YELLOW;
//        }
//
//        return detectedPollen.UNKNOWN;
//    }
//}
