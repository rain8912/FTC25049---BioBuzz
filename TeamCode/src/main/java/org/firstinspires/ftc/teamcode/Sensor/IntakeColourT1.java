//package org.firstinspires.ftc.teamcode.Sensor;
//
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.DistanceSensor;
//import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
//import com.qualcomm.robotcore.hardware.NormalizedRGBA;
//import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
//import android.graphics.Color;
//
//@TeleOp(name = "Colour Sensor Test")
//public class ColourSensorTest extends LinearOpMode {
//    @Override
//    public void runOpMode() {
//        NormalizedColorSensor sensor =
//                hardwareMap.get(NormalizedColorSensor.class, "color");
//                        waitForStart();
//
//        while (opModeIsActive()) {
//            NormalizedRGBA c = sensor.getNormalizedColors();
//            float[] hsv = new float[3];
//            Color.colorToHSV(c.toColor(), hsv);
//            double cm = ((DistanceSensor) sensor).getDistance(DistanceUnit.CM);
//
//            boolean yellow = hsv[0] >= 45 && hsv[0] <= 75 && hsv[1] > 0.5;
//
//            telemetry.addData("Red", "%.3f", c.red);
//            telemetry.addData("Green", "%.3f", c.green);
//            telemetry.addData("Blue", "%.3f", c.blue);
//            telemetry.addData("Hue", "%.1f", hsv[0]);
//            telemetry.addData("Saturation", "%.2f", hsv[1]);
//            telemetry.addData("Distance (cm)", "%.2f", cm);
//            telemetry.addData("Looks yellow?", yellow);
//            telemetry.update();
//        }
//    }
//}
