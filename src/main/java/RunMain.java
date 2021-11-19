import java.util.InputMismatchException;
import java.util.Scanner;

class RunMain {
    public static void main(String args[]) {
        System.out.println("Proyecto final de Investigación de Operaciones");

        int operacion;
        do {
            System.out.println();
            System.out.println("[1] Definir Tabla de Decisión");
            System.out.println("[2] Visualizar Tabla de Decisión");
            System.out.println("[3] Métodos de Decisión");
            System.out.println("[0] Salir");
            System.out.println();
            System.out.println();
            System.out.println("Por favor seleccione una opcion");
            do {
                operacion = Control.leeEntero("");
            } while ((operacion < 0) ||
                    (operacion > 3));
            System.out.println();
            if (operacion == 1)
                definirTabla();
            else if (operacion == 2)
                Metodos.verTabla();
            else if (operacion == 3)
                Metodos.decision();
        } while (operacion != 0);
    }

    /*******************************
           definir Matriz          *
     *******************************/

    private static void definirTabla() {


        int numAlter = 0;
        int numEst = 0;
        int i, j;
        double valor;
        String cadena;
        int random = (int) (Math.random() * 9 + 1);

        /*
         ** Obtener número de alternativas y estados
         */

        Scanner escaner = new Scanner(System.in);

        System.out.println("Por favor digite el numero de alternativas: ");
        try {
            
            numAlter = escaner.nextInt();
            
        } catch (InputMismatchException e) {

            System.out.println("No introdujiste un número válido");
        }




        System.out.println("Por favor digite el numero de estados: ");
        try {

            numEst = escaner.nextInt();

        } catch (InputMismatchException e) {

            System.out.println("No introdujiste un número válido");
        }


        /*
         **  Crear tabla
         */

        new Metodos(numAlter, numEst);

        for (i = 1; i <= numAlter; i++) {
            cadena = String.valueOf(random);
            Metodos.definirAlter(i, cadena);
        }

        for (j = 1; j <= numEst; j++) {
            cadena = String.valueOf(random);
            Metodos.definirEstado(j, cadena);
        }

        /*
         ** Obtener Valoración de los resultados
         */

        for (i = 1; i <= numAlter; i++) {
            System.out.println();
            for (j = 1; j <= numEst; j++) {
                valor = (int) (Math.random() * 100);
                Metodos.definirResultado(i, j, valor);
            }
        }

    }

}
