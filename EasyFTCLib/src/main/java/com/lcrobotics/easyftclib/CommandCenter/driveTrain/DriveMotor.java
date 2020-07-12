package com.lcrobotics.easyftclib.CommandCenter.driveTrain;

import com.qualcomm.robotcore.hardware.DcMotor;

public class DriveMotor {
    DcMotor motor;
    WheelPosition position;

    public DriveMotor(DcMotor motor, WheelPosition motorPosition){
        this.motor = motor;
        this.position = motorPosition;
    }

    public DcMotor getMotor() {
        return motor;
    }

    public void setMotor(DcMotor motor) {
        this.motor = motor;
    }

    public void setPosition(WheelPosition position) {
        this.position = position;
    }

    public WheelPosition getPosition() {
        return position;
    }
}
