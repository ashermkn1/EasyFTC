package com.lcrobotics.easyftclib;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Hardware;

import java.util.ArrayList;

public abstract class AdvancedOpMode extends OpMode {
    public ArrayList<DcMotor> DcMotors = new ArrayList<>(6);


}
