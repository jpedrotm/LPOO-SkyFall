package de.tomgrill.gdxtesting.examples;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import com.jose.skyfall.Logic.Obstacle;

import org.junit.Test;
import org.junit.runner.RunWith;

import de.tomgrill.gdxtesting.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class TestObstacle {

    @Test
    public void testObstacleRandomization(){
        Obstacle obs1 = new Obstacle(100);
        Obstacle obs2 = new Obstacle(100);

        assertNotEquals(obs1.getPosition().x, obs2.getPosition().x);
    }

    @Test
    public void testObstaclePosition(){
        Obstacle obs = new Obstacle(100);
        assertEquals(obs.getPosition().y, 100, 0.1);
    }

    @Test
    public void testObstacleReposition(){
        Obstacle obs = new Obstacle(100);
        float x = obs.getPosition().x;
        obs.reposition(200);
        assertEquals(obs.getPosition().y, 200, 0.1);
        assertNotEquals(x, obs.getPosition().x);
    }

    @Test
    public void testOstacleUpdatePosition(){
        Obstacle o = new Obstacle(1000);
        o.setMovX(true);
        float x = o.getPosition().x + 100;
        o.updatePosition(1);
        assertEquals(o.getPosition().x, x, 0.1);
        o.destroy();
        o.update(1);
        assertEquals(true, o.isDestroied());
    }

}
