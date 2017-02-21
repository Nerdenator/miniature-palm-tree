package bstProblems_test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import binaryTree_src.BinaryTreeInsertRandomDirection;
import binaryTree_util.Node;
import bstProblems_src.GetTreeDiameter;

public class GetTreeDiameterTest {
	BinaryTreeInsertRandomDirection<Character> tree;
	BinaryTreeInsertRandomDirection<Integer> empty;
	BinaryTreeInsertRandomDirection<Integer> one;
	BinaryTreeInsertRandomDirection<Integer> two;

	@Before
	public void setUp() throws Exception {
		empty = new BinaryTreeInsertRandomDirection<>();
		one = new BinaryTreeInsertRandomDirection<>();
		one.insert(10);
		two = new BinaryTreeInsertRandomDirection<>();
		two.insert(10);
		two.insertLeft(two.root, 20);

		tree = new BinaryTreeInsertRandomDirection<>();

		Node<Character> A = new Node<Character>('A');
		Node<Character> B = new Node<Character>('B');
		Node<Character> C = new Node<Character>('C');
		Node<Character> D = new Node<Character>('D');
		Node<Character> E = new Node<Character>('E');
		Node<Character> F = new Node<Character>('F');
		Node<Character> G = new Node<Character>('G');
		Node<Character> H = new Node<Character>('H');
		Node<Character> I = new Node<Character>('I');
		Node<Character> J = new Node<Character>('J');
		Node<Character> K = new Node<Character>('K');
		Node<Character> L = new Node<Character>('L');
		Node<Character> M = new Node<Character>('M');
		Node<Character> N = new Node<Character>('N');
		Node<Character> Z = new Node<Character>('Z');

		tree.root = C;

		C.left = B;
		B.left = H;
		A.left = G;
		H.left = J;
		I.left = L;
		K.left = M;
		M.left = N;
		E.left = F;

		C.right = A;
		B.right = Z;
		H.right = I;
		Z.right = D;
		I.right = K;
		D.right = E;
	}

	@Test
	public void test() {
		Assert.assertEquals(10, GetTreeDiameter.getTreeDiameterInefficient(tree));
		Assert.assertEquals(10, GetTreeDiameter.getTreeDiameterOpt(tree));

		Assert.assertEquals(-1, GetTreeDiameter.getTreeDiameterInefficient(empty));
		Assert.assertEquals(0, GetTreeDiameter.getTreeDiameterInefficient(one));
		Assert.assertEquals(1, GetTreeDiameter.getTreeDiameterInefficient(two));

		Assert.assertEquals(-1, GetTreeDiameter.getTreeDiameterOpt(empty));
		Assert.assertEquals(0, GetTreeDiameter.getTreeDiameterOpt(one));
		Assert.assertEquals(1, GetTreeDiameter.getTreeDiameterOpt(two));
	}

}
