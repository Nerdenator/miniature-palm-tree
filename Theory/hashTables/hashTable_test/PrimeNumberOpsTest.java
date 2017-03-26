package hashTable_test;

import org.junit.Assert;
import org.junit.Test;

import hashTable_util.PrimeNumberOps;

public class PrimeNumberOpsTest {

	@Test
	public void testIsPrime() {
		Assert.assertTrue(PrimeNumberOps.isPrime(2));
		Assert.assertTrue(PrimeNumberOps.isPrime(3));
		Assert.assertTrue(PrimeNumberOps.isPrime(5));
		Assert.assertTrue(PrimeNumberOps.isPrime(7));
		Assert.assertTrue(PrimeNumberOps.isPrime(11));
		Assert.assertTrue(PrimeNumberOps.isPrime(13));
		Assert.assertTrue(PrimeNumberOps.isPrime(59));

		Assert.assertFalse(PrimeNumberOps.isPrime(1));
		Assert.assertFalse(PrimeNumberOps.isPrime(6));
		Assert.assertFalse(PrimeNumberOps.isPrime(21));
		Assert.assertFalse(PrimeNumberOps.isPrime(57));
		Assert.assertFalse(PrimeNumberOps.isPrime(63));
	}

	@Test
	public void testGetPreviousPrime() {
		Assert.assertEquals(2, PrimeNumberOps.getPreviousPrime(2));
		Assert.assertEquals(2, PrimeNumberOps.getPreviousPrime(3));
		Assert.assertEquals(3, PrimeNumberOps.getPreviousPrime(5));
		Assert.assertEquals(5, PrimeNumberOps.getPreviousPrime(7));
		Assert.assertEquals(7, PrimeNumberOps.getPreviousPrime(11));
		Assert.assertEquals(11, PrimeNumberOps.getPreviousPrime(13));
		Assert.assertEquals(53, PrimeNumberOps.getPreviousPrime(59));

		Assert.assertEquals(2, PrimeNumberOps.getPreviousPrime(1));
		Assert.assertEquals(5, PrimeNumberOps.getPreviousPrime(6));
		Assert.assertEquals(19, PrimeNumberOps.getPreviousPrime(21));
		Assert.assertEquals(53, PrimeNumberOps.getPreviousPrime(57));
		Assert.assertEquals(61, PrimeNumberOps.getPreviousPrime(63));
	}

	@Test
	public void testGetNextPrime() {
		Assert.assertEquals(3, PrimeNumberOps.getNextPrime(2));
		Assert.assertEquals(5, PrimeNumberOps.getNextPrime(3));
		Assert.assertEquals(7, PrimeNumberOps.getNextPrime(5));
		Assert.assertEquals(11, PrimeNumberOps.getNextPrime(7));
		Assert.assertEquals(13, PrimeNumberOps.getNextPrime(11));
		Assert.assertEquals(17, PrimeNumberOps.getNextPrime(13));
		Assert.assertEquals(61, PrimeNumberOps.getNextPrime(59));

		Assert.assertEquals(2, PrimeNumberOps.getNextPrime(1));
		Assert.assertEquals(7, PrimeNumberOps.getNextPrime(6));
		Assert.assertEquals(23, PrimeNumberOps.getNextPrime(21));
		Assert.assertEquals(59, PrimeNumberOps.getNextPrime(57));
		Assert.assertEquals(67, PrimeNumberOps.getNextPrime(63));
	}
}
