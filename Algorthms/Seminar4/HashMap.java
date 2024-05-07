﻿import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashMap<K, V> implements Iterable<HashMap<K, V>.Entity> {

    private static final int INIT_BUCKET_COUNT = 16;
    private static final double LOAD_FACTOR = 0.5;

    private Bucket[] buckets;
    private int size;

    @Override
    public Iterator<HashMap<K, V>.Entity> iterator() {
        return new HashMapIterator();
    }

    class HashMapIterator implements Iterator<HashMap<K, V>.Entity> {

        private int bucketIndex = 0;
        private Bucket.Node currentNode = null;

        HashMapIterator() {
            if (buckets != null && buckets.length > 0) {
                while (bucketIndex < buckets.length
                        && (buckets[bucketIndex] == null || buckets[bucketIndex].head == null)) {
                    bucketIndex++;
                }
                if (bucketIndex < buckets.length) {
                    currentNode = buckets[bucketIndex].head;
                }
            }
        }

        @Override
        public boolean hasNext() {
            while (bucketIndex < buckets.length && (buckets[bucketIndex] == null || currentNode == null)) {
                bucketIndex++;
                if (bucketIndex < buckets.length && buckets[bucketIndex] != null) {
                    currentNode = buckets[bucketIndex].head;
                }
            }
            return bucketIndex < buckets.length && currentNode != null;
        }

        @Override
        public Entity next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Entity nextEntity = currentNode.value;
            currentNode = currentNode.next;
            return nextEntity;
        }
    }

    /**
     * TODO: Вывести все элементы хэш-таблицы на экран через toString()
     * 
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
    sb.append("{\n");

    Iterator<Entity> iterator = iterator();
    while (iterator.hasNext()) {
        Entity entity = iterator.next();
        sb.append("\t").append(entity.key).append(" = ").append(entity.value).append(",\n");
    }

    sb.append("}\n");
    return sb.toString();
}

    /**
     * Элемент хэш-таблицы
     */
    public class Entity {
        /**
         * Ключ
         */
        K key;

        /**
         * Значение элемента
         */
        V value;
    }

    /**
     * Бакет, связный список
     */
    class Bucket {
        /**
         * Указатель на первый элемент связного списка
         */
        Node head;

        /**
         * Узел бакета (связного списка)
         */
        public class Node {

            /**
             * Указатель на следующий элемент связного списка
             */

            Node next;

            /**
             * Значение узла, указывающее на элемент хэш-таблицы
             */
            Entity value;
        }

        public V add(Entity entity) {
            Node node = new Node();
            node.value = entity;

            if (head == null) {
                head = node;
                return null;
            }

            Node currentNode = head;
            while (true) {
                if (currentNode.value.key.equals(entity.key)) {
                    V buf = currentNode.value.value;
                    currentNode.value.value = entity.value;
                    return buf;
                }

                if (currentNode.next != null) {
                    currentNode = currentNode.next;
                }

                else {
                    currentNode.next = node;
                    return null;
                }
            }
        }

        public V remove(K key) {
            if (head == null) {
                return null;
            }
            if (head.value.key.equals(key)) {
                V buf = head.value.value;
                head = head.next;
                return buf;
            } else {
                Node node = head;
                while (node.next != null) {
                    if (node.next.value.key.equals(key)) {
                        V buf = node.next.value.value;
                        node.next = node.next.next;
                        return buf;
                    }
                    node = node.next;
                }
                return null;
            }
        }

        public V get(K key) {
            Node node = head;
            while (node != null) {
                if (node.value.key.equals(key)) {
                    return node.value.value;
                }

                node = node.next;
            }
            return null;
        }
    }

    private int calculateBucketIndex(K key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }

    private void recalculate() {
        size = 0;
        Bucket[] old = buckets;
        buckets = new HashMap.Bucket[old.length * 2];
        for (int i = 0; i < old.length; i++) {
            Bucket bucket = old[i];
            if (bucket != null) {
                Bucket.Node node = bucket.head;
                while (node != null) {
                    put(node.value.key, node.value.value);
                    node = node.next;
                }
            }
        }
    }

    public V put(K key, V value) {
        if (size >= buckets.length * LOAD_FACTOR) {
            recalculate();
        }
        int index = calculateBucketIndex(key);
        Bucket bucket = buckets[index];
        if (bucket == null) {
            bucket = new Bucket();
            buckets[index] = bucket;
        }

        Entity entity = new Entity();
        entity.key = key;
        entity.value = value;

        V buf = bucket.add(entity);
        if (buf == null) {
            size++;
        }
        return buf;
    }

    public V get(K key) {
        int index = calculateBucketIndex(key);
        Bucket bucket = buckets[index];
        if (bucket == null) {
            return null;
        }

        return bucket.get(key);
    }

    public V remove(K key) {
        int index = calculateBucketIndex(key);
        Bucket bucket = buckets[index];
        if (bucket == null) {
            return null;
        }
        V buf = bucket.remove(key);
        if (buf != null) {
            size--;
        }
        return buf;
    }

    public HashMap() {
        buckets = new HashMap.Bucket[INIT_BUCKET_COUNT];
    }

    public HashMap(int initCount) {
        buckets = new HashMap.Bucket[initCount];
    }

}
