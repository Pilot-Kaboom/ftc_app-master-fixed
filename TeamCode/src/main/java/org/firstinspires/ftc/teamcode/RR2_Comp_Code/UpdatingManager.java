package org.firstinspires.ftc.teamcode.RR2_Comp_Code;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RR2_Comp_Code.Updatable;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by David on 1/24/2018.
 */

public class UpdatingManager extends Thread {

    public volatile Set<Updatable> updatables = new LinkedHashSet<>();
    public volatile LinearOpMode linearOpMode;
    public volatile boolean killed;

    private ElapsedTime runtime = new ElapsedTime();

    public void addUpdatable(Updatable updatable) {
        updatables.add(updatable);
    }

    public void clearUpdatables(){
        updatables.clear();
    }

    @Override
    public void run() {
        killed = false;
        runtime.reset();
        while (!(linearOpMode.isStopRequested() || killed) && !interrupted()) {
            for (Updatable updatable : updatables) {
                if(linearOpMode.isStopRequested() || killed)
                    break;
                if(updatable != null)
                    updatable.update();
            }
            /*linearOpMode.telemetry.addData("Runtime", runtime.seconds());
            linearOpMode.telemetry.update();*/
        }
    }

    public void kill() {
        killed = true;
    }

    public UpdatingManager(LinearOpMode linearOpMode) {
        this.linearOpMode = linearOpMode;
    }
}
