/*******************************************************************************
 * Copyright 2015 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package de.tomgrill.gdxtesting.examples;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.jose.skyfall.Logic.Hero;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.Gdx;

import de.tomgrill.gdxtesting.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class TestHero {

	@Test
	public void testHeroPosition() {
		Hero h = new Hero(100,200);
		assertEquals(h.getPosition().x, -8, 0.1);
		assertEquals(h.getPosition().y, 50, 0.1);
	}

	@Test
	public void testHeroUpdate(){
		Hero h = new Hero(1000, 2000);
		h.move(10,1);
		h.update(1);
		assertEquals(h.getPosition().x, 432, 0.1);
		assertEquals(h.getPosition().y, 1550, 0.1);
	}

	@Test
	public void testHeroBounds(){
		Hero h = new Hero(100, 200);
		assertEquals(h.getBounds().x, -8, 0.1);
		assertEquals(h.getBounds().y, 50, 0.1);

	}

	@Test
	public void testHeroUpdateVelocity(){
		Hero h = new Hero(1000, 2000);
		h.updateVolocity();
		h.move(0,1);
		h.update(1);
		assertEquals(h.getPosition().x, 442, 0.1);
		assertEquals(h.getPosition().y, 1549.6, 0.1);
	}

	@Test
	public void testHeroSuperPower(){
		Hero h = new Hero(1000, 2000);
		assertEquals(false, h.getSuperPower());
		h.setSuperPower(true);
		assertEquals(true, h.getSuperPower());
	}
}
