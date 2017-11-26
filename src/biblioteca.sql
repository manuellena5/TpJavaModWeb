# SQL Manager 2005 Lite for MySQL 3.7.0.1
# ---------------------------------------
# Host     : localhost
# Port     : 3306
# Database : biblioteca


SET FOREIGN_KEY_CHECKS=0;

DROP DATABASE IF EXISTS `biblioteca`;

CREATE DATABASE `biblioteca`
    CHARACTER SET 'latin1'
    COLLATE 'latin1_swedish_ci';

#
# Structure for the `categorias` table : 
#

CREATE TABLE `categorias` (
  `id_categoria` int(11) NOT NULL auto_increment,
  `descripcion` varchar(50) default NULL,
  PRIMARY KEY  (`id_categoria`),
  UNIQUE KEY `id_categoria` (`id_categoria`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `tipo_elementos` table : 
#

CREATE TABLE `tipo_elementos` (
  `id_tipoelemento` int(11) NOT NULL auto_increment,
  `nombre` varchar(50) default NULL,
  `cantMaxReservasPend` int(11) default NULL,
  PRIMARY KEY  (`id_tipoelemento`),
  UNIQUE KEY `id_tipoelemento` (`id_tipoelemento`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `elementos` table : 
#

CREATE TABLE `elementos` (
  `id_elemento` int(11) NOT NULL auto_increment,
  `nombre` varchar(50) default NULL,
  `stock` int(11) default NULL,
  `autor` varchar(50) default NULL,
  `genero` varchar(50) default NULL,
  `descripcion` varchar(200) default NULL,
  `id_tipoelemento` int(11) default NULL,
  PRIMARY KEY  (`id_elemento`),
  UNIQUE KEY `id_elemento` (`id_elemento`),
  KEY `id_tipoelemento` (`id_tipoelemento`),
  CONSTRAINT `elementos_tipoelemento_fk` FOREIGN KEY (`id_tipoelemento`) REFERENCES `tipo_elementos` (`id_tipoelemento`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `personas` table : 
#

CREATE TABLE `personas` (
  `id_persona` int(11) NOT NULL auto_increment,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `dni` varchar(50) default NULL,
  `usuario` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `estado` tinyint(1) default NULL,
  `id_categoria` int(11) default NULL,
  PRIMARY KEY  (`id_persona`),
  UNIQUE KEY `id_persona` (`id_persona`),
  KEY `id_categoria` (`id_categoria`),
  CONSTRAINT `personas_categoria_fk` FOREIGN KEY (`id_categoria`) REFERENCES `categorias` (`id_categoria`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `reservas` table : 
#

CREATE TABLE `reservas` (
  `id_elemento` int(11) NOT NULL,
  `id_persona` int(11) NOT NULL,
  `fecha_registro` date NOT NULL,
  `fecha_inicio` date default NULL,
  `fecha_fin` date default NULL,
  `detalle` varchar(50) default NULL,
  `estado` varchar(50) default NULL,
  PRIMARY KEY  (`id_elemento`,`id_persona`,`fecha_registro`),
  KEY `id_elemento` (`id_elemento`),
  KEY `id_persona` (`id_persona`),
  CONSTRAINT `reservas_persona_fk` FOREIGN KEY (`id_persona`) REFERENCES `personas` (`id_persona`) ON DELETE CASCADE,
  CONSTRAINT `reservas_elemento_fk` FOREIGN KEY (`id_elemento`) REFERENCES `elementos` (`id_elemento`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for the `categorias` table  (LIMIT 0,500)
#

INSERT INTO `categorias` (`id_categoria`, `descripcion`) VALUES 
  (1,'Usuario'),
  (2,'Administrador'),
  (3,'Encargado');

COMMIT;

#
# Data for the `tipo_elementos` table  (LIMIT 0,500)
#

INSERT INTO `tipo_elementos` (`id_tipoelemento`, `nombre`, `cantMaxReservasPend`) VALUES 
  (1,'Libro',2),
  (2,'Revistas',3),
  (3,'Peliculas',3);

COMMIT;

#
# Data for the `elementos` table  (LIMIT 0,500)
#

INSERT INTO `elementos` (`id_elemento`, `nombre`, `stock`, `autor`, `genero`, `descripcion`, `id_tipoelemento`) VALUES 
  (1,'Harry Potter y la Piedra Filosofal',1,'J K Rowling','Fantasia','Harry Potter ',1),
  (2,'Don Quijote de la mancha',1,'Miguel Cervantes','Novela Española','Don Quijote de la Mancha? es una novela escrita por el español Miguel de Cervantes Saavedra.',1),
  (5,'Descubrimiento America',2,'Nat Geo','Historia','Sin descripcion',2),
  (6,'Papeles en el viento',1,'Eduardo Sacheri','Novela','Sin descripcion',1),
  (7,'El señor de los Anillos',1,'J R R','Fantasia','Sin descripcion',1),
  (13,'Titanic',0,'James cameron','drama','Sin descripcion',3),
  (14,'Caperucita',2,'Pablo','Cuento infantil','Sin descripcion',1),
  (16,'Martin Fierro',1,'Jose Hernandez','Ficcion','Sin descripcion',1);

COMMIT;

#
# Data for the `personas` table  (LIMIT 0,500)
#

INSERT INTO `personas` (`id_persona`, `nombre`, `apellido`, `dni`, `usuario`, `password`, `estado`, `id_categoria`) VALUES 
  (1,'Franco','Pololo','33123456','polo19966','123456789',1,1),
  (2,'Manuel Alejandro','Ellena','37774145','manu_landeta','123456789',0,1),
  (3,'Roman','Bressan','37896541','roman.94','123456789',1,1),
  (4,'Carlos','Menem','1000000','carlitosmenem','123456789',0,1),
  (5,'Pedro','Canoero','10338974','pedrocanoero','123456789',0,1),
  (8,'Admin','Admin','000','admin','admin',1,2),
  (9,'Mauro','Matos','10236578','maurito2014','123456789',1,1),
  (12,'Gaston','Coyette','123456789a','gast','123456789',1,1),
  (13,'aaaaaaaaaaaa','bbbbbbbbbbbbbb','12345678','asda','123456789',1,1);

COMMIT;

#
# Data for the `reservas` table  (LIMIT 0,500)
#

INSERT INTO `reservas` (`id_elemento`, `id_persona`, `fecha_registro`, `fecha_inicio`, `fecha_fin`, `detalle`, `estado`) VALUES 
  (1,1,'2017-09-11','2017-09-11','2017-10-21','Sin detalle','Terminada'),
  (1,1,'2017-09-12','2017-09-12','2017-11-15','Sin detalle','Sin devolver'),
  (2,2,'2017-10-28','2017-10-31','2017-11-01','Fue modificado con exito','Sin devolver'),
  (2,2,'2017-11-01','2017-11-01','2017-11-03','Sin detalle','Sin devolver'),
  (2,2,'2017-11-02','2017-11-03','2017-11-05','Sin detalle','Sin devolver'),
  (2,2,'2017-11-03','2017-11-07','2017-11-09','Sin detalle','Sin devolver'),
  (2,2,'2017-11-17','2017-11-18','2017-11-19','Sin detalle','Sin devolver'),
  (2,2,'2017-11-24','2017-11-24','2017-11-26','Sin detalle','Activa'),
  (2,2,'2018-02-01','2018-02-01','2018-02-05','Sin detalle','Cancelada'),
  (5,2,'2017-10-25','2017-10-26','2017-10-27','Sin detalle','Sin devolver'),
  (5,2,'2017-10-27','2017-10-28','2017-10-29','lll','Cancelada'),
  (5,2,'2017-11-01','2017-11-02','2017-11-05','','Sin devolver'),
  (5,2,'2017-11-09','2017-11-09','2017-11-10','Sin detalle','Sin devolver'),
  (5,2,'2017-11-10','2017-11-12','2017-11-13','Sin detalle','Sin devolver'),
  (5,2,'2018-02-01','2018-02-02','2018-02-06','Sin detalle','Activa'),
  (5,3,'2017-09-28','2017-09-28','2017-09-30','Sin detalle','Terminada'),
  (5,4,'2017-11-24','2017-11-24','2017-11-28','Sin detalle','Activa'),
  (6,2,'2017-10-25','2017-10-26','2017-10-28','Sin detalle','Sin devolver'),
  (6,2,'2017-10-26','2017-10-28','2017-10-29','Sin detalle','Cancelada'),
  (6,2,'2017-11-01','2017-11-01','2017-11-04','Sin detalle','Sin devolver'),
  (7,2,'2017-10-27','2017-10-28','2017-10-29','Sin detalle','Cancelada'),
  (7,2,'2017-11-09','2017-11-12','2017-11-13','Sin detalle','Sin devolver'),
  (7,3,'2017-09-11','2017-09-11','2017-09-17','Sin detalle','Sin devolver'),
  (7,4,'2017-11-24','2017-11-24','2017-11-26','Sin detalle','Activa'),
  (14,4,'2017-11-25','2017-11-26','2017-11-27','Sin detalle','Activa'),
  (16,2,'2017-11-24','2017-11-24','2017-11-27','Sin detalle','Activa');

COMMIT;

