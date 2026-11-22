//package org.firstinspires.ftc.teamcode.Testing.Subsystems;
//
//import com.qualcomm.robotcore.hardware.HardwareMap;
//
//public class AutoOrientationTesting {
//
//    private OdometryTesting odometry;
//
//    public double Hx = 0.0;
//    public double Hy = 0.0;
//
//    public double Bx;
//    public double By;
//
//    public AutoOrientationTesting(HardwareMap hardwareMap) {
//        odometry = new OdometryTesting(hardwareMap);
//    }
//
//    public double Orientate() {
//        Bx = odometry.getX();
//        By = odometry.getY();
//
//        double x = Hx - Bx;
//        double y = Hy - By;
//
//        double theta = Math.atan2(y, x);
//        double thetaRtoD = Math.toDegrees(theta);
//    }
//}
