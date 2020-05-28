package com.tree.redblack;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * red-black-tree (self-balanced binary tree)
 *
 * @author 송훤출
 * @since 20.05.25
 */
public class RedBlackTree<E> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private final Comparator<? super E> comparator;
    private Node<E> root;
    private int size;

    public RedBlackTree() {
        this(null);
    }

    public RedBlackTree(Comparator<? super E> comparator) {
        this.comparator = comparator;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void insert(E data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }

        Node<E> newNode = new Node<>(data);
        Node<E> current = root;
        while (current != null) {
            int comp = compare(data, current.item);
            if (comp < 0) {
                // move left
                if (current.left == null) {
                    current.left = newNode;
                    newNode.parent = current;
                    break;
                }
                current = current.left;
            } else if (comp > 0) {
                // move right
                if (current.right == null) {
                    current.right = newNode;
                    newNode.parent = current;
                    break;
                }
                current = current.right;
            } else {
                // already exist value
                return;
            }
        }
        if (root == null) {
            root = newNode;
        }
        size++;
        fixAfterInsertion(newNode);
    }

    private void fixAfterInsertion(Node<E> node) {
        while (node.parent != null && Node.colorOf(node.parent) == RED) {
            // No.3 property violation (No Double Red)
            if (node.parent == node.parent.parent.left) {
                Node<E> uncle = node.parent.parent.right;
                // Recoloring (insertion case 1)
                if (Node.colorOf(uncle) == RED) {
                    node.parent.color = BLACK;
                    uncle.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                } else {
                    // Restructuring
                    if (node == node.parent.right) {
                        // (insertion case 2) : double rotate
                        node = node.parent;
                        rotateLeft(node);
                    }
                    // (insertion case 3) : single rotate
                    node.parent.color = BLACK;
                    node.parent.parent.color = RED;
                    rotateRight(node.parent.parent);
                }
            } else {
                Node<E> uncle = node.parent.parent.left;
                if (Node.colorOf(uncle) == RED) {
                    // Recoloring (case 1)
                    node.parent.color = BLACK;
                    uncle.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent; // 할아버지 노드를 기준으로 다시 해결
                } else {
                    // Restructuring
                    if (node.parent.left == node) {
                        // (case 2)
                        node = node.parent;
                        rotateRight(node);
                    }
                    // (case 3)
                    node.parent.color = BLACK;
                    node.parent.parent.color = RED;
                    rotateLeft(node.parent.parent);
                }
            }
        }
        // No.1 property (root's color is black)
        root.color = BLACK;
    }

    private void rotateLeft(Node<E> p) {
        Node<E> child = p.right;
        // update parent link
        p.right = child.left;
        if (p.right != null) {
            p.right.parent = p;
        }
        // update child link
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
    }

    private void rotateRight(Node<E> p) {
        Node<E> child = p.left;
        // update parent link
        p.left = child.right;
        if (p.left != null) {
            p.left.parent = p;
        }
        // update child link
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
    }

    @SuppressWarnings("unchecked")
    private int compare(E data1, E data2) {
        return comparator == null
                ? ((Comparable<? super E>) data1).compareTo(data2)
                : comparator.compare(data1, data2);
    }

    public void remove(E deleteData) {
        Node<E> node = searchNode(deleteData);
        if (node == null) {
            throw new NoSuchElementException();
        }
        deleteNode(node);
    }

    private Node<E> searchNode(E deleteData) {
        if (deleteData == null) {
            throw new IllegalArgumentException();
        }

        Node<E> current = root;
        while (current != null) {
            int comp = compare(deleteData, current.item);
            if (comp < 0) {
                current = current.left;
            } else if (comp > 0) {
                current = current.right;
            } else {
                return current;
            }
        }
        return null;
    }

    private void deleteNode(Node<E> node) {
        size--;

        // if node's child is two, copy successor's element to node
        // and point to successor
        if (node.left != null && node.right != null) {
            Node<E> successor = getSuccessor(node);
            node.item = successor.item;
            node = successor;
        }
        Node<E> parent = node.parent;
        Node<E> replacement = node.left != null ? node.left
                : node.right;
        if (replacement != null) {
            // link replacement to parent, if replacement exists
            replacement.parent = node.parent;
            // link parent to replacement
            if (parent == null) {
                root = replacement;
            } else if (parent.left == node) {
                parent.left = replacement;
            } else {
                parent.right = replacement;
            }
            // destroy node
            node.parent = node.left = node.right = null;
            replacement.color = BLACK;
        } else if (node.parent == null) {
            // when tree's size is 1
            root = null;
        } else {
            // when no children
            if (node.color == BLACK) {
                fixAfterDeletion(node);
            }

            if (node.parent.left == node) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
            node.parent = node.left = node.right = null;
        }
    }

    private void fixAfterDeletion(Node<E> node) {
        while (node != root && Node.colorOf(node) == BLACK) {
            if (node.parent.left == node) {
                Node<E> sibling = node.parent.right;
                if (Node.colorOf(sibling) == RED) {
                    // deletion case 1
                    node.parent.color = RED;
                    sibling.color = BLACK;
                    rotateLeft(node.parent);
                    sibling = node.parent.right;
                }

                if (Node.colorOf(sibling.left) == BLACK
                        && Node.colorOf(sibling.right) == BLACK) {
                    // deletion case 2
                    sibling.color = RED;
                    node = node.parent;
                } else {
                    if (Node.colorOf(sibling.right) == BLACK) {
                        // deletion case 3
                        sibling.left.color = BLACK;
                        sibling.color = RED;
                        rotateRight(sibling);
                        sibling = node.parent.right;
                    }
                    // deletion case 4
                    node.parent.color = BLACK;
                    sibling.right.color = BLACK;
                    sibling.color = RED;
                    rotateLeft(node.parent);
                    node = root;
                }
            } else {
                // symmetric
                Node<E> sibling = node.parent.left;
                if (Node.colorOf(sibling) == RED) {
                    // deletion case 1
                    node.parent.color = RED;
                    sibling.color = BLACK;
                    rotateRight(node.parent);
                    sibling = node.parent.left;
                }

                if (Node.colorOf(sibling.left) == BLACK
                        && Node.colorOf(sibling.right) == BLACK) {
                    // deletion case 2
                    sibling.color = RED;
                    node = node.parent;
                } else {
                    if (Node.colorOf(sibling.left) == BLACK) {
                        // deletion case 3
                        sibling.right.color = BLACK;
                        sibling.color = RED;
                        rotateLeft(sibling);
                        sibling = node.parent.left;
                    }
                    // deletion case 4
                    node.parent.color = BLACK;
                    sibling.left.color = BLACK;
                    sibling.color = RED;
                    rotateRight(node.parent);
                    node = root;
                }
            }
        }
        node.color = BLACK;
    }

    private Node<E> getSuccessor(Node<E> node) {
        Node<E> successor = node.right;
        while (successor.left != null) {
            successor = successor.left;
        }
        return successor;
    }

    public Object[] toArrayInOrder() {
        List<Object> resultList = new ArrayList<>();
        Node<E> current = root;
        inOrder(resultList, current);
        return resultList.toArray();
    }

    private void inOrder(List<Object> list, Node<E> node) {
        if (node != null) {
            inOrder(list, node.left);
            list.add(node.item);
            inOrder(list, node.right);
        }
    }

    public Object[] toArrayPreOrder() {
        List<Object> resultList = new ArrayList<>();
        Node<E> current = root;
        preOrder(resultList, current);
        return resultList.toArray();
    }

    private void preOrder(List<Object> list, Node<E> node) {
        if (node != null) {
            list.add(node.item);
            preOrder(list, node.left);
            preOrder(list, node.right);
        }
    }

    public Object[] toArrayPostOrder() {
        List<Object> resultList = new ArrayList<>();
        Node<E> current = root;
        postOrder(resultList, current);
        return resultList.toArray();
    }

    private void postOrder(List<Object> list, Node<E> node) {
        if (node != null) {
            postOrder(list, node.left);
            postOrder(list, node.right);
            list.add(node.item);
        }
    }

    private static class Node<E> {
        E item;
        Node<E> parent;
        Node<E> left;
        Node<E> right;
        boolean color = RED;

        Node(E item) {
            this(item, null);
        }

        Node(E item, Node<E> parent) {
            this.item = item;
            this.parent = parent;
        }

        public static <E> boolean colorOf(Node<E> node) {
            return node == null ? BLACK : node.color;
        }
    }
}
