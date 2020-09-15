package org.firstinspires.ftc.teamcode.AutonomousOpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.teamcode.SuperOp;

import java.util.List;


@Autonomous
public class VisionAuto extends SuperOp {

    private static final String TFOD_MODEL_ASSET = "Ring.tflite";
    private static final String LABEL_FIRST_ELEMENT = "Ring";

    private static final int A = 0;
    private static final int B = 1;
    private static final int C = 4;


    private final static String VUFORIA_KEY = "ARgYuCf/////AAABmUYfc1+dVEQsgUBCPA2kCAFRmuTRB/XUfAJzLsRyFDRg6uMMjj6EXM8YNiY5l3oTw83H+PKgfF46gctdzrln2nnVXMebpgN9ULy1cOfdSsPk0hwSZqzcY0LWCj+rPPrZ3JyQT7gf2aw7bo8ZvWedWB7skuGIjg+9cyTJdDyXmXrQ8Bo4r4siTFNTVFxg21OH/Gd8wrVJF4RqjE+kcez3MzcnE2EPCqWTNixSge5yLg+tN87/R/dMPzqHWvmjE6F6J/7/sahPt7FQ9G6tYWnV1impzZsH7T/JT6pGr2SALwHdaNjBGbYY76ZfvAxixEdob9g6qMBhKOyLg6HTP9VzRZ06ksUhErmR2K2LSkyjxBBz";

    private VuforiaLocalizer vuforia;

    private TFObjectDetector tfod;

    private boolean seenRings = false;
    private int numRings;
    private AUTOSTATUS status = AUTOSTATUS.START;

    @Override
    public void init() {
        super.init();
        initVuforia();
        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod();
        } else {
            telemetry.addData("Sorry!", "This device is not compatible with TFOD");
        }
        telemetry.addData("Drive Train: ", wheels);
    }

    @Override
    public void loop() {

        switch (status) {
            // detect rings
            case START:
                List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                // make sure objects were detected
                if (updatedRecognitions != null) {
                    // get amount of rings
                    telemetry.addData("# Object Detected", updatedRecognitions.size());
                    numRings = updatedRecognitions.size();
                    status = AUTOSTATUS.MOVEGOAL;
                }
                break;
            // move goal to defined zone
            case MOVEGOAL:
                // drive to A

                if (numRings == A) {
                    // place wobble goal in zone
                    status = AUTOSTATUS.SHOOTRINGS;
                    break;
                }

                // drive to B

                if (numRings == B) {
                    // rotate robot 180 degrees and place wobble goal in zone
                    status = AUTOSTATUS.SHOOTRINGS;
                    // rotate back 180 degrees
                    break;
                }

                // drive to C

                // place wobble goal

                status = AUTOSTATUS.SHOOTRINGS;
                break;

            case SHOOTRINGS:
                // TBD
                status = AUTOSTATUS.PARK;
                break;

            case PARK:
                // drive so robot is over midline

        }
    }




    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the TensorFlow Object Detection engine.
    }

    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minimumConfidence = 0.8f;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_FIRST_ELEMENT);
    }


}
