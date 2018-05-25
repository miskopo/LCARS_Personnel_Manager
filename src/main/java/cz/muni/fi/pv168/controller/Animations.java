package cz.muni.fi.pv168.controller;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class Animations {

    public static void AnimateText(Label label, String labelContent) {
        final Animation animation = new Transition() {
            {
                setCycleDuration(Duration.millis(4000));
            }

            protected void interpolate(double frac) {
                final int length = labelContent.length();
                final int n = Math.round(length * (float) frac);
                label.setText(labelContent.substring(0, n));
            }
        };
        animation.play();

    }
}
