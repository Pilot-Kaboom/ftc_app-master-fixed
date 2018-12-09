package org.firstinspires.ftc.teamcode.RR2_Comp_Code;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

public abstract class SuperSuperClass extends LinearOpMode {

    public ElapsedTime time = new ElapsedTime();
    public ElapsedTime atime = new ElapsedTime();
    @Override
    public void runOpMode() {
        initiate();
        waitForStart();
        time.reset();
        run();
    }
    public abstract void initiate();

    public abstract void run();
}