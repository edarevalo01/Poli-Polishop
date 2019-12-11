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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-11 17:33:58
