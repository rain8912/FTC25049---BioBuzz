package org.firstinspires.ftc.teamcode.Testing.Subsystems;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.List;

public class LLTrackingTesting {
    private Limelight3A limeLight;
    private OdometryTesting odometry;

    private int blueID[][] = {
            {42, 43, 43, 44, 45},
            {42, 43, 43, 44, 45},
            {0, 0, 0, 0, 0},
            {41, 40, 40, 39, 38},
            {41, 40, 40, 39, 38}
    };

    private int redID[][] = {
            {30, 31, 32, 32, 33},
            {30, 31, 32, 32, 33},
            {0, 0, 0, 0, 0},
            {34, 35, 36, 36, 37},
            {34, 35, 36, 36, 37}
    };

    private double minX = 0;
    private double maxX = 0;
    private double minY = 0;
    private double maxY = 0;
    private int column;
    private int row;
    private double targetID;
    private double tx;

    public LLTrackingTesting(HardwareMap hardwareMap) {
        limeLight = hardwareMap.get(Limelight3A.class, "limelight");
        odometry = new OdometryTesting(hardwareMap);

        limeLight.setPollRateHz(100);
        limeLight.pipelineSwitch(0);
        limeLight.start();
    }

    private void Update() {
        odometry.Update();

        double x = odometry.getX();
        double y = odometry.getY();
    }

    private void zoneFinder(double x, double y) {

        column = getColumn(x);
        row = getRow(y);
    }

    public int getColumn(double x) {

        double columnWidth = (maxX - minX) / 5;
        int column = (int) ((x - minX) / columnWidth);

        return Math.min(column, 4);
    }

    public int getRow(double y) {

        double rowLength = (maxY - minY) / 5;
        int row = (int) ((y - minY) / rowLength);

        return Math.min(row, 4);
    }

    public void FindTags(int[][] allianceTagID) {

        if (row < 0 || row > 4 || row == 2) {
            return;
        }

        targetID = -1;
        tx = 0;

        int ID = allianceTagID[row][column];

        if (ID == 0) {
            return;
        }

        LLResult llResult = limeLight.getLatestResult();

        if (llResult != null && llResult.isValid()) {
            List<LLResultTypes.FiducialResult> detection = llResult.getFiducialResults();

            for (LLResultTypes.FiducialResult tag : detection) {
                if (tag.getFiducialId() == ID) {
                    targetID = tag.getFiducialId();
                    tx = tag.getTargetXDegrees();
                    return;
                }
            }
        }
    }

    public void BlueHives() {
        FindTags(blueID);
    }

    public void RedHives() {
        FindTags(redID);
    }
}
