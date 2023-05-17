-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: web_phim
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;get_episodes_by_favorites
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accounts` (
  `account_name` varchar(50) NOT NULL,
  `password` varchar(500) NOT NULL,
  `is_enabled` tinyint DEFAULT '0',
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`account_name`),
  UNIQUE KEY `client_id_UNIQUE` (`user_id`),
  KEY `role_id_idx` (`role_id`),
  CONSTRAINT `role_id` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`) ON UPDATE CASCADE,
  CONSTRAINT `user_id_accounts` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES ('Hieu','$2a$10$kYqE9rSpYBry8kB.h6rPDuP4xY3x/I0kzL8x69HsAAI0naAbY7u3a',1,1,2),('Hieu1','$2a$10$mZMJO0ct6GcR8ytXR7zjD.kIFhREsX0kK.ww64dYscyIj3W1OSQtW',1,13,2),('nguyenvanb','$2a$10$kYqE9rSpYBry8kB.h6rPDuP4xY3x/I0kzL8x69HsAAI0naAbY7u3a',0,2,3),('nguyenvanc','$2a$10$kYqE9rSpYBry8kB.h6rPDuP4xY3x/I0kzL8x69HsAAI0naAbY7u3a',0,3,3),('nguyenvand','$2a$10$kYqE9rSpYBry8kB.h6rPDuP4xY3x/I0kzL8x69HsAAI0naAbY7u3a',0,4,3),('nguyenvane','$2a$10$kYqE9rSpYBry8kB.h6rPDuP4xY3x/I0kzL8x69HsAAI0naAbY7u3a',0,6,3),('nguyenvanf','$2a$10$lAD46Tz4ChQrBTEkdnu1l.5iNBDmIPzLaAikE3cEY1C0QVaHSzK3C',0,7,3),('nguyenvang','$2a$10$NIfUCidNvskAVBC4.2UHkekkRbtYGeq7xkF/T3.m7ug7iJEt3P7Eq',0,8,3),('nguyenvanh','$2a$10$uSMRyjDY28VN9.tFgY7K0u/Eh7PLVf.Jq/A1ku7QTOjq8976RB71.',0,9,3),('nguyenvani','$2a$10$EclK.XGmAoK/1ZKnF3gKReMJFmgi6plTVCioDlU.Hq/mt7ezoiyLi',1,12,3);
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actors`
--

DROP TABLE IF EXISTS `actors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `actors` (
  `actor_id` int NOT NULL AUTO_INCREMENT,
  `actor_name` varchar(45) NOT NULL,
  PRIMARY KEY (`actor_id`),
  UNIQUE KEY `actor_name_UNIQUE` (`actor_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actors`
--

LOCK TABLES `actors` WRITE;
/*!40000 ALTER TABLE `actors` DISABLE KEYS */;
/*!40000 ALTER TABLE `actors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(45) NOT NULL,
  PRIMARY KEY (`category_id`),
  UNIQUE KEY `category_name_UNIQUE` (`category_name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'Action'),(2,'Cartoon'),(4,'Family'),(3,'Horror'),(6,'Tragedy'),(5,'War');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments` (
  `comment_id` int NOT NULL AUTO_INCREMENT,
  `film_id` int NOT NULL,
  `account_name` varchar(50) NOT NULL,
  `comment_content` varchar(200) NOT NULL,
  `comment_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `parent_comment_id` int DEFAULT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `fk_comments_film_id_idx` (`film_id`),
  KEY `fk_comments_account_name_idx` (`account_name`),
  KEY `fk_comments_reply_idx` (`parent_comment_id`),
  CONSTRAINT `fk_comments_account_name` FOREIGN KEY (`account_name`) REFERENCES `accounts` (`account_name`) ON UPDATE CASCADE,
  CONSTRAINT `fk_comments_film_id` FOREIGN KEY (`film_id`) REFERENCES `films` (`film_id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_comments_reply` FOREIGN KEY (`parent_comment_id`) REFERENCES `comments` (`comment_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (1,1,'Hieu','hay','2023-05-15 12:10:41',NULL),(2,1,'Hieu','rely','2023-05-15 12:11:15',1),(3,1,'Hieu','rely1','2023-05-15 12:12:42',2),(10,1,'Hieu1','hay','2023-05-15 14:35:52',NULL),(11,1,'Hieu1','hay','2023-05-15 14:39:01',1),(12,2,'Hieu1','hay','2023-05-15 14:43:14',NULL),(13,1,'Hieu','Hay','2023-05-15 17:39:32',3),(14,2,'Hieu1','hay','2023-05-16 09:37:27',NULL),(15,2,'Hieu1','hay','2023-05-16 09:37:47',NULL),(16,1,'Hieu1','hay','2023-05-16 09:37:52',2),(17,1,'Hieu1','hay','2023-05-16 09:44:53',2);
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `directors`
--

DROP TABLE IF EXISTS `directors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `directors` (
  `director_id` int NOT NULL AUTO_INCREMENT,
  `director_name` varchar(45) NOT NULL,
  PRIMARY KEY (`director_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `directors`
--

LOCK TABLES `directors` WRITE;
/*!40000 ALTER TABLE `directors` DISABLE KEYS */;
INSERT INTO `directors` VALUES (1,'Brian De Palma');
/*!40000 ALTER TABLE `directors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discount_details`
--

DROP TABLE IF EXISTS `discount_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discount_details` (
  `discount_id` int NOT NULL,
  `film_package_id` int NOT NULL,
  PRIMARY KEY (`discount_id`,`film_package_id`),
  KEY `film_package_id_idx` (`film_package_id`),
  KEY `discount_details_film_package_id_idx` (`film_package_id`),
  CONSTRAINT `discount_details_film_package_id` FOREIGN KEY (`film_package_id`) REFERENCES `film_packages` (`film_package_id`) ON UPDATE CASCADE,
  CONSTRAINT `discount_id` FOREIGN KEY (`discount_id`) REFERENCES `discounts` (`discount_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discount_details`
--

LOCK TABLES `discount_details` WRITE;
/*!40000 ALTER TABLE `discount_details` DISABLE KEYS */;
INSERT INTO `discount_details` VALUES (2,5),(3,5),(5,5),(4,6);
/*!40000 ALTER TABLE `discount_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discounts`
--

DROP TABLE IF EXISTS `discounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discounts` (
  `discount_id` int NOT NULL AUTO_INCREMENT,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `discount_rate` float NOT NULL,
  PRIMARY KEY (`discount_id`),
  KEY `start_dateDESC_discounts` (`start_date` DESC)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discounts`
--

LOCK TABLES `discounts` WRITE;
/*!40000 ALTER TABLE `discounts` DISABLE KEYS */;
INSERT INTO `discounts` VALUES (1,'2022-03-03','2022-03-13',0.3),(2,'2022-04-03','2022-04-13',0.25),(3,'2023-04-26','2023-05-05',0.25),(4,'2023-04-20','2023-04-26',0.25),(5,'2023-04-20','2023-04-25',0.2);
/*!40000 ALTER TABLE `discounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `episodes`
--

DROP TABLE IF EXISTS `episodes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `episodes` (
  `episode_id` int NOT NULL AUTO_INCREMENT,
  `episode_path` varchar(200) NOT NULL,
  `film_id` int NOT NULL,
  `title` varchar(1000) NOT NULL,
  `cre_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`episode_id`),
  KEY `fk_episodes_film_id_idx` (`film_id`),
  CONSTRAINT `fk_episodes_film_id` FOREIGN KEY (`film_id`) REFERENCES `films` (`film_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `episodes`
--

LOCK TABLES `episodes` WRITE;
/*!40000 ALTER TABLE `episodes` DISABLE KEYS */;
INSERT INTO `episodes` VALUES (1,'adsd',2,'tap1','2023-05-17 15:23:17'),(2,'aad',1,'tap 1','2023-05-17 15:23:17'),(3,'asd',1,'tap2','2023-05-17 10:23:17'),(4,'http://localhost:8081/films/content/1dfbbbae-231e-4f8d-a30d-c34475728f57.mp4',6,'1','2023-05-17 15:23:17'),(5,'http://localhost:8081/films/content/56a063f4-9e8a-430a-b55d-1aa73f996fe9.mp4',6,'2','2023-05-17 15:23:17'),(6,'http://localhost:8081/films/content/a0254a68-e533-4f57-a402-af5e106351ae.mp4',6,'tap1','2023-05-17 17:45:51');
/*!40000 ALTER TABLE `episodes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evaluations`
--

DROP TABLE IF EXISTS `evaluations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evaluations` (
  `film_id` int NOT NULL,
  `account_name` varchar(50) NOT NULL,
  `star_number` int NOT NULL,
  `comment` varchar(200) NOT NULL DEFAULT '',
  PRIMARY KEY (`film_id`,`account_name`),
  KEY `FK_evaluations_filmid_idx` (`film_id`),
  KEY `FK_evaluations_account_name_idx` (`account_name`),
  CONSTRAINT `FK_evaluations_account_name` FOREIGN KEY (`account_name`) REFERENCES `accounts` (`account_name`) ON UPDATE CASCADE,
  CONSTRAINT `FK_evaluations_film_id` FOREIGN KEY (`film_id`) REFERENCES `films` (`film_id`) ON UPDATE CASCADE,
  CONSTRAINT `evaluations_chk_1` CHECK (((`star_number` >= 1) and (`star_number` <= 5)))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evaluations`
--

LOCK TABLES `evaluations` WRITE;
/*!40000 ALTER TABLE `evaluations` DISABLE KEYS */;
INSERT INTO `evaluations` VALUES (1,'Hieu',1,'hay');
/*!40000 ALTER TABLE `evaluations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favorites`
--

DROP TABLE IF EXISTS `favorites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `favorites` (
  `film_id` int NOT NULL,
  `account_name` varchar(45) NOT NULL,
  `cre_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`film_id`,`account_name`),
  KEY `fk_account_name_idx` (`account_name`),
  CONSTRAINT `fk_account_name` FOREIGN KEY (`account_name`) REFERENCES `accounts` (`account_name`),
  CONSTRAINT `fk_film_id` FOREIGN KEY (`film_id`) REFERENCES `films` (`film_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorites`
--

LOCK TABLES `favorites` WRITE;
/*!40000 ALTER TABLE `favorites` DISABLE KEYS */;
INSERT INTO `favorites` VALUES (1,'Hieu','2023-05-17 14:39:28'),(1,'Hieu1','2023-05-17 13:22:41'),(2,'Hieu','2023-05-17 13:22:33');
/*!40000 ALTER TABLE `favorites` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `film_packages`
--

DROP TABLE IF EXISTS `film_packages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `film_packages` (
  `film_package_id` int NOT NULL AUTO_INCREMENT,
  `used_time` int NOT NULL,
  `applicable_date` date NOT NULL,
  `price` int NOT NULL,
  PRIMARY KEY (`film_package_id`),
  KEY `applicable_dateDESC_used_timeASC` (`applicable_date` DESC,`used_time`),
  CONSTRAINT `film_packages_chk_price` CHECK ((`price` > 0))
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `film_packages`
--

LOCK TABLES `film_packages` WRITE;
/*!40000 ALTER TABLE `film_packages` DISABLE KEYS */;
INSERT INTO `film_packages` VALUES (1,1,'2019-01-01',10),(2,1,'2020-02-02',15),(3,3,'2019-01-01',30),(4,6,'2019-01-01',55),(5,3,'2020-01-01',45),(6,6,'2020-01-01',85);
/*!40000 ALTER TABLE `film_packages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `film_producers`
--

DROP TABLE IF EXISTS `film_producers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `film_producers` (
  `film_producer_id` int NOT NULL AUTO_INCREMENT,
  `film_producer_name` varchar(80) NOT NULL,
  PRIMARY KEY (`film_producer_id`),
  UNIQUE KEY `film_producer_name_UNIQUE` (`film_producer_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `film_producers`
--

LOCK TABLES `film_producers` WRITE;
/*!40000 ALTER TABLE `film_producers` DISABLE KEYS */;
INSERT INTO `film_producers` VALUES (1,'Tom Cruise');
/*!40000 ALTER TABLE `film_producers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `films`
--

DROP TABLE IF EXISTS `films`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `films` (
  `film_id` int NOT NULL AUTO_INCREMENT,
  `film_name` varchar(80) NOT NULL,
  `film_poster_path` varchar(150) NOT NULL,
  `trailer_path` varchar(150) NOT NULL,
  `film_description` varchar(2000) NOT NULL,
  `film_duration` int NOT NULL,
  `release_time` date NOT NULL,
  `odd_film` tinyint NOT NULL,
  `average_rating` float DEFAULT NULL,
  `film_producer_id` int NOT NULL,
  `nation_id` int NOT NULL,
  `director_id` int NOT NULL,
  PRIMARY KEY (`film_id`),
  UNIQUE KEY `film_name_UNIQUE` (`film_name`),
  KEY `film_producer_id_idx` (`film_producer_id`),
  KEY `nation_id_idx` (`nation_id`),
  KEY `director_id_idx` (`director_id`),
  CONSTRAINT `director_id` FOREIGN KEY (`director_id`) REFERENCES `directors` (`director_id`) ON UPDATE CASCADE,
  CONSTRAINT `film_producer_id` FOREIGN KEY (`film_producer_id`) REFERENCES `film_producers` (`film_producer_id`) ON UPDATE CASCADE,
  CONSTRAINT `nation_id` FOREIGN KEY (`nation_id`) REFERENCES `nations` (`nation_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `films`
--

LOCK TABLES `films` WRITE;
/*!40000 ALTER TABLE `films` DISABLE KEYS */;
INSERT INTO `films` VALUES (1,'Mission: Impossible','abc','abc','abc',120,'1996-03-03',1,0,1,1,1),(2,'Mission: Impossible 2','abc','abc','abc',120,'1998-02-02',1,0,1,1,1),(3,'a','http://localhost:8081/films/7332234c-cc58-405d-8e70-03d2b4fbb860.png','http://localhost:8081/films/9ab63ce3-9337-4e41-9235-09e8bf38a2c3.mp4','adsdsd',120,'2022-03-01',0,NULL,1,1,1),(5,'phimmoi','http://localhost:8081/films/6b15768e-05d8-48a6-8bba-a2ac346d2b50.png','http://localhost:8081/films/2aca6632-2a75-4aca-a99f-6e9c45012fa9.mp4','adsdsd',120,'2022-03-01',0,NULL,1,1,1),(6,'phimmoi1','http://localhost:8081/films/54149a4d-5b42-450a-a4fc-ea53e4710a9a.png','http://localhost:8081/films/b1086738-dcbd-4e4d-a497-da1fa7dc0103.mp4','adsdsd',120,'2022-03-01',0,NULL,1,1,1);
/*!40000 ALTER TABLE `films` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `films_actors`
--

DROP TABLE IF EXISTS `films_actors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `films_actors` (
  `film_id` int NOT NULL,
  `actor_id` int NOT NULL,
  PRIMARY KEY (`film_id`,`actor_id`),
  KEY `actor_id_idx` (`actor_id`),
  CONSTRAINT `actor_id` FOREIGN KEY (`actor_id`) REFERENCES `actors` (`actor_id`) ON UPDATE CASCADE,
  CONSTRAINT `movie_id` FOREIGN KEY (`film_id`) REFERENCES `films` (`film_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `films_actors`
--

LOCK TABLES `films_actors` WRITE;
/*!40000 ALTER TABLE `films_actors` DISABLE KEYS */;
/*!40000 ALTER TABLE `films_actors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `films_categories`
--

DROP TABLE IF EXISTS `films_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `films_categories` (
  `film_id` int NOT NULL,
  `category_id` int NOT NULL,
  PRIMARY KEY (`film_id`,`category_id`),
  KEY `category_id_idx` (`category_id`),
  CONSTRAINT `category_id` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`) ON UPDATE CASCADE,
  CONSTRAINT `film_id` FOREIGN KEY (`film_id`) REFERENCES `films` (`film_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `films_categories`
--

LOCK TABLES `films_categories` WRITE;
/*!40000 ALTER TABLE `films_categories` DISABLE KEYS */;
INSERT INTO `films_categories` VALUES (1,1),(2,1);
/*!40000 ALTER TABLE `films_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nations`
--

DROP TABLE IF EXISTS `nations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nations` (
  `nation_id` int NOT NULL AUTO_INCREMENT,
  `nation_name` varchar(60) NOT NULL,
  PRIMARY KEY (`nation_id`),
  UNIQUE KEY `nation_name_UNIQUE` (`nation_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nations`
--

LOCK TABLES `nations` WRITE;
/*!40000 ALTER TABLE `nations` DISABLE KEYS */;
INSERT INTO `nations` VALUES (1,'America');
/*!40000 ALTER TABLE `nations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchased_film_packages`
--

DROP TABLE IF EXISTS `purchased_film_packages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchased_film_packages` (
  `id` int NOT NULL AUTO_INCREMENT,
  `account_name` varchar(50) NOT NULL,
  `film_package_id` int NOT NULL,
  `purchase_date` date NOT NULL,
  `start_date` date NOT NULL,
  `expiration_date` date NOT NULL,
  `status` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`,`account_name`,`film_package_id`),
  KEY `account_name_purchased_film_packages_idx` (`account_name`),
  KEY `film_package_id_purchased_film_packages_idx` (`film_package_id`),
  CONSTRAINT `account_name_purchased_film_packages` FOREIGN KEY (`account_name`) REFERENCES `accounts` (`account_name`) ON UPDATE CASCADE,
  CONSTRAINT `film_package_id_purchased_film_packages` FOREIGN KEY (`film_package_id`) REFERENCES `film_packages` (`film_package_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchased_film_packages`
--

LOCK TABLES `purchased_film_packages` WRITE;
/*!40000 ALTER TABLE `purchased_film_packages` DISABLE KEYS */;
INSERT INTO `purchased_film_packages` VALUES (1,'nguyenvanc',2,'2023-01-01','2023-01-01','2023-01-31',1),(3,'nguyenvanc',2,'2023-05-01','2023-05-01','2023-05-31',1),(4,'nguyenvanc',2,'2023-05-01','2023-05-31','2023-06-30',0);
/*!40000 ALTER TABLE `purchased_film_packages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_name_UNIQUE` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (2,'ROLE_ADMIN'),(3,'ROLE_CLIENT'),(1,'ROLE_USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tokens`
--

DROP TABLE IF EXISTS `tokens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tokens` (
  `id` int NOT NULL AUTO_INCREMENT,
  `token` varchar(500) NOT NULL,
  `revoked` tinyint DEFAULT NULL,
  `expired` tinyint DEFAULT NULL,
  `account_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `token_UNIQUE` (`token`),
  KEY `account_name_tokens_idx` (`account_name`),
  CONSTRAINT `account_name_tokens` FOREIGN KEY (`account_name`) REFERENCES `accounts` (`account_name`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tokens`
--

LOCK TABLES `tokens` WRITE;
/*!40000 ALTER TABLE `tokens` DISABLE KEYS */;
INSERT INTO `tokens` VALUES (1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuZ3V5ZW52YW5iIiwiaWF0IjoxNjgxOTc5ODcyLCJleHAiOjE2ODI1ODQ2NzJ9.Y09TZs72rRZnTWcq15YNzvYKo4G1DoiDCMZLrdzE8DNPtu9hNf4EawPz8i9TrSNLqI4bEralQ2voN04Tqiw76Q',1,1,'nguyenvanb'),(2,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuZ3V5ZW52YW5jIiwiaWF0IjoxNjgyMDQ1NTE4LCJleHAiOjE2ODI2NTAzMTh9.8ZyFvX6eidKRzddm-9sWsjX69Qbepl-3CayxidzZFkynFqfZAZzABX7PV_YWVK1f3zJwyc0Z6Q_Daubt-wCyNQ',1,1,'nguyenvanc'),(3,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuZ3V5ZW52YW5iIiwiaWF0IjoxNjgyMDQ1NTY2LCJleHAiOjE2ODI2NTAzNjZ9.0NWhcN15Szj_kpzdqfaXSnfpsVy_t7DGz4pIYytlywmLHtwN5Z9nI1JGcjTkyE3a7x4vCyDJqFyDHeKshMj4TA',1,1,'nguyenvanb'),(4,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuZ3V5ZW52YW5iIiwiaWF0IjoxNjgyMDQ1NjQyLCJleHAiOjE2ODI2NTA0NDJ9.sy3OfX21D9edwejQeJV3BVe_9VzL_53sviA8mmlKtSrOqpI3IvGAFqWmcI_fUMhoOaAjHoERVdDC_D8xh8hLYQ',1,1,'nguyenvanb'),(5,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuZ3V5ZW52YW5jIiwiaWF0IjoxNjgyMDQ1NjY4LCJleHAiOjE2ODI2NTA0Njh9.XChTNEBvd9JQNFCpUMY7KucfRYW-uc-nOznfTpiLyhHOTB_R_hp75kRkwVcU0fN-z-c6uaplkjOfM5sN-xRP2w',1,1,'nguyenvanc'),(6,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuZ3V5ZW52YW5jIiwiaWF0IjoxNjgyMDQ1NzYxLCJleHAiOjE2ODI2NTA1NjF9.4q-VhvceadAGuBgIXyqT0Uj7QQxeSGa4ypuZyOzVbgbC-dGbUoAt0g0y0-QOsWs_059MbYZiZbsRzwOA3Vcqiw',1,1,'nguyenvanc'),(7,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuZ3V5ZW52YW5jIiwiaWF0IjoxNjgyMDgzNzIyLCJleHAiOjE2ODI2ODg1MjJ9.sS9VrJ1IhEwuQkgrf3-kmM-CKt4i96ZbiLdY-BSBQt-Vlpu_xcSr6gQfqMNoxuECHEXR8rEJF2zvh2oCTk6WDw',1,1,'nguyenvanc'),(8,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuZ3V5ZW52YW5jIiwiaWF0IjoxNjgyMDgzODk2LCJleHAiOjE2ODI2ODg2OTZ9.CLWKyJzl3fSXSC9oH5tWXRTC-mK4NU4m1F62DSCu9iDDWEuT1Ow1OZ8LOdaalmvYdisC1KsgKPzsOka0tIbzQg',1,1,'nguyenvanc'),(9,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuZ3V5ZW52YW5jIiwiaWF0IjoxNjgyMDg0MzY5LCJleHAiOjE2ODI2ODkxNjl9.4QmBOafnFO9ijS2w3cMHFQLbYU2pUMU9NBNvsJBz268DrLUZ2V2iYeOef4mchqJPnfUQ-vtb18u4zWIl9pojGA',0,0,'nguyenvanc'),(10,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuZ3V5ZW52YW5iIiwiaWF0IjoxNjgyMDg1Mzc5LCJleHAiOjE2ODI2OTAxNzl9.ljqC3N_NZ7eEncx4Ge4R2Z1BwxixCcwlATQbFTxAofWhh6Tf0t5OUD8ZfoYIM3bpNpLw1rPHhFtH-GLYXIpXLA',0,0,'nguyenvanb'),(11,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuZ3V5ZW52YW5nIiwiaWF0IjoxNjgyNTc0NDg2LCJleHAiOjE2ODI2NjA4ODZ9.0E0BDI5NIDPHYQ15s4ZlgyaMczbJAm2t1n1onYiSZh_VX4BwOmZxXGivsP1ChQ2oIN1ku1eeLruOvp4xC5SnDQ',0,0,'nguyenvang'),(12,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuZ3V5ZW52YW5oIiwiaWF0IjoxNjgyNTc0NTE4LCJleHAiOjE2ODI2NjA5MTh9.IJdEFtJbkBhQOtsO8Lm7qA5yM_wARhmHLZ-OLNXFph0hrCynnlACLgldxDZkgj0Jx9VxHv1KfkvHxpqrKFhqPQ',0,0,'nguyenvanh'),(15,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuZ3V5ZW52YW5pIiwiaWF0IjoxNjgzMDEyOTQyLCJleHAiOjE2ODMwOTkzNDJ9.8XmmhUMeeVFctEzaqHDkA3I4clZZrmNpdpzRDYIVkAVUQNt7FOA4pmpQuh4Egmd0nxS9wpomzASOcVBkAP5qAQ',1,1,'nguyenvani'),(16,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuZ3V5ZW52YW5pIiwiaWF0IjoxNjgzMDEyOTgwLCJleHAiOjE2ODM2MTc3ODB9.dGi0b7EhcrxpTQDfD2SaFPqLLR4XVJ-8lvyFCk2c97DLlhwuCSyYUsiHoxcjhna8beArZLMR_lMIVuaouoPYeg',0,0,'nguyenvani'),(17,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJIaWV1MSIsImlhdCI6MTY4NDEzNTcwMiwiZXhwIjoxNjg0MjIyMTAyfQ.LWsUUVTq9VEk3tLs5-YhJ_2PdhCeWs0DFEtkqMOwZVlWu8iW6iIeRMkkzlBnBl3Lr2GqUY_XogA48L1xrhfXxg',1,1,'Hieu1'),(18,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJIaWV1MSIsImlhdCI6MTY4NDEzNTgxMSwiZXhwIjoxNjg0NzQwNjExfQ.z0yzys4o0AjUm5_HhRhPwaF3WHlozhD03-qlbQa5ROgIUeSDU0cv0bs2Nj_bF-24Ii1bNzpWsWZ5y_nhR1kTaw',1,1,'Hieu1'),(19,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJIaWV1MSIsImlhdCI6MTY4NDIwNTA2OCwiZXhwIjoxNjg0ODA5ODY4fQ.PbYmnGOO4w4Wt5T-G3jO6Q-6s7I131Ez96z_2RnowJA68g2hjduh9tb1SQnWtgUO4NgBOq0_S7PeUFyYHOTFYQ',1,1,'Hieu1'),(20,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJIaWV1MSIsImlhdCI6MTY4NDMxMDgwMSwiZXhwIjoxNjg0OTE1NjAxfQ.WvZG518Wx40dV0FQA6x3qc7KGei0QsKuHXdlnjGho-Ihzqstq6WrnufeBGMhx6j9FScBk1ELFP4_jcqbZOS2_A',1,1,'Hieu1'),(21,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJIaWV1MSIsImlhdCI6MTY4NDMyMDIwNSwiZXhwIjoxNjg0OTI1MDA1fQ.h-PPt0PPmgeh4GNceIdcNDYQYvrkfKYL2PMoxHa1g5pouVlEXHzTkrkPVamnR8gjkHYFmJMD0NpSgglPqaAfUg',0,0,'Hieu1');
/*!40000 ALTER TABLE `tokens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL,
  `phone_number` varchar(45) DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  `sex` tinyint(1) NOT NULL DEFAULT '0',
  `birthdate` date NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `phone_UNIQUE` (`phone_number`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'nguyenvana','0969665842','nguyenvana@gmail.com',0,'2001-01-01'),(2,'nguyenvanb','0969665482','nguyenvanb@gmail.com',0,'2001-05-16'),(3,'nguyenvanc','0969665472','nguyenvanc@gmail.com',0,'2001-05-16'),(4,'nguyenvand','0969615472','nguyenvand@gmail.com',0,'2001-05-16'),(6,'nguyenvane','0949615472','nguyenvane@gmail.com',0,'2001-05-16'),(7,'nguyenvanf','0949615672','nguyenvanf@gmail.com',0,'2001-05-16'),(8,'nguyenvang',NULL,'nguyenvang@gmail.com',0,'2001-05-16'),(9,'nguyenvanh',NULL,'nguyenvanh@gmail.com',0,'2001-05-16'),(12,'nguyenvani','0965665842','ninhdng37@gmail.com',0,'2001-01-01'),(13,'Hieu1',NULL,'hieuhdhk@gmail.com',0,'2001-05-16');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'web_phim'
--
/*!50003 DROP PROCEDURE IF EXISTS `get_comments_for_film` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_comments_for_film`(IN id INT)
BEGIN
	WITH RECURSIVE comments_cte(comment_id, account_name, comment_content, parent_comment_id, level, path) AS (
		SELECT comment_id, account_name, comment_content, parent_comment_id, 0, CAST(comment_id AS CHAR(200))
		FROM comments WHERE parent_comment_id IS NULL and film_id = id
		UNION ALL
		SELECT c.comment_id,c.account_name, c.comment_content, c.parent_comment_id, p.level + 1, CONCAT(p.path, '-', c.comment_id)
		FROM comments c JOIN comments_cte p ON c.parent_comment_id = p.comment_id
	  )
	  SELECT * FROM comments_cte ORDER BY path;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `get_episodes_by_favorites` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_episodes_by_favorites`(IN  account_name TEXT)
BEGIN
	SELECT e.*
		FROM episodes e
		JOIN favorites f ON e.film_id = f.film_id
		WHERE f.account_name = account_name AND e.cre_at > f.cre_at
		ORDER BY e.cre_at;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `get_film_packages` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_film_packages`()
BEGIN
CREATE TEMPORARY TABLE B1 (
    FILM_PACKAGE_ID INT,
    USED_TIME INT,
    PRICE INT);

	INSERT INTO B1 (FILM_PACKAGE_ID, USED_TIME, PRICE)
	SELECT FILM_PACKAGE_ID, USED_TIME, PRICE
	FROM FILM_PACKAGES
	USE INDEX (applicable_dateDESC_used_timeASC)
	WHERE APPLICABLE_DATE <= CURDATE()
	LIMIT 3;
	
    CREATE TEMPORARY TABLE B2 (
    DISCOUNT_ID INT,
    FILM_PACKAGE_ID INT);
    
    INSERT INTO B2 (DISCOUNT_ID, FILM_PACKAGE_ID)
	(SELECT *
	FROM DISCOUNT_DETAILS
	WHERE FILM_PACKAGE_ID IN 
	(SELECT FILM_PACKAGE_ID
	FROM B1));
    
    CREATE TEMPORARY TABLE B3 (
    FILM_PACKAGE_ID INT,
    DISCOUNT_RATE float);
    
    INSERT INTO B3 (FILM_PACKAGE_ID, DISCOUNT_RATE)
    (SELECT B2.FILM_PACKAGE_ID, DISCOUNTS.DISCOUNT_RATE
    FROM B2
    INNER JOIN (SELECT * FROM DISCOUNTS WHERE START_DATE <= CURDATE() AND END_DATE >= CURDATE()) AS DISCOUNTS
    ON B2.DISCOUNT_ID = DISCOUNTS.DISCOUNT_ID);
    
    SELECT B3.DISCOUNT_RATE, B1.USED_TIME, B1.PRICE
    FROM B3
    RIGHT JOIN B1
    ON B3.FILM_PACKAGE_ID = B1.FILM_PACKAGE_ID;
    
    DROP TEMPORARY TABLE IF EXISTS B1;
    DROP TEMPORARY TABLE IF EXISTS B2;
    DROP TEMPORARY TABLE IF EXISTS B3;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `get_film_package_for_client` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_film_package_for_client`()
BEGIN
	SELECT * 
	FROM web_phim.purchased_film_packages
	WHERE start_date <= CURDATE() AND expiration_date >= CURDATE();
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-17 17:59:35
