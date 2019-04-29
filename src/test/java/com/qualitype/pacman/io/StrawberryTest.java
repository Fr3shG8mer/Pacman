package com.qualitype.pacman.io;

import org.junit.Assert;
import org.junit.Test;

import com.qualitype.pacman.Board;
import com.qualitype.pacman.GameObject;
import com.qualitype.pacman.Strawberry;

public class StrawberryTest {
	@Test
	public void testSimple() throws Exception {
		final LevelDesign design = new LevelDesign();
		final Board board = design.readLevel(getClass().getResourceAsStream("fruits.txt"));

		board.tick(60 * 60 * 1000); // let one hour pass

		Assert.assertNotNull(board);
		Assert.assertEquals("Width is wrong!", 2, board.getWidth());
		Assert.assertEquals("Height is wrong!", 1, board.getHeight());

		assertTileHasClass(board.getGameObjectOn(0, 0), Strawberry.class);
	}

	private static void assertTileHasClass(GameObject gameObject, Class<? extends GameObject> gameObjectClass) {
		Assert.assertNotNull("Tile is null!", gameObject);
		Assert.assertTrue("Tile is wrong: " + gameObject, gameObjectClass.isAssignableFrom(gameObject.getClass()));
	}
}
