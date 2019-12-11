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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-11 17:34:04
