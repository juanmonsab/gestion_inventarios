-- Creating database schema for H2

-- Create table categoria
CREATE TABLE IF NOT EXISTS categoria (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(255) NOT NULL
);

-- Create table producto
CREATE TABLE IF NOT EXISTS producto (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(255) NOT NULL,
  descripcion TEXT,
  precio DOUBLE NOT NULL,
  cantidad INT NOT NULL,
  categoria_id BIGINT,
  CONSTRAINT FK_categoria FOREIGN KEY (categoria_id) REFERENCES categoria(id)
);
