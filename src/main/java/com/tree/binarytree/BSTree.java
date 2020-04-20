package com.tree.binarytree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Binary Search Tree : time-complexity (log N)
 *
 * @author Hwun Chul
 * @since April 16, 2020
 */
public class BSTree<E> {
    private final Comparator<? super E> comparator;
    private TreeNode<E> root;
    private int treeSize;

    public BSTree() {
        this(null);
    }

    public BSTree(Comparator<? super E> comparator) {
        this.comparator = comparator;
    }

    public void insert(E data) {
        if (data == null) {
            throw new NullPointerException();
        }
        if (root == null) {
            root = new TreeNode<>(data);
            treeSize++;
            return;
        }

        explicitInsert(data);
    }

    public boolean isEmpty() {
        return treeSize == 0;
    }

    private void explicitInsert(E data) {
        TreeNode<E> current = root;

        while (current != null) {
            int comp = compare(data, current.data);
            if (comp < 0) {
                // to left child
                if (current.left == null) {
                    current.left = new TreeNode<>(data);
                    break;
                }
                current = current.left;
            } else if (comp > 0) {
                // to right child
                if (current.right == null) {
                    current.right = new TreeNode<>(data);
                    break;
                }
                current = current.right;
            } else {
                // value already exits.
                return;
            }
        }
        treeSize++;
    }

    @SuppressWarnings("unchecked")
    private int compare(E data1, E data2) {
        return (comparator == null)
                ? ((Comparable<? super E>) data1).compareTo(data2)
                : comparator.compare(data1, data2);
    }

    public Object search(E data) {
        TreeNode<E> current = root;

        while (current != null) {
            if (current.data.equals(data)) {
                return current.data;
            }
            if (compare(data, current.data) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return null;
    }

    public void remove(E data) {
        TreeNode<E> current = root;
        TreeNode<E> parent = current;
        boolean isLeftChild = false;

        while (true) {
            if (current == null) {
                throw new IllegalArgumentException();
            }
            int comp = compare(data, current.data);

            if (comp < 0) {
                // to left child
                parent = current;
                current = current.left;
                isLeftChild = true;
            } else if (comp > 0) {
                // to right child
                parent = current;
                current = current.right;
                isLeftChild = false;
            } else {
                // get target
                break;
            }
        }

        if ((current.left == null) && (current.right == null)) {
            // if deleted Node is leaf node..
            if (current == root) {
                root = null;
            } else if (isLeftChild) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else {
            // if one child node
            if (current.left == null) {
                if (current == root) {
                    root = current.right;
                } else if (isLeftChild) {
                    parent.left = current.right;
                } else {
                    parent.right = current.right;
                }
            } else if (current.right == null) {
                if (current == root) {
                    root = current.left;
                } else if (isLeftChild) {
                    parent.left = current.left;
                } else {
                    parent.right = current.left;
                }
            } else {
                // if two child node.
                TreeNode<E> successor = getSuccessor(current);

                if (current == root) {
                    root = successor;
                } else if (isLeftChild) {
                    parent.left = successor;
                } else {
                    parent.right = successor;
                }
            }
        }
        treeSize--;
    }

    private TreeNode<E> getSuccessor(TreeNode<E> deleteNode) {
        TreeNode<E> successor = deleteNode.right;
        TreeNode<E> successorParent = successor;

        while (successor.left != null) {
            successorParent = successor;
            successor = successor.left;
        }

        if (successor != deleteNode.right) {
            successorParent.left = successor.right;
            successor.right = deleteNode.right;
        }
        successor.left = deleteNode.left;

        return successor;
    }

    public Object[] toArray() {
        List<Object> result = new ArrayList<>();
        inOrder(root, result);
        return result.toArray();
    }

    private void inOrder(TreeNode<E> node, List<Object> list) {
        // in-order : L - D - R
        if (node != null) {
            inOrder(node.left, list);
            list.add(node.data);
            inOrder(node.right, list);
        }
    }

    public Object[] toArrayByReverse() {
        List<Object> result = new ArrayList<>();
        reverse(root, result);
        return result.toArray();
    }

    private void reverse(TreeNode<E> node, List<Object> list) {
        if (node != null) {
            reverse(node.right, list);
            list.add(node.data);
            reverse(node.left, list);
        }
    }

    public int size() {
        return treeSize;
    }

    /**
     * linked node
     */
    static class TreeNode<E> {
        TreeNode<E> left;
        TreeNode<E> right;
        E data;

        TreeNode(E data) {
            this(null, null, data);
        }

        TreeNode(TreeNode<E> left, TreeNode<E> right, E data) {
            this.left = left;
            this.right = right;
            this.data = data;
        }
    }
}
