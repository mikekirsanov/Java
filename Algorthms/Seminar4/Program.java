/**
 * Алгоритмы и структуры данных (семинары)
Урок 4. Структуры данных дерево и хэш-таблица
Необходимо доработать структуру класса HashMap, реализованную на семинаре.
У нас появился метод добавления элементов, каким образом я могу распечатать все элементы структуры на экране?

Распечатайте все элементы структуры HashMap переопределив метод toString() - самый простой вариант.
2****. (Дополнительная, необязательная задача, для тех, кому очень скучно) Добавить возможность перебора
всех элементов нашей структуры данных, необходимо добавить несколько элементов, а затем перебрать все элементы
структуры HashTable используя цикл foreach. Подумайте, возможно вам стоит обратиться к интерфейсу Iterable и
в рамках имплементации подобного интерфейса создать объект типа Iterator, далее, вы реализуете метод next и hasNext,
наделите способностью нашу структуру HashMap быть перечисляемой.
 */

 public class Program {
    public static void main(String[] args) {
        Employee employee1 = new Employee("AAAA", 33);
        System.out.println(employee1.hashCode());
        Employee employee2 = new Employee("AAAA", 41);
        System.out.println(employee2.hashCode());

        HashMap<String, String> hashMap1 = new HashMap<>(4);

        String oldValue;
        oldValue = hashMap1.put("+79007774431", "BBBBB");
        oldValue = hashMap1.put("+79007774432", "CCCCC");
        oldValue = hashMap1.put("+79007774431", "ABCDE");
        oldValue = hashMap1.put("+79007774433", "BBBBB1");
        oldValue = hashMap1.put("+79007774434", "CCCCC1");
        oldValue = hashMap1.put("+79007774435", "ABCDE1");
        oldValue = hashMap1.put("+79007774436", "BBBBB2");
        oldValue = hashMap1.put("+79007774437", "CCCCC2");
        oldValue = hashMap1.put("+79007774438", "ABCDE2");
        oldValue = hashMap1.put("+79007774439", "BBBBB3");
        oldValue = hashMap1.put("+79007774410", "CCCCC3");
        oldValue = hashMap1.put("+79007774441", "ABCDE3");
        oldValue = hashMap1.put("+79007774442", "BBBBB4");
        oldValue = hashMap1.put("+79007774443", "CCCCC4");
        oldValue = hashMap1.put("+79007774444", "ABCDE4");
        oldValue = hashMap1.put("+79007774445", "BBBBB5");
        oldValue = hashMap1.put("+79007774446", "CCCCC5");
        oldValue = hashMap1.put("+79007774447", "ABCDE5");

        String res = hashMap1.get("+79007774436");
        res = hashMap1.get("+79017774436");

        oldValue = hashMap1.remove("+79007774436");
        oldValue = hashMap1.remove("+79007774436");

        System.out.println("Printing HashMap:");
        System.out.println(hashMap1);
    }
}
