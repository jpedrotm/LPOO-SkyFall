package de.tomgrill.gdxtesting.examples;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import com.jose.skyfall.Logic.HighScores;

import org.junit.Test;
import org.junit.runner.RunWith;

import de.tomgrill.gdxtesting.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class TestHighScore {

    @Test
    public void testScores(){
        HighScores hg1 = new HighScores(4);
        hg1.loadHighscore();
        int score1 = hg1.getScore();
        hg1.update(10);
        hg1.saveScore();
        assertNotEquals(hg1.getScore(), score1);

        HighScores hg2 = new HighScores(1);
        hg2.loadHighscore();
        int score2 = hg2.getScore();
        hg2.update(10);
        hg2.saveScore();
        assertNotEquals(hg2.getScore(), score2);
    }
}
