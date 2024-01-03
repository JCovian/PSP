package es.covian.psp.mensajes;

import java.util.Scanner;

public class HijoProfesor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String leido = sc.nextLine();

        switch (leido) {
            case "Tic":
                System.out.println("Toc");
                break;
            case "Eco":
                System.out.println("Eco");
                break;
            case "Marco":
                System.out.println("Polo");
                break;
            default:
                System.err.println("Error");
                System.exit(-1);
        }
    }
}
