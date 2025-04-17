package view;
import uk.co.caprica.vlcj.*;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

import javax.swing.*;
import java.awt.*;

public class VideoBackgroundPanel extends JPanel {

    private final EmbeddedMediaPlayerComponent mediaPlayerComponent;

    public VideoBackgroundPanel(String videoFilePath) {
        setLayout(new BorderLayout());
        mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        add(mediaPlayerComponent, BorderLayout.CENTER);

        // Make sure the video fills the panel
        mediaPlayerComponent.mediaPlayer().media().play(videoFilePath);

        // Set video surface to match panel size
        mediaPlayerComponent.mediaPlayer().videoSurface();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        mediaPlayerComponent.mediaPlayer().videoSurface();
    }
}
