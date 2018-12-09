package org.firstinspires.ftc.teamcode.RR2_Comp_Code;
import org.firstinspires.ftc.teamcode.New_Test_Code.mecanumBotHard;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Keith Harder on 9/3/2018.
**/

public class LightsEnum {

    private final Servo lights;

    public enum Color{

        PALE_WHITE(.77),
        BRIGHT_WHITE(.76),
        PURPLE_WHITE(.75),
        BLUE_DARK(.74),
        BLUE_MID(.73),
        BLUE_PALE(.72),
        GREEN(.71),
        GREEN_YELLOW(.70),
        YELLOW(.69),
        ORANGE(.68),
        RED(.67),
        PURPLE(.66),
        TC_SLOW_REVOLVING(.65);


        private final double col;
        Color(double col){
            this.col = col;
        }
    }

    public void setColor(Color color){
        lights.setPosition(color.col);
    }

    public LightsEnum(HardwareMap hMap) {
        lights = hMap.servo.get("lights");
    }
}
