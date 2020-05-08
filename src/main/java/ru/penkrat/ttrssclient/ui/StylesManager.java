package ru.penkrat.ttrssclient.ui;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.fxmisc.easybind.EasyBind;
import org.fxmisc.easybind.Subscription;
import org.springframework.stereotype.Component;

import javafx.scene.Scene;
import ru.penkrat.ttrssclient.ui.settings.SettingsService;

@Component
public class StylesManager {

	private SettingsService settings;

	private String commonCss = getClass().getResource("/styles/styles.css").toExternalForm();
	private String darkCss = getClass().getResource("/styles/dark.css").toExternalForm();
	private String scale150Css = getClass().getResource("/styles/150.css").toExternalForm();

	private List<Scene> scenes = new ArrayList<>();

	Subscription darkModeSub, scaledSub;

	@Inject
	public StylesManager(SettingsService settings) {
		this.settings = settings;

		darkModeSub = EasyBind.subscribe(settings.darkModeProperty(), isDark -> {
			for (Scene scene : scenes) {
				if (isDark) {
					scene.getStylesheets().add(darkCss);
				} else {
					scene.getStylesheets().remove(darkCss);
				}
			}
		});
		scaledSub = EasyBind.subscribe(settings.scaledProperty(), isScaled -> {
			for (Scene scene : scenes) {
				if (isScaled) {
					scene.getStylesheets().add(scale150Css);
				} else {
					scene.getStylesheets().remove(scale150Css);
				}
			}
		});
	}

	public void registerScene(Scene scene) {
		scenes.add(scene);

		scene.getStylesheets().add(commonCss);
		if (settings.darkModeProperty().getValue()) {
			scene.getStylesheets().add(darkCss);
		}
		if (settings.scaledProperty().getValue()) {
			scene.getStylesheets().add(scale150Css);
		}
	}

}
