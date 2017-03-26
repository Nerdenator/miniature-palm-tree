package bstProblems_test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import binaryTree_src.BinaryTreeInsertRandomDirection;
import bstProblems_src.IsTreeSymmetric;

public class IsTreeSymmetricTest {

	BinaryTreeInsertRandomDirection<Integer> empty;
	BinaryTreeInsertRandomDirection<Integer> sym;
	BinaryTreeInsertRandomDirection<Integer> notSym;

	@Before
	public void setUp() throws Exception {
		empty = new BinaryTreeInsertRandomDirection<>();

		sym = new BinaryTreeInsertRandomDirection<>();
		sym.insert(1);
		sym.insertLeft(sym.root, 2);
		sym.insertRight(sym.root, 2);
		sym.insertLeft(sym.root.left, 3);
		sym.insertRight(sym.root.left, 4);
		sym.insertLeft(sym.root.right, 4);
		sym.insertRight(sym.root.right, 3);

		notSym = new BinaryTreeInsertRandomDirection<>();
		notSym.insert(1);
		notSym.insertLeft(notSym.root, 2);
		notSym.insertRight(notSym.root, 2);
		notSym.insertRight(notSym.root.left, 3);
		notSym.insertRight(notSym.root.right, 3);
	}

	@Test
	public void testIsSymmetric() {
		Assert.assertTrue(IsTreeSymmetric.isTreeSymmetric(empty));
		Assert.assertTrue(IsTreeSymmetric.isTreeSymmetric(sym));
		Assert.assertFalse(IsTreeSymmetric.isTreeSymmetric(notSym));
	}

}
