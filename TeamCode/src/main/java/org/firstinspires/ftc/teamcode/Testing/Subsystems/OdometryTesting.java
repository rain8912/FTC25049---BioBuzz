package org.firstinspires.ftc.teamcode.Testing.Subsystems;

import com.pedropathing.revhub.localizers.PinpointConfig;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.robotcore.external.navigation.UnnormalizedAngleUnit;

public class OdometryTesting {

    private GoBildaPinpointDriver pinPointLocation;

//    public static PinpointConfig localizerConfig = new PinpointConfig(c -> {
//        c.name.set("pinpoint");
//        c.podType.set(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
//        c.xPodOffset.set(0.5017178828322043);
//        c.yPodOffset.set(1.752950563205509);
//        c.xPodDirection.set(GoBildaPinpointDriver.EncoderDirection.REVERSED);
//        c.yPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
//        c.globalDistanceUnit.set(DistanceUnit.INCH);
//        c.offsetUnits.set(DistanceUnit.INCH);
//    });

    public OdometryTesting(HardwareMap hardwareMap) {

        pinPointLocation = hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint");

        pinPointLocation.setOffsets(0.7286881274125708, -1.9257135466327817, DistanceUnit.MM);
        pinPointLocation.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);

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
