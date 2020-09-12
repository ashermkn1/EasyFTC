package org.firstinspires.ftc.teamcode.AutonomousOpModes;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.SuperOp;

public class ZeroRingAuto extends SuperOp {

    AUTOSTATUS status = AUTOSTATUS.START;
    ElapsedTime timer;

    @Override
    public void init() {
        super.init();
        timer = new ElapsedTime();
    }

    @Override
    public void loop() {

        switch (status) {
            case START:

        }
    }
}
