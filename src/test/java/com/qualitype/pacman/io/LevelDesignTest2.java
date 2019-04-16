package com.qualitype.pacman.io;

import org.junit.Assert;
import org.junit.Test;

import com.qualitype.pacman.Board;
import com.qualitype.pacman.LittlePill;
import com.qualitype.pacman.Obstacle;
import com.qualitype.pacman.Pacman;

public class LevelDesignTest2 {
	@Test
	public void testSimple() throws Exception {
		final LevelDesign design = new LevelDesign();
		final Board board = design.readLevel(getClass().getResourceAsStream("test2-level.txt"));

		Assert.assertNotNull(board);
		Assert.assertEquals("Width is wrong!", 5, board.getWidth());
		Assert.assertEquals("Height is wrong!", 4, board.getHeight());

		assertTileHasClass(board.getGameObjectOn(0, 0), Obstacle.class);
		assertTileHasClass(board.getGameObjectOn(1, 0), Obstacle.class);
		assertTileHasClass(board.getGameObjectOn(2, 0), Obstacle.class);
		assertTileHasClass(board.getGameObjectOn(3, 0), Obstacle.class);
		assertTileHasClass(board.getGameObjectOn(4, 0), Obstacle.class);

		assertTileHasClass(board.getGameObjectOn(0, 1), Obstacle.class);
		assertTileHasClass(board.getGameObjectOn(1, 1), LittlePill.class);
		assertTileHasClass(board.getGameObjectOn(2, 1), LittlePill.class);
		assertTileHasClass(board.getGameObjectOn(3, 1), LittlePill.class);
		assertTileHasClass(board.getGameObjectOn(4, 1), Obstacle.class);

		assertTileHasClass(board.getGameObjectOn(0, 2), Obstacle.class);
		assertTileHasClass(board.getGameObjectOn(1, 2), LittlePill.class);
		assertTileHasClass(board.getGameObjectOrPacmanOn(2, 2), Pacman.class);
		assertTileHasClass(board.getGameObjectOn(3, 2), LittlePill.class);
		assertTileHasClass(board.getGameObjectOn(4, 2), Obstacle.class);

		assertTileHasClass(board.getGameObjectOn(0, 3), Obstacle.class);
		assertTileHasClass(board.getGameObjectOn(1, 3), Obstacle.class);
		assertTileHasClass(board.getGameObjectOn(2, 3), Obstacle.class);
		assertTileHasClass(board.getGameObjectOn(3, 3), Obstacle.class);
		assertTileHasClass(board.getGameObjectOn(4, 3), Obstacle.class);
	}

	private static void assertTileHasClass(Object gameObject, Class<?> gameObjectClass) {
		Assert.assertNotNull("Tile is null! ", gameObject);
		Assert.assertTrue("Tile is wrong: " + gameObject, gameObjectClass.isAssignableFrom(gameObject.getClass()));
	}

}
