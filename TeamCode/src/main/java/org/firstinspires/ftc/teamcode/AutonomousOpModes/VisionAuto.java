package org.firstinspires.ftc.teamcode.AutonomousOpModes;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.SuperOp;
public class VisionAuto extends SuperOp {

    // for timing movement
    ElapsedTime motorTimer;
    // tells which stage of autonomous we are in
    AUTOSTATUS status;
    AUTOCONFIG config;
    // for timing how much time is left in autonomous period
    ElapsedTime gameTimer;
    @Override
    public void init() {
        super.init();
        // initialize timer
        motorTimer = new ElapsedTime();
        gameTimer = new ElapsedTime();
        // set status to start
        status = AUTOSTATUS.START;

    }

    @Override
    public void loop() {
        switch (status) {
            case START:
                gameTimer.reset();
                // determine which configuration it is

                // computer vision stuff

                config = AUTOCONFIG.ZERORING;
                break;

            case MOVEGOAL:

                // pick up goal


                // move goal

        }
    }
}
