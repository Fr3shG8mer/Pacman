package com.qualitype.pacman.io;

import org.junit.Assert;
import org.junit.Test;

import com.qualitype.pacman.Blinky;
import com.qualitype.pacman.Board;
import com.qualitype.pacman.Clyde;
import com.qualitype.pacman.GameObject;
import com.qualitype.pacman.Inky;
import com.qualitype.pacman.Pinky;

public class GhostsTest {
	@Test
	public void testSimple() throws Exception {
		final LevelDesign design = new LevelDesign();
		final Board board = design.readLevel(getClass().getResourceAsStream("ghosts.txt"));

		Assert.assertNotNull(board);
		Assert.assertEquals("Width is wrong!", 4, board.getWidth());
		Assert.assertEquals("Height is wrong!", 1, board.getHeight());

		assertTileHasClass(board.getGameObjectOn(0, 0), Blinky.class);
		assertTileHasClass(board.getGameObjectOn(1, 0), Clyde.class);
		assertTileHasClass(board.getGameObjectOn(2, 0), Inky.class);
		assertTileHasClass(board.getGameObjectOn(3, 0), Pinky.class);
	}

	private static void assertTileHasClass(GameObject gameObject, Class<? extends GameObject> gameObjectClass) {
		Assert.assertNotNull("Tile is null!", gameObject);
		Assert.assertTrue("Tile is wrong: " + gameObject, gameObjectClass.isAssignableFrom(gameObject.getClass()));
	}

}
