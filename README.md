### Práctica 3 - Desarrollo de software basado en componentes - Master II

**Luis Alberto Segura Delgado**

![](https://img.shields.io/badge/license-GPLv2-blue.svg)

En este práctica se pedía crear un servicio RESTful para gestionar contenidos (incluyendo contenido multimedia), basado en Jersey, librería para Java que nos facilitará el trabajo.

Se ha desarrollado un servicio web para gestionar las entradas de un blog. Como se puede ver en el código, se hace uso de los diferentes métodos HTTP para listar, crear o eliminar los contenidos.

Por un lado tenemos la clase "Entrada", que modela la información que vamos a guardar (id, título, contenido e imagen).

Por otro lado, tenemos las clases "EntradaRecurso" y "EntradasRecurso". La primera gestiona la información relativa a una entrada (crea, devuelve la información y elimina), mientras que la segunda nos sirve para listar todas las entradas del sistema mediante una petición GET (/entradas). Para solicitar la información de una entrada, tendremos que navegar a la url "/rest/entrada?entrada=ID", donde **ID** se corresponde con el identificador de la entrada que queramos ver.

Se han implementado dos formas de crear una nueva entrada, una para realizar una petición PUT (que no permite añadir la imagen) y otra por POST (que si permite enviar la imagen a partir de un formulario MULTIPART).


Para probar la aplicación, solamente debemos acceder a dirección raíz y veremos una interfaz web sencilla para visualizar, crear, eliminar y modificar las diferentes entradas. La interfaz web esta programada con JavaScript (con ayuda de la librería jQuery) para realizar las peticiones al servicio RESTful.

Una vez importado el proyecto a Eclipse e iniciado el servidor Tomcat, solamente habrá que navegar a la dirección: http://localhost:8080/Practica3_LuisAlbertoSeguraDelgado/ para visualizar la aplicación web. El servicio RESTful se encuentra en http://localhost:8080/Practica3_LuisAlbertoSeguraDelgado/rest/

Si el servicio web no se encuentra en esta dirección, quizás se encuentre en: http://localhost:8080/com.blog (siguiendo la estructura de paquetes). Si no se encuentra en ninguna de esas direcciones, quizás se encuentre en alguna dirección conbinación de las anteriores: http://localhost:8080/Practica3_LuisAlbertoSeguraDelgado/com.blog ó http://localhost:8080/com.blog/Practica3_LuisAlbertoSeguraDelgado/. Si no esta en ninguna de esas, no se donde estará, porque esto pone las cosas donde quiere..


# Licencia

Este software esta publicado bajo la licencia GNU GENERAL PUBLIC LICENSE Version 2.