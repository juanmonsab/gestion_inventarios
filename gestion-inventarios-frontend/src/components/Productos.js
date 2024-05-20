import React, { useState, useEffect } from 'react';
import ProductoService from '../services/ProductoService';
import CategoriaService from '../services/CategoriaService';

const Productos = () => {
  const [productos, setProductos] = useState([]);
  const [nombre, setNombre] = useState('');
  const [descripcion, setDescripcion] = useState('');
  const [precio, setPrecio] = useState(0);
  const [cantidad, setCantidad] = useState(0);
  const [categoriaId, setCategoriaId] = useState('');
  const [productoId, setProductoId] = useState(null);
  const [mensaje, setMensaje] = useState('');
  const [categorias, setCategorias] = useState([]);
  const [mostrarFormulario, setMostrarFormulario] = useState(false);
  const [producto, setProducto] = useState(null);
  const [idProducto, setIdProducto] = useState('');

  useEffect(() => {
    const obtenerProductos = async () => {
      try {
        const data = await ProductoService.obtenerProductos();
        setProductos(data);
      } catch (error) {
        console.error('Error al obtener los productos:', error);
      }
    };

    const obtenerCategorias = async () => {
      try {
        const data = await CategoriaService.obtenerCategorias();
        setCategorias(data);
      } catch (error) {
        console.error('Error al obtener las categorías:', error);
      }
    };

    obtenerProductos();
    obtenerCategorias();
  }, []);

  const handleEliminarProducto = async (id) => {
    try {
      await ProductoService.eliminarProducto(id);
      setProductos(productos.filter((producto) => producto.id !== id));
      console.log('Producto eliminado exitosamente');
    } catch (error) {
      console.error('Error al eliminar el producto:', error);
    }
  };

  const handleActualizarProducto = async (id) => {
    try {
      const producto = productos.find((p) => p.id === id);
      if (producto) {
        setProductoId(producto.id);
        setNombre(producto.nombre);
        setDescripcion(producto.descripcion);
        setPrecio(producto.precio);
        setCantidad(producto.cantidad);
        setCategoriaId(producto.categoria.id.toString());
      }
    } catch (error) {
      console.error('Error al obtener el producto para actualizar:', error);
    }
  };

  const handleConsultarStock = async (id) => {
    try {
      const stock = await ProductoService.consultarStockDisponible(id);
      alert(`Stock disponible para el producto ID ${id}: ${stock}`);
    } catch (error) {
      console.error('Error al consultar el stock:', error);
    }
  };

  const handleAgregarProducto = async (e) => {
    e.preventDefault();
    try {
      const productoData = { nombre, descripcion, precio, cantidad, categoria: { id: parseInt(categoriaId) } };
      await ProductoService.agregarProducto(productoData);
      setMensaje('Producto agregado correctamente');
      setTimeout(() => {
        setMensaje('');
      }, 3000);
      setNombre('');
      setDescripcion('');
      setPrecio(0);
      setCantidad(0);
      setCategoriaId('');
      setMostrarFormulario(false);
    } catch (error) {
      console.error('Error al agregar el producto:', error);
      setMensaje('Error al agregar producto');
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const productoData = { nombre, descripcion, precio, cantidad, categoria: { id: parseInt(categoriaId) } };
      await ProductoService.actualizarProducto(productoId, productoData);
      setMensaje('Producto actualizado correctamente');
      setTimeout(() => {
        setMensaje('');
      }, 3000);
    } catch (error) {
      console.error('Error al actualizar producto:', error);
      setMensaje('Error al actualizar producto');
    }
  };

const handleConsultarProducto = async () => {
  try {
    const productoObtenido = await ProductoService.obtenerProductoPorId(idProducto);
    setProducto(productoObtenido);
    setIdProducto('');
  } catch (error) {
    console.error('Error al consultar el producto:', error);
  }
};

  return (
    <div>
      <h1>Productos</h1>
      {mensaje && <div className="mensaje">{mensaje}</div>}
      <button onClick={() => setMostrarFormulario(!mostrarFormulario)}>
        {mostrarFormulario ? 'Ocultar Formulario' : 'Agregar Producto'}
      </button>
      {mostrarFormulario && (
        <form onSubmit={handleAgregarProducto}>
          <input type="text" placeholder="Nombre" value={nombre} onChange={(e) => setNombre(e.target.value)} />
          <input type="text" placeholder="Descripción" value={descripcion} onChange={(e) => setDescripcion(e.target.value)} />
          <input type="number" placeholder="Precio" value={precio} onChange={(e) => setPrecio(e.target.value)} />
          <input type="number" placeholder="Cantidad" value={cantidad} onChange={(e) => setCantidad(e.target.value)} />
          <select value={categoriaId} onChange={(e) => setCategoriaId(e.target.value)}>
            <option value="">Seleccionar categoría</option>
            {categorias.map((categoria) => (
              <option key={categoria.id} value={categoria.id}>
                {categoria.nombre}
              </option>
            ))}
          </select>
          <button type="submit">Agregar</button>
        </form>
      )}
    <div>
      <input
        type="text"
        value={idProducto}
        onChange={(e) => setIdProducto(e.target.value)}
        placeholder="Ingrese ID del producto"
      />
      <button onClick={handleConsultarProducto}>
        Consultar Producto
      </button>
        {producto && (
          <div>
            <h2>Producto</h2>
            <p>ID: {producto.id}</p>
            <p>Nombre: {producto.nombre}</p>
            <p>Descripción: {producto.descripcion}</p>
            <p>Precio: {producto.precio}</p>
            <p>Cantidad disponible: {producto.cantidad}</p>
            <p>Categoría: {producto.categoria.nombre}</p>
          </div>
        )}
    </div>
      <ul>
        {productos.length > 0 ? (
          productos.map((producto) => (
            <li key={producto.id}>
              <strong>ID:</strong> {producto.id}<br />
              <strong>Nombre:</strong> {producto.nombre}<br />
              <strong>Descripción:</strong> {producto.descripcion}<br />
              <strong>Precio:</strong> {producto.precio}<br />
              <strong>Cantidad disponible:</strong> {producto.cantidad}<br />
              <button className="btn-oscuro" onClick={() => handleActualizarProducto(producto.id)}>Actualizar</button>
              <button className="btn-oscuro" onClick={() => handleConsultarStock(producto.id)}>Consultar Stock</button>
              <button className="btn-eliminar" onClick={() => handleEliminarProducto(producto.id)}>Eliminar</button>
            </li>
          ))
        ) : (
          <li>No hay productos disponibles</li>
        )}
      </ul>
      {productoId && (
        <form onSubmit={handleSubmit}>
          <input type="text" value={nombre} onChange={(e) => setNombre(e.target.value)} />
          <input type="text" value={descripcion} onChange={(e) => setDescripcion(e.target.value)} />
          <input type="number" value={precio} onChange={(e) => setPrecio(e.target.value)} />
          <input type="number" value={cantidad} onChange={(e) => setCantidad(e.target.value)} />
          <select value={categoriaId} onChange={(e) => setCategoriaId(e.target.value)}>
            <option value="">Seleccionar categoría</option>
            {categorias.map((categoria) => (
              <option key={categoria.id} value={categoria.id}>
                {categoria.nombre}
              </option>
            ))}
          </select>
          <button type="submit">Actualizar</button>
        </form>
      )}
    </div>
  );
};

export default Productos;

