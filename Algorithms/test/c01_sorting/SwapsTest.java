package c01_sorting;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import c01_sorting.Swaps;

public class SwapsTest {
	int[] A;
	int[] Aswapped;
	int posi,posj;
	
	@Before
	public void setUp(){
		A = new int[] {1,2,3,4,5};
		posi = 1;
		posj = 3;
		Aswapped = new int[] {1,4,3,2,5};
	}
	
	@Test
	public void testSwapXOR() {
		Swaps.swapXOR(A, posi, posi);
		Assert.assertArrayEquals(A, A);
		
		Swaps.swapXOR(A, posi, posj);
		Assert.assertArrayEquals(Aswapped, A);
	}

	@Test
	public void testSwapTemp() {
		Swaps.swapTemp(A, posi, posi);
		Assert.assertArrayEquals(A, A);
		
		Swaps.swapTemp(A, posi, posj);
		Assert.assertArrayEquals(Aswapped, A);
	}
	
	@Test
	public void testSwapMath() {
		Swaps.swapMath(A, posi, posi);
		Assert.assertArrayEquals(A, A);
		
		Swaps.swapMath(A, posi, posj);
		Assert.assertArrayEquals(Aswapped, A);
	}
}
