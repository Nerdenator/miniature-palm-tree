package c05_HashTables;

import org.junit.Assert;
import org.junit.Test;

import c05_HashTables.HashTableUtil;

public class HashTableUtilTest {

	@Test
	public void testIsPrime() {
		Assert.assertTrue(HashTableUtil.isPrime(2));
		Assert.assertTrue(HashTableUtil.isPrime(3));
		Assert.assertTrue(HashTableUtil.isPrime(5));
		Assert.assertTrue(HashTableUtil.isPrime(7));
		Assert.assertTrue(HashTableUtil.isPrime(11));
		Assert.assertTrue(HashTableUtil.isPrime(13));
		Assert.assertTrue(HashTableUtil.isPrime(59));

		Assert.assertFalse(HashTableUtil.isPrime(1));
		Assert.assertFalse(HashTableUtil.isPrime(6));
		Assert.assertFalse(HashTableUtil.isPrime(21));
		Assert.assertFalse(HashTableUtil.isPrime(57));
		Assert.assertFalse(HashTableUtil.isPrime(63));
	}

	@Test
	public void testGetPreviousPrime() {
		Assert.assertEquals(2, HashTableUtil.getPreviousPrime(2));
		Assert.assertEquals(2, HashTableUtil.getPreviousPrime(3));
		Assert.assertEquals(3, HashTableUtil.getPreviousPrime(5));
		Assert.assertEquals(5, HashTableUtil.getPreviousPrime(7));
		Assert.assertEquals(7, HashTableUtil.getPreviousPrime(11));
		Assert.assertEquals(11, HashTableUtil.getPreviousPrime(13));
		Assert.assertEquals(53, HashTableUtil.getPreviousPrime(59));

		Assert.assertEquals(2, HashTableUtil.getPreviousPrime(1));
		Assert.assertEquals(5, HashTableUtil.getPreviousPrime(6));
		Assert.assertEquals(19, HashTableUtil.getPreviousPrime(21));
		Assert.assertEquals(53, HashTableUtil.getPreviousPrime(57));
		Assert.assertEquals(61, HashTableUtil.getPreviousPrime(63));
	}

	@Test
	public void testGetNextPrime() {
		Assert.assertEquals(3, HashTableUtil.getNextPrime(2));
		Assert.assertEquals(5, HashTableUtil.getNextPrime(3));
		Assert.assertEquals(7, HashTableUtil.getNextPrime(5));
		Assert.assertEquals(11, HashTableUtil.getNextPrime(7));
		Assert.assertEquals(13, HashTableUtil.getNextPrime(11));
		Assert.assertEquals(17, HashTableUtil.getNextPrime(13));
		Assert.assertEquals(61, HashTableUtil.getNextPrime(59));

		Assert.assertEquals(2, HashTableUtil.getNextPrime(1));
		Assert.assertEquals(7, HashTableUtil.getNextPrime(6));
		Assert.assertEquals(23, HashTableUtil.getNextPrime(21));
		Assert.assertEquals(59, HashTableUtil.getNextPrime(57));
		Assert.assertEquals(67, HashTableUtil.getNextPrime(63));
	}
}
