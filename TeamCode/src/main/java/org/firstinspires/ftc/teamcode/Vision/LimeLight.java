package org.firstinspires.ftc.teamcode.Vision;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.List;

public class LimeLight {
    public Limelight3A limelight;
    public double tx;
    public double ty;
    public double ta;

    public LimeLight(HardwareMap hwMap) {

        limelight = hwMap.get(Limelight3A.class, "limelight");

        limelight.setPollRateHz(100);
        limelight.pipelineSwitch(0);
        limelight.start();
    }


    public double aprilx() {
        LLResult llResult = limelight.getLatestResult();

        if (llResult != null && llResult.isValid()) {
            List<LLResultTypes.FiducialResult> detection = llResult.getFiducialResults();
            for (LLResultTypes.FiducialResult april : detection) {
                if (april.getFiducialId() == 39) {
                    tx = april.getTargetXDegrees();
                    break;
                }
            }
        }
        return tx;
    }

    public double aprily() {
        LLResult llResult = limelight.getLatestResult();

        if (llResult != null && llResult.isValid()) {
            List<LLResultTypes.FiducialResult> detection = llResult.getFiducialResults();
            for (LLResultTypes.FiducialResult april : detection) {
                if (april.getFiducialId() == 39) {
                    ty = april.getTargetYDegrees();
                    break;
                }
            }
        }
        return ty;
    }

    public double aprila() {
        LLResult llResult = limelight.getLatestResult();

        if (llResult != null && llResult.isValid()) {
            List<LLResultTypes.FiducialResult> detection = llResult.getFiducialResults();
            for (LLResultTypes.FiducialResult april : detection) {
                if (april.getFiducialId() == 39) {
                    ta = april.getTargetArea();
                    break;
                }
            }
        }
        return ta;
    }

    public int getTagCount() {
        LLResult llResult = limelight.getLatestResult();

        if (llResult != null && llResult.isValid()) {
            return llResult.getFiducialResults().size();
        }

        return 0;
    }
}
