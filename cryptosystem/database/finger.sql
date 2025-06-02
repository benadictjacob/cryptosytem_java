CREATE DATABASE  IF NOT EXISTS `fingerprint` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `fingerprint`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: fingerprint
-- ------------------------------------------------------
-- Server version	5.6.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `analysis`
--

DROP TABLE IF EXISTS `analysis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `analysis` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `no.finger` int(11) NOT NULL,
  `far` float NOT NULL,
  `u_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `analysis`
--

LOCK TABLES `analysis` WRITE;
/*!40000 ALTER TABLE `analysis` DISABLE KEYS */;
INSERT INTO `analysis` VALUES (1,1,0.2,1),(2,2,0.35,1),(3,3,0.6,1),(4,4,1,1);
/*!40000 ALTER TABLE `analysis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `downloadkey`
--

DROP TABLE IF EXISTS `downloadkey`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `downloadkey` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `use_id` int(11) NOT NULL,
  `key` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `use_id_idx` (`use_id`),
  CONSTRAINT `use_id` FOREIGN KEY (`use_id`) REFERENCES `registration` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `downloadkey`
--

LOCK TABLES `downloadkey` WRITE;
/*!40000 ALTER TABLE `downloadkey` DISABLE KEYS */;
INSERT INTO `downloadkey` VALUES (5,2,'46_22_74_-180.0_-180.0_0.0'),(6,2,'29_15_48_-180.0_-180.0_0.0'),(7,2,'71_37_144_-180.0_-180.0_90.0'),(8,2,'34_49_119_-180.0_-180.0_90.0'),(9,1,'46_22_74_-180.0_-180.0_0.0'),(10,1,'29_15_48_-180.0_-180.0_0.0'),(11,1,'71_37_144_-180.0_-180.0_90.0'),(12,1,'34_49_119_-180.0_-180.0_90.0');
/*!40000 ALTER TABLE `downloadkey` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `file`
--

DROP TABLE IF EXISTS `file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `file` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `filepath` varchar(200) NOT NULL,
  `log_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `log_id_idx` (`log_id`),
  CONSTRAINT `log_id` FOREIGN KEY (`log_id`) REFERENCES `registration` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file`
--

LOCK TABLES `file` WRITE;
/*!40000 ALTER TABLE `file` DISABLE KEYS */;
INSERT INTO `file` VALUES (27,'C:\\Users\\user\\Documents\\ansscore.txt',1),(28,'C:\\Users\\user\\Documents\\Cost Effective Authentic And Anonymous Data Sharing With Forward Security.docx',1);
/*!40000 ALTER TABLE `file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `keyss`
--

DROP TABLE IF EXISTS `keyss`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `keyss` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `key` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id_idx` (`user_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `registration` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `keyss`
--

LOCK TABLES `keyss` WRITE;
/*!40000 ALTER TABLE `keyss` DISABLE KEYS */;
/*!40000 ALTER TABLE `keyss` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registration`
--

DROP TABLE IF EXISTS `registration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `registration` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `location` varchar(45) NOT NULL,
  `need` varchar(45) NOT NULL,
  `user` varchar(45) NOT NULL,
  `pass` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registration`
--

LOCK TABLES `registration` WRITE;
/*!40000 ALTER TABLE `registration` DISABLE KEYS */;
INSERT INTO `registration` VALUES (1,'jasmimol','kochi','Occupational','jasmi','jasmi'),(2,'Aneesha','kochi','Legal','aneesha','aneesha');
/*!40000 ALTER TABLE `registration` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-02-09 13:10:21
