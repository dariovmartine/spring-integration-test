# spring-integration-test

La app jms-producer genera mensajes 
La app spring integration los consume y por cada mensaje le pega al ws ws-soap


Para ejecutarlo :

Iniciar la app del ws (web-service-soap/)

Iniciar la app generadora de los mensajes  (jms-producer/)

Iniciar la app SI (spring-integration/)

     Para cambiar de implementacion modificar el archivo SpringIntegrationApp.java con el xml deseado:
     
            Apache CXF NIO: applicationContext-async-cxf-nio.xml
            WS CLiente usando REST: applicationContext-async-ws-usingrest.xml
            Implementacion directa: applicationContext-syncronico.xml
            Usando pool de threads: applicationContext-thread-pool.xml

Volver a la consola de jms-producer y presionar ENTER para que inyecte el lote de mensajes.


