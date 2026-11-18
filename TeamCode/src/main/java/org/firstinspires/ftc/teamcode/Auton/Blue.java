package org.firstinspires.ftc.teamcode.Auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

//import org.firstinspires.ftc.teamcode.Teleop.Subsystems.Shooter;
//import org.firstinspires.ftc.teamcode.Sensor.BreakBeam;
import org.firstinspires.ftc.teamcode.Servo.FlowerRamp;
import org.firstinspires.ftc.teamcode.Teleop.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Teleop.Subsystems.Shooter;
import org.firstinspires.ftc.teamcode.Vision.LimeLight;

//section 5 equal squares from each side: far left square aim at far right april tag
//move from spawn to set position
//when in squares area, turn on shooter, open gate
//shoot for 1-2 seconds and close gate
//memorize last seen april tag position
//if april tag isn't in that position anymore switchpipeline(1) and collect 4 pollen: counter to 4 and move to set position on other side of hive, counter = 4 switchpipeline(0)
//when in square area, start shooter, open gate
//start shoot, open gate for 1-2 seconds then stop and close
//switchpipeline(1), memorize pollen positions

@Autonomous (name = "blue", group = "Autonomous")
public class Blue extends LinearOpMode {

    public void runOpMode() {

        Shooter shooter = new Shooter(hardwareMap);
        LimeLight limelight = new LimeLight(hardwareMap);
//        BreakBeam breakbeam = new BreakBeam(hardwareMap);
        Intake intake = new Intake(hardwareMap);
        FlowerRamp flowerRamp = new FlowerRamp(hardwareMap);

        limelight.pipelineSwitch(0);

        telemetry.addData("Initialize", "compelete");
        telemetry.update();


        waitForStart();

        while (opModeIsActive()) {

            shooter.startShoot();
        }
    }
}
