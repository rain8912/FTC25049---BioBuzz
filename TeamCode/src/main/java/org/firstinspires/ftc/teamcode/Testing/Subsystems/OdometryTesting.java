package org.firstinspires.ftc.teamcode.Testing.Subsystems;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.robotcore.external.navigation.UnnormalizedAngleUnit;

public class OdometryTesting {

    private GoBildaPinpointDriver pinPointLocation;

    public OdometryTesting(HardwareMap hardwareMap) {

        pinPointLocation = hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint");

        pinPointLocation.setOffsets(-60.667, -155.296, DistanceUnit.MM);
        pinPointLocation.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        pinPointLocation.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD, GoBildaPinpointDriver.EncoderDirection.FORWARD);

        pinPointLocation.resetPosAndIMU();
    }

    public void Update() {
        pinPointLocation.update();
    }

    public double getX() {
        return pinPointLocation.getPosX(DistanceUnit.MM);
    }

    public double getY() {
        return pinPointLocation.getPosY(DistanceUnit.MM);
    }

    public double getHeading() {
        return pinPointLocation.getHeading(UnnormalizedAngleUnit.DEGREES);
    }

    public void setPos(double x, double y, double heading) {
        pinPointLocation.setPosition(new org.firstinspires.ftc.robotcore.external.navigation.Pose2D(DistanceUnit.MM, x, y, AngleUnit.DEGREES, heading));
    }
}
