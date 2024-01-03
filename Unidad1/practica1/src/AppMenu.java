import java.io.IOException;
import java.rmi.ServerError;
import java.util.Scanner;

public class AppMenu {
    public static void main(String[] args) {
        boolean salir = false;
        int opc = 0;

        while(!salir) {
            System.out.println(menu());
            opc = validaEntero();
            switch (opc) {
                case 0:
                    salir = true;
                    break;
                case 1:
                    abrirProceso("C:\\Program Files\\Microsoft Office\\root\\Office16\\WINWORD.EXE");
                    break;
                case 2:
                    abrirProceso("C:\\Program Files\\Microsoft Office\\root\\Office16\\EXCEL.EXE");
                    break;
                case 3:
                    abrirProceso("notepad");
                    break;
                case 4:
                    abrirProceso("cmd");
                    break;
                default:
                    System.err.println("ERROR! Esa opción no existe");
                    break;
            }
        }
    }
    //Metodo para generar el menú
    public static String menu() {
        return ("============================"
                + "\n "
                + "\n 1. Word"
                + "\n 2. Excel"
                + "\n 3. Notepad"
                + "\n 4. CMD"
                + "\n "
                + "\n 0. Salir"
                + "\n "
                + "\n============================");
    }

    //Metodo para abrir los procesos
    public static void abrirProceso(String programa) {
        Runtime rt = Runtime.getRuntime();
        try {
            Process proceso = rt.exec(programa);
            System.out.printf("%nPID del proceso: %s%nInfo del proceso: %s%n%n", proceso.pid(), proceso.info());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Valida que introduzcamos un entero
    public static int validaEntero() {
        boolean esCorrecto = false;
        Scanner sc = new Scanner(System.in);
        int entero = 0;

        while(!esCorrecto) {
            if(sc.hasNextInt()) {
                entero = sc.nextInt();
                if(entero < 0) {
                    System.err.println("ERROR! Debe de introducir un número entero positivo");
                } else {
                    //sc.close();
                    esCorrecto = true;
                }
            } else {
                System.err.println("ERROR! No ha introducido un valor numérico entero");
                sc.nextLine();
            }

            if (!esCorrecto) {
                System.out.println("Introduzca un valor entero positivo");
            }
        }
        return entero;
    }

}
