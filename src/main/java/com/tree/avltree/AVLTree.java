package com.tree.avltree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * AVL Tree : balance Binary Search Tree
 * search, insert, remove : 모두 O(log N) 보장
 *
 * @author 송훤출
 * @since 20.04.27
 */
public class AVLTree<E> {
    private TreeNode<E> root;
    private final Comparator<? super E> comparator;
    private int size;

    public AVLTree() {
        this(null);
    }

    public AVLTree(Comparator<? super E> comparator) {
        this.comparator = comparator;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void insert(E data) {
        boolean hasUpdated = insertNode(data);
        if (hasUpdated) {
            reBalance(root);
        }
    }

    private boolean insertNode(E data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
        if (root == null) {
            root = new TreeNode<>(data);
            size++;
            return true;
        }
        TreeNode<E> current = root;
        while (true) {
            int comp = compare(data, current.data);
            if (comp < 0) {
                // move left
                if (current.left == null) {
                    current.left = new TreeNode<>(data, current);
                    break;
                }
                current = current.left;
            } else if (comp > 0) {
                // move right
                if (current.right == null) {
                    current.right = new TreeNode<>(data, current);
                    break;
                }
                current = current.right;
            } else {
                // already exist value.
                return false;
            }
        }
        size++;
        return true;
    }

    private void reBalance(TreeNode<E> node) {
        if (node == null) {
            return;
        }
        reBalance(node.left);
        reBalance(node.right);
        node.updateHeight();

        int balance_factor = node.getBalance();
        if (Math.abs(balance_factor) <= 1) {
            // balance factor 1 이하 = 균형 트리
            return;
        }
        rotate(node);
    }

    private void rotate(TreeNode<E> node) {
        int balance_factor = node.getBalance();
        if (balance_factor > 0) {
            // right rotate
            int left_balance_factor = node.left.getBalance();
            if (left_balance_factor <= 0) {
                // LR rotate
                rotateLeft(node.left);
            }
            rotateRight(node);
        } else {
            // left rotate
            int right_balance_factor = node.right.getBalance();
            if (right_balance_factor >= 0) {
                // RL rotate
                rotateRight(node.right);
            }
            rotateLeft(node);
        }
    }

    private void rotateLeft(TreeNode<E> p) {
        TreeNode<E> child = p.right;
        // update parent
        p.right = child.left;
        if (p.right != null) {
            p.right.parent = p;
        }
        // update child
        child.parent = p.parent;
        if (p.parent == null) {
            root = child;
        } else if (p.parent.left == p) {
            p.parent.left = child;
        } else {
            p.parent.right = child;
        }
        child.left = p;
        p.parent = child;

        p.updateHeight();
        child.updateHeight();
    }

    private void rotateRight(TreeNode<E> p) {
        TreeNode<E> child = p.left;
        p.left = child.right;
        if (p.left != null) {
            p.left.parent = p;
        }
        child.parent = p.parent;
        if (p.parent == null) {
            root = child;
        } else if (p.parent.left == p) {
            p.parent.left = child;
        } else {
            p.parent.right = child;
        }
        child.right = p;
        p.parent = child;

        p.updateHeight();
        child.updateHeight();
    }

    @SuppressWarnings("unchecked")
    private int compare(E data1, E data2) {
        return comparator == null
                ? ((Comparable<? super E>) data1).compareTo(data2)
                : comparator.compare(data1, data2);
    }

    public void remove(E data) {
        if (isEmpty()) {
            return;
        }

        removeNode(data);
        reBalance(root);
    }

    private void removeNode(E data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }

        TreeNode<E> current = root;
        while (true) {
            if (current == null) {
                throw new NoSuchElementException();
            }
            int comp = compare(data, current.data);
            if (comp < 0) {
                // move left
                current = current.left;
            } else if (comp > 0) {
                // move right
                current = current.right;
            } else {
                break;
            }
        }

        if (current.left != null && current.right != null) {
            TreeNode<E> successor = getSuccessor(current);
            current.data = successor.data;
            current = successor;
        }
        TreeNode<E> parent = current.parent;
        TreeNode<E> replacement = (current.left != null) ? current.left
                : current.right;
        if (replacement != null) {
            replacement.parent = current.parent;
        }
        // link replacement to parent
        if (parent == null) {
            root = replacement;
        } else if (parent.left == current) {
            parent.left = replacement;
        } else {
            parent.right = replacement;
        }
        // destroy current node
        current.parent = current.right = current.left = null;

        size--;
    }

    private TreeNode<E> getSuccessor(TreeNode<E> deleteNode) {
        TreeNode<E> successor = deleteNode.right;
        while (successor.left != null) {
            successor = successor.left;
        }
        return successor;
    }

    public Object[] toArrayInOrder() {
        List<Object> result = new ArrayList<>();
        inOrder(root, result);
        return result.toArray();
    }

    private void inOrder(TreeNode<E> root, List<Object> result) {
        // L - D - R
        if (root != null) {
            inOrder(root.left, result);
            result.add(root.data);
            inOrder(root.right, result);
        }
    }

    public Object[] toArrayPreOrder() {
        List<Object> result = new ArrayList<>();
        preOrder(root, result);
        return result.toArray();
    }

    private void preOrder(TreeNode<E> root, List<Object> result) {
        // D - L - R
        if (root != null) {
            result.add(root.data);
            preOrder(root.left, result);
            preOrder(root.right, result);
        }
    }

    public Object[] toArrayPostOrder() {
        List<Object> result = new ArrayList<>();
        postOrder(root, result);
        return result.toArray();
    }

    private void postOrder(TreeNode<E> root, List<Object> result) {
        // L - R - D
        if (root != null) {
            postOrder(root.left, result);
            postOrder(root.right, result);
            result.add(root.data);
        }
    }

    private static class TreeNode<E> {
        E data;
        TreeNode<E> parent;
        TreeNode<E> left;
        TreeNode<E> right;
        int height;

        TreeNode(E data) {
            this(data, null);
        }

        TreeNode(E data, TreeNode<E> parent) {
            this.data = data;
            this.parent = parent;
        }

        void updateHeight() {
            int leftHeight = heightOf(left);
            int rightHeight = heightOf(right);
            height = Math.max(leftHeight, rightHeight) + 1;
        }

        private int heightOf(TreeNode<E> right) {
            return (right == null)
                    ? 0
                    : right.height;
        }


        int getBalance() {
            int leftHeight = heightOf(left);
            int rightHeight = heightOf(right);
            return leftHeight - rightHeight;
        }
    }

}

