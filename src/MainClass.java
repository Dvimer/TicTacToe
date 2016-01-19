import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;

public class MainClass {


    public static void main(String[] args) {
        System.out.println("Введите размер поля: ");
        Game game = new Game();
        game.initField();//инициализация поля
        game.printField();//отрисовка поля

        while(true) {
            game.playerTurn();//ход плеера
            game.printField();//отрисовка поля
            //
            if(game.checkWin(game.getPlayerDot())){//проверка на победу плеера
                System.out.println("Победил игрок");
                break;
            }
            if (game.isFieldFull()){    //проверка на заполненность поля
                System.out.println("Ничья!");
                break;
            }

            game.aiTurn();//ход компьюетера
            game.printField();//отрисовка поля
            if(game.checkWin(game.getAiDot())){// проверка победы компьюетра
                System.out.println("Победил компьютер");
                break;
            }
            if (game.isFieldFull()){// проверка на заполнение поля
                System.out.println("Ничья!");
                break;
            }
        }
    }




}
