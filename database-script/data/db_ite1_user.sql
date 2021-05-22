-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: localhost    Database: db_ite1
-- ------------------------------------------------------
-- Server version	8.0.25

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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `image` varchar(200) DEFAULT NULL,
  `fullname` varchar(45) NOT NULL,
  `gender` tinyint NOT NULL,
  `email` text NOT NULL,
  `roleid` tinyint NOT NULL,
  `address` text,
  `statusid` tinyint NOT NULL,
  `mobile` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `roleid` (`roleid`),
  KEY `statusid` (`statusid`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`roleid`) REFERENCES `role` (`id`),
  CONSTRAINT `user_ibfk_2` FOREIGN KEY (`statusid`) REFERENCES `status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'https://i.pinimg.com/originals/51/f6/fb/51f6fb256629fc755b8870c801092942.png','MrA',1,'abc@gmail.com',0,'VietNam',0,'0123456789'),(2,'https://i.pinimg.com/originals/51/f6/fb/51f6fb256629fc755b8870c801092942.png','MrB',0,'12@gmail.com',1,'VietNam',1,'1234567891'),(3,'https://i.pinimg.com/originals/51/f6/fb/51f6fb256629fc755b8870c801092942.png','MrC',0,'skjadh@fpt.vn',2,'America',0,'1234567894'),(4,'https://i.pinimg.com/originals/51/f6/fb/51f6fb256629fc755b8870c801092942.png','Griezmann',1,'griezzy@ins.vn',1,'France',0,'1234567894'),(5,'https://i.pinimg.com/originals/51/f6/fb/51f6fb256629fc755b8870c801092942.png','Tammy',1,'tammy@edu.vn',1,'England',1,'0123654789'),(6,'https://i.pinimg.com/originals/51/f6/fb/51f6fb256629fc755b8870c801092942.png','Greenwood',1,'greenwood@loveVN.vn',2,'VietNam',0,'1234567890'),(7,'https://i.pinimg.com/originals/51/f6/fb/51f6fb256629fc755b8870c801092942.png','Seo yea ji',0,'loveAV@yeuAnh',1,'Hankook',0,'0231456987'),(8,'https://i.pinimg.com/originals/51/f6/fb/51f6fb256629fc755b8870c801092942.png','Jeon ji huyn',0,'LoveAnhVu@gmail.com',2,'Korea',1,'1234567894'),(9,'https://i.pinimg.com/originals/51/f6/fb/51f6fb256629fc755b8870c801092942.png','M.Mount',1,'fanChelsea10years@gmail.com',2,'England',0,'0123564789'),(10,'https://i.pinimg.com/originals/51/f6/fb/51f6fb256629fc755b8870c801092942.png','Jorginho',0,'fcFanThay@chelseaNhayChansao.com',1,'italia',1,'1234561231');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-19 16:03:48
