## Ejemplo de métodos sincronizados, pero sin notificaciones

Típico ejemplo Productor/Consumidor: 
 - Tenemos un hilo Productor, un hilo Consumidor, y un recurso compartido: una Cola
 - El Productor guarda un número en la Cola, y el Consumidor extrae y muestra ese número. El objetivo es que se alternen perfectamente. 
 - Para dificultar la alternancia perfecta, hacemos que el Productor duerma un poco en cada iteración. Así, el Consumidor será más rápido. 
 - Vemos que no basta con sincronizar los métodos get y put.
   - Eso evita que mientras un hilo produce, el otro escriba, y viceversa.
   - Pero **NO evita** que el consumidor consuma dos o más veces consecutivas, sin dar tiempo al productor o a producir (o viceversa).
   - Incluso sin el sleep() en Productor, no se produce una alternancia correcta. 
   - Tenemos que usar otros mecanismos: wait() y notify() o notifyAll(). Podéis verlos en el módulo ProductorConsumidorConEsperas

