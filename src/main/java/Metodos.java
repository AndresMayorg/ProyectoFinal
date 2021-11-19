
public class Metodos {
    private static int Nalternativas = 0;     // Número de alternativas
    private static int Nestados = 0;       // Número de estados de la Naturaleza
    private static String[] Alter;        // Alternativas
    private static String[] Estado;       // Estados
    private static double[][] Resultado;    // Resultados

    /*******************************
     *         Constructor         *
     *******************************/

    public Metodos(int numAlter, int numEst) {

        Nalternativas = numAlter;
        Nestados = numEst;

        Alter = new String[Nalternativas];
        Estado = new String[Nestados];
        Resultado = new double[Nalternativas][Nestados];

    }

    /*******************************
     *        definirAlter         *
     *******************************/

    public static void definirAlter(int numAlter, String DescAlter) {

        if (numAlter < 1 || numAlter > Nalternativas) {
            System.out.println("Número de Alternativa incorrecto");
            return;
        }

        Alter[numAlter - 1] = DescAlter;

    }

    /*******************************
     *        definirEstado        *
     *******************************/

    public static void definirEstado(int numEst, String DescEstado) {

        if (numEst < 1 || numEst > Nestados) {
            System.out.println("Número de Estado incorrecto");
            return;
        }

        Estado[numEst - 1] = DescEstado;

    }

    /*******************************
     *      definirResultado       *
     *******************************/

    public static void definirResultado(int numAlter, int numEst,
                                        double ValResultado) {

        if (numAlter < 1 || numAlter > Nalternativas) {
            System.out.println("Número de Alternativa incorrecto");
            return;
        }

        if (numEst < 1 || numEst > Nestados) {
            System.out.println("Número de Estado incorrecto");
            return;
        }

        Resultado[numAlter - 1][numEst - 1] = ValResultado;

    }

    /*******************************
     *           verTabla          *
     *******************************/

    public static void verTabla() {

        int i, j;

        if (Nalternativas == 0 || Nestados == 0) return;

        System.out.println("DECISION BAJO INCERTIDUMBRE");
        System.out.println("---------------------------");
        System.out.println();


        // Imprimir cabecera de estados

        String cadena = "    |";

        for (j = 0; j < Nestados; j++) {
            cadena += "  " + relleno("e" + (j + 1), 4);
        }

        System.out.println(cadena);
        for (j = 0; j < cadena.length(); j++) System.out.print("-");
        System.out.println();

        // Para cada alternativa

        for (i = 0; i < Nalternativas; i++) {

            System.out.print(relleno("a" + (i + 1), 4) + "| ");
            // Para cada estado
            for (j = 0; j < Nestados; j++)
                System.out.print(" " + relleno(Double.toString(Resultado[i][j]), 5));
            System.out.println();
        }
        System.out.println();
        System.out.println();

    }

    /*******************************
     *           relleno          *
     *******************************/

    public static String relleno(String cadena, int longitud) {
        String blanco = new String(cadena);
        int dif = longitud - cadena.length();

        while (dif > 0) {
            blanco = new String(blanco + " ");
            dif--;
        }

        return blanco;
    }


    /*******************************
     *           decision          *
     *******************************/

    public static void decision() {

        int operacion;

        do {
            System.out.println("Criterios de Decision bajo incertidumbre");
            System.out.println();

            System.out.println("[1] Método Pesimista");
            System.out.println("[2] Método Optimista ");
            System.out.println("[3] Método de Hurwicz");
            System.out.println("[4] Método de Savage");
            System.out.println("[5] Método de Laplace");
            System.out.println("[0] Salir");

            do {
                operacion = Control.leeEntero("Indique una operacion: ");
            } while ((operacion < 0) || (operacion > 5));

            System.out.println();
            if (operacion == 1)
                Pesimista();
            else if (operacion == 2)
                Optimista();
            else if (operacion == 3)
                Hurwicz();
            else if (operacion == 4)
                Savage();
            else if (operacion == 5)
                Laplace();
        } while (operacion != 0);
    }

    /*******************************
     *   Método pesimista            *
     *******************************/

    public static void Pesimista() {

        double Maximo = Double.NEGATIVE_INFINITY;
        int AltMax = 1;

        double Minimo;
        int EstMin = 1;

        int i, j;

        System.out.println("Método pesimista");
        System.out.println("----------------");
        System.out.println();

        System.out.println("Valoracion de las alternativas");
        System.out.println();

        for (i = 0; i < Nalternativas; i++) {

            Minimo = Double.POSITIVE_INFINITY;
            for (j = 0; j < Nestados; j++) {
                if (Resultado[i][j] < Minimo) {
                    Minimo = Resultado[i][j];
                    EstMin = j + 1;
                }
            }

            System.out.println("Alt. " + (i + 1) + ": Valoracion=" + Minimo + "  Estado=" + EstMin);

            if (Minimo > Maximo) {
                Maximo = Minimo;
                AltMax = i + 1;
            }
        }

        System.out.println();
        System.out.println("Alternativa óptima: " + AltMax + " " + Maximo);
        System.out.println();
        System.out.println();

    }

    /*******************************
     *  Método Optimista          *
     *******************************/

