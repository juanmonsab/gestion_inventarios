import axios from 'axios';

const baseUrl = 'http://localhost:8080'; // Cambia el puerto si es diferente

const CategoriaService = {
  obtenerCategorias: async () => {
    const response = await axios.get(`${baseUrl}/categorias/obtener`);
    return response.data;
  },
  agregarCategoria: async (categoria) => {
    const response = await axios.post(`${baseUrl}/categorias/agregar`, categoria);
    return response.data;
  },
};

const ProductoService = {
  obtenerProducto: async (id) => {
    const response = await axios.get(`${baseUrl}/productos/${id}`);
    return response.data;
  },
  agregarProducto: async (producto) => {
    const response = await axios.post(`${baseUrl}/productos/agregar`, producto);
    return response.data;
  },
  actualizarProducto: async (id, producto) => {
    const response = await axios.put(`${baseUrl}/productos/${id}`, producto);
    return response.data;
  },
  eliminarProducto: async (id) => {
    await axios.delete(`${baseUrl}/productos/${id}`);
  },
};

export { CategoriaService, ProductoService };
