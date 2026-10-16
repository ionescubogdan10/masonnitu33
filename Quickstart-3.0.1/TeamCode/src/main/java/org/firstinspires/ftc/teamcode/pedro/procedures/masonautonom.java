package org.firstinspires.ftc.teamcode.pedro.procedures;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;
import com.pedropathing.math.Pose;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.Path;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

import org.firstinspires.ftc.teamcode.pedro.Constants;

@Autonomous
public class masonautonom extends OpMode {
    private Follower follower;
    private final PoseFactory poseFactory = PoseFactory.degrees();

    // Poses (Pozitii)
    private final Pose startPose = poseFactory.of(24, 24, 0);
    private final Pose scorePose = poseFactory.of(48, 48, 90);
    private final Pose parkPose = poseFactory.of(72, 48, 90);

    // Metode pentru generat Path (Traiectorii)
    private Path startToScore() {
        Path path = new Path(new BezierLine(startPose, scorePose));
        path.setLinearHeadingInterpolation(startPose.heading(), scorePose.heading());
        return path;
    }

    private Path park() {
        Path path = new Path(new BezierLine(scorePose, parkPose));
        path.setLinearHeadingInterpolation(scorePose.heading(), parkPose.heading());
        return path;
    }

    private Command autoRoutine() {
        return sequential(
                follow(follower, startToScore()),
                // Adauga comenzi de mecanisme aici (ex: ridicat glider)
                follow(follower, park())
        );
    }

    @Override
    public void init() {
        Scheduler.reset();

        // Initializam Follower-ul cu Constants
        follower = new Follower(hardwareMap, Constants.foresightConfig, Constants.drivetrainConfig, Constants.localizerConfig);
        follower.setStartingPose(startPose);
        follower.update();
    }

    @Override
    public void start() {
        schedule(autoRoutine());
    }

    @Override
    public void loop() {
        follower.update();
        Scheduler.execute();

        // Telemetrie corectata (folosim telemetry nativ din OpMode)
        telemetry.addData("X", follower.getPose().getX());
        telemetry.addData("Y", follower.getPose().getY());
        telemetry.addData("Heading", Math.toDegrees(follower.getPose().getHeading()));
        telemetry.update();
    }
}