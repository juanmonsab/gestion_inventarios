import React, { useState, useEffect } from 'react';
import CategoriaService from '../services/CategoriaService';
import ProductoService from '../services/ProductoService';

const Categorias = () => {
  const [categorias, setCategorias] = useState([]);
  const [nombreCategoria, setNombreCategoria] = useState('');
  const [mensaje, setMensaje] = useState('');
  const [categoriaIdConsulta, setCategoriaIdConsulta] = useState('');
  const [productosCategoria, setProductosCategoria] = useState([]);
  const [categoriasConId, setCategoriasConId] = useState([]);

  useEffect(() => {
    const obtenerCategorias = async () => {
      try {
        const data = await CategoriaService.obtenerCategorias();
        setCategorias(data);
        setCategoriasConId(data.map(cat => ({ ...cat, nombre: `${cat.id}: ${cat.nombre}` })));
      } catch (error) {
        console.error('Error al obtener las categorías:', error);
      }
    };

    obtenerCategorias();
  }, []);

  const handleEliminarCategoria = async (id) => {
    try {
      await CategoriaService.eliminarCategoria(id);
      setCategorias(categorias.filter((categoria) => categoria.id !== id));
      setCategoriasConId(categoriasConId.filter((categoria) => categoria.id !== id));
      setMensaje('Categoría eliminada correctamente');
      setTimeout(() => {
        setMensaje('');
      }, 3000);
    } catch (error) {
      console.error('Error al eliminar la categoría:', error);
      setMensaje('Error al eliminar la categoría');
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await CategoriaService.agregarCategoria({ nombre: nombreCategoria });
      setMensaje('Categoría agregada correctamente');
      setTimeout(() => {
        setMensaje('');
      }, 3000);
      setNombreCategoria('');
      const data = await CategoriaService.obtenerCategorias();
      setCategorias(data);
      setCategoriasConId(data.map(cat => ({ ...cat, nombre: `${cat.id}: ${cat.nombre}` })));
    } catch (error) {
      console.error('Error al agregar la categoría:', error);
      setMensaje('Error al agregar la categoría');
    }
  };

  const handleConsultarProductosCategoria = async (e) => {
    e.preventDefault();
    try {
      const productos = await ProductoService.obtenerProductosPorCategoria(categoriaIdConsulta);
      setProductosCategoria(productos);
      setMensaje('');
    } catch (error) {
      console.error('Error al obtener productos por categoría:', error);
      setProductosCategoria([]);
      setMensaje('Error al obtener productos por categoría');
    }
  };

  return (
    <div>
      <h1>Categorías</h1>
      {mensaje && <div className="mensaje">{mensaje}</div>}
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Nombre de la categoría"
          value={nombreCategoria}
          onChange={(e) => setNombreCategoria(e.target.value)}
        />
        <button type="submit">Agregar</button>
      </form>
      <ul>
        {categoriasConId.length > 0 ? (
          categoriasConId.map((categoria) => (
            <li key={categoria.id}>
              {categoria.nombre}
              <button onClick={() => handleEliminarCategoria(categoria.id)}>Eliminar</button>
            </li>
          ))
        ) : (
          <li>No hay categorías disponibles</li>
        )}
      </ul>
      <form onSubmit={handleConsultarProductosCategoria}>
        <input
          type="text"
          placeholder="ID de la categoría"
          value={categoriaIdConsulta}
          onChange={(e) => setCategoriaIdConsulta(e.target.value)}
        />
        <button type="submit">Consultar Productos</button>
      </form>
      <ul>
        {productosCategoria.length > 0 ? (
          productosCategoria.map((producto) => (
            <li key={producto.id}>
              <strong>ID:</strong> {producto.id}<br />
              <strong>Nombre:</strong> {producto.nombre}<br />
              <strong>Descripción:</strong> {producto.descripcion}<br />
              <strong>Precio:</strong> {producto.precio}<br />
              <strong>Cantidad disponible:</strong> {producto.cantidad}<br />
            </li>
          ))
        ) : (
          <li>No hay existencias disponibles en esta categoría</li>
        )}
      </ul>
    </div>
  );
};

export default Categorias;





