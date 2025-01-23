-- MySQL dump 10.13  Distrib 9.0.1, for macos14.4 (arm64)
--
-- Host: localhost    Database: perpustakaan
-- ------------------------------------------------------
-- Server version	8.4.3

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `buku`
--

DROP TABLE IF EXISTS `buku`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `buku` (
  `kode_buku` char(4) NOT NULL,
  `judul` varchar(100) DEFAULT NULL,
  `pengarang` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `jumlah` int DEFAULT NULL,
  `kode_penerbit` char(2) DEFAULT NULL,
  PRIMARY KEY (`kode_buku`),
  KEY `buku_ibfk_1` (`kode_penerbit`),
  CONSTRAINT `buku_ibfk_1` FOREIGN KEY (`kode_penerbit`) REFERENCES `penerbit` (`kode_penerbit`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buku`
--

LOCK TABLES `buku` WRITE;
/*!40000 ALTER TABLE `buku` DISABLE KEYS */;
INSERT INTO `buku` VALUES ('11','Buku A10','Pengarang B',NULL,'5'),('12','Buku E11','Pengarang E',NULL,'2'),('13','Buku A12','Pengarang A',NULL,'5'),('14','Buku Baru Banget','Pengarang D',NULL,'1'),('15','Buku C14','Pengarang C',NULL,'4'),('16','Buku C15','Pengarang B',NULL,'5'),('17','Buku B16','Ahmad Sja',NULL,'1'),('18','Buku C17','Pengarang B',NULL,'3'),('19','Buku A18','Pengarang B',NULL,'5'),('2','Buku C1','Pengarang A',NULL,'3'),('20','Buku D19','Pengarang D',NULL,'2'),('21','Buku A20','Pengarang C',NULL,'4'),('22','Buku A21','Pengarang A',NULL,'2'),('23','Buku B22','Pengarang C',NULL,'3'),('24','Buku C23','Pengarang C',NULL,'5'),('25','Buku A24','Pengarang C',NULL,'1'),('26','Buku E25','Pengarang C',NULL,'5'),('27','Buku A26','Pengarang B',NULL,'3'),('28','Buku A27','Pengarang B',NULL,'2'),('29','Buku B28','Pengarang A',NULL,'3'),('3','Buku E2','Pengarang C',NULL,'3'),('30','Buku A29','Pengarang D',NULL,'2'),('31','Buku kita','Kamu dan mereka',NULL,'1'),('4','Buku E3','Pengarang A',NULL,'3'),('5','Buku E4','Pengarang E',NULL,'5'),('6','Buku B5','Pengarang B',NULL,'4'),('7','Buku B6','Pengarang E',NULL,'1'),('8','Buku E7','Pengarang A',NULL,'4'),('9','Buku A8','Pengarang D',NULL,'4');
/*!40000 ALTER TABLE `buku` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `buku_rak`
--

DROP TABLE IF EXISTS `buku_rak`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `buku_rak` (
  `id` int NOT NULL AUTO_INCREMENT,
  `rak` char(4) DEFAULT NULL,
  `buku` char(4) DEFAULT NULL,
  `qty` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `buku_rak_buku_FK` (`buku`),
  KEY `buku_rak_rak_FK` (`rak`),
  CONSTRAINT `buku_rak_buku_FK` FOREIGN KEY (`buku`) REFERENCES `buku` (`kode_buku`) ON DELETE CASCADE,
  CONSTRAINT `buku_rak_rak_FK` FOREIGN KEY (`rak`) REFERENCES `rak` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buku_rak`
--

LOCK TABLES `buku_rak` WRITE;
/*!40000 ALTER TABLE `buku_rak` DISABLE KEYS */;
/*!40000 ALTER TABLE `buku_rak` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `peminjam`
--

DROP TABLE IF EXISTS `peminjam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `peminjam` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nama` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `peminjam`
--

LOCK TABLES `peminjam` WRITE;
/*!40000 ALTER TABLE `peminjam` DISABLE KEYS */;
/*!40000 ALTER TABLE `peminjam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `penerbit`
--

DROP TABLE IF EXISTS `penerbit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `penerbit` (
  `kode_penerbit` char(2) NOT NULL,
  `nama_penerbit` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`kode_penerbit`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `penerbit`
--

LOCK TABLES `penerbit` WRITE;
/*!40000 ALTER TABLE `penerbit` DISABLE KEYS */;
INSERT INTO `penerbit` VALUES ('1','Penerbit A'),('2','Penerbit B'),('3','Penerbit C'),('4','Penerbit D'),('5','Penerbit E');
/*!40000 ALTER TABLE `penerbit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rak`
--

DROP TABLE IF EXISTS `rak`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rak` (
  `id` char(4) NOT NULL,
  `nama` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rak`
--

LOCK TABLES `rak` WRITE;
/*!40000 ALTER TABLE `rak` DISABLE KEYS */;
INSERT INTO `rak` VALUES ('1','Rak A'),('2','Rak B'),('3','Rak C'),('4','Rak D'),('5','Rak E');
/*!40000 ALTER TABLE `rak` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaksi`
--

DROP TABLE IF EXISTS `transaksi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaksi` (
  `id` int NOT NULL AUTO_INCREMENT,
  `peminjam` int DEFAULT NULL,
  `tanggal` datetime DEFAULT NULL,
  `buku` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `transaksi_buku_FK` (`buku`),
  KEY `transaksi_peminjam_FK` (`peminjam`),
  CONSTRAINT `transaksi_buku_FK` FOREIGN KEY (`buku`) REFERENCES `buku` (`kode_buku`) ON DELETE SET NULL,
  CONSTRAINT `transaksi_peminjam_FK` FOREIGN KEY (`peminjam`) REFERENCES `peminjam` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaksi`
--

LOCK TABLES `transaksi` WRITE;
/*!40000 ALTER TABLE `transaksi` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaksi` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-24  5:43:06
