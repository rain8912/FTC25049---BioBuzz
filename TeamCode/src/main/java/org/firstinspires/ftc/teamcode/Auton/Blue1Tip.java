package org.firstinspires.ftc.teamcode.Auton;

import com.pedropathing.api.Paths;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import com.pedropathing.follower.Follower;

import org.firstinspires.ftc.teamcode.Servo.FlowerRamp;
import org.firstinspires.ftc.teamcode.Servo.TransferGate;
import org.firstinspires.ftc.teamcode.Teleop.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Teleop.Subsystems.Shooter;
import org.firstinspires.ftc.teamcode.pedro.Constants;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;

import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;
import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

import static com.pedropathing.ivy.commands.Commands.instant;
import static com.pedropathing.ivy.commands.Commands.waitMs;
import static com.pedropathing.ivy.commands.Commands.waitUntil;
import static com.pedropathing.ivy.groups.Groups.parallel;

//section 5 equal squares from each side: far left square aim at far right april tag
//move from spawn to set position
//when in squares area, turn on shooter, open gate
//shoot for 1-2 seconds and close gate
//memorize last seen april tag position
//if april tag isn't in that position anymore switchpipeline(1) and collect 4 pollen: counter to 4 and move to set position on other side of hive, counter = 4 switchpipeline(0)
//when in square area, start shooter, open gate
//start shoot, open gate for 1-2 seconds then stop and close
//switchpipeline(1), memorize pollen positions

@Autonomous (name = "solo blue auton", group = "Autonomous")
public class Blue1Tip extends OpMode {

    private Follower follower;
    private final PoseFactory p = PoseFactory.degrees();

    private final Pose startPose = p.of(85.8366, 133.2302, 270);
    private final Pose park = p.of(132.6902, 41.1631, 180);
    private final Pose controlPoint = p.of(123.3988, 74.6527, 0);

    private Path path1() {
        return Paths.curve(startPose, controlPoint, park).linear(startPose, park);
    }

    private Command autoRoutine() {
        return sequential(

                parallel(
                        instant(() -> shooter.startShoot())
                ),

                waitUntil(() -> shooter.currentVelo >= shooter.rpmThreshold),

                instant(() -> gate.GateOpen()),
                instant(() -> intake.StartIntake()),
                waitMs(1500),
                instant(() -> {
                    gate.GateClose();
                    shooter.stopShoot();
                    intake.StopIntake();
                }),


                follow(follower, path1())
        );
    }

    private Shooter shooter;
    private Intake intake;
    private FlowerRamp ramp;
    private TransferGate gate;

    @Override
    public void init() {
        Scheduler.reset();

        follower = Constants.create(hardwareMap);
        follower.setPose(startPose);
        follower.update();

        shooter = new Shooter(hardwareMap);
        intake = new Intake(hardwareMap);
        ramp = new FlowerRamp(hardwareMap);
        gate = new TransferGate(hardwareMap);

        gate.GateClose();
        ramp.OpenPos();

    }


    @Override
    public void start() {
        schedule(autoRoutine());
    }

    @Override
    public void loop() {
        follower.update();
        Scheduler.execute();
        shooter.update();

        telemetry.addData("x", follower.pose().x());
        telemetry.addData("y", follower.pose().y());
        telemetry.addData("heading", Math.toDegrees(follower.pose().heading()));
        telemetry.addData("follower mode", follower.mode());
        telemetry.addData("shooter velocity", shooter.currentVelo);

        telemetry.update();
    }

    @Override
    public void stop() {
        shooter.stopShoot();
        intake.StopIntake();
    }
}