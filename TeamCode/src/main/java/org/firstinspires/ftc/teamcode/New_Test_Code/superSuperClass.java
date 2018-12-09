package org.firstinspires.ftc.teamcode.New_Test_Code;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Keith Harder on 7/23/2018.
 */

public abstract class superSuperClass extends LinearOpMode {

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
