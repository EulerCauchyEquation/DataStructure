package com.tree.avltree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
        if (data == null) {
            throw new NullPointerException();
        }
        if (root == null) {
            root = new TreeNode<>(data);
            size++;
            return;
        }

        boolean result = explicitInsert(data);

        if (result) {
            root = reBalance(root);
        }
    }

    private boolean explicitInsert(E data) {
        TreeNode<E> current = root;

        while (current != null) {
            int comp = compare(data, current.data);

            if (comp < 0) {
                // to left
                if (current.left == null) {
                    current.left = new TreeNode<>(data);
                    break;
                }
                current = current.left;
            } else if (comp > 0) {
                // to right
                if (current.right == null) {
                    current.right = new TreeNode<>(data);
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

    private TreeNode<E> reBalance(TreeNode<E> node) {
        if (node == null) {
            return null;
        }

        node.left = reBalance(node.left);
        node.right = reBalance(node.right);
        node.updateHeight();
        node.updateBalance();

        int balance_factor = node.balance;

        if (Math.abs(balance_factor) <= 1) {
            // balance factor 1 이하 = 균형 트리
            return node;
        }
        node = rotate(node);
        return node;
    }

    private TreeNode<E> rotate(TreeNode<E> node) {
        int balance_factor = node.balance;

        if (balance_factor > 0) {
            // 양수면 일단 Right Rotate or LR Rotate
            int left_balance_factor = node.left.balance;

            if (left_balance_factor <= 0) {
                node.left = rotateL(node.left);
            }
            return rotateR(node);
        } else {
            int right_balance_factor = node.right.balance;

            if (right_balance_factor >= 0) {
                node.right = rotateR(node.right);
            }
            return rotateL(node);
        }
    }

    private TreeNode<E> rotateL(TreeNode<E> parent) {
        TreeNode<E> child = parent.right;

        parent.right = child.left;
        child.left = parent;

        parent.updateHeight();
        parent.updateBalance();
        child.updateHeight();
        child.updateBalance();

        return child;
    }

    private TreeNode<E> rotateR(TreeNode<E> parent) {
        TreeNode<E> child = parent.left;

        parent.left = child.right;
        child.right = parent;

        parent.updateHeight();
        parent.updateBalance();
        child.updateHeight();
        child.updateBalance();

        return child;
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

        explicitRemove(data);
        root = reBalance(root);
    }

    private void explicitRemove(E data) {
        TreeNode<E> current = root;
        TreeNode<E> parent = null;
        boolean isLeftChild = false;

        while (true) {
            if (current == null) {
                throw new IllegalArgumentException();
            }

            int comp = compare(data, current.data);

            if (comp < 0) {
                // to left
                parent = current;
                isLeftChild = true;
                current = current.left;
            } else if (comp > 0) {
                // to right
                parent = current;
                isLeftChild = false;
                current = current.right;
            } else {
                break;
            }
        }

        TreeNode<E> successor;
        if (current.left == null) {
            successor = current.right;
        } else if (current.right == null) {
            successor = current.left;
        } else {
            successor = getSuccessor(current);
        }

        if (parent == null) {
            root = successor;
        } else if (isLeftChild) {
            parent.left = successor;
        } else {
            parent.right = successor;
        }
        size--;
    }

    private TreeNode<E> getSuccessor(TreeNode<E> delete) {
        TreeNode<E> successor = delete.right;
        TreeNode<E> successorParent = successor;

        while (successor.left != null) {
            successorParent = successor;
            successor = successor.left;
        }

        if (successor != delete.right) {
            successorParent.left = successor.right;
            successor.right = successorParent;
        }
        successor.left = delete.left;

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

    static class TreeNode<E> {
        private E data;
        private TreeNode<E> left;
        private TreeNode<E> right;
        private int height;
        private int balance;

        TreeNode(E data) {
            this(data, null, null);
        }

        TreeNode(E data, TreeNode<E> left, TreeNode<E> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public void updateHeight() {
            int leftHeight = heightOf(left);
            int rightHeight = heightOf(right);
            height = Math.max(leftHeight, rightHeight) + 1;
        }

        private int heightOf(TreeNode<E> right) {
            return (right == null)
                    ? -1
                    : right.height;
        }


        public void updateBalance() {
            int leftHeight = heightOf(left);
            int rightHeight = heightOf(right);
            balance = leftHeight - rightHeight;
        }
    }

}

