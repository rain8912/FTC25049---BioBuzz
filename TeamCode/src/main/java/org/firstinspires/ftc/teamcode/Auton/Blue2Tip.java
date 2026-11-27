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
public class Blue2Tip extends OpMode {

    private Follower follower;
    private final PoseFactory p = PoseFactory.degrees();

    private final Pose startPose = p.of(133.4536, 8.0742, 180);
    private final Pose scorePath1 = p.of(86.3825, 133.3785, 270);
    private final Pose controlScorePath1 = p.of(58.9837, 19.5198, 0);

    private final Pose startFlowerPath1 = p.of(86.3825, 133.3785, 270);
    private final Pose flowerPath1 = p.of(129.5477, 94.2481, 0);

    private final Pose startPath2 = p.of(129.5477, 94.2481, 0);
    private final Pose scorePath2 = p.of(83.0668, 10.7315, 90);
    private final Pose controlScorePath2 = p.of(118.8388, 55.8252, 0);

    private final Pose startRotate1 = p.of(83.0668, 10.7315, 90);
    private final Pose rotateFlower2 = p.of(94.9589, 26.5767, 90);
    private final Pose flowerPath2 = p.of(94.7173, 11.7995, 270);

    private final Pose startPath3 = p.of(94.7173, 11.7995, 270);
    private final Pose scorePath3 = p.of(110.09, 18.5876, 270);

    private final Pose rotateScore1 = p.of(110.09, 18.5876, 129);

    private final Pose startPark = p.of(110.09, 18.59, 129);
    private final Pose parkPose = p.of(133.3316, 32.8932, 180);

    public Path path1() {
        return Paths.curve(startPose, controlScorePath1, scorePath1).linear(startPose, scorePath1);
    }

    public Path flower1() {
        return Paths.line(startFlowerPath1, flowerPath1).linear(startFlowerPath1, flowerPath1);
    }

    public Path path2() {
        return Paths.curve(startPath2, controlScorePath2, scorePath2).linear(startPath2, scorePath2);
    }

    public Path rotate1() {
        return Paths.line(startRotate1, rotateFlower2).constant(rotateFlower2);
    }

    public Path flower2() {
        return Paths.line(rotateFlower2, flowerPath2).constant(flowerPath2);
    }

    public Path path3() {
        return Paths.line(startPath3, scorePath3).linear(startPath3, scorePath3);
    }

    public Path rotate2() {
        return Paths.line(scorePath3, rotateScore1).constant(rotateScore1);
    }

    public Path park() {
        return Paths.line(startPark, parkPose).linear(startPark, parkPose);
    }

    private Command autoRoutine() {
        return sequential(

                //to area 1 shooting
                parallel(
                        follow(follower, path1()),
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

                //to flower1 pickup
                parallel(
                        follow(follower, flower1()),
                        instant(() -> intake.StartIntake()),
                        instant(() -> ramp.DownPos())
                ),

                waitMs(2000),
                instant(() -> intake.StopIntake()),

                //to area 2 shooting
                parallel(
                        follow(follower, path2()),
                        instant(() -> ramp.OpenPos()),
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

                //on the way to pick up pollen
                follow(follower, rotate1()),
                follow(follower, flower2()),

                //to area 2 shoot #2
                follow(follower, path3()),

                //rotate to area 2 shoot
                parallel(
                        follow(follower, rotate2()),
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

                //go to park
                follow(follower, park())
//                follow(follower, path1()), DONE
//                follow(follower, flower1()), DONE
//                follow(follower, path2()), DONE
//                follow(follower, rotate1()), DONE
//                follow(follower, flower2()), DONE
//                follow(follower, path3()), DONE
//                follow(follower, rotate2()), DONE
//                follow(follower, park()) DONE
        );
    }

    private Shooter shooter;
    private Intake intake;
    private FlowerRamp ramp;
    private TransferGate gate;

//    private Shooter shooter;
//    private Intake intake;
//
//    private FlowerRamp ramp;
//    private TransferGate gate;

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