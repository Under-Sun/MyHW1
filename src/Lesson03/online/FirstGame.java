package Lesson03.online;

import java.util.Random;
import java.util.Scanner;

public class FirstGame {

        public static char[][] map; // 1.создаём массив типа char (потому что х,о это символы),называем его.
        public static int mapSizeW; // 2. объявляем переменные int(массив измеряется только целочисленными значениями), характеризующие наше поле.
        public static int mapSizeH; //  2.1 W будет наше поле для ширины, Н будет для высоты.

        public static char player = 'X'; // 3. выносим наши значения(игроки и символы) в переменные и называем их. наш игрок
        public static char computer = 'O'; // 3.1 наш противник
        public static char empty_cell = '.'; // 3.2 наша пустая клетка

        public static Scanner scanner = new Scanner(System.in); // 9. инструмент, класс, который работает с пользовательским вводом(нужно чтото напечатать), наз-ся scanner.
                    // 9.1 Создаётся точно так же как массив. В скобках(System.in), т.к сканер будет считывать наши значения(система будет принимать их в себя).
        public static Random random = new Random(); // 13. инструмент, он же класс, который отвечает за рандомизацию выдаваемых значений.
// 13.1 random - это любое число от 0 до 1, но сама единица никогда не выпадет.


        public static void buildMap () {  // 4. нам нужно отобразить наше поле, которое мы объявили.(Создаём нашу карту)
             mapSizeW = 3; // 4.1. значение нашей карты по горизонтали
             mapSizeH = 3; // 4.2 значение нашей карты по вертикали
             map = new char[mapSizeH][mapSizeW]; //4.3 в нашем поле создаем массив размерами H(сначала столбцы) и W(затем строки)

                    // 5. чтобы наш игрок не видел поле заполненное char нулями, мы скажем, что при создании карты, заполни поле пустыми клетками.

             for (int h = 0; h < mapSizeH; h++) { // 5.1 цикл, который "бежит" по всей высоте, пока высота не закончится
                 for (int w = 0; w < mapSizeW; w++){ // 5.2 параллельно заходит в каждую строку, пока та не закочнится
                     map[h][w] = empty_cell; // 5.3 сообщим карте, в текущих координатах h и w у нас будет стоять пустая клетка(empty_cell).
                 }
             }
         }


        public static void printMap() { // 7. команда отображения поля. далее обычный двумерный массив. Используем вложенные циклы.
            for (int h = 0; h < mapSizeH ; h++) { // 7.1 бежим по столбцам, пока не они закончатся
                for (int w = 0; w < mapSizeW; w++) { // 7.2 бежим по строкам, пока не закончатся
                    System.out.print("|" + (map[h][w]) + "|"); // 7.3 распечатай нам текущее значение в ячейках по координатам h и w через разделитель(" | ")
                }
                System.out.println(); // 7.4 чтобы после каждой строки наша каретка перемещалась на новую строку, создадим пустую строку.
            }
            System.out.println("******************");
        }

        public static void playerTurn () { // 8. после того, как карта распечатается, предоставим возможность игроку ходить. создаем метод
             // 8.1 что нужно знать игроку, чтоб сходить? координаты ячеек, куда сходить.
            int x;
            int y;
            //  8.2 далее нужно как то ввести свои координаты, чтобы они рассчитывались и отображались в массиве.


                                   // 10. если компьютер походит в эту же клетку что и мы, она заменится на его значение(О), поэтому прописываем логику пустой клетки

            do { // 12. цикл в котором будет выполнено что-то хотя бы 1 раз.
                System.out.println("Введите значение: ");
                x = scanner.nextInt() - 1; // 9.2 теперь наши координаты х и у мы будем запрашивать у игрока.
                y = scanner.nextInt() - 1; // 9.3 мы будем задавать строковое значение, нужно перевести его в  integer
            if (!isValidCell(y, x)) {
                    System.out.println("Вы вышли за пределы поля!");
                }
            } while (!isValidCell(y, x) || !isEmptyCell(y, x)); // 12.1 мы спросим координаты до тех пор, пока ячейка не валидна, или не пустая, только тогда мы поставим наши значения в текущий ход игрока.
               map[y][x] = player;
            // 10.1 ниже (условия для хода игрока,было - if (map [y][x] == empty_cell && x >= 0 && x < mapSizeW && y >= 0 && y < mapSizeH) и чтобы не было копипасты(если использовать проверку еще раз))-, сделаем метод.
//            if (!isValidCell(y, x) || !isEmptyCell(y, x)) { // 11.3 нам нужно проверить, либо человек сходил в пределы нашего поля(не валидна ли ячейка) ИЛИ  ячейка не пустая, то тогда можно сходить. If  не подходит при этом, нужно while (пункт 12)
//                map [y][x] = player;
//            }
            // map[y][x] = player;// 9.4 теперь карта по координатам у и х будет ход игрока( меняем после начала пункта 10 выше)
        }

