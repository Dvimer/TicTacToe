import java.util.Random;
import java.util.Scanner;

/**
 * Created by Алексей on 16.01.2016.
 */
public class Game {

    public Scanner sc = new Scanner(System.in);
    public Random rand = new Random();

    private final int FIELD_SIZE = sc.nextInt();
    public final int OX_TO_WIN = 3;
    private char[][] field;
    private char aiDot = '0';
    private char playerDot = 'X';

    // Заполняем поле звездочками
    public void initField() {
        field = new char[FIELD_SIZE][FIELD_SIZE];
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                field[i][j] = '*';
            }
        }

        String a = "Hello World";
    }
    // отрисовываем поле с координатами
    public void printField() {
        System.out.print("  ");
        for (int i = 0; i < FIELD_SIZE; i++) {
            System.out.print(" " + (i + 1));
        }
        System.out.println();
        for (int i = 0; i < FIELD_SIZE; i++) {
            System.out.print((i + 1) + "  ");
            for (int j = 0; j < FIELD_SIZE; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
    }

    // ход игрока, сами указываем координаты, если не заполнено - ставим
    public void playerTurn() // X
    {
        int x, y;
        do {
            System.out.println("Введите номер поля в формате X Y");
            x = sc.nextInt();
            y = sc.nextInt();
        } while (!isCellEmpty(y - 1, x - 1));
        setXO(y - 1, x - 1, playerDot);
    }

// проверка по длинне победы, если есть линия(горизонталь,вертикаль, диагональ) - возвращаем победу
    public boolean checkWin(char ox) {
        for (int i = 0; i < FIELD_SIZE; i++)
            for (int j = 0; j < FIELD_SIZE; j++) {
                if (checkLine(i, j, 1, 0, OX_TO_WIN, ox)) return true;
                if (checkLine(i, j, 0, 1, OX_TO_WIN, ox)) return true;
                if (checkLine(i, j, 1, 1, OX_TO_WIN, ox)) return true;
                if (checkLine(i, j, 1, -1, OX_TO_WIN, ox)) return true;
            }
        return false;
    }
// проверка вхождения координат в массив и выбор координат и направления движения
    public boolean checkLine(int y, int x, int vx, int vy, int l, char ox) {
        if (x + vx * l > FIELD_SIZE || y + vy * l > FIELD_SIZE || y + vy * l < -1) return false;
        for (int i = 0; i < l; i++)
            if (field[y + i * vy][x + i * vx] != ox) return false;
        return true;
    }



//ход компьютера
    public void aiTurn() {
        int x, y;
//бьем рандомно по полю
        do {
            x = rand.nextInt(FIELD_SIZE);
            y = rand.nextInt(FIELD_SIZE);
        } while (!isCellEmpty(y, x));
//если поставим наш символ, а потом еще 1 и будет победа - ставим в последние координаты
        for (int i = 0; i < FIELD_SIZE; i++)
            for (int j = 0; j < FIELD_SIZE; j++) {
                if (isCellEmpty(i, j)) {
                    setXO(i, j, aiDot);

                    for (int k = 0; k < FIELD_SIZE; k++)
                        for (int p = 0; p < FIELD_SIZE; p++) {
                            if (isCellEmpty(k, p)) {
                                setXO(k, p, aiDot);
                                if (checkWin(aiDot)) {
                                    y = i;
                                    x = j;
                                }
                                setXO(k, p, '*');
                            }
                        }

                    
                    setXO(i, j, '*');
                }
            }
//если поставим символ и плеер победит, то запоминаем координаты
        for (int i = 0; i < FIELD_SIZE; i++)
            for (int j = 0; j < FIELD_SIZE; j++) {
                if (isCellEmpty(i, j)) {
                    setXO(i, j, playerDot);
                    if (checkWin(playerDot)) {
                        y = i;
                        x = j;
                    }
                    setXO(i, j, '*');
                }
            }
// если поставим символ мы победм - запоминаем координаты
        for (int i = 0; i < FIELD_SIZE; i++)
            for (int j = 0; j < FIELD_SIZE; j++) {
                if (isCellEmpty(i, j)) {
                    setXO(i, j, aiDot);
                    if (checkWin(aiDot)) {
                        y = i;
                        x = j;
                    }
                    setXO(i, j, '*');
                }
            }
//ставим символ по значимости
//        1. есть ли наша победа, если нет то
//        2. есть ли победа игрока
//        3.есть ли ситуация, когда поставим 2 символа мы выиграли бы
//        4.ставим рандомно
        setXO(y, x, aiDot);
        System.out.println("Комп стрельнул в X/Y: " + (x + 1) + "/" + (y + 1));
    }


// ставим в координаты, выбранный символ
    public void setXO(int y, int x, char ox) {
        field[y][x] = ox;
    }

//проверка поле на пустые клетки, если есть - возвращаем фолс, нету возвращаем тру и по условию играем дальше
    public boolean isFieldFull() {
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                if (field[i][j] == '*') return false;
            }
        }
        return true;
    }

    public char getAiDot() {
        return aiDot;
    }

    public char getPlayerDot() {
        return playerDot;
    }
// проверка клетки на пустоту
    public boolean isCellEmpty(int y, int x) {
        if (field[y][x] == '*') return true;
        return false;
    }
}
