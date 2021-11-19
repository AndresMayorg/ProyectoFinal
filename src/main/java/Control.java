

public class Control {
    /**
     * Imprime un mensaje en el dispositivo
     * estandar de salida sin salto de linea
     * @param mensaje mensaje que se va a imprimir
     */
    public static void imprimeMensaje
    (String mensaje) {
        System.out.print(mensaje + " ");
        System.out.flush();
    }

    /**
     * Lee una cadena de caracteres (string) desde
     * el terminal(desde el dispositivo estandar
     * de entrada).  La cadena se extiende hasta
     * el siguiente salto de linea
     * @return la cadena leida (sin salto de linea)
     */

    public static String leeCadena() {
        int caracter;
        String cadena = "";
        boolean fin = false;
        while (!fin) {
            try {
                caracter = System.in.read();
                if (caracter < 0 ||
                        (char)caracter == '\n')
                    fin = true;
                else
                if (!Character.isISOControl((char)caracter))
                    cadena += (char)caracter;
            } catch(java.io.IOException e) {
                fin = true;
            }
        }
        return cadena;
    }

    /**
     * Lee un número entero (int) desde el terminal
     * (dispositivo estandar de entrada). El número
     * debe finalizar con un salto de línea.
     * @param mensaje mensaje que se imprime
     * @return el valor numérico leido (como int)
     * @exception NumberFormatException si se
     * introduce un valor incorrecto
     */

    static int leeEntero(String mensaje) {
        while(true) {
            imprimeMensaje(mensaje);
            try {
                return Integer.parseInt(
                        leeCadena().trim());
            } catch(NumberFormatException e) {
                System.out.println
                        ("");
            }
        }
    }


    /**
     * Lee un número real (double) desde el terminal
     * (dispositivo estandar de entrada). El número
     * debe finalizar con un salto de línea.
     * @param mensaje mensaje que se imprime
     * @return el valor numérico leido (como double)
     * @exception NumberFormatException si se
     * introduce un valor incorrecto
     */

    static double leeReal(String mensaje) {
        while(true) {
            imprimeMensaje(mensaje);
            try {
                return Double.parseDouble(
                        leeCadena().trim());
            } catch(NumberFormatException e) {
                System.out.println
                        ("");
            }
        }
    }

}

