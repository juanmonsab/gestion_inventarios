import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import Categorias from './components/Categorias';
import Productos from './components/Productos';
import Inicio from './components/Inicio';
import NotFound from './components/NotFound';
import './App.css';  // Importa el archivo CSS

const App = () => {
  return (
    <Router>
      <div>
        <nav>
          <ul>
            <li>
              <Link to="/">Inicio</Link>
            </li>
            <li>
              <Link to="/categorias">Categorías</Link>
            </li>
            <li>
              <Link to="/productos">Productos</Link>
            </li>
          </ul>
        </nav>

        <Routes>
          <Route path="/" element={<Inicio />} />
          <Route path="/categorias" element={<Categorias />} />
          <Route path="/productos" element={<Productos />} />
          <Route path="*" element={<NotFound />} />
        </Routes>
      </div>
    </Router>
  );
};

export default App;
