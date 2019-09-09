package cn.liuxin.flowerFiled.Controller;
/*
Copyright 2019 Liuxin

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

import cn.liuxin.flowerFiled.Util.Util;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Paths;

public class Music {
    private static Music sound;
    private boolean openSound;
    private MediaPlayer player;
    private JFXPanel jxJfxPanel;
    private boolean allowPlay;
    public Music() {
        allowPlay=true;
        jxJfxPanel=new JFXPanel();
        player=new MediaPlayer(new Media(Paths.get("music.mp3").toUri().toString()));
        sound=this;
    }

    public Music(boolean openSound) {
        this.openSound = openSound;
    }

    public static Music getMusic() {
        if(sound==null)
            sound=new Music();
        return sound;
    }

    public JFXPanel getJxJfxPanel() {
        return jxJfxPanel;
    }

    public void setJxJfxPanel(JFXPanel jxJfxPanel) {
        this.jxJfxPanel = jxJfxPanel;
    }

    public boolean isOpenSound() {
        return openSound;
    }

    public void setOpenSound(boolean openSound) {
        this.openSound = openSound;
    }

    public void musicPlay()
    {
        if(Util.getUtil().isAllowMusic())
        {
            player.setCycleCount(MediaPlayer.INDEFINITE);
            player.play();
        }
    }

    public void stopPlay()
    {
        player.stop();
    }

    public void musicPlayLose()
    {
        if(Util.getUtil().isAllowSound())
        {
            player=new MediaPlayer(new Media(Paths.get("lose.mp3").toUri().toString()));
            player.play();
        }
    }
}
