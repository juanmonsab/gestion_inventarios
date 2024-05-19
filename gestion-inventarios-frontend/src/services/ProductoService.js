import axios from 'axios';

const baseUrl = 'http://localhost:8080';

const ProductoService = {
  obtenerProductos: async () => {
    try {
      const response = await axios.get(`${baseUrl}/productos/obtener`);
      return response.data;
    } catch (error) {
      console.error('Error al obtener productos:', error);
      throw error;
    }
  },

  agregarProducto: async (productoData) => {
    try {
      const response = await axios.post(`${baseUrl}/productos/agregar`, productoData);
      return response.data;
    } catch (error) {
      console.error('Error al agregar producto:', error);
      throw error;
    }
  },

  eliminarProducto: async (id) => {
    try {
      await axios.delete(`${baseUrl}/productos/${id}`);
    } catch (error) {
      console.error('Error al eliminar producto:', error);
      throw error;
    }
  },

  obtenerProductoPorId: async (id) => {
    try {
      const response = await axios.get(`${baseUrl}/productos/${id}`);
      return response.data;
    } catch (error) {
      console.error('Error al obtener producto por ID:', error);
      throw error;
    }
  },

  actualizarProducto: async (id, productoData) => {
    try {
      const response = await axios.put(`${baseUrl}/productos/${id}`, productoData);
      return response.data;
    } catch (error) {
      console.error('Error al actualizar producto:', error);
      throw error;
    }
  },

  obtenerProductosPorCategoria: async (categoriaId) => {
    try {
      const response = await axios.get(`${baseUrl}/productos/categoria/${categoriaId}`);
      return response.data;
    } catch (error) {
      console.error('Error al obtener productos por categoría:', error);
      throw error;
    }
  },

  consultarStockDisponible: async (id) => {
    try {
      const response = await axios.get(`${baseUrl}/productos/${id}/stock`);
      return response.data;
    } catch (error) {
      console.error('Error al consultar stock disponible:', error);
      throw error;
    }
  },
};

export default ProductoService;


