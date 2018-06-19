package ch.cpnv.angrywirds;

import com.badlogic.gdx.Game;

import ch.cpnv.angrywirds.Activities.Welcome;
import ch.cpnv.angrywirds.Controllers.GameActivityManager;

public class AngryWirds extends Game {


	public AngryWirds() {
	}

	@Override
	public void create() {
		GameActivityManager.push(new Welcome());

	}

	@Override
	public void render() {
		GameActivityManager.render();
	}
}
