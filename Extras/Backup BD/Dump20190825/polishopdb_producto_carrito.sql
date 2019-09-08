-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: polishop.c0sdfz2tkgho.us-east-2.rds.amazonaws.com    Database: polishopdb
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '';

--
-- Table structure for table `producto_carrito`
--

DROP TABLE IF EXISTS `producto_carrito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto_carrito`
--

LOCK TABLES `producto_carrito` WRITE;
/*!40000 ALTER TABLE `producto_carrito` DISABLE KEYS */;
INSERT INTO `producto_carrito` VALUES (1,1,1,10),(2,3,2,3);
/*!40000 ALTER TABLE `producto_carrito` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-08-25 12:22:16
