drop database if exists tiendaonline;
create database tiendaonline;
use tiendaonline;

DROP TABLE IF EXISTS 'cliente';
CREATE TABLE 'cliente' (
  'idCliente' bigint(20) NOT NULL AUTO_INCREMENT,
  'apellidos' varchar(255) DEFAULT NULL,
  'banco' varchar(255) DEFAULT NULL,
  'codigoSeguridad' int(11) DEFAULT NULL,
  'direccionEnvio' varchar(255) DEFAULT NULL,
  'direccionFacturacion' varchar(255) DEFAULT NULL,
  'email' varchar(255) DEFAULT NULL,
  'fechaNacimniento' datetime DEFAULT NULL,
  'nombre' varchar(255) DEFAULT NULL,
  'nombreUsuario' varchar(255) DEFAULT NULL,
  'numTarjeta' bigint(20) DEFAULT NULL,
  'password' varchar(255) DEFAULT NULL,
  'titular' varchar(255) DEFAULT NULL,
  PRIMARY KEY ('idCliente')
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS 'producto';
CREATE TABLE 'producto' (
  'idProducto' bigint(20) NOT NULL AUTO_INCREMENT,
  'nombreProducto' varchar(255) NOT NULL,
  'precio' float NOT NULL,
  'stock' int(11) NOT NULL,
  'categoria' varchar(255) NOT NULL,
  'descripcion' varchar(255) NOT NULL,
  PRIMARY KEY ('idProducto'),
  CONSTRAINT 'fk_categiria' FOREIGN KEY ('idCategoria') REFERENCES 'categoria' ('idCategoria')
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS 'categoria';
CREATE TABLE 'categoria' (
  'idCategoria' bigint(20) NOT NULL AUTO_INCREMENT,
  'nombre' varchar(255) NOT NULL,
  'descripcion' varchar(255) NOT NULL,
  PRIMARY KEY ('idCategoria')
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS 'compra';
CREATE TABLE 'compra' (
  'idCompra' bigint(20) NOT NULL AUTO_INCREMENT,
  'fecha' datetime NOT NULL,
  'precioTotal' float NOT NULL,
  'idProducto' bigint(20) NOT NULL,
  'idCliente' bigint(20) NOT NULL,
  PRIMARY KEY ('idCompra'),
CONSTRAINT 'fk_cliente' FOREIGN KEY ('idCliente') REFERENCES 'cliente' ('idCliente'),
CONSTRAINT 'fk_producto' FOREIGN KEY ('idProducto') REFERENCES 'producto' ('idProducto')
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS 'venta';
CREATE TABLE 'venta' (
  'idVenta' bigint(20) NOT NULL AUTO_INCREMENT,
  'unidades' int(11) NOT NULL,
  'idProducto' bigint(20) NOT NULL,
  'idCliente' bigint(20) NOT NULL,
  PRIMARY KEY ('idVenta'),
CONSTRAINT 'fk_cliente' FOREIGN KEY ('idCliente') REFERENCES 'cliente' ('idCliente'),
CONSTRAINT 'fk_producto' FOREIGN KEY ('idProducto') REFERENCES 'producto' ('idProducto')
CONSTRAINT 'fk_compra' FOREIGN KEY ('idCompra') REFERENCES 'compra' ('idcompra')
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS 'compra_producto';
CREATE TABLE 'compra_producto' (
  'idProducto' bigint(20) NOT NULL,
  'idCompra' bigint(20) NOT NULL,
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS 'pregunta';
CREATE TABLE 'pregunta' (
  'idPregunta' bigint(20) NOT NULL AUTO_INCREMENT,
  'textoPregunta' varchar(255) NOT NULL,
  'idProducto' bigint(20) NOT NULL,
  PRIMARY KEY ('idPregunta'),
CONSTRAINT 'fk_producto' FOREIGN KEY ('idProducto') REFERENCES 'producto' ('idProducto')
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS 'respuesta';
CREATE TABLE 'respuesta' (
  'idRespuesta' bigint(20) NOT NULL AUTO_INCREMENT,
  'textoRespuesta' varchar(255) NOT NULL,
  'idPregunta' bigint(20) NOT NULL,
  PRIMARY KEY ('idRespuesta'),
CONSTRAINT 'fk_pregunta' FOREIGN KEY ('idPregunta') REFERENCES 'preguntas' ('idPregunta')
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS 'rol';
CREATE TABLE 'rol' (
  'idRol' int NOT NULL AUTO_INCREMENT,
  'nombreRol' varchar(255) NOT NULL,
  PRIMARY KEY ('idRol')
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS 'cliente_rol';
CREATE TABLE 'cliente_rol' (
  'idCliente' bigint NOT NULL,
  'idRol' bigint NOT NULL,
  PRIMARY KEY ('idCliente','idRol'),
  KEY 'fk_rolrol_idx' ('idRol'),
  CONSTRAINT 'fk_clienterol' FOREIGN KEY ('idCliente') REFERENCES 'cliente' ('idCliente') ON DELETE CASCADE,
  CONSTRAINT 'fk_rolrol' FOREIGN KEY ('idRol') REFERENCES 'rol' ('idRol')
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
