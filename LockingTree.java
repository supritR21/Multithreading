
/*
String[] names = {
    "A", "B", "C", "D", "E", "F", "G"
};
int[] parent = {
    -1, 0, 0, 1, 1, 2, 2
};
                 A
              /     \
             B       C
           /  \     / \
          D    E   F   G
 */

import java.util.*;

class Node {

    String name;
    Node parent;
    List<Node> children;

    boolean locked;
    int lockedBy;

    int lockedDescendants;

    Node(String name) {
        this.name = name;
        this.children = new ArrayList<>();
        this.locked = false;
        this.lockedBy = -1;
        this.lockedDescendants = 0;
    }
}

public class LockingTree {

    private final Map<String, Node> nodes = new HashMap<>();

    public LockingTree(String[] names, int[] parent) {

        // Create nodes
        for (String name : names) {
            nodes.put(name, new Node(name));
        }

        // Build tree
        for (int i = 1; i < names.length; i++) {

            Node child = nodes.get(names[i]);
            Node par = nodes.get(names[parent[i]]);

            child.parent = par;
            par.children.add(child);
        }
    }

    private boolean hasLockedAncestor(Node node) {

        Node current = node.parent;

        while (current != null) {

            if (current.locked) {
                return true;
            }

            current = current.parent;
        }

        return false;
    }

    private void updateAncestors(Node node, int delta) {

        Node current = node.parent;

        while (current != null) {

            current.lockedDescendants += delta;
            current = current.parent;
        }
    }

    public boolean lock(String name, int user) {

        Node node = nodes.get(name);

        if (node.locked) {
            return false;
        }

        if (node.lockedDescendants > 0) {
            return false;
        }

        if (hasLockedAncestor(node)) {
            return false;
        }

        node.locked = true;
        node.lockedBy = user;

        updateAncestors(node, 1);

        return true;
    }

    public boolean unlock(String name, int user) {

        Node node = nodes.get(name);

        if (!node.locked) {
            return false;
        }

        if (node.lockedBy != user) {
            return false;
        }

        node.locked = false;
        node.lockedBy = -1;

        updateAncestors(node, -1);

        return true;
    }

    private void collectLockedDescendants(
            Node node,
            List<Node> result) {

        for (Node child : node.children) {

            if (child.locked) {
                result.add(child);
            }

            collectLockedDescendants(child, result);
        }
    }

    public boolean upgrade(String name, int user) {

        Node node = nodes.get(name);

        if (node.locked) {
            return false;
        }

        if (hasLockedAncestor(node)) {
            return false;
        }

        if (node.lockedDescendants == 0) {
            return false;
        }

        List<Node> lockedNodes = new ArrayList<>();

        collectLockedDescendants(node, lockedNodes);

        // Every locked descendant must belong to same user
        for (Node lockedNode : lockedNodes) {

            if (lockedNode.lockedBy != user) {
                return false;
            }
        }

        // Unlock descendants
        for (Node lockedNode : lockedNodes) {

            lockedNode.locked = false;
            lockedNode.lockedBy = -1;

            updateAncestors(lockedNode, -1);
        }

        // Lock current node
        node.locked = true;
        node.lockedBy = user;

        updateAncestors(node, 1);

        return true;
    }
}