        public static boolean isEmptyCell (int y, int x) { // 10.2 вносим два значения, либо пустая, либо не пустая, сюда запросим координаты той ячейки, которую хотим проверить(int y, и  int x)
         return  map[y][x] == empty_cell;   // 10.3 проверь, в текущих координатах нашей карты содержится ли пустая ячейка? если да, верни true, если нет, верни false.
            // 10.4 После этого строку из 10.1 до x >= 0 (map [y][x] == empty_cell &&) можно удалить.
        }


        public static boolean isValidCell(int y, int x) { // 11. У нас есть проверка на пределы поля (10.1), мы будем так же спрашивать у компьютера, т.е переиспользовать  метод. Логично создать еще один метод boolean
            return x >= 0 && x < mapSizeW && y >= 0 && y < mapSizeH; //11.1 спросим, валидна ли ячейка, в которую пытается сейчас сходить игрок? и вставляем сюда готовый метод из 10.1
        }  // 11.2 после 10.1 просим проверить, х >=о и < чем размер карты? тоже самое сделай с у и верни нам результат и если одна из проверок выдаст false, значит игрок ходит не туда.

   public static boolean checkWin(char actualPlayer) {
       if (map [0][0] == actualPlayer && map [0][1] == actualPlayer && map[0][2] == actualPlayer) return true;
       if (map [1][0] == actualPlayer && map [1][1] == actualPlayer && map[1][2] == actualPlayer) return true;
       if (map [2][0] == actualPlayer && map [2][1] == actualPlayer && map[2][2] == actualPlayer) return true;

       if (map [0][0] == actualPlayer && map [1][0] == actualPlayer && map[2][0] == actualPlayer) return true;
       if (map [0][1] == actualPlayer && map [1][1] == actualPlayer && map[2][1] == actualPlayer) return true;
       if (map [0][2] == actualPlayer && map [1][2] == actualPlayer && map[2][2] == actualPlayer) return true;

       if (map [0][0] == actualPlayer && map [1][1] == actualPlayer && map[2][2] == actualPlayer) return true;
       if (map [0][2] == actualPlayer && map [1][1] == actualPlayer && map[2][0] == actualPlayer) return true;

       return false;
   }

    public static boolean isFullMap () { // 14. Пустота. делаем логику Ничья(пустых клеток нет)
        for (int y = 0; y < mapSizeW; y++) { //14. 1 бежим по всему массиву, строки, столбцы, и спрашиваем, текущее поле по координатам у,х, а внутри есть пустая ячейка? если да, верни false. А если такого нет, то true
            for (int x = 0; x < mapSizeH; x++) {
                if (map[y][x] == empty_cell) {
                    return false;
                }
            }
        }
         return true;
    }



        public static void computerTurn () { // 8.1 создаём метод для хождения компьютеру(далее см. пункт 13.)

            int x;
            int y;

            // 13.2 нужно попросить компьютер выкинуть кубик по х и по у., по умолчанию выдается любое float значение(0.78888...) и
            // чтобы преобразовать в int  и заставить метод выдавать значение не от 0 до 1, а до 2.
           do {
               x = random.nextInt(mapSizeH); // 13.3  в скобках указываем верхнюю границу нашей карты(3), а это Н.(и будет от 0-2)
               y = random.nextInt(mapSizeW); // 13.4  на валидность не проверяем, потому что он не выбросит значение больше 2.
           } while (!isEmptyCell(y, x));
            // 13.5 далее скажем через do while , компьютер, брось кубик до тех пор, пока не попадешь на пустую ячейку.
            map[y][x] = computer;
        }

    public static void main (String[] args){ // главный метод, в котором будет начинать выполняться программа.
        buildMap(); // 6. Java - вызови нам этот метод 4., создай нам карту и заполни ее. ( при запуске ничего не покажет, т.к нет команды отображения этого поля.)
        printMap(); // 7.5 после создания карты, мы её распечатаем.


        while (true) {
            playerTurn();
            printMap();
            // 15. может нам вести бесконечный цикл до тех пор, пока ктото не выиграет или будет ничья, с использованием ключевых слов использования циклов (break)
            if (checkWin(player)) {                // нужно проверить, а вдруг после хода игрока наступила победа?
                System.out.println("Игрок выиграл!");
                break;
            }
            if (isFullMap()) {
                System.out.println("Ничья!");
                break;
            }

            computerTurn(); // ход компьютера
            printMap(); // 9.5 чтобы после каждого хода игрока, нам показывало текущее положение поля.(Куда походили)
            if (checkWin(computer)) {                // нужно проверить, а вдруг после хода компьютера наступила победа?
                System.out.println("Компьютер выиграл!");
                break;
            }
            if (isFullMap()) {
                System.out.println("Ничья!");
                break;
            }
        }
    }
}
