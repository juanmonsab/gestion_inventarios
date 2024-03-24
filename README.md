# Gestión de Inventarios

Este proyecto de gestión de inventarios tiene como objetivo proporcionar una plataforma simple y eficiente para administrar los productos y existencias de una empresa, incluyendo la gestión de categorías.

## Equipo

- Juan Montes Sabogal
- Esteban Bernal Cortes
- Camilo Arenas Ortiz

## Funcionalidades

- **Agregar Productos:** Permite agregar nuevos productos al inventario, incluyendo información como nombre, descripción, precio, cantidad, etc.
- **Editar Productos:** Permite modificar la información de productos existentes, como su nombre, descripción, precio, cantidad, etc.
- **Eliminar Productos:** Permite eliminar productos del inventario.
- **Buscar Productos:** Permite buscar productos por nombre u otros criterios.
- **Control de Existencias:** Permite llevar un control preciso de las existencias de cada producto, actualizando automáticamente la cantidad disponible.
- **Gestión de Categorías:** Permite administrar las categorías de productos para una mejor organización y clasificación.

## Cómo ejecutar el proyecto

1. Clona este repositorio en tu máquina local:

   ```bash
   git clone https://github.com/juanmonsab/gestion_inventarios.git

2. Ejecuta el archivo build.gradle
3. Ejecuta el archivo GestionInventariosApplication
4. Para realizar peticiones puedes utilizar una herramienta como Postman. Aquí hay un ejemplo de cómo podrías hacerlo:

   **Agregar un Producto:**
   
   - **Método:** POST
   - **URL:** `http://localhost:8080/productos`
   - **Body (JSON):**
     ```json
     {
        "nombre": "Aguardiente",
        "descripcion": "Aguardiente Antioqueño 750ml",
        "precio": 50000,
        "cantidad": 200,
        "categoria": {
            "id": 1,
            "nombre": "Licores"
        }
     }
     ```
   
   Este ejemplo muestra cómo agregar un nuevo producto al inventario, proporcionando detalles como el nombre, la descripción, el precio, la cantidad disponible y la categoría a la que pertenece el producto.
