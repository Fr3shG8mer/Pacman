package com.qualitype.pacman.io;

import org.junit.Assert;
import org.junit.Test;

import com.qualitype.pacman.BluePortal;
import com.qualitype.pacman.Board;
import com.qualitype.pacman.GameObject;
import com.qualitype.pacman.OrangePortal;

public class PortalsTest {

	@Test
	public void testPortal1() throws Exception {
		testPortalFile("portals1.txt");
	}

	@Test
	public void testPortal2() throws Exception {
		testPortalFile("portals2.txt");
	}

	@Test
	public void testPortal3() throws Exception {
		testPortalFile("portals3.txt");
	}

	@Test
	public void testPortal4() throws Exception {
		testPortalFile("portals4.txt");
	}

	@Test
	public void testPortal5() throws Exception {
		testPortalFile("portals5.txt");
	}
	@Test
	public void testPortal6() throws Exception {
		testPortalFile("portals6.txt");
	}
	@Test
	public void testPortal7() throws Exception {
		testPortalFile("portals7.txt");
	}
	@Test
	public void testPortal8() throws Exception {
		testPortalFile("portals8.txt");
	}
	@Test
	public void testPortal9() throws Exception {
		testPortalFile("portals9.txt");
	}
	@Test
	public void testPortal0() throws Exception {
		testPortalFile("portals0.txt");
	}

	private void testPortalFile(String fileName) throws Exception {
		final LevelDesign design = new LevelDesign();
		final Board board = design.readLevel(getClass().getResourceAsStream(fileName));

		Assert.assertNotNull(board);
		Assert.assertEquals("Width is wrong!", 3, board.getWidth());
		Assert.assertEquals("Height is wrong!", 1, board.getHeight());

		final GameObject gameObject1 = board.getGameObjectOn(2, 0);
		Assert.assertTrue("Tile is wrong: " + gameObject1, gameObject1 instanceof BluePortal);
		final BluePortal bluePortal = (BluePortal) gameObject1;

		Assert.assertNull(board.getGameObjectOn(1, 0));

		final GameObject gameObject2 = board.getGameObjectOn(0, 0);
		Assert.assertTrue("Tile is wrong: " + gameObject2, gameObject2 instanceof OrangePortal);
		final OrangePortal orangePortal = (OrangePortal) gameObject2;

		// test that portals are linked

		Assert.assertSame(bluePortal, orangePortal.getExit());
		Assert.assertSame(orangePortal, bluePortal.getEntrance());
	}

	@Test
	public void testBroken1() throws Exception {
		testBrokenFile("portals-missing1.txt", "1");
	}

	@Test
	public void testBroken2() throws Exception {
		testBrokenFile("portals-missing2.txt", "2");
	}

	private void testBrokenFile(String fileName, String number) throws Exception {
		final LevelDesign design = new LevelDesign();
		try {
			design.readLevel(getClass().getResourceAsStream(fileName));
			Assert.fail("There should have been an exception!");
		} catch (final IllegalArgumentException e) {
			Assert.assertEquals("Portal for character " + number + " is missing!", e.getMessage());
		}
	}

	@Test
	public void testMultiplePortals() throws Exception {
		final LevelDesign design = new LevelDesign();
		final Board board = design.readLevel("123321");

		Assert.assertNotNull(board);
		Assert.assertEquals("Width is wrong!", 6, board.getWidth());
		Assert.assertEquals("Height is wrong!", 1, board.getHeight());

		final GameObject gameObject0 = board.getGameObjectOn(0, 0);
		Assert.assertTrue("Tile is wrong: " + gameObject0, gameObject0 instanceof OrangePortal);
		final OrangePortal orangePortal0 = (OrangePortal) gameObject0;

		final GameObject gameObject1 = board.getGameObjectOn(1, 0);
		Assert.assertTrue("Tile is wrong: " + gameObject1, gameObject1 instanceof OrangePortal);
		final OrangePortal orangePortal1 = (OrangePortal) gameObject1;

		final GameObject gameObject2 = board.getGameObjectOn(2, 0);
		Assert.assertTrue("Tile is wrong: " + gameObject2, gameObject2 instanceof OrangePortal);
		final OrangePortal orangePortal2 = (OrangePortal) gameObject2;

		final GameObject gameObject3 = board.getGameObjectOn(3, 0);
		Assert.assertTrue("Tile is wrong: " + gameObject3, gameObject3 instanceof BluePortal);
		final BluePortal bluePortal3 = (BluePortal) gameObject3;

		final GameObject gameObject4 = board.getGameObjectOn(4, 0);
		Assert.assertTrue("Tile is wrong: " + gameObject4, gameObject4 instanceof BluePortal);
		final BluePortal bluePortal4 = (BluePortal) gameObject4;

		final GameObject gameObject5 = board.getGameObjectOn(5, 0);
		Assert.assertTrue("Tile is wrong: " + gameObject3, gameObject3 instanceof BluePortal);
		final BluePortal bluePortal5 = (BluePortal) gameObject5;

		// test that portals are linked

		Assert.assertSame(bluePortal5, orangePortal0.getExit());
		Assert.assertSame(orangePortal0, bluePortal5.getEntrance());

		Assert.assertSame(bluePortal4, orangePortal1.getExit());
		Assert.assertSame(orangePortal1, bluePortal4.getEntrance());

		Assert.assertSame(bluePortal3, orangePortal2.getExit());
		Assert.assertSame(orangePortal2, bluePortal3.getEntrance());
	}
}
