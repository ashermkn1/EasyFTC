package org.firstinspires.ftc.teamcode;

import com.lcrobotics.easyftclib.CommandCenter.driveTrain.DriveTrain;
import com.lcrobotics.easyftclib.CommandCenter.driveTrain.WheelType;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Autonomous(name="Test4WheelAuto", group="Test")
public class Test4WheelAuto extends Test4Wheel {


    public boolean first = true;
    public ElapsedTime timer;

    @Override
    public void init() {
        super.init();
        timer = new ElapsedTime();
    }

    @Override
    public void loop() {
        if (first) {
            timer.reset();
            first = !first;
        }
        if (timer.seconds() < 5) {
            spin();
        } else {
            stopMotors();
        }

    }

    private void stopMotors() {
        wheels.setPower(0, 0, 0);
    }

    public void spin() {
        wheels.setPower(0, 0, 0.5);
    }
}
