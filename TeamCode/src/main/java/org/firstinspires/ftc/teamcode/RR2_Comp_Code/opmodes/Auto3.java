package org.firstinspires.ftc.teamcode.RR2_Comp_Code.opmodes;

import org.firstinspires.ftc.teamcode.RR2_Comp_Code.superclasses.RR2_AutoBot;

public class Auto3 extends RR2_AutoBot {

    @Override
    public void run() {
        vision.disable();// disables tracking algorithms. this will free up your phone's processing power for other jobs.

        goldPosition = vision.getTfLite().getLastKnownSampleOrder();


    }
}
