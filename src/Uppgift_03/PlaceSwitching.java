package Uppgift_03;

import javax.swing.*;
import java.util.Collections;
import java.util.List;

public class PlaceSwitching {

    public void switchPlaces(JLabel labelBackgroundImage, List<JLabel> labelList, List<JLabel> correctList, int indexBlank, int indexSiffra, JLabel winnerMess){
        for (JLabel jl : labelList) {
            if (jl.getText().equals(" ")) {
                indexBlank = labelList.indexOf(jl);
            }
        }

        if (indexBlank % 4 == indexSiffra % 4 && Math.abs((indexBlank / 4) - (indexSiffra / 4)) == 1
                || Math.abs(indexBlank - indexSiffra) == 1 && !((indexSiffra % 4 == 0 && indexBlank % 4 == 3) ||
                (indexSiffra % 4 == 3 && indexBlank % 4 == 0))) {
            labelBackgroundImage.removeAll();
            Collections.swap(labelList, indexSiffra, indexBlank);
            for (JLabel jLabel : labelList) {
                labelBackgroundImage.add(jLabel);
            }
            if (labelList.equals(correctList))
                winnerMess.setText("Grattis du vann!");
        }
    }
}
