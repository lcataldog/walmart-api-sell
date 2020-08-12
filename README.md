Para levantar el proyecto se debe:

Clonar el repositorio.

```
git clone https://github.com/lcataldog/walmart-api-sell.git
```

Ahora debes acceder a tu proyecto y correr aplicacion:

```
cd ruta_del_proyecto
make run
```

# Tareas del Proyecto

Las tareas que se puede correr en proyecto se encuentran en la carpeta makefiles

* **run**: Levanta la aplicacion 

* **unit-test**: Ejecuta pruebas unitarias.

* **integration-test**: Ejecuta pruebas integracion.

* **docker-run**: Levanta la aplicacion desde un contenedor docker 

* **docker-unit-test**: Corre unit test desde un contenedor docker

* **docker-integration-test**: Corre integration test desde un contenedor docker


# Estrategia del desarrollo

* Se ocuparon patrones de diseño (factory y strategy).

* Strategy nos permite la seleccion del comportamiento de un algoritmo en tiempo de ejecución (tipo de busqueda).

* Factory debido a que cuando se implementa strategy, siempre se requiere codigo para selecccionar la instancia adecuada y en lugar de inscrustar este codigo directamente en la clase que llama al algoritmo, es mejor enfoque desacoplar este codigo en una clase factory.

* En Produccion nos conectamos a Mongo Db Atlas.

* Para pruebas de integracion embebimos mongo db, a traves de la libreria de.flapdoodle.embed.


# Supuestos del desarrollo

* Se asumio que el id del producto siempre es int, para poder diferenciar la estrategia de busqueda.

* Se asumio que para busquedas por descripcion, brand que fueran menos a 4 caracteres no se aplicaba el descuento, pero si debia entregar listado de productos.


