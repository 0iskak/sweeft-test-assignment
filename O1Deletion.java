import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class O1Deletion<T> implements Iterable<T> {
    private final Map<Integer, Node<T>> nodes = new LinkedHashMap<>();
    private int nodeOffset = 0;
    private Node<T> first;
    private Node<T> last;

    public O1Deletion(List<T> items) {
        var index = new AtomicInteger();
        var nodes = items.stream()
                .map(item -> {
                    var currIndex = index.getAndIncrement();
                    var prevIndex = currIndex - 1;
                    var nextIndex = currIndex + 1;

                    var node = getOrDefault(currIndex, items);
                    var prev = getOrDefault(prevIndex, items);
                    var next = getOrDefault(nextIndex, items);

                    node.prev = prev;
                    node.next = next;

                    return node;
                })
                .toList();

        first = nodes.get(0);
        last = nodes.get(nodes.size() - 1);
    }

    private void delete(int index) {
        var node = nodes.remove(index + nodeOffset++);

        if (node == null)
            throw new IndexOutOfBoundsException();
        if (index == 0)
            first = node.next;
        else if (index == nodes.size())
            last = node.prev;
        else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }

    private Node<T> getOrDefault(int index, List<T> items) {
        var node = nodes.get(index);


        try {
            if (node == null)
                node = new Node<>(items.get(index));
            nodes.put(index, node);
        } catch (IndexOutOfBoundsException ignored) {
        }

        return node;
    }

    @Override
    public String toString() {
        return stream().map(Objects::toString)
                .collect(Collectors.joining(", ", "[", "]"));
    }

    public Stream<T> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Node<T> node = first;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                var value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        private final T value;

        private Node<T> next;
        private Node<T> prev;

        public Node(T value) {
            this.value = value;
        }
    }
}
