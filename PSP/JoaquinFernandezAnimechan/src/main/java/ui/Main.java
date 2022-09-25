package ui;

import dao.DaoCartas;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {



        DaoCartas daoCartas = new DaoCartas();
        Scanner scanner = new Scanner(System.in);
        String nombre = scanner.nextLine();

//        DecksList<DataItem> r = daoCartas.verUnaCarta(nombre).;
//        System.out.println(r.body());


    }
}