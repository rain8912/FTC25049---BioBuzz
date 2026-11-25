package org.firstinspires.ftc.teamcode.Teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

//import org.firstinspires.ftc.teamcode.Sensor.IntakeSensorDetector;
//import org.firstinspires.ftc.teamcode.Sensor.BreakBeam;
import org.firstinspires.ftc.teamcode.Servo.FlowerRamp;
import org.firstinspires.ftc.teamcode.Servo.TransferGate;
import org.firstinspires.ftc.teamcode.Teleop.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Teleop.Subsystems.MechDrive;
import org.firstinspires.ftc.teamcode.Teleop.Subsystems.Shooter;

@Config
@TeleOp(name="Teleop")
public class Teleop extends OpMode {
    private Shooter shooter;
    private boolean circle, RB, LB, bCross, bSqaure = false;
    private boolean shooterOn, intakeOn, outtakeOn, transfer, ramp = false;
    private boolean gateOpened = true;
    private MechDrive mechDrive;
    private Intake intake;
    private TransferGate gate;
    private FlowerRamp flowerRamp;
//    private BreakBeam breakBeam;
//    private LimeLight limeLight;
//    private IntakeSensorDetector colourSensor;
    private final ElapsedTime revTimer = new ElapsedTime();

    @Override
    public void init() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        shooter = new Shooter(hardwareMap);
        mechDrive = new MechDrive(hardwareMap);
        intake = new Intake(hardwareMap);
        gate = new TransferGate(hardwareMap);
        flowerRamp = new FlowerRamp(hardwareMap);
//        breakBeam = new BreakBeam(hardwareMap);
//        colourSensor = new IntakeSensorDetector(hardwareMap);
//        limeLight = new LimeLight(hardwareMap);
//        limeLight.pipelineSwitch(1);
    }

    public void start() {
        gate.GateClose();
    }

    public void loop() {

        mechDrive.drive(gamepad1);

        shooter.update();


        if(gamepad1.circle && !circle) {
            shooterOn = !shooterOn;
            circle = true;

            if (shooterOn) {
                shooter.startShoot();
                revTimer.reset();
                gateOpened = false;
            } else {
                shooter.stopShoot();
                gate.GateClose();
            }
        }
        circle = gamepad1.circle;

        if (shooterOn && !gateOpened && revTimer.milliseconds() >= 2000) {
            gate.GateOpen();
            gateOpened = true;
        }

//        if (breakBeam.isBeam4Broken() && breakBeam.isBeam3Broken() && breakBeam.isBeam2Broken() && breakBeam.isBeam1Broken()) {
//            intakeOn = false;
//        }

        if(gamepad1.right_bumper && !RB) {
            intakeOn = !intakeOn;
            RB = true;

            if(intakeOn) {
                intake.StartIntake();
            } else {
                intake.StopIntake();
            }
        }
        RB = gamepad1.right_bumper;

        if (gamepad1.left_bumper && !LB) {
            outtakeOn = !outtakeOn;
            LB = true;

            if (outtakeOn) {
                intake.StartOuttake();
            } else {
                intake.StopIntake();
            }
        }
        LB = gamepad1.left_bumper;

        if (gamepad1.square) {
            flowerRamp.closePos();
        }

        if (gamepad1.triangle) {
            flowerRamp.openPos();
        }

//        if (gamepad1.right_bumper && !RB) {
//            gateOn = !gateOn;
//            RB = true;
//
//            if(gateOn) {
//                gate.GateOpen();
//            } else {
//                gate.GateClose();
//            }
//        }
//        RB = gamepad1.right_bumper;


//        if (gamepad1.right_bumper) {
//            limeLight.pipelineSwitch(0);
//        }
//
//        if (gamepad1.left_bumper) {
//            limeLight.pipelineSwitch(1);
//        }



//        telemetry.addData("Tag Count", limeLight.getTagCount());
//        telemetry.addData("Target X", limeLight.aprilx());
//        telemetry.addData("Target Y", limeLight.aprily());
//        telemetry.addData("Target A", limeLight.aprila());
//        telemetry.addData("Pollen Detection", limeLight.detectPollen());
//        telemetry.addData("pollen X", limeLight.pollenX());
//        telemetry.addData("pollen A", limeLight.pollenA());
//        telemetry.addData("Intake Pollen Detected", colourSensor.detectedPollen(telemetry));
//        telemetry.addData("gateOpened", gateOpened);
//        telemetry.addData("shooterOn", shooterOn);
//        telemetry.addData("revTimer (ms)", (int) revTimer.milliseconds());
        telemetry.addData("currentVelo", shooter.currentVelo);
        telemetry.addData("targetVelo", shooter.sConfig.targetVelo);
        telemetry.addData("Kf", shooter.sConfig.kF);
        telemetry.addData("Ks", shooter.sConfig.kS);
        telemetry.addData("Kd", shooter.sConfig.kD);
        telemetry.addData("Ki", shooter.sConfig.kI);
        telemetry.addData("Kp", shooter.sConfig.kP);
        telemetry.addData("rawTicks", shooter.getRawPosition());
        telemetry.addData("loopHeartbeat", System.currentTimeMillis());

        telemetry.update();
    }
}