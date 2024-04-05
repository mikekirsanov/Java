import java.util.Arrays;
/*
Реализуйте метод divArrays, принимающий в качестве аргументов два целочисленных массива a и b, и возвращающий новый массив с, каждый элемент которого равен частному элементов двух входящих массивов в той же ячейке.

Если длины массивов не равны - верните нулевой массив длины 1.

Важно: При выполнении метода единственное исключение, которое пользователь может увидеть - RuntimeException, т.е. ваше.

Напишите свой код в методе divArrays класса Answer. Метод divArrays принимает на вход два параметра:

int[] a - первый массив
int[] b - второй массив
Пример


a = new int[]{12, 8, 16};
b = new int[]{4, 2, 4};

Вывод: [3, 4, 4]

a = new int[]{12, 8, 16, 25};
b = new int[]{4, 2, 4};

Вывод: [0] */

class Answer2 {
    public int[] divArrays(int[] a, int[] b){
      // Введите свое решение ниже
      int[] c;
      if(a.length == b.length){
          c = new int[a.length];
          for (int i = 0; i < a.length; i++) {
            try {
                c[i] = a[i] / b[i];
            } catch (ArithmeticException e) {
                throw new RuntimeException("Деление на ноль");
            }
          }
      } else {
          c = new int[1];
          c[0] = 0;
      }
      return c;
       
    }
}

// Не удаляйте этот класс - он нужен для вывода результатов на экран и проверки
public class Printer2{ 
    public static void main(String[] args) { 
      int[] a = {};
      int[] b = {};
      
      if (args.length == 0) {
        // При отправке кода на Выполнение, вы можете варьировать эти параметры
        a = new int[]{12, 8, 16};
        b = new int[]{4, 2, 4};
      }
      else{
        a = Arrays.stream(args[0].split(", ")).mapToInt(Integer::parseInt).toArray();
        b = Arrays.stream(args[1].split(", ")).mapToInt(Integer::parseInt).toArray();
      }     
      
      Answer2 ans = new Answer2(); 
      String itresume_res = Arrays.toString(ans.divArrays(a, b));     
      System.out.println(itresume_res);
    }
}
