/**
*
* @author Jose A. Covian
*
*/

##UNIDAD 1
    Ejercicio2 --> Numeros primos (una app calcula primos o no, la otra comunica con un hilo)
    Mensajes -->   - Hijo (metes tic sale toc, metes marco sale polo)
                   - Hijo2 visualiza texto en mayuscula
                   - HijoCaracteres 
                   - HijoProfersor (solución del profesor)
                   - Padre (llama a hijo)
                   - Padre2 (llama a Hijo2)
                   - PadreCaracteres (Envia una cadena escrita literalmente en el código al proceso               HijoCaracteres y lee su salida estándard)
                   - PadreProfesor (solucion del profesor Tic Toc Eco)                 
    practica1 -->  AppMenu (abre diferentes programas con Rutime y rt.exec)
    practica3 -->  Lee por entrada standard caracter a caracter hasta encontrar * y muestra todo lo leido
    procesos -->   Varios ejemplos con procesos (abrir aplicacines, ver info de los procesos, etc)
    

##UNIDAD 2
    CuentaPrimosHiloPersonalizable --> Cuenta primos con hilos (personalizable) y nos dice num de primos
    Ejemplos --> EjemplosSincronizacion --> - ProductorConsumidorConEsperas
                                            - ProductorConsumidorSinEsperas
                                            - SincronizacionContador
                                            - SincronizacionFicheroSaludo
    PanaderiaHilos -->  - Panadero y vendoedor
                        - Cuando se fabrica una barra se deposita en el mostrador
                        - En el mostrador solo puede haber x barras de pan
    PanaderiaSemaforos --> El anterior con semáforos
    Practica2.1 --> - Programa usando 3 clases (extends Thread, implements Runnable y mainClass)
                    - El programa principal creará 3 valores aleatorios y 3 hilos que cuenten desde 1 al valor              generado
                    - Cada hilo se genera con uno de los 3 metodos vistos (Thread, runnable y lambda)
                    - Asignar nombre descriptivo a cada hilo
                    - Guardar los 3 hilos en un array de hilos de tamaño 3 lanzados en bucle
                    - El programa principal espera a que terminen los 3 hilos y muestra un mensaje al finalizar
    Practica2.2 --> - Contador de primos multihilo
    Tarea25 --> - Crear programa Java usando hilos (pin, pan, pun)
    #Tarea26 --> - El anterior con semáforos