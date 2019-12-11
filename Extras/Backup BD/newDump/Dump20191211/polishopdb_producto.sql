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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-11 17:33:57
