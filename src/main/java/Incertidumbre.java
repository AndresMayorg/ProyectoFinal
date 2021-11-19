
class Incertidumbre {
    public static void main(String args[]) {
        System.out.println("Decisión bajo Incertidumbre");

        int operacion;
        do {
            System.out.println();
            System.out.println("[1] Definir Tabla de Decision");
            System.out.println("[2] Visualizar Tabla de Decision");
            System.out.println("[3] Criterios de Decision");
            System.out.println("[0] Salir");
            do {
                operacion = Terminal.leeEntero("Indique una operacion: ");
            } while ((operacion < 0) ||
                    (operacion > 3));
            System.out.println();
            if (operacion == 1)
                definirTabla();
            else if (operacion == 2)
                TablaIncert.verTabla();
            else if (operacion == 3)
                TablaIncert.decision();
        } while (operacion != 0);
    }

    /*******************************
     *         definirTabla           *
     *******************************/

    private static void definirTabla() {
        System.out.println("DEFINICION DE LA TABLA DE DECISION:");

        int numAlter;
        int numEst;
        int i, j;
        double valor;
        String cadena;
        int random = (int) (Math.random() * 9 + 1);

        /*
         ** Obtener número de alternativas y estados
         */

        do {
            numAlter = Terminal.leeEntero("NUMERO DE ALTERNATIVAS? ");
        } while (numAlter < 0);

        if (numAlter == 0) return;

        do {
            numEst = Terminal.leeEntero("NUMERO DE ESTADOS? ");
        } while (numEst < 0);

        if (numEst == 0) return;

        /*
         **  Crear tabla
         */

        new TablaIncert(numAlter, numEst);


        /*
         ** Obtener descripción de alternativas
         */

        for (i = 1; i <= numAlter; i++) {
            cadena = String.valueOf(random);
            TablaIncert.definirAlter(i, cadena);
        }

        /*
         ** Obtener descripción de estados
         */

        for (j = 1; j <= numEst; j++) {
            cadena = String.valueOf(random);
            TablaIncert.definirEstado(j, cadena);
        }

        /*
         ** Obtener Valoración de los resultados
         */

        for (i = 1; i <= numAlter; i++) {
            System.out.println();
            for (j = 1; j <= numEst; j++) {
                valor = (int) (Math.random() * 100);
                TablaIncert.definirResultado(i, j, valor);
            }
        }

    }

}       // Fin clase Incertidumbre

/******** Fin de Incertidumbre.java ****************/