package de.tomgrill.gdxtesting.examples;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import com.jose.skyfall.Logic.Diamond;

import org.junit.Test;
import org.junit.runner.RunWith;

import de.tomgrill.gdxtesting.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class TestDiamond {

    @Test
    public void testDaimondRandomization(){
        Diamond d1 = new Diamond(100);
        Diamond d2 = new Diamond(100);

        assertNotEquals(d1.getPosition().x, d2.getPosition().x);
    }

    @Test
    public void testDiamondPosition(){
        Diamond d = new Diamond(100);
        assertEquals(d.getPosition().y, 100, 0.1);
    }

    @Test
    public void testDiamondReposition(){
        Diamond d = new Diamond(100);
        float x = d.getPosition().x;
        d.reposition(200);
        assertEquals(d.getPosition().y, 200, 0.1);
        assertNotEquals(x, d.getPosition().x);
    }

    @Test
    public void testDiamondUpdatePosition(){
        Diamond d = new Diamond(1000);
        d.setMovX(true);
        float x = d.getPosition().x + 100;
        d.updatePosition(1);
        assertEquals(d.getPosition().x, x, 0.1);
        d.setCatched(true);
        assertEquals(true, d.wasCatched());
    }
}
