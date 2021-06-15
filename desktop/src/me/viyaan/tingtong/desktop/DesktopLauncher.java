package me.viyaan.tingtong.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import me.viyaan.tingtong.TingTong;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();//very tired to make this so I just sticked with the default config
		new LwjglApplication(new TingTong(), config);//yeah I was very tired and lazy
	}
}
