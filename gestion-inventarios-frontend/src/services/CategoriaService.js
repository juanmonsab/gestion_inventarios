import axios from 'axios';

const baseUrl = 'http://localhost:8080'; // Cambia el puerto y la URL base según tu configuración del backend

const CategoriaService = {
  obtenerCategorias: async () => {
    const response = await axios.get(`${baseUrl}/categorias/obtener`);
    return response.data;
  },
  agregarCategoria: async (categoria) => {
    const response = await axios.post(`${baseUrl}/categorias/agregar`, categoria);
    return response.data;
  },
  eliminarCategoria: async (id) => {
    const response = await axios.delete(`${baseUrl}/categorias/eliminar/${id}`);
    return response.data;
  },
};

export default CategoriaService;

