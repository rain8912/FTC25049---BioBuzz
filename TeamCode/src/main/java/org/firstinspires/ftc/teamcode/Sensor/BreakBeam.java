//package org.firstinspires.ftc.teamcode.Sensor;
//
//import com.qualcomm.robotcore.hardware.DigitalChannel;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//
//public class BreakBeam {
//
//    private DigitalChannel beam1, beam2, beam3, beam4;
//
//    public BreakBeam(HardwareMap hardwaremap) {
//
//        beam1 = hardwaremap.get(DigitalChannel.class, "b1");
//        beam1.setMode(DigitalChannel.Mode.INPUT);
//
//        beam2 = hardwaremap.get(DigitalChannel.class, "b2");
//        beam2.setMode(DigitalChannel.Mode.INPUT);
//
//        beam3 = hardwaremap.get(DigitalChannel.class, "b3");
//        beam3.setMode(DigitalChannel.Mode.INPUT);
//
//        beam4 = hardwaremap.get(DigitalChannel.class, "b4");
//        beam4.setMode(DigitalChannel.Mode.INPUT);
//    }
//
//    public boolean isBeam1Broken() {
//        return beam1.getState();
//    }
//
//    public boolean isBeam2Broken() {
//        return beam2.getState();
//    }
//
//    public boolean isBeam3Broken() {
//        return beam3.getState();
//    }
//
//    public boolean isBeam4Broken() {
//        return beam4.getState();
//    }
//}
