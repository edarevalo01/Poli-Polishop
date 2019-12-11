-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: polishopdb
-- ------------------------------------------------------
-- Server version	8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `administrador`
--

DROP TABLE IF EXISTS `administrador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administrador` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `apellidos` varchar(255) DEFAULT NULL,
  `contrasena` varchar(255) NOT NULL,
  `correo` varchar(255) NOT NULL,
  `nombres` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrador`
--

LOCK TABLES `administrador` WRITE;
/*!40000 ALTER TABLE `administrador` DISABLE KEYS */;
/*!40000 ALTER TABLE `administrador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carrito`
--

DROP TABLE IF EXISTS `carrito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carrito` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fecha_modificacion` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carrito`
--

LOCK TABLES `carrito` WRITE;
/*!40000 ALTER TABLE `carrito` DISABLE KEYS */;
INSERT INTO `carrito` VALUES (1,'2019-07-30 16:46:47'),(2,'2019-07-30 23:45:28');
/*!40000 ALTER TABLE `carrito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,'Venta y comercialización de productos Artesanales.','Artesanías'),(2,'Productos dedicados a la belleza.','Belleza'),(3,'Productos electrónicos de diferentes categorías','Electrónicos'),(4,'Productos dedicados a computadores portatiles o de escritorio.','Computadores'),(5,'Categoría dedicada a libros, literatura o académicos.','Libros'),(6,'Dedicado a la comercialización de productos musicales','Música'),(7,'Dedicado a la comercialización de prendas de vestir.','Ropa'),(8,'Categoria dedicada a la comercialización de productos deportivos.','Deportes'),(9,'Categoria dedicada a la comercialización de productos para el hogar.','Hogar'),(10,'Venta de productos alimenticios de diferentes categorías.','Alimentos'),(11,'Categoría dedicada a la venta de productos para mascotas.','Mascotas'),(12,'Productos oficiales de la Institución Universitaria Politécnico Grancolombiano','Politécnico'),(13,'Categoría para venta de relojes.','Relojes'),(14,'Categoría para venta de accesorios para celular.','Celular');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoria_producto`
--

DROP TABLE IF EXISTS `categoria_producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria_producto` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_categoria` bigint(20) DEFAULT NULL,
  `id_producto` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKptfeu4so6d9f3cx6jtl5l994h` (`id_categoria`),
  KEY `FK1pfku80d8pjwt439jrsvtblnp` (`id_producto`),
  CONSTRAINT `FK1pfku80d8pjwt439jrsvtblnp` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id`),
  CONSTRAINT `FKptfeu4so6d9f3cx6jtl5l994h` FOREIGN KEY (`id_categoria`) REFERENCES `categoria` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria_producto`
--

LOCK TABLES `categoria_producto` WRITE;
/*!40000 ALTER TABLE `categoria_producto` DISABLE KEYS */;
/*!40000 ALTER TABLE `categoria_producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comentario`
--

DROP TABLE IF EXISTS `comentario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comentario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comentario` varchar(255) NOT NULL,
  `fecha` datetime NOT NULL,
  `id_comprador` bigint(20) DEFAULT NULL,
  `id_producto` bigint(20) DEFAULT NULL,
  `puntuacion` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqab93bms10cn67sisob4q73p` (`id_comprador`),
  KEY `FKauvhsib9xfob0t4t0j4ylyt02` (`id_producto`),
  CONSTRAINT `FKauvhsib9xfob0t4t0j4ylyt02` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id`),
  CONSTRAINT `FKqab93bms10cn67sisob4q73p` FOREIGN KEY (`id_comprador`) REFERENCES `comprador` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comentario`
--