    public static void Optimista() {

        double Maximo = Double.NEGATIVE_INFINITY;
        int AltMax = 1;

        double MaximoAux;
        int EstMax = 1;

        int i, j;

        System.out.println("Método Optimista");
        System.out.println("----------------");
        System.out.println();

        System.out.println("Valoracion de las alternativas");
        System.out.println();

        for (i = 0; i < Nalternativas; i++) {

            MaximoAux = Double.NEGATIVE_INFINITY;
            for (j = 0; j < Nestados; j++) {
                if (Resultado[i][j] > MaximoAux) {
                    MaximoAux = Resultado[i][j];
                    EstMax = j + 1;
                }
            }

            System.out.println("Alt. " + (i + 1) + ": Valoracion=" + MaximoAux + "  Estado=" + EstMax);

            if (MaximoAux > Maximo) {
                Maximo = MaximoAux;
                AltMax = i + 1;
            }
        }

        System.out.println();
        System.out.println("Alternativa óptima: " + AltMax + " " + Maximo);
        System.out.println();
        System.out.println();

    }

    /*******************************
     * Método de Hurwicz
     *******************************/

    public static void Hurwicz() {

        double Maximo = Double.NEGATIVE_INFINITY;
        int AltMax = 1;

        double MinimoAux;
        int EstMin = 1;
        double MaximoAux;
        int EstMax = 1;

        int i, j;
        double Alfa;
        double valorAlt;

        System.out.println("Método de Hurwicz");
        System.out.println("-------------------");
        System.out.println();

        do {
            Alfa = Control.leeReal("Valor de Alfa (coeficiente) entre 0 y 1 : ");
        } while (Alfa < 0 || Alfa > 1);


        System.out.println("Valoracion de las alternativas");
        System.out.println();

        for (i = 0; i < Nalternativas; i++) {

            MinimoAux = Double.POSITIVE_INFINITY;
            MaximoAux = Double.NEGATIVE_INFINITY;
            for (j = 0; j < Nestados; j++) {
                if (Resultado[i][j] < MinimoAux) {
                    MinimoAux = Resultado[i][j];
                    EstMin = j + 1;
                }
                if (Resultado[i][j] > MaximoAux) {
                    MaximoAux = Resultado[i][j];
                    EstMax = j + 1;
                }
            }

            valorAlt = Alfa * MinimoAux + (1 - Alfa) * MaximoAux;
            System.out.println("Alt. " + (i + 1) + ": Valoracion=" + valorAlt + "  Estado=" + EstMin);

            if (valorAlt > Maximo) {
                Maximo = valorAlt;
                AltMax = i + 1;
            }
        }

        System.out.println();
        System.out.println("Alternativa óptima: " + AltMax + " " + Maximo);
        System.out.println();
        System.out.println();

    }


    /*******************************
     *  Método de Savage  *
     *******************************/

    public static void Savage() {

        double Maximo = Double.NEGATIVE_INFINITY;
        int EstMax = 1;

        double Minimo = Double.POSITIVE_INFINITY;
        int AltMin = 1;

        int i, j;

        System.out.println("Método de Savage");
        System.out.println("------------------");
        System.out.println();

        /*
        /* Construir matriz de pérdidas relativas
        */

        double[][] PerdidaRel = new double[Nalternativas][Nestados];

        for (j = 0; j < Nestados; j++) {
            Maximo = Double.NEGATIVE_INFINITY;
            for (i = 0; i < Nalternativas; i++)
                if (Resultado[i][j] > Maximo) Maximo = Resultado[i][j];

            for (i = 0; i < Nalternativas; i++)
                PerdidaRel[i][j] = Maximo - Resultado[i][j];
        }


        /*
         ** Determinar el minimo de las mayores perdidas relativas por filas
         */

        System.out.println("Valoracion de las alternativas");
        System.out.println();

        for (i = 0; i < Nalternativas; i++) {

            Maximo = Double.NEGATIVE_INFINITY;
            for (j = 0; j < Nestados; j++) {
                if (PerdidaRel[i][j] > Maximo) {
                    Maximo = PerdidaRel[i][j];
                    EstMax = j + 1;
                }
            }

            System.out.println("Alt. " + (i + 1) + ": Valoracion=" + Maximo + "  Estado=" + EstMax);

            if (Maximo < Minimo) {
                Minimo = Maximo;
                AltMin = i + 1;
            }
        }

        System.out.println();
        System.out.println("Alternativa óptima: " + AltMin + " " + Minimo);
        System.out.println();
        System.out.println();

    }


    /*******************************
     * Método de Laplace           *
     *******************************/

    public static void Laplace() {

        double Maximo = Double.NEGATIVE_INFINITY;
        int AltMax = 1;

        int i, j;
        double Suma;
        double valorAlt;

        System.out.println("Método de Laplace ");
        System.out.println("-------------------");
        System.out.println();

        System.out.println("Valoracion de las alternativas");
        System.out.println();

        for (i = 0; i < Nalternativas; i++) {

            Suma = 0;
            for (j = 0; j < Nestados; j++) Suma += Resultado[i][j];
            valorAlt = Suma / Nestados;

            System.out.println("Alt. " + (i + 1) + ": Valoracion=" + valorAlt);

            if (valorAlt > Maximo) {
                Maximo = valorAlt;
                AltMax = i + 1;
            }
        }

        System.out.println();
        System.out.println("Alternativa óptima: " + AltMax + " " + Maximo);
        System.out.println();
        System.out.println();

    }

}

