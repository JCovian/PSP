## Ejemplo de Sincronización de flujo (stream)
Basado en el ejemplo de las diapositivas

En la clase Fichero, la función insertarTexto() realiza las siguientes operaciones
1. Abre un flujo de escritura en un fichero
2. Escribe en el flujo
3. Cierra el flujo

Si un hilo intenta ejecutar el paso 2 justo a continuación de que otro hilo ejecute el paso 3, se provoca una IOException, al tratar de escribir en un flujo cerrado

Podéis probarlo comentando el modificador _synchronized_ en la declaración de la función.