LOCK TABLES `comentario` WRITE;
/*!40000 ALTER TABLE `comentario` DISABLE KEYS */;
INSERT INTO `comentario` VALUES (1,'Excelente producto, la entrega fue rápida y segura. muy recomendado.','2019-07-30 15:29:35',2,17,5),(2,'Excelente producto, la entrega fue rápida y segura. muy recomendado, la variedad de colores es excelente y de muy buena calidad.','2019-07-30 15:29:35',1,5,5),(3,'Muy buena atención, excelente servicio, muy rápido, muchas gracias por el producto.','2019-07-30 15:29:35',3,5,5),(4,'Producto de calidad y entrega puntual.','2019-07-30 15:29:35',4,5,4),(5,'Entregado Puntualmente muy Cumplidos Se Recomiendan Pará Negociar Con está Compañía. Fueron muy cordiales y cumplieron con los tiempos de entrega','2019-07-30 15:29:35',1,12,4);
/*!40000 ALTER TABLE `comentario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compra`
--

DROP TABLE IF EXISTS `compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `compra` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ciudad` varchar(255) NOT NULL,
  `departamento` varchar(255) DEFAULT NULL,
  `direccion_envio` varchar(255) NOT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `fecha_creacion` datetime NOT NULL,
  `fecha_estimada_entrega` datetime DEFAULT NULL,
  `id_carrito` bigint(20) DEFAULT NULL,
  `id_comprador` bigint(20) DEFAULT NULL,
  `metodo_pago` varchar(255) DEFAULT NULL,
  `nombre_destinatario` varchar(255) NOT NULL,
  `numero_documento` varchar(255) NOT NULL,
  `numero_pedido` varchar(255) NOT NULL,
  `observaciones` varchar(255) DEFAULT NULL,
  `pais` varchar(255) NOT NULL,
  `telefono_dos` varchar(255) DEFAULT NULL,
  `telefono_uno` varchar(255) NOT NULL,
  `tipo_documento` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1g6hytc6y1gtjwp9ha1d0094y` (`id_carrito`),
  KEY `FKida7hxgbl6r4g62vya1ha3oll` (`id_comprador`),
  CONSTRAINT `FK1g6hytc6y1gtjwp9ha1d0094y` FOREIGN KEY (`id_carrito`) REFERENCES `carrito` (`id`),
  CONSTRAINT `FKida7hxgbl6r4g62vya1ha3oll` FOREIGN KEY (`id_comprador`) REFERENCES `comprador` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compra`
--

LOCK TABLES `compra` WRITE;
/*!40000 ALTER TABLE `compra` DISABLE KEYS */;
INSERT INTO `compra` VALUES (1,'','','','comprando','2019-07-30 16:46:47','2019-08-04 16:46:47',1,57,'','','','1564505207467-57','','','','',''),(2,'','','','comprando','2019-07-30 23:45:28','2019-08-04 23:45:28',2,2,'','','','1564530328260-2','','','','','');
/*!40000 ALTER TABLE `compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comprador`
--

DROP TABLE IF EXISTS `comprador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comprador` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `apellidos` varchar(255) DEFAULT NULL,
  `ciudad` varchar(255) NOT NULL,
  `contrasena` varchar(255) NOT NULL,
  `correo` varchar(255) NOT NULL,
  `nombres` varchar(255) NOT NULL,
  `pais` varchar(255) NOT NULL,
  `puntuacion` bigint(20) DEFAULT NULL,
  `url_foto` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_pb7ybxbwy7o19ftcs2qvcalav` (`correo`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comprador`
--

LOCK TABLES `comprador` WRITE;
/*!40000 ALTER TABLE `comprador` DISABLE KEYS */;
INSERT INTO `comprador` VALUES (1,'Moreno Mendez','Bogotá','a123*','momorenomendez@poligran.edu.co','Monica Fernanda','Colombia',5,'assets/profilePhotos/users/momorenomendez.png','momorenomendez'),(2,'Arevalo Nocua','Bogotá','a123*','edarevalo@poligran.edu.co','Edwar Alejandro ','Colombia',5,'assets/profilePhotos/users/edarevalo.png','edarevalo'),(3,'Segura Rodriguez','Bogotá','a123*','dasegura@poligran.edu.co','David Eduardo','Colombia',5,'assets/profilePhotos/users/dasegura.png','dasegura'),(4,'Osorio Jimenez','Bogotá','a123*','anosorio@poligran.edu.co','Andres Gustavo','Colombia',5,'assets/profilePhotos/users/anosorio.png','anosorio'),(5,'Martinez Rodriguez','Bogotá','a123*','henmartinez@poligran.edu.co','Hendy Daniel','Colombia',5,'assets/profilePhotos/users/henmartinez.png','henmartinez'),(6,'Londoño Leal','Bogotá','a123*','lalondono@poligran.edu.co','Laura Daniela','Colombia',5,'assets/profilePhotos/users/lalondono.png','lalondono'),(7,'Mendez Nuñez','Bogotá','a123*','mamendez@poligran.edu.co','Margarita','Colombia',5,'assets/profilePhotos/users/mamendez.png','mamendez'),(8,'Moreno Romero','Bogotá','a123*','femoreno@poligran.edu.co','Fernando','Colombia',5,'assets/profilePhotos/users/femoreno.png','femoreno'),(9,'Moreno Mendez','Bogotá','a123*','bmoreno@poligran.edu.co','Brayan ','Colombia',5,'assets/profilePhotos/users/bmoreno.png','bmoreno'),(10,'Moreno Mendez','Bogotá','a123*','jmoreno@poligran.edu.co','Jessica ','Colombia',5,'assets/profilePhotos/users/jmoreno.png','jmoreno'),(11,'Mendez','Bogotá','a123*','jmendez@poligran.edu.co','Jose','Colombia',5,'assets/profilePhotos/users/sin.png','jmendez'),(12,'Lopez Lombana','Bogotá','a123*','llopez@poligran.edu.co','Luis','Colombia',5,'assets/profilePhotos/users/sin.png','llopez'),(13,'Rigueros Bello','Bogotá','a123*','crigueros@poligran.edu.co','Camilo','Colombia',5,'assets/profilePhotos/users/crigueros.png','crigueros'),(14,'Alonzo Alvarez','Bogotá','a123*','jalonzo@poligran.edu.co','Juliana','Colombia',5,'assets/profilePhotos/users/sin.png','jalonzo'),(15,'Arevalo Rodriguez','Bogotá','a123*','earevaloro@poligran.edu.co','Eduardo','Colombia',5,'assets/profilePhotos/users/sin.png','earevaloro'),(16,'Jimenez Castro','Bogotá','a123*','lijimenez@poligran.edu.co','Liliana','Colombia',5,'assets/profilePhotos/users/sin.png','lijimenez'),(17,'Rojas Quevedo','Mendellin','a123*','orojas@poligran.edu.co','Onaida','Colombia',5,'assets/profilePhotos/users/sin.png','orojas'),(18,'Martinez Benavidez','Mendellin','a123*','jmartinez@poligran.edu.co','Javier','Colombia',5,'assets/profilePhotos/users/sin.png','jmartinez'),(19,'Huertas Puentes','Mendellin','a123*','hpuentes@poligran.edu.co','Hermes','Colombia',5,'assets/profilePhotos/users/sin.png','hpuentes'),(20,'Trevilcok Mares','Mendellin','a123*','imares@poligran.edu.co','Ivan','Colombia',5,'assets/profilePhotos/users/sin.png','imares'),(21,'Rodriguez Sanchez','Mendellin','a123*','jarodriguez@poligran.edu.co','Javier','Colombia',5,'assets/profilePhotos/users/sin.png','jarodriguez'),(22,'Avendaño Suarez','Mendellin','a123*','favendano@poligran.edu.co','Felipe','Colombia',5,'assets/profilePhotos/users/sin.png','favendano'),(23,'Espinosa Bernal','Mendellin','a123*','cespinosa@poligran.edu.co','Catalina','Colombia',5,'assets/profilePhotos/users/cespinosa.png','cespinosa'),(24,'Correa Nuñez','Mendellin','a123*','jcorrea@poligran.edu.co','Judi Paola','Colombia',5,'assets/profilePhotos/users/sin.png','jcorrea'),(25,'Urquijo Mancilla','Mendellin','a123*','purquijo@poligran.edu.co','Paola Andrea','Colombia',5,'assets/profilePhotos/users/sin.png','purquijo'),(26,'Moreno Mendez','Mendellin','a123*','wmoreno@poligran.edu.co','Wendy Daniela','Colombia',5,'assets/profilePhotos/users/sin.png','wmoreno'),(27,'Camargo Huertas','Mendellin','a123*','scamargo@poligran.edu.co','Sergio','Colombia',5,'assets/profilePhotos/users/scamargo.png','scamargo'),(28,'Nuñez Mendez','Mendellin','a123*','lununez@poligran.edu.co','Luciana','Colombia',5,'assets/profilePhotos/users/sin.png','lununez'),(29,'Nuñez Mendez','Mendellin','a123*','enunez@poligran.edu.co','Elver','Colombia',5,'assets/profilePhotos/users/enunez.png','enunez'),(30,'Nuñez Mendez','Mendellin','a123*','enunez1@poligran.edu.co','Elias Jacob','Colombia',5,'assets/profilePhotos/users/enunez1.png','enunez1'),(31,'Nuñez Mendez','Bogota','a123*','cnunez@poligran.edu.co','Clemencia','Colombia',5,'assets/profilePhotos/users/cnunez.png','cnunez'),(32,'Gomez Romero','Bogota','a123*','jgomez@poligran.edu.co','Jose','Colombia',5,'assets/profilePhotos/users/sin.png','jgomez'),(33,'Gomez Romero','Bogota','a123*','mgomez@poligran.edu.co','Mercedez','Colombia',5,'assets/profilePhotos/users/sin.png','mgomez'),(34,'Garzon Forero','Bogota','a123*','lgarzon@poligran.edu.co','Lisa','Colombia',5,'assets/profilePhotos/users/lgarzon.png','lgarzon'),(35,'Yepez','Bogota','a123*','jyepez@poligran.edu.co','Juan Jose','Colombia',5,'assets/profilePhotos/users/sin.png','jyepez'),(36,'Lopez Montaner','Bogota','a123*','elopez@poligran.edu.co','Evaluna ','Colombia',5,'assets/profilePhotos/users/sin.png','elopez'),(37,'Mora Murcia','Bogota','a123*','cmora@poligran.edu.co','Camilo ','Colombia',5,'assets/profilePhotos/users/cmora.png','cmora'),(38,'Santos Uribe','Bogota','a123*','gsantos@poligran.edu.co','Gabriel Gerardo','Colombia',5,'assets/profilePhotos/users/sin.png','gsantos'),(39,'Gomez Umbarila','Bogota','a123*','cgomez@poligran.edu.co','Cristian David','Colombia',5,'assets/profilePhotos/users/cgomez.png','cgomez'),(40,'Gomez Romero','Bogota','a123*','pgomez@poligran.edu.co','Pedro','Colombia',5,'assets/profilePhotos/users/sin.png','pgomez'),(41,'Umbarila Cielos','Bogota','a123*','oumbarila@poligran.edu.co','Olga','Colombia',5,'assets/profilePhotos/users/oumbarila.png','oumbarila'),(42,'Uriel Velasquez','Bogota','a123*','juriel@poligran.edu.co','James','Colombia',5,'assets/profilePhotos/users/juriel.png','juriel'),(43,'Perez Moreno','Bogota','a123*','lperez@poligran.edu.co','Laura','Colombia',5,'assets/profilePhotos/users/lperez.png','lperez'),(45,'Camacho Rojas','Bogota','a123*','ccamacho@poligran.edu.co','Carlos','Colombia',5,'assets/profilePhotos/users/ccamacho.png','ccamacho'),(46,'Rojas Pineda','Bogota','a123*','jrojas@poligran.edu.co','Johan Felipe','Colombia',5,'assets/profilePhotos/users/jrojas.png','jrojas'),(47,'Forero Lombana','Bogota','a123*','gforero@poligran.edu.co','Geraldine','Colombia',5,'assets/profilePhotos/users/gforero.png','gforero'),(48,'Satoba Castro','Bogota','a123*','ddsatoba@poligran.edu.co','Diego David','Colombia',5,'assets/profilePhotos/users/ddsatoba.png','ddsatoba'),(49,'Rodriguez Jimenez','Bogota','a123*','lrodriguez@poligran.edu.co','Leonardo','Colombia',5,'assets/profilePhotos/users/lrodriguez.png','lrodriguez'),(50,'Castellanos Cruz','Bogota','a123*','lacastellanos@poligran.edu.co','Laura','Colombia',5,'assets/profilePhotos/users/lacastellanos.png','lacastellanos'),(51,'JImenez Camargo','Bogota','a123*','jjimenez@poligran.edu.co','Julian','Colombia',5,'assets/profilePhotos/users/jjimenez.png','jjimenez'),(52,'Carreño','Bogota','a123*','fcarreno@poligran.edu.co','Fabian Ricargo','Colombia',5,'assets/profilePhotos/users/sin.png','fcarreno'),(53,'Carreño Marin','Bogota','a123*','lcarreno@poligran.edu.co','Leidy Johana','Colombia',5,'assets/profilePhotos/users/sin.png','lcarreno'),(54,'Eraso Rosas','Bogota','a123*','jeraso@poligran.edu.co','Jorge Alberto','Colombia',5,'assets/profilePhotos/users/jeraso.png','jeraso'),(55,'Rincon','Bogota','a123*','jrincon@poligran.edu.co','Jose Abeiro','Colombia',5,'assets/profilePhotos/users/sin.png','jrincon'),(56,'Cardenas Gomez','Bogota','a123*','dcardenas@poligran.edu.co','David','Colombia',5,'assets/profilePhotos/users/dcardenas.png','dcardenas'),(57,'Roa Santana','Bogota','a123*','eroa@poligran.edu.co','Erick Duvan','Colombia',5,'assets/profilePhotos/users/eroa.png','eroa'),(58,'Niño Velasquez','Bogotá','a123','jninovel@poligran.edu.co','Javier Fernando','Colombia',0,'assets/profilePhotos/users/sin.png','jninovel');
/*!40000 ALTER TABLE `comprador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log_actividades`
--

DROP TABLE IF EXISTS `log_actividades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `log_actividades` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `nombre_actividad` varchar(255) DEFAULT NULL,
  `usuario` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log_actividades`
--

LOCK TABLES `log_actividades` WRITE;
/*!40000 ALTER TABLE `log_actividades` DISABLE KEYS */;
/*!40000 ALTER TABLE `log_actividades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log_error`
--

DROP TABLE IF EXISTS `log_error`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `log_error` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `nombre_error` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log_error`
--

LOCK TABLES `log_error` WRITE;
/*!40000 ALTER TABLE `log_error` DISABLE KEYS */;
/*!40000 ALTER TABLE `log_error` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `calificacion` bigint(20) DEFAULT NULL,
  `dependencia` varchar(255) DEFAULT NULL,
  `descripcion` varchar(3000) NOT NULL,
  `fecha_publicacion` datetime NOT NULL,
  `id_propietario` bigint(20) DEFAULT NULL,
  `id_vendedor` bigint(20) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `precio` varchar(255) NOT NULL,
  `url_carpeta_imagenes` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlkdqowib02cfhahnkcsakc4dh` (`id_propietario`),
  KEY `FKqfppjvhswu0p3tw0ty707ig0c` (`id_vendedor`),
  CONSTRAINT `FKlkdqowib02cfhahnkcsakc4dh` FOREIGN KEY (`id_propietario`) REFERENCES `propietario_negocio` (`id`),
  CONSTRAINT `FKqfppjvhswu0p3tw0ty707ig0c` FOREIGN KEY (`id_vendedor`) REFERENCES `vendedor` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (3,0,'Poli','Bolsos y mochilas en diferentes colores contramarcados con el logotipo del Politécnico Grancolombiano.','2019-07-30 06:10:49',1,6,'Bolsos multicolor - Politécnico Grancolombiano','35000','assets/ImagenesPolishop/Productos/bolsosmulticolorpolitcnicograncolombianoguidpolitcnicograncolombianoundefined'),(5,0,'Poli','Práctico collar multicolor contramarcado con el logo del Politécnico Grancolombiano, útil para transportar tus llaves, carné y demás accesorios!','2019-07-30 06:31:31',1,6,'Collar para llaves o carné colores varios - Politécnico Grancolombiano','15000','assets/ImagenesPolishop/Productos/collarparallavesocarncoloresvariospolitcnicograncolombianoguidpolitcnicograncolombiano'),(8,0,'Poli','Buzos tipo hoodie de diferentes colores unisex contramarcado con el logo del Politécnico Grancolombiano y bolsillo en la parte frontal. Tallas XS S M L XL. Disponibles para compra inmediata','2019-07-30 11:48:35',1,6,'Buzo tipo hoodie unisex de lana - Politécnico Grancolombiano','39900','assets/ImagenesPolishop/Productos/buzotipohoodieunisexdelanapolitcnicograncolombianoguidpolitcnicograncolombiano'),(9,0,'Poli','Lleva el mejor estilo de la institución con estas maravillosas gorras personalizadas con el logo del Politécnico Grancolombiano en 5 diferentes colores, rojo, azul, gris, beige y blanco.','2019-07-30 11:50:11',1,6,'Gorras personalizadas con logo del Politécnico Grancolombiano de diferentes colores','15000','assets/ImagenesPolishop/Productos/gorraspersonalizadasconlogodelpolitcnicograncolombianodediferentescoloresguidpolitcnicograncolombiano'),(10,0,'Poli','Sketchbook Agenda Cuaderno Notebook 21 X 12 Moleskine 84 Hojas Marca Alpen de dimensiones 21x12cm, peso de 85 gramos, de pasta dura de diferentes colores contramarcado con el logo del Politécnico Grancolombiano, lo puedes encontrar con hojas de tipo cuadriculado, rayado o totalmente blancas.','2019-07-30 11:55:39',1,6,'Sketchbook Agenda Cuaderno Notebook 21 X 12 Moleskine 84 con logo del Politécnico Grancolombiano','20000','assets/ImagenesPolishop/Productos/sketchbookagendacuadernonotebook21x12moleskine84conlogodelpolitcnicograncolombianoguidpolitcnicograncolombiano'),(11,0,'Poli','Funcionan con todos los tipos de ratones y ajustes, con 3 mm de goma natural antideslizante, proporciona un agarre excelente superficie durante la sesión de uso. El tamaño de la almohadilla es de 35x25cm con 3mm de espesor. Con diferentes motivos y diseño del logo que te va a encantar.','2019-07-30 12:00:59',1,6,'Pad mouse con superficie de tela y goma antedeslizante con logo del Politécnico Grancolombiano','5900','assets/ImagenesPolishop/Productos/padmouseconsuperficiedetelaygomaantedeslizanteconlogodelpolitcnicograncolombianoguidpolitcnicograncolombiano'),(12,0,'Poli','Estuche para celular de goma rígida con paño interno que protege el teléfono de rayones; además no se ensucia. Disponible en muchos colores, algunos de nuestros colores son amarillo, negro, gris, azul oscuro, rojo, cereza, rasado, gris, verde menta y muchos mas.','2019-07-30 12:04:15',1,6,'Estuche de goma rígida para iPhone 5, 6, 6s, 7, 8, 7plus, 8plus, X - Politécnico Grancolombiano','18000','assets/ImagenesPolishop/Productos/estuchedegomargidaparaiphone566s787plus8plusxpolitcnicograncolombianoguidpolitcnicograncolombiano'),(13,0,'Poli','Indispensable accesorio que funciona como sujetador y base para sus equipos móviles, se puede expandir o comprimir para mayor comodidad. Evita que tu celular se resbale de tus manos.Contramarcado con el Logo del Politécnico Grancolombiano, disponible en diferentes colores.','2019-07-30 12:09:38',1,6,'Soporte Para Celular o Tablet Pop Holder Socket - Politécnico Grancolombiano','6500','assets/ImagenesPolishop/Productos/soporteparacelularotabletpopholdersocketpolitcnicograncolombianoguidpolitcnicograncolombiano'),(14,0,'Poli','Vaso pitillo de plástico de diferentes colores, azul, rojo, verde, transparente y más. Prácticos para llevar a cualquier lugar ya que ofrecen una gran resistencia a golpes y caídas, capacidad de 10 onzas, contramarcado con el logo del Politécnico Grancolombiano.\nAltura 16cm\nDiámetro 7cm','2019-07-30 12:14:54',1,6,'Vaso con tapa y pitillo flexible con capacidad de 10 onzas - Politécnico Grancolombiano','8900','assets/ImagenesPolishop/Productos/vasocontapaypitilloflexibleconcapacidadde10onzaspolitcnicograncolombianoguidpolitcnicograncolombiano'),(15,0,'Comunidad','Todos tenemos un patron personal del dinero arraigado en nuestro subconsciente, y es este patron, mas que cualquier otra cosa, lo que determina nuestra vida financiera. Puedes saberlo todo sobre mercadotecnia, ventas, negociaciones, acciones, propiedad inmobiliaria y finanzas en general, pero si tu patron para el dinero no esta programado para el exito nunca tendras mucho dinero; y si de algun modo lo consigues, ¡o perderas con gran facilidad.','2019-07-30 12:24:58',1,11,'Los secretos de la mente millonaria (Libros - Edición en Español)','22900','assets/ImagenesPolishop/Productos/lossecretosdelamentemillonarialibrosedicinenespaolguidjuancarlosarizalpez'),(16,0,'Comunidad','Pantalón en Dril Rígido 5 bolsillos, excelente calidad para hombre, todos los pantalones vienen con su respectiva correa. Disponemos los colores negro, azul oscuro, azul petroleo, vinotinto, café oscuro, gris oscuro, gris claro, caqui, beige y hoja seca. Encuentralo en diferentes tallas desde 28 a 36','2019-07-30 12:42:35',1,4,'Pantalon Dril rigido Hombre - Ropa','70000','assets/ImagenesPolishop/Productos/pantalondrilrigidohombreropaguidjuancarloslealrodriguez'),(17,5,'Comunidad','Reloj Aiman Mykonos multifuncional con Cronógrafo, resistencia al agua de 50m, carcasa de acero inoxidable, correa de cuero, tapa de fondo atornillada, baño parcial de ione, funcionales clásicas con agujas de reloj, presición  /- 20 segundos por mes','2019-07-30 12:51:17',1,5,'Relojes: AIMANT Mykonos Classic para hombre','210000','assets/ImagenesPolishop/Productos/relojesaimantmykonosclassicparahombreguiddavidandrssierralaverde'),(18,0,'Comunidad','ALMUERZOS RICOS Y SALUDABLES\r ​\r Aliméntate bien a un precio justo. Te llevamos el almuerzo a donde prefieras, menú aprobado por nutricionistas. Somos especialistas en la preparación de comida saludable a domicilio.','2019-07-30 12:56:27',1,20,'FoodyFast comida saludable a Domicilio - Alimentos Ricos en proteínas y carbohidratos.','12900','assets/ImagenesPolishop/Productos/foodyfastcomidasaludableadomicilioalimentosricosenprotenasycarbohidratosguidcristianandrssabogalherrera'),(19,0,'Comunidad','El Ukelele es un instrumento de cuerda originaria de Hawai. Perfecto para la iniciación musical de niños y adultos. Es el instrumento indicado para salir de viaje a la playa o a las montañas. Producto hecho con la mejor madera, cuerdas de nylon con 12 trastes y acabado brillante.','2019-07-30 13:04:06',1,9,'Ukulele Soprano Standard Huawind Uk-21-30 Con Forro Ukelele - Música','140000','assets/ImagenesPolishop/Productos/ukulelesopranostandardhuawinduk2130conforroukeleleguidedwardnicolsespinosabarn'),(20,0,'Comunidad','Zapatos de excelente calidad, hechos en Colombia 100% en cuero graso. Cocidos y vulcanizados para mas durabilidad. Suela Anti-deslizante en caucho. Costuras reforzadas. Industria Nacional.','2019-07-30 13:10:16',1,15,'Zapatos En Cuero Mocasín Negro Semi Formal para cualquier ocasión - Ropa','79900','assets/ImagenesPolishop/Productos/zapatosencueromocasnnegrosemiformalparacualquierocasinropaguidnatalycarolinaceballoscortz'),(21,0,'Comunidad','El diseño limpio de esta gorra deportiva le imprime estilo moderno a tu look diario. Confeccionada con un cierre trasero ajustable a presión, esta gorra moderna luce una corona alta e incorpora una banda para el sudor en Climalite que absorbe la humedad en tu camino hacia y desde el gimnasio. Banda para el sudor Climalite que mantiene el sudor alejado de tu piel','2019-07-30 13:16:14',1,15,'Gorras Adidas premium para hombre - Ropa masculina deportes','79900','assets/ImagenesPolishop/Productos/gorrasadidaspremiumparahombreropamasculinadeportesguidnatalycarolinaceballoscortz'),(22,0,'Comunidad','Fantásticas selfies, vídeo llamadas, ver videos, nevegar por la web, textear y mucho más.\nLe permite a tú mano relajarse mientras sostiene tu teléfono, nunca se caerá tu celular de nuevo!\nGran precio y de primera calidad.','2019-07-30 13:20:39',1,13,'Pop Socket - Accesorio ideal para celular con distintos motivos y diseños','11000','assets/ImagenesPolishop/Productos/popsocketaccesorioidealparacelularcondistintosmotivosydiseosguidbrayanstivencastro'),(23,0,'Comunidad','Las gafas son un accesorio importante que marcan un estilo diferente en el rostro de las personas. Pueden ser utilizadas en cualquier temporada, desde el verano hasta el invierno y sólo tú decides el momento de usarlas. Mejora tu visión y cuida tus ojos con lo último en tecnología de alta definición. ','2019-07-30 13:25:51',1,1,'Gafas Hd Vision para Día y noche con protección de rayos ultravioleta, visión clara y segura - Belleza','25000','assets/ImagenesPolishop/Productos/gafashdvisionparadaynocheconproteccinderayosultravioletavisinclaraysegurabellezaguidcarlosestebanvelezmontaa'),(24,0,'Comunidad','Libros nuevos y usados desde 20.000 pesos todos en excelente estado, pregunta por el libro de tu preferencia! Escritores famosos desde Mario Vargas Llosa hasta Homero, cada libro esconde una historia y está en ti descubrir los misterios de cada una. TODOS LOS LIBROS SON ORIGINALES.','2019-07-30 13:46:29',1,16,'Libros nuevos o usados en excelente estado - Literatura Colombiana y universal, más de 70 títulos.','20000','assets/ImagenesPolishop/Productos/librosnuevosousadosenexcelenteestadoliteraturacolombianayuniversalmsde70ttulosguidfrankalfonsomolinamendoza');
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto_carrito`
--

DROP TABLE IF EXISTS `producto_carrito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto_carrito` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cantidad` bigint(20) DEFAULT NULL,
  `id_carrito` bigint(20) DEFAULT NULL,
  `id_producto` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKht34thlww4903in1c6r1wmctl` (`id_carrito`),
  KEY `FKs257sbnksvgvn2bifpfswvaam` (`id_producto`),
  CONSTRAINT `FKht34thlww4903in1c6r1wmctl` FOREIGN KEY (`id_carrito`) REFERENCES `carrito` (`id`),
  CONSTRAINT `FKs257sbnksvgvn2bifpfswvaam` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto_carrito`
--

LOCK TABLES `producto_carrito` WRITE;
/*!40000 ALTER TABLE `producto_carrito` DISABLE KEYS */;
INSERT INTO `producto_carrito` VALUES (1,1,1,10),(2,3,2,3),(3,1,2,9);
/*!40000 ALTER TABLE `producto_carrito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `propietario_negocio`
--

DROP TABLE IF EXISTS `propietario_negocio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `propietario_negocio` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `apellidos` varchar(255) NOT NULL,
  `contrasena` varchar(255) NOT NULL,
  `correo` varchar(255) NOT NULL,
  `nombres` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_26v2nufsr6kbmxuudljpjm74c` (`correo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `propietario_negocio`
--

LOCK TABLES `propietario_negocio` WRITE;
/*!40000 ALTER TABLE `propietario_negocio` DISABLE KEYS */;
INSERT INTO `propietario_negocio` VALUES (1,'Grancolombiano','poli123*','poli@poligran.edu.co','Politécnico');
/*!40000 ALTER TABLE `propietario_negocio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendedor`
--

DROP TABLE IF EXISTS `vendedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vendedor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `apellidos` varchar(255) NOT NULL,
  `celular` varchar(255) DEFAULT NULL,
  `ciudad` varchar(255) NOT NULL,
  `contrasena` varchar(255) NOT NULL,
  `correo` varchar(255) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `nombres` varchar(255) NOT NULL,
  `pais` varchar(255) NOT NULL,
  `puntuacion_vendedor` bigint(20) DEFAULT NULL,
  `url_foto` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_b7wpxgsw0rva0ngcagpqnpx4y` (`correo`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendedor`
--

LOCK TABLES `vendedor` WRITE;
/*!40000 ALTER TABLE `vendedor` DISABLE KEYS */;
INSERT INTO `vendedor` VALUES (1,'Velez Montaña','3123312251','null','a123','cavelez1@poligran.edu.co','Estudiante de séptimo semestre de medios audiovisuales.','Carlos Esteban','null',0,'assets/ImagenesPolishop/cavelez1poligraneduco.png'),(2,'undefined','3156298450','null','a123','gusrodri@poligran.edu.co','Tatuador, estudiante de diseño gráfico y diseño industrial en el Politénico Grancolombiano @gustavorodrii','Gustavo Rodriguez','null',0,'assets/ImagenesPolishop/gusrodripoligraneduco.png'),(3,'undefined','3209347128','null','a123','jorojas1@poligran.edu.co','Empresa creada por estudiante de Ingeniería de sistemas del politécnico grancolombiano enfocada en la venta de partes para computador portatil o de escritorio.','TechParts','null',0,'assets/ImagenesPolishop/jorojas1poligraneduco.png'),(4,'Leal Rodriguez','3143435267','null','a123','jclrodigu@poligran.edu.co','Docente de dibujo digital de la institución de la facultad de Ingeniería, diseño e innovación, como profesor de planta quiero emprender en mi pequeño negocio personal.','Juan Carlos','null',0,'assets/ImagenesPolishop/jclrodigupoligraneduco.png'),(5,'Sierra Laverde','3145210987','null','a123','dasierra3@poligran.edu.co','Soy de la planta administrativa de la universidad, encargado del área de gestión de procesos académicos.','David Andrés','null',0,'assets/ImagenesPolishop/dasierra3poligraneduco.png'),(6,'Grancolombiano','7455555','null','a123','poli@poligran.edu.co','Tienda oficial de la Institución Universitaria Politécnico Grancolombiano.','Politécnico','null',0,'assets/ImagenesPolishop/polipoligraneduco.png'),(7,'Rojas Maldonado','3130914618','null','a123','marojas1@poligran.edu.co','Dueño de Euforia, tienda de ropa dedicada a venta de ropa femenina fabricada de manera manual en Colombia. Estudiante de Diseño de modas en el Politécnico Grancolombiano.','Mauricio David','null',0,'assets/ImagenesPolishop/marojas1poligraneduco.png'),(8,'Rodriguez Moreno','3127235201','null','a123','jarodigu@poligran.edu.co','Estudiante de quinto semestre psicologia, emprendedor y aficionado por los videojuegos.','Jhon Alexander','null',0,'assets/ImagenesPolishop/jarodigupoligraneduco.png'),(9,'Espinosa Barón','3156248266','null','a123','edespin1@poligran.edu.co','Administrativo de la universidad en gestión humana con pequeño negocio de instrumentos musicales.','Edward Nicolás ','null',0,'assets/ImagenesPolishop/edespin1poligraneduco.png'),(10,'Alvarado Sierra','3125430091','null','a123','ngalvarado@poligran.edu.co','Co-fundadora de Zooland veterinaria, especializados en consulta veterinaria y laboratorio clinico además de venta de productos para mascotas.','Natalia Geraldinne','null',0,'assets/ImagenesPolishop/ngalvaradopoligraneduco.png'),(11,' Ariza López','3003920001','null','a123','jucariza@poligran.edu.co','Estudiante de sexto semestre de diseño gráfico del Politécnico Grancolombiano con un pequeño negocio de venta de libros.','Juan Carlos','null',0,'assets/ImagenesPolishop/jucarizapoligraneduco.png'),(12,'Hernández Herrera','31566232221','null','a123','anhernher@poligran.edu.co','Soy estudiante de cuarto semestre en negocios internacionales en el politécnico y con mi familia tenemos un negocio de partes para electrodomésticos','Andrés','null',0,'assets/ImagenesPolishop/anhernherpoligraneduco.png'),(13,'Stiven Castro','3001611161','null','a123','brcastro@poligran.edu.co','Ingeniero en telecomunicaciones del Poli con un negocio independiente de venta de accesorios para celular','Brayan','null',0,'assets/ImagenesPolishop/brcastropoligraneduco.png'),(14,'Giraldo Plazas','3159920231','null','a123','crplazas@poligran.edu.co','Soy estudiante de psicología por profesión y chef por pasión, me dedico a la venta de postres y pasteles.','Cristian','null',0,'assets/ImagenesPolishop/crplazaspoligraneduco.png'),(15,'Ceballos Cortéz','3053002123','null','a123','natceballos@poligran.edu.co','Soy estudiante de tercer semestre de psicologia en el poli, tengo un negocio de venta de calzado y artículos deportivos.','Nataly Carolina','null',0,'assets/ImagenesPolishop/natceballospoligraneduco.png'),(16,'Molina Mendoza','3113206332','null','a123','frmolina1@poligran.edu.co','Amante al cine y la literatura, estudiante de medios audiovisuales del politecnico grancolombiano, trabajo en una librería independiente.','Frank Alfonso','null',0,'assets/ImagenesPolishop/frmolina1poligraneduco.png'),(17,'Garcia Cruz','3129530292','null','a123','lagarcia3@poligran.edu.co','Dueña de pastelería AwesomeCupcake dedicada a la venta de postres, desayunos conmemorativos y pasteles, soy estudiante de diseño gráfico en el Poli','Laura Daniela','null',0,'assets/ImagenesPolishop/lagarcia3poligraneduco.png'),(18,'Gonzáles Prieto','3154121093','null','a123','vagoprieto@poligran.edu.co','Soy estudiante de sexto semestre de psicología y tengo un negocio familiar de venta de bolsos y mochilas artesanales','Valeria ','null',0,'assets/ImagenesPolishop/vagoprietopoligraneduco.png'),(19,'Cárdenas Ortiz','3193216472','null','a123','lncardenas@poligran.edu.co','Me dedico a la venta de artículos para el hogar, soy estudiante de psicología en cuarto semestre del politécnico grancolombiano.','Lady Natalia','null',0,'assets/ImagenesPolishop/lncardenaspoligraneduco.png'),(20,'Sabogal Herrera','3156663245','null','a123','cransabogal@poligran.edu.co','Dueño de FoodyFast especialistas en comida saludable con entrega a domicilio, también soy estudiante del politécnico grancolombiano de ingeniería industrial.','Cristian Andrés','null',0,'assets/ImagenesPolishop/cransabogalpoligraneduco.png'),(21,'Avendaño Molina','3192996708','null','a123*','mfavendano@poligran.edu.co','a123*','Felipe AM','null',0,'/assets/ImagenesPolishop/mfavendanopoligraneduco.png'),(22,'Niño Velasquez','3124315542','null','a123','eaninovel@poligran.edu.co','Profesor de planta en el Politécnico Grancolombiano en la facultad de ingeniería Diseño e Innovación.','Edwin Andrés','null',0,'/assets/ImagenesPolishop/eaninovelpoligraneduco.png');
/*!40000 ALTER TABLE `vendedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'polishopdb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-11 17:37:40
