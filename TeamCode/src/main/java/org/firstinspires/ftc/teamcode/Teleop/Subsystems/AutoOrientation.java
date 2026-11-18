package org.firstinspires.ftc.teamcode.Teleop.Subsystems;

public class AutoOrientation {

    public double Hx = 0.0;
    public double Hy = 0.0;
    public double Bx = 0.0;
    public double By = 0.0;

    public double x = Hx - Bx;
    public double y = Hy - By;

    public double theta = Math.atan2(y, x);

    public double thetaRtoD = Math.toDegrees(theta);
}
