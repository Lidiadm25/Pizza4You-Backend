-- ============================================
-- Pizza4You - Database Dump
-- MySQL 8.0+ / compatible with MySQL 9.x
-- ============================================
-- Import: mysql -u root -p < database/proyecto_final.sql
-- ============================================

SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT;
SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS;
SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION;
SET NAMES utf8;
SET @OLD_TIME_ZONE=@@TIME_ZONE;
SET TIME_ZONE='+00:00';
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO';
SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0;

-- Table structure for table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categorias` (
  `id_cat` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `imagen` varchar(255) NOT NULL,
  PRIMARY KEY (`id_cat`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorias`
--

LOCK TABLES `categorias` WRITE;
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
INSERT INTO `categorias` VALUES (1,'Pizzas','pizza_cat.png'),(2,'Bebidas','drinks.png'),(3,'Complementos','complementos.png'),(4,'Postres','postres.png');
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-09-05 12:59:50

-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id_rol` int NOT NULL AUTO_INCREMENT,
  `nombre_cargo` varchar(50) NOT NULL,
  PRIMARY KEY (`id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'Cocinero'),(2,'Repartidor'),(3,'Atencion');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-09-05 12:59:51

-- Table structure for table `ingredientes`
--

DROP TABLE IF EXISTS `ingredientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingredientes` (
  `descatalogado` bit(1) DEFAULT NULL,
  `es_vegano` bit(1) DEFAULT NULL,
  `id_ing` int NOT NULL AUTO_INCREMENT,
  `precio_extra` decimal(38,2) DEFAULT NULL,
  `stock` int NOT NULL,
  `unidad_medida` varchar(20) DEFAULT NULL,
  `nombre` varchar(100) NOT NULL,
  `alergenos` varchar(255) DEFAULT NULL,
  `imagen` varchar(255) DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_ing`),
  UNIQUE KEY `UKmswp95l2180nvkxkl3hoge6fy` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredientes`
--

LOCK TABLES `ingredientes` WRITE;
/*!40000 ALTER TABLE `ingredientes` DISABLE KEYS */;
INSERT INTO `ingredientes` VALUES (_binary '\0',_binary '\0',1,1.20,115,'g','Bacon','Ninguno','87968109-1acc-4c33-a173-09254e65eff3_bacon_ing.png','Extra'),(_binary '\0',_binary '',2,0.50,324,'ml','Salsa Barbacoa','Soja','3bd2b179-b5d3-4589-9811-b343bfbb4afd_barbacoa_salsa.png','Salsa'),(_binary '\0',_binary '\0',3,1.50,1112,'g','Carne Picada','Ninguno','3bf1f114-e194-434e-a3de-7ed646f61c68_carne_ing.png','Extra'),(_binary '\0',_binary '',4,1.00,800,'g','ChampiÃ±ones','Ninguno','99600c94-598c-460e-a4ad-abe749c514e5_champinones_ing.png','Extra'),(_binary '\0',_binary '\0',5,1.00,493,'g','Mozzarella','Lacteos','c4b97888-37eb-4204-a535-253be4c3e9ec_mozzarella_ing.png','Extra'),(_binary '\0',_binary '',6,0.50,1299,'ml','Salsa de Tomate','Ninguno','cf8faec5-b461-42c8-bbf0-f68ffc52e361_tomate_salsa.png','Salsa'),(_binary '\0',_binary '\0',7,1.00,760,'g','Pepperoni','Ninguno','c05ef977-7dca-42a7-a647-417046107fd9_pepperoni_ing.png','Extra');
/*!40000 ALTER TABLE `ingredientes` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-09-05 12:59:51

-- Table structure for table `masas`
--

DROP TABLE IF EXISTS `masas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `masas` (
  `id_masa` int NOT NULL AUTO_INCREMENT,
  `precio_extra` decimal(38,2) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_masa`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `masas`
--

LOCK TABLES `masas` WRITE;
/*!40000 ALTER TABLE `masas` DISABLE KEYS */;
INSERT INTO `masas` VALUES (1,0.00,'Fina'),(2,0.00,'ClÃ¡sica'),(3,2.00,'Bordes de queso');
/*!40000 ALTER TABLE `masas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-09-05 12:59:51

-- Table structure for table `tamanos`
--

DROP TABLE IF EXISTS `tamanos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tamanos` (
  `id_tamano` int NOT NULL AUTO_INCREMENT,
  `precio_extra` decimal(38,2) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_tamano`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tamanos`
--

LOCK TABLES `tamanos` WRITE;
/*!40000 ALTER TABLE `tamanos` DISABLE KEYS */;
INSERT INTO `tamanos` VALUES (1,0.00,'Mediana'),(2,3.00,'Familiar');
/*!40000 ALTER TABLE `tamanos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-09-05 12:59:51

-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos` (
  `id_cat` int NOT NULL,
  `id_prod` int NOT NULL AUTO_INCREMENT,
  `precio_base` decimal(38,2) DEFAULT NULL,
  `nombre` varchar(100) NOT NULL,
  `descripcion` text,
  `imagen` varchar(255) DEFAULT NULL,
  `descatalogado` bit(1) NOT NULL,
  PRIMARY KEY (`id_prod`),
  KEY `FKppl4ce770ucrka3jb4lbisvd2` (`id_cat`),
  CONSTRAINT `FKppl4ce770ucrka3jb4lbisvd2` FOREIGN KEY (`id_cat`) REFERENCES `categorias` (`id_cat`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (1,1,10.50,'Pizza barbacoa','La mejor barbacoa de la ciudad!!','7c935c07-faf5-4372-9551-4bd99b4d46c1_bbq_pizza.png',_binary '\0'),(1,2,9.50,'Pizza pepperoni','Autentico pepperoni italiano','00b7201d-eb6c-4fb2-a942-297c8a14d861_pepperoni_pizza.png',_binary '\0'),(2,3,1.50,'Agua','Botella de agua mineral de 500ml','67efaf48-a55e-498e-986e-384d21a67832_agua.png',_binary '\0'),(2,14,6.00,'Pizza serrana','dasdas','c5c0fd85-7247-4c3c-b12c-dac8bf15f095_texas_pizza.png',_binary '');
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-09-05 12:59:51

-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `id_cliente` int NOT NULL AUTO_INCREMENT,
  `tlf` varchar(20) DEFAULT NULL,
  `ape1` varchar(50) DEFAULT NULL,
  `ape2` varchar(50) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `pass` varchar(255) NOT NULL,
  PRIMARY KEY (`id_cliente`),
  UNIQUE KEY `UK1c96wv36rk2hwui7qhjks3mvg` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,'600123456','Perez','Gomez','prueba@pizza.com','Usuario','$2b$12$pQY/mMXRDq2NQQ/a7D1V1.MHRQAiRoJqm7Ge4GMzfKjJ6BZ4C3YQ6'),(12,'22',NULL,NULL,'lidiadmdt@gmail.com','Lidia del Moral','$2a$10$dm/EaNUq6J0DJx4K19N0iuQ/JkDpBIHTk8NAh6kXDQhJPn6J3kmo.'),(13,'685175843',NULL,NULL,'lidia@gmail.com','Lidia','$2a$10$7YQrJdcVJvhZdauN4pT9nubft6R2wYSScDhDFUBgvlT3XRdAHYLKC'),(14,'685175843',NULL,NULL,'lid@gmail.com','aaa','$2a$10$gh/6yzclgS1oW/5D1c/mCu2D9Pd7xVhATlBfzGTRJomoK8V.6bGa6'),(15,'685175843',NULL,NULL,'prueba@gmail.com','pruebaa','$2a$10$t5O8eUeJrM/hMSjQPvTljecbdgerDnYnTBHxxHtp2VTvRYDHQLXhG'),(16,'+34664450420',NULL,NULL,'jm.delmoralg@gmail.com','hvyh','$2a$10$acRk8JHPV4dFvxjglXPpauMwj/XWI3Cb7VfbF/pFpjeWh1z6lMBji'),(17,NULL,NULL,NULL,'aaa@gmail.com',NULL,'$2a$10$89dfNfw7XuEsbPvYuR8ev.nGwlSacskctXrE0409HnM3.bX3VLVJe'),(18,NULL,NULL,NULL,'lidia15yt@gmail.com',NULL,'$2a$10$OOt3dgvvIGleZzGzYOVVw.Ab6L5vxDtkeIJlidwAsdLs1nkT7CmEK'),(19,NULL,NULL,NULL,'pedrito@gmail.com',NULL,'$2a$10$oCdi6RSH342jAGiHhmonteJijuKmN0qJ44M9I6YaPUk4ywmkFkJZy'),(20,NULL,NULL,NULL,'lidiaaa@gmail.com',NULL,'$2a$10$3C/lr97MD8NRhvMm32NU4OnJOQ5Q5mMVPfhLdZkauH7VCPR5x.nJO'),(21,'685175843',NULL,NULL,'pruebaa@gmail.com','Lidia del Moral','$2a$10$F9NAzsB3MtDcZnryBYoCC.T0TlgXzV2EU3Urc3iIUs5jc6msxLJsm');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-09-05 12:59:51

-- Table structure for table `direcciones`
--

DROP TABLE IF EXISTS `direcciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `direcciones` (
  `id_cliente` int NOT NULL,
  `id_dir` int NOT NULL AUTO_INCREMENT,
  `numero` int DEFAULT NULL,
  `bloque` varchar(10) DEFAULT NULL,
  `planta` varchar(10) DEFAULT NULL,
  `portal` varchar(10) DEFAULT NULL,
  `puerta` varchar(10) DEFAULT NULL,
  `nombre_via` varchar(100) NOT NULL,
  `activo` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id_dir`),
  KEY `FKe32spd6mjtedm9iolngn2uk7g` (`id_cliente`),
  CONSTRAINT `FKe32spd6mjtedm9iolngn2uk7g` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `direcciones`
--

LOCK TABLES `direcciones` WRITE;
/*!40000 ALTER TABLE `direcciones` DISABLE KEYS */;
INSERT INTO `direcciones` VALUES (1,1,123,'A','2','1','B','Calle Falsa',1),(12,2,1,'','','','','Calle Fuente del Peral',0),(13,3,1,'','','','','Calle Fuente del Peral',1),(14,4,1,'','','','','Calle Fuente del Peral',1),(12,5,5,'10','4','','D','Calle Fuente del Alamillo',1),(12,6,1,'','4','10','d','Calle Fuente del Peral',0),(12,7,1,'10','4','10','d','Calle Fuente del Peral',0),(12,8,1,'2','3','2','1','Calle Fuente del Peral',0),(12,9,1,'4','2','3','1','Calle Fuente del Peral',0),(12,10,1,'5','2','1','3','Calle Fuente del Peral',0),(16,11,1,'','','','','Calle Fuente del Peral',0),(16,12,1,'','','','y','Calle Fuente del Peral',1),(12,13,1,'2','4','3','5','Calle Fuente del Peral',0),(12,14,1600,'','','','','Amphitheatre Parkway',0),(12,15,1600,'','','','','Amphitheatre Parkway',0),(12,16,1600,'','','','','Amphitheatre Parkway',0),(12,17,1600,'','','','','Amphitheatre Parkway',0),(21,18,1,'','','','','Calle Fuente del Peral',0),(21,19,1,'10','s','4','3','Calle Fuente del Peral',1),(21,20,1,'1','','a','','Calle Fuente del Peral',1),(15,21,1,'','','','','Calle Fuente del Peral',1);
/*!40000 ALTER TABLE `direcciones` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-09-05 12:59:51

-- Table structure for table `empleados`
--

DROP TABLE IF EXISTS `empleados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleados` (
  `fecha_alta` date DEFAULT NULL,
  `fecha_baja` date DEFAULT NULL,
  `id_empleado` int NOT NULL AUTO_INCREMENT,
  `salario` double DEFAULT NULL,
  `dni` varchar(20) NOT NULL,
  `ssn` varchar(50) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `pass` varchar(255) NOT NULL,
  `disponible` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id_empleado`),
  UNIQUE KEY `UKgdkxcplgdjv6ny0g0vu8f8dcm` (`dni`),
  UNIQUE KEY `UK6fdpo2x5rmegfbngre7xb3yoh` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleados`
--

LOCK TABLES `empleados` WRITE;
/*!40000 ALTER TABLE `empleados` DISABLE KEYS */;
INSERT INTO `empleados` VALUES (NULL,NULL,1,0,'1111','2132313','chef@pizza.com','Mario','$2a$12$iKVptRjDPdMpY4uNChlbpuqHL/doJuWE/lAClJw2S72omAp3cPyRa',_binary '\0'),('2026-03-20',NULL,2,1200,'87654321B',NULL,'moto@pizza.com','Luigi','$2a$12$Vtiy8oIspy1/kagwaMfPSegmkLdjDTAOGyBU8oAqUs1x0oiYupwXS',_binary '\0'),(NULL,NULL,7,0,'111','111111','dasdsa@gmail.com','Prueba','$2a$10$rLwiENottMgA6jApURQnLefedtZHFFVLba/yX6Oz3gJ65Y2Vrn5Ha',_binary '');
/*!40000 ALTER TABLE `empleados` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-09-05 12:59:51

-- Table structure for table `empleado_rol`
--

DROP TABLE IF EXISTS `empleado_rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleado_rol` (
  `id_empleado` int NOT NULL,
  `id_rol` int NOT NULL,
  KEY `FKibo0l6c36pt9rd7d6hhornt70` (`id_rol`),
  KEY `FKaxy0f4p6pfpn4ap8mifogo7ke` (`id_empleado`),
  CONSTRAINT `FKaxy0f4p6pfpn4ap8mifogo7ke` FOREIGN KEY (`id_empleado`) REFERENCES `empleados` (`id_empleado`),
  CONSTRAINT `FKibo0l6c36pt9rd7d6hhornt70` FOREIGN KEY (`id_rol`) REFERENCES `roles` (`id_rol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleado_rol`
--

LOCK TABLES `empleado_rol` WRITE;
/*!40000 ALTER TABLE `empleado_rol` DISABLE KEYS */;
INSERT INTO `empleado_rol` VALUES (2,2),(1,1),(1,2),(7,1);
/*!40000 ALTER TABLE `empleado_rol` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-09-05 12:59:50

-- Table structure for table `productos_ingredientes`
--

DROP TABLE IF EXISTS `productos_ingredientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos_ingredientes` (
  `cantidad` decimal(38,2) DEFAULT NULL,
  `id_ing` int NOT NULL,
  `id_prod` int NOT NULL,
  PRIMARY KEY (`id_ing`,`id_prod`),
  KEY `FKb5jdexxtqnjy3rrtwaxfp76lw` (`id_prod`),
  CONSTRAINT `FKb5jdexxtqnjy3rrtwaxfp76lw` FOREIGN KEY (`id_prod`) REFERENCES `productos` (`id_prod`),
  CONSTRAINT `FKe440tbhnievjwxf3w9mu38duc` FOREIGN KEY (`id_ing`) REFERENCES `ingredientes` (`id_ing`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos_ingredientes`
--

LOCK TABLES `productos_ingredientes` WRITE;
/*!40000 ALTER TABLE `productos_ingredientes` DISABLE KEYS */;
INSERT INTO `productos_ingredientes` VALUES (1.00,1,1),(5.00,2,1),(100.00,5,2),(50.00,6,2),(60.00,7,2);
/*!40000 ALTER TABLE `productos_ingredientes` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-09-05 12:59:50

-- Table structure for table `productos_masas`
--

DROP TABLE IF EXISTS `productos_masas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos_masas` (
  `id_masa` int NOT NULL,
  `id_prod` int NOT NULL,
  KEY `FK9epvhr5cto94a2hlyll6mb2m5` (`id_masa`),
  KEY `FK2p8tu8doo9903a8lbn8qq91gu` (`id_prod`),
  CONSTRAINT `FK2p8tu8doo9903a8lbn8qq91gu` FOREIGN KEY (`id_prod`) REFERENCES `productos` (`id_prod`),
  CONSTRAINT `FK9epvhr5cto94a2hlyll6mb2m5` FOREIGN KEY (`id_masa`) REFERENCES `masas` (`id_masa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos_masas`
--

LOCK TABLES `productos_masas` WRITE;
/*!40000 ALTER TABLE `productos_masas` DISABLE KEYS */;
INSERT INTO `productos_masas` VALUES (1,2),(2,2),(3,2),(1,1),(3,1);
/*!40000 ALTER TABLE `productos_masas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-09-05 12:59:51

-- Table structure for table `productos_tamanos`
--

DROP TABLE IF EXISTS `productos_tamanos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos_tamanos` (
  `id_prod` int NOT NULL,
  `id_tamano` int NOT NULL,
  KEY `FKr3869crumj4g0xkaipsj987p1` (`id_tamano`),
  KEY `FKimk8g2ehe653xytwdpk31lfyi` (`id_prod`),
  CONSTRAINT `FKimk8g2ehe653xytwdpk31lfyi` FOREIGN KEY (`id_prod`) REFERENCES `productos` (`id_prod`),
  CONSTRAINT `FKr3869crumj4g0xkaipsj987p1` FOREIGN KEY (`id_tamano`) REFERENCES `tamanos` (`id_tamano`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos_tamanos`
--

LOCK TABLES `productos_tamanos` WRITE;
/*!40000 ALTER TABLE `productos_tamanos` DISABLE KEYS */;
INSERT INTO `productos_tamanos` VALUES (2,2),(1,1);
/*!40000 ALTER TABLE `productos_tamanos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-09-05 12:59:50

-- Table structure for table `pedidos`
--

DROP TABLE IF EXISTS `pedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedidos` (
  `id_atencion` int DEFAULT NULL,
  `id_cliente` int NOT NULL,
  `id_cocinero` int DEFAULT NULL,
  `id_dir` int NOT NULL,
  `id_pedido` int NOT NULL AUTO_INCREMENT,
  `id_repartidor` int DEFAULT NULL,
  `precio_total` decimal(38,2) DEFAULT NULL,
  `fecha` datetime(6) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_pedido`),
  KEY `FKbooerlgiail0l1fjbup8j5rai` (`id_atencion`),
  KEY `FKdnomiluem4t3x66t6b9aher47` (`id_cliente`) /*!80000 INVISIBLE */,
  KEY `FKgdatmhm208il1csycs3r5n6gl` (`id_cocinero`),
  KEY `FKkbd5gxqpy6c8r8fljyidnvk1w` (`id_dir`),
  KEY `FKmg6f0cgx8e5s0qja03hnxhtk9` (`id_repartidor`),
  CONSTRAINT `FKbooerlgiail0l1fjbup8j5rai` FOREIGN KEY (`id_atencion`) REFERENCES `empleados` (`id_empleado`),
  CONSTRAINT `FKdnomiluem4t3x66t6b9aher47` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id_cliente`),
  CONSTRAINT `FKgdatmhm208il1csycs3r5n6gl` FOREIGN KEY (`id_cocinero`) REFERENCES `empleados` (`id_empleado`),
  CONSTRAINT `FKkbd5gxqpy6c8r8fljyidnvk1w` FOREIGN KEY (`id_dir`) REFERENCES `direcciones` (`id_dir`),
  CONSTRAINT `FKmg6f0cgx8e5s0qja03hnxhtk9` FOREIGN KEY (`id_repartidor`) REFERENCES `empleados` (`id_empleado`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedidos`
--

LOCK TABLES `pedidos` WRITE;
/*!40000 ALTER TABLE `pedidos` DISABLE KEYS */;
INSERT INTO `pedidos` VALUES (NULL,1,1,1,1,2,16.70,'2026-03-20 19:30:00.000000','ENTREGADO'),(NULL,1,NULL,1,3,NULL,10.50,'2026-03-20 18:44:01.205162','ENTREGADO'),(NULL,1,NULL,1,4,NULL,9.50,'2026-03-20 18:44:38.079302','ENTREGADO'),(NULL,1,NULL,1,5,NULL,12.00,'2026-03-20 18:45:08.859644','ENTREGADO'),(NULL,1,NULL,1,6,NULL,15.50,'2026-03-20 18:45:54.722066','ENTREGADO'),(NULL,1,NULL,1,7,NULL,1.50,'2026-03-21 18:00:53.137686','ENTREGADO'),(NULL,1,NULL,1,8,NULL,3.00,'2026-04-08 16:56:25.952072','ENTREGADO'),(NULL,1,NULL,1,11,NULL,31.50,'2026-04-13 16:01:22.578339','ENTREGADO'),(NULL,1,NULL,1,12,NULL,25.50,'2026-04-14 17:24:43.368582','ENTREGADO'),(NULL,13,NULL,3,13,NULL,21.00,'2026-04-25 12:26:52.338930','ENTREGADO'),(NULL,14,NULL,4,14,NULL,21.00,'2026-04-25 12:30:16.292805','ENTREGADO'),(NULL,12,NULL,2,15,NULL,10.50,'2026-04-25 12:45:13.697762','ENTREGADO'),(NULL,12,NULL,5,16,NULL,17.20,'2026-04-25 12:50:54.716268','ENTREGADO'),(NULL,12,NULL,6,17,NULL,42.80,'2026-04-25 15:37:07.313165','ENTREGADO'),(NULL,12,NULL,6,18,NULL,46.50,'2026-04-25 15:50:25.866308','ENTREGADO'),(NULL,12,NULL,2,19,NULL,18.20,'2026-05-09 10:24:05.999817','ENTREGADO'),(NULL,12,NULL,2,20,NULL,1.50,'2026-05-09 12:29:21.286796','ENTREGADO'),(NULL,12,NULL,2,21,NULL,10.50,'2026-05-09 17:54:28.861053','ENTREGADO'),(NULL,12,NULL,7,22,NULL,10.50,'2026-05-09 18:26:42.292988','ENTREGADO'),(NULL,12,NULL,5,23,NULL,1.50,'2026-05-09 18:27:37.076820','ENTREGADO'),(NULL,12,NULL,5,24,NULL,12.00,'2026-05-09 19:18:44.251054','ENTREGADO'),(NULL,12,NULL,5,25,NULL,10.50,'2026-05-09 19:19:18.783466','ENTREGADO'),(NULL,16,NULL,12,26,NULL,1.50,'2026-05-09 20:10:20.138352','ENTREGADO'),(NULL,12,NULL,13,27,NULL,11.50,'2026-05-10 16:36:02.966052','ENTREGADO'),(NULL,12,NULL,13,28,NULL,42.00,'2026-05-10 16:42:57.976133','ENTREGADO'),(NULL,12,NULL,2,29,NULL,1.50,'2026-05-12 18:18:54.465359','ENTREGADO'),(NULL,1,NULL,1,30,NULL,5.00,'2026-05-13 18:48:08.919088','ENTREGADO'),(NULL,1,NULL,1,31,NULL,11.00,'2026-05-13 20:05:48.055140','ENTREGADO'),(NULL,12,NULL,1,32,NULL,21.00,'2026-05-15 19:55:02.558716','ENTREGADO'),(NULL,1,NULL,1,33,NULL,10.50,'2026-05-18 19:14:34.007016','ENTREGADO'),(NULL,12,NULL,1,34,NULL,15.30,'2026-05-19 12:22:07.152114','ENTREGADO'),(NULL,12,NULL,1,35,NULL,14.10,'2026-05-19 12:22:48.478114','ENTREGADO'),(NULL,12,NULL,1,36,NULL,14.10,'2026-05-19 12:24:24.769562','ENTREGADO'),(NULL,1,NULL,1,37,NULL,1.50,'2026-05-19 21:01:03.221998','ENTREGADO'),(NULL,1,NULL,1,38,NULL,25.00,'2026-05-20 17:23:47.536938','ENTREGADO'),(NULL,12,NULL,1,39,NULL,1.50,'2026-05-20 17:57:33.081699','ENTREGADO'),(NULL,12,NULL,1,40,NULL,3.00,'2026-05-20 18:03:28.417664','ENTREGADO'),(NULL,12,NULL,5,41,NULL,10.50,'2026-05-20 18:44:57.205622','ENTREGADO'),(NULL,12,NULL,5,42,NULL,10.50,'2026-05-20 19:14:40.815914','ENTREGADO'),(NULL,12,NULL,5,43,NULL,17.90,'2026-05-20 20:16:20.937088','ENTREGADO'),(NULL,1,1,1,44,NULL,26.50,'2026-05-20 20:23:09.798984','PREPARANDO'),(NULL,15,NULL,21,45,2,26.00,'2026-05-21 17:28:03.962053','ENCAMINO'),(NULL,21,NULL,18,46,NULL,10.50,'2026-05-21 17:32:24.870084','ENCAMINO');
/*!40000 ALTER TABLE `pedidos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-09-05 12:59:50

-- Table structure for table `detalle_pedido`
--

DROP TABLE IF EXISTS `detalle_pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_pedido` (
  `cantidad` int NOT NULL,
  `id_detalle` int NOT NULL AUTO_INCREMENT,
  `id_masa` int DEFAULT NULL,
  `id_pedido` int NOT NULL,
  `id_prod` int NOT NULL,
  `id_tamano` int DEFAULT NULL,
  `precio_compra` decimal(38,2) NOT NULL,
  PRIMARY KEY (`id_detalle`),
  KEY `FKnehvd2jyfly4kfut33vdkqesi` (`id_masa`),
  KEY `FKh10qteor08f4cbxhsf97qtgyk` (`id_pedido`),
  KEY `FKo55nttr6rmtk5viuyarpj64ty` (`id_prod`),
  KEY `FKjuin8e0fu8ekpvxvvgoydy52h` (`id_tamano`),
  CONSTRAINT `FKh10qteor08f4cbxhsf97qtgyk` FOREIGN KEY (`id_pedido`) REFERENCES `pedidos` (`id_pedido`),
  CONSTRAINT `FKjuin8e0fu8ekpvxvvgoydy52h` FOREIGN KEY (`id_tamano`) REFERENCES `tamanos` (`id_tamano`),
  CONSTRAINT `FKnehvd2jyfly4kfut33vdkqesi` FOREIGN KEY (`id_masa`) REFERENCES `masas` (`id_masa`),
  CONSTRAINT `FKo55nttr6rmtk5viuyarpj64ty` FOREIGN KEY (`id_prod`) REFERENCES `productos` (`id_prod`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_pedido`
--

LOCK TABLES `detalle_pedido` WRITE;
/*!40000 ALTER TABLE `detalle_pedido` DISABLE KEYS */;
INSERT INTO `detalle_pedido` VALUES (1,1,3,1,1,2,16.70),(1,2,3,3,1,2,10.50),(1,3,3,4,2,1,9.50),(1,4,1,5,1,1,12.00),(1,5,3,6,1,2,15.50),(1,6,NULL,7,3,NULL,1.50),(2,7,NULL,8,3,NULL,3.00),(3,8,1,11,1,1,31.50),(2,9,NULL,12,3,NULL,3.00),(1,10,3,12,1,2,22.50),(2,11,1,13,1,1,21.00),(2,12,1,14,1,1,21.00),(1,13,1,15,1,1,10.50),(1,14,1,16,1,2,17.20),(2,15,1,17,1,1,42.80),(1,16,1,18,1,1,46.50),(1,17,1,19,1,2,18.20),(1,18,NULL,20,3,NULL,1.50),(1,19,1,21,1,1,10.50),(1,20,1,22,1,1,10.50),(1,21,NULL,23,3,NULL,1.50),(1,22,NULL,24,3,NULL,1.50),(1,23,1,24,1,1,10.50),(1,24,1,25,1,1,10.50),(1,25,NULL,26,3,NULL,1.50),(1,26,3,27,2,1,11.50),(4,27,1,28,1,1,42.00),(1,28,NULL,29,3,NULL,1.50),(1,30,NULL,31,2,NULL,11.00),(2,31,1,32,1,1,21.00),(1,32,NULL,33,1,NULL,10.50),(1,33,1,34,1,1,15.30),(1,34,1,35,1,1,14.10),(1,35,1,36,1,1,14.10),(1,36,NULL,37,3,NULL,1.50),(2,37,NULL,38,2,2,25.00),(1,38,NULL,39,3,NULL,1.50),(2,39,NULL,40,3,NULL,3.00),(1,40,1,41,1,1,10.50),(1,41,1,42,1,1,10.50),(1,42,3,43,1,2,17.90),(2,43,3,44,1,1,25.00),(1,44,NULL,44,3,NULL,1.50),(2,45,1,45,1,1,26.00),(1,46,1,46,1,1,10.50);
/*!40000 ALTER TABLE `detalle_pedido` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-09-05 12:59:51

-- Table structure for table `detalle_pedido_ingrediente`
--

DROP TABLE IF EXISTS `detalle_pedido_ingrediente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_pedido_ingrediente` (
  `cantidad` int NOT NULL,
  `id_detalle` int NOT NULL,
  `id_detalle_ing` int NOT NULL AUTO_INCREMENT,
  `id_ing` int NOT NULL,
  PRIMARY KEY (`id_detalle_ing`),
  KEY `FKjl982hpgydscdxiefoelik0ru` (`id_detalle`),
  KEY `FK89b6bj7esx9cm7rlex2g43cso` (`id_ing`),
  CONSTRAINT `FK89b6bj7esx9cm7rlex2g43cso` FOREIGN KEY (`id_ing`) REFERENCES `ingredientes` (`id_ing`),
  CONSTRAINT `FKjl982hpgydscdxiefoelik0ru` FOREIGN KEY (`id_detalle`) REFERENCES `detalle_pedido` (`id_detalle`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_pedido_ingrediente`
--

LOCK TABLES `detalle_pedido_ingrediente` WRITE;
/*!40000 ALTER TABLE `detalle_pedido_ingrediente` DISABLE KEYS */;
INSERT INTO `detalle_pedido_ingrediente` VALUES (1,1,1,1),(3,4,2,2),(2,10,3,2),(5,10,4,1),(1,14,5,5),(1,14,6,3),(1,14,7,1),(4,15,8,2),(2,15,9,1),(3,15,10,3),(2,15,11,5),(72,16,12,2),(2,17,13,2),(1,17,14,5),(1,17,15,3),(1,17,16,1),(1,30,17,5),(1,30,18,6),(4,33,19,1),(3,34,20,1),(3,35,21,1),(2,42,22,1),(5,45,23,2);
/*!40000 ALTER TABLE `detalle_pedido_ingrediente` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-09-05 12:59:50



SET TIME_ZONE=@OLD_TIME_ZONE;
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT;
SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS;
SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION;
SET SQL_NOTES=@OLD_SQL_NOTES;

-- Dump completed
