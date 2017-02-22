# miniature-palm-tree
Implementation of data structures and algorithms

# Algorithms

## Searching 

| Binary Search |
| ------------- |
| - O(log n)    |
| - [Notes](https://goo.gl/fhaqYe) |
| - [Code](https://github.com/adinutzyc21/miniature-palm-tree/blob/master/Algorithms/searching/binarySearch/BinarySearch.java) |

## Sorting

| Bubble Sort | Insertion Sort | Selection Sort | Merge Sort   |
| ----------- | -------------- | -------------- | ------------ |
| - O(n^2)    | - O(n^2)       | - O(n^2)       | - O(n log n) |
| - [Notes](https://goo.gl/Enr70l) | - [Notes](https://goo.gl/4fCZo3) | - [Notes](https://goo.gl/88VuSz) | - [Notes](https://goo.gl/3wvsqe) |
| - [Code](https://github.com/adinutzyc21/miniature-palm-tree/blob/master/Algorithms/sorting/bubbleSort/BubbleSort.java)  | - [Code](https://github.com/adinutzyc21/miniature-palm-tree/blob/master/Algorithms/sorting/insertionSort/InsertionSort.java)|    - [Code](https://github.com/adinutzyc21/miniature-palm-tree/blob/master/Algorithms/sorting/selectionSort/SelectionSort.java) | - [Code](https://github.com/adinutzyc21/miniature-palm-tree/blob/master/Algorithms/sorting/mergeSort/MergeSort.java) |

# Data Structures

| Arrays | Dynamic Arrays |
| ------ | -------------- |
|  | - [Code](https://github.com/adinutzyc21/miniature-palm-tree/blob/master/DataStructures/dynamicArrays/dynamicArrays/DynamicArray.java) |
| - [Unsorted](https://goo.gl/Ao7izn), [Sorted](https://goo.gl/R5vnUJ) | - [Theory](https://goo.gl/uVgZ0m) |
| - [Java API](https://goo.gl/gt35qv) | - [Java API](https://goo.gl/yWLsPT) |

## Stacks and Queues

| [Stacks](https://goo.gl/FvxQpz) | [Queues](https://goo.gl/ui1H6w) |
| ------ | -------------- |
| [Stack Interface](https://github.com/adinutzyc21/miniature-palm-tree/blob/master/DataStructures/stacks/stacks_src/InterfaceStack.java)   | [Queue Interface](https://github.com/adinutzyc21/miniature-palm-tree/blob/master/DataStructures/queues/queues_src/InterfaceQueue.java)|
| [Stack with Array](https://github.com/adinutzyc21/miniature-palm-tree/blob/master/DataStructures/stacks/stacks_src/StackArray.java)   | [Queue with Array](https://github.com/adinutzyc21/miniature-palm-tree/blob/master/DataStructures/queues/queues_src/QueueArray.java)|
| [Stack with Linked List](https://github.com/adinutzyc21/miniature-palm-tree/blob/master/DataStructures/stacks/stacks_src/StackList.java)   | [Queue with Linked List](https://github.com/adinutzyc21/miniature-palm-tree/blob/master/DataStructures/queues/queues_src/QueueList.java)|


## Binary Search Trees (BST)

### BST Utils

| [Binary Trees Interface](https://github.com/adinutzyc21/miniature-palm-tree/blob/master/DataStructures/binaryTrees/binaryTree_util/InterfaceBinaryTree.java) | Tree [Node](https://github.com/adinutzyc21/miniature-palm-tree/blob/master/DataStructures/binaryTrees/binaryTree_util/Node.java) datastructure |
| --- | --- |

### Binary Trees

| [Notes](https://goo.gl/JD4IFW) | [Random Insert Direction](https://github.com/adinutzyc21/miniature-palm-tree/blob/master/DataStructures/binaryTrees/binaryTree_src/BinaryTreeInsertRandomDirection.java) |
| --------- | --------------------------- |

### Binary Tree Traversals

| [Notes](https://goo.gl/cgOg2M) | [Binary Tree Traversals](https://github.com/adinutzyc21/miniature-palm-tree/blob/master/DataStructures/binaryTrees/bst_src/BinaryTreeTraversals.java) (pre-order, in-order, post-order, level-order) |
| --------- | --------------------------- |

### Binary Search Trees 

| [Notes](https://goo.gl/4E91pF) | [BST Implementation](https://github.com/adinutzyc21/miniature-palm-tree/blob/master/DataStructures/binaryTrees/bst_src/BinarySearchTree.java) |
| --------- | --------------------------- |


## Graphs

### Graph Utils

| [Unweighted Graphs Interface](https://github.com/adinutzyc21/miniature-palm-tree/blob/master/DataStructures/graphs/graph_util/InterfaceUnweightedGraph.java) | Graph [Vertex](https://github.com/adinutzyc21/miniature-palm-tree/blob/master/DataStructures/graphs/graph_util/Vertex.java) datastructure |
| --- | --- |
| **[Weighted Graphs Interface](https://github.com/adinutzyc21/miniature-palm-tree/blob/master/DataStructures/graphs/graph_util/InterfaceWeightedGraph.java)** | **Adjacency List [Item](https://github.com/adinutzyc21/miniature-palm-tree/blob/master/DataStructures/graphs/graph_util/Item.java) datastructure** |

### Graph Representations

| 1. Using Adjacency Matrices ([Notes]()) | 2. Using Adjacency Lists ([Notes]()) | 
| --------------------------------------- | ------------------------------------ | 
| - [Undirected Unweighted Graph](https://github.com/adinutzyc21/miniature-palm-tree/blob/master/DataStructures/graphs/graph_matrix_src/UndirectedUnweightedGraphM.java) | - [Undirected Unweighted Graph](https://github.com/adinutzyc21/miniature-palm-tree/blob/master/DataStructures/graphs/graph_list_src/UndirectedUnweightedGraphL.java) | 
| - [Undirected Weighted Graph](https://github.com/adinutzyc21/miniature-palm-tree/blob/master/DataStructures/graphs/graph_matrix_src/UndirectedWeightedGraphM.java) | - [Undirected Weighted Graph](https://github.com/adinutzyc21/miniature-palm-tree/blob/master/DataStructures/graphs/graph_list_src/UndirectedWeightedGraphL.java) | 
| - [Directed Unweighted Graph](https://github.com/adinutzyc21/miniature-palm-tree/blob/master/DataStructures/graphs/graph_matrix_src/DirectedUnweightedGraphM.java) | - [Directed Unweighted Graph](https://github.com/adinutzyc21/miniature-palm-tree/blob/master/DataStructures/graphs/graph_list_src/DirectedUnweightedGraphL.java) | 
| - [Directed Weigthed Graph](https://github.com/adinutzyc21/miniature-palm-tree/blob/master/DataStructures/graphs/graph_matrix_src/DirectedWeightedGraphM.java) |  - [Directed Weigthed Graph](https://github.com/adinutzyc21/miniature-palm-tree/blob/master/DataStructures/graphs/graph_list_src/DirectedWeightedGraphL.java) | 
  
### Graph Traversals

| [Breadth-First Search](https://goo.gl/znyy7N) | [Depth-First Search](https://goo.gl/Qp1p8q) | [Code](https://github.com/adinutzyc21/miniature-palm-tree/blob/master/DataStructures/graphs/graphs_algorithms_src/GraphTraversals.java) |
|------------------- | -------------------------------- | -------------------------------- | 

### Minimum Spanning Trees
