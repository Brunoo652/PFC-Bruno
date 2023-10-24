-- TABLAS DE LA API

-- Tabla de jugadores
DROP TABLE IF EXISTS jugadores;
CREATE TABLE jugadores (
    id           INT AUTO_INCREMENT PRIMARY KEY,
    nombre       VARCHAR(250) NOT NULL,
    sprite       VARCHAR(250) NOT NULL,
    descripcion  VARCHAR(500),
    sexo CHAR(10) CHECK (sexo IN ('Femenino', 'Masculino')),
    afinidad VARCHAR(10) CHECK (afinidad IN ('Montaña', 'Fuego', 'Aire', 'Bosque', 'Neutro')),
    posicion     VARCHAR(15),
    favorito     BOOLEAN DEFAULT false
);

-- Tabla de clubes
DROP TABLE IF EXISTS clubes;
CREATE TABLE clubes (
    id           INT AUTO_INCREMENT PRIMARY KEY,
    nombre       VARCHAR(250) ,
    sprite       VARCHAR(250) NOT NULL,
    descripcion  VARCHAR(250),
    formacion    VARCHAR(250),
    miembros     VARCHAR(250),
    favorito     BOOLEAN DEFAULT false
);

-- Tabla de objetos
DROP TABLE IF EXISTS objetoFichajes;
CREATE TABLE objetosfichajes (
    id           INT AUTO_INCREMENT PRIMARY KEY,
    nombre       VARCHAR(250) NOT NULL,
    sprite       VARCHAR(250) NOT NULL,
    area         VARCHAR(250),
    localizacion VARCHAR(250),
    equipo       VARCHAR(250),
    favorito     BOOLEAN DEFAULT false
);

DROP TABLE IF EXISTS objetos;
CREATE TABLE objetos(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    nombre       VARCHAR(250) NOT NULL,
    detalle      VARCHAR(250),
    sprite       VARCHAR(250),
    favorito     BOOLEAN default FALSE

 );

-- Tabla de supertécnicas
DROP TABLE IF EXISTS supertecnicas;
CREATE TABLE `supertecnicas` (
`nombreIngles`     varchar(250) NOT NULL,
`id`               int NOT NULL AUTO_INCREMENT,
`nombre`           varchar(250) NOT NULL,
`sprite`           varchar(250) NOT NULL,
`tipo`             varchar(250) DEFAULT NULL,
`elemento`         varchar(250) DEFAULT NULL,
`favorito`         BOOLEAN DEFAULT FALSE,
PRIMARY KEY (`id`)
)

-- Tabla de usuarios
DROP TABLE IF EXISTS usuarios;
CREATE TABLE usuarios (
 id       INT AUTO_INCREMENT PRIMARY KEY,
 email    VARCHAR(250) NOT NULL,
 password VARCHAR(20) NOT NULL,
 token    VARCHAR(25) Not null
);

-- Tabla de favoritos
DROP TABLE IF EXISTS favoritos;
CREATE TABLE favoritos (
    id            INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id    INT NOT NULL, -- ID del usuario que marca como favorito
    elemento_id   INT NOT NULL, -- ID del elemento marcado como favorito
    tipo_elemento VARCHAR(50) NOT NULL, -- Puede ser 'jugadores', 'club', 'objetos', 'supertecnicas', u otro si es necesario
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    FOREIGN KEY (elemento_id) REFERENCES jugadores(id) ON DELETE CASCADE, -- Establecer referencias a las tablas existentes
    FOREIGN KEY (elemento_id) REFERENCES clubes(id) ON DELETE CASCADE,
    FOREIGN KEY (elemento_id) REFERENCES objetos(id) ON DELETE CASCADE,
    FOREIGN KEY (elemento_id) REFERENCES supertecnicas(id) ON DELETE CASCADE,
    UNIQUE (usuario_id, elemento_id, tipo_elemento) -- Asegurar que un usuario solo pueda marcar un elemento una vez
);