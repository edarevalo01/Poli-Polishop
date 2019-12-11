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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-11 17:33:57
