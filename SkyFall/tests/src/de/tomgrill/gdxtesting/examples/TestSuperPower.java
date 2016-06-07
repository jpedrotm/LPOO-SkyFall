package de.tomgrill.gdxtesting.examples;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import com.jose.skyfall.Logic.SuperPower;

import org.junit.Test;
import org.junit.runner.RunWith;

import de.tomgrill.gdxtesting.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class TestSuperPower {

    @Test
    public void testSuperPowerPosition(){
        SuperPower sp1 = new SuperPower(1000);
        SuperPower sp2 = new SuperPower(1000);
        assertEquals(sp1.getPosition().y, 1000, 0.1);
        assertEquals(sp1.getPosition().y, sp2.getPosition().y, 0.1);
        assertNotEquals(sp1.getPosition().x, sp2.getPosition().y);
    }

    @Test
    public void testSuperPowerReposition(){
        SuperPower sp = new SuperPower(100);
        float x = sp.getPosition().x;
        sp.reposition(200);
        assertEquals(sp.getPosition().y, 200, 0.1);
        assertNotEquals(x, sp.getPosition().x);
    }

    @Test
    public void testOstacleUpdatePosition(){
        SuperPower sp = new SuperPower(1000);
        sp.setCatched(true);
        assertEquals(true, sp.getCathed());
    }
}
