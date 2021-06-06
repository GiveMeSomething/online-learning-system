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
-- Table structure for table `blog`
--

DROP TABLE IF EXISTS `blog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blog` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `authorid` int NOT NULL,
  `updateddate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `categoryid` int NOT NULL,
  `postid` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `categoryid` (`categoryid`),
  KEY `postid` (`postid`),
  CONSTRAINT `blog_ibfk_1` FOREIGN KEY (`categoryid`) REFERENCES `category` (`id`),
  CONSTRAINT `blog_ibfk_2` FOREIGN KEY (`postid`) REFERENCES `post` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog`
--

LOCK TABLES `blog` WRITE;
/*!40000 ALTER TABLE `blog` DISABLE KEYS */;
INSERT INTO `blog` VALUES (1,'LoveSeoYeaJi',1,'2021-05-18 22:48:30',1,1),(2,'Griezmann Backs to Atletico',2,'2021-05-18 22:49:26',2,2);
/*!40000 ALTER TABLE `blog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `categoryName` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Software Engineering'),(2,'Economy'),(3,'Digital Marketing'),(4,'Artificial Intelligence'),(5,'Information Assurance'),(6,'Language');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `id` int NOT NULL AUTO_INCREMENT,
  `thumbnail` text NOT NULL,
  `title` varchar(100) NOT NULL,
  `price` float NOT NULL,
  `description` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwYJYEt68wPJLkPtIRlmrGC8f0FW4kG0zxlw&usqp=CAU','PRJ301',149.9,'Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old.'),(2,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwYJYEt68wPJLkPtIRlmrGC8f0FW4kG0zxlw&usqp=CAU','PRN292',99.9,'Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old.'),(3,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwYJYEt68wPJLkPtIRlmrGC8f0FW4kG0zxlw&usqp=CAU','SWE201',129.99,'Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old.');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coursetag`
--

DROP TABLE IF EXISTS `coursetag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coursetag` (
  `courseid` int NOT NULL,
  `tagid` int NOT NULL,
  PRIMARY KEY (`courseid`,`tagid`),
  KEY `tagid` (`tagid`),
  CONSTRAINT `coursetag_ibfk_1` FOREIGN KEY (`courseid`) REFERENCES `course` (`id`),
  CONSTRAINT `coursetag_ibfk_2` FOREIGN KEY (`tagid`) REFERENCES `tag` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coursetag`
--

LOCK TABLES `coursetag` WRITE;
/*!40000 ALTER TABLE `coursetag` DISABLE KEYS */;
INSERT INTO `coursetag` VALUES (1,1),(3,1),(1,2),(2,3),(1,4),(3,4),(2,5);
/*!40000 ALTER TABLE `coursetag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post` (
  `id` int NOT NULL AUTO_INCREMENT,
  `thumbnail` text,
  `categoryid` int NOT NULL,
  `title` varchar(45) NOT NULL,
  `briefInfo` varchar(100) NOT NULL,
  `description` text NOT NULL,
  `feature` tinyint NOT NULL,
  `statusid` tinyint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `statusid` (`statusid`),
  KEY `categoryid` (`categoryid`),
  CONSTRAINT `post_ibfk_1` FOREIGN KEY (`statusid`) REFERENCES `status` (`id`),
  CONSTRAINT `post_ibfk_2` FOREIGN KEY (`categoryid`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (1,'https://w7.pngwing.com/pngs/908/570/png-transparent-white-and-blue-gear-logo-technical-support-information-technology-customer-service-tech-electronics-company-text-thumbnail.png',1,'tech','hahaha','This is fake, hihi',0,1),(2,'https://w7.pngwing.com/pngs/908/570/png-transparent-white-and-blue-gear-logo-technical-support-information-technology-customer-service-tech-electronics-company-text-thumbnail.png',2,'tech','hihihiihihhihi','hahahhashdhasjh',1,0);
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` tinyint NOT NULL,
  `roleName` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (0,'admin'),(1,'teacher'),(2,'student');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `status` (
  `id` tinyint NOT NULL,
  `value` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (0,'inactive'),(1,'active');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tag` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tagName` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES (1,'Tag 1'),(2,'Tag 2'),(3,'Tag 3'),(4,'Tag 4'),(5,'Tag 5'),(6,'Tag 6'),(7,'Tag 7'),(8,'Tag 8'),(9,'Tag 9'),(10,'Tag 10');
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

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

--
-- Table structure for table `user_course`
--

DROP TABLE IF EXISTS `user_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_course` (
  `userid` int NOT NULL,
  `courseid` int NOT NULL,
  `valid_from` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`userid`,`courseid`),
  KEY `courseid` (`courseid`),
  CONSTRAINT `user_course_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`id`),
  CONSTRAINT `user_course_ibfk_2` FOREIGN KEY (`courseid`) REFERENCES `course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_course`
--

LOCK TABLES `user_course` WRITE;
/*!40000 ALTER TABLE `user_course` DISABLE KEYS */;
INSERT INTO `user_course` VALUES (1,1,'2021-05-19 15:51:02'),(1,2,'2021-05-19 15:51:14'),(2,1,'2021-05-19 15:51:32'),(2,3,'2021-05-19 15:51:22'),(3,1,'2021-05-19 15:51:49'),(3,2,'2021-05-19 15:51:49'),(4,1,'2021-05-19 15:51:49'),(4,3,'2021-05-19 15:51:49');
/*!40000 ALTER TABLE `user_course` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-19 22:29:32
