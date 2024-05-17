import axios from 'axios';

const instance = axios.create({
  baseURL: 'http://localhost:8080', // Reemplaza '8080' por el puerto de tu backend
});

export default instance;
