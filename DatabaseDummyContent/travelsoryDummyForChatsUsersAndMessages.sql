-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: travelstory
-- ------------------------------------------------------
-- Server version	8.0.12

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

--
-- Table structure for table `chats`
--

DROP TABLE IF EXISTS `chats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `chats` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `chat_name` varchar(255) DEFAULT NULL,
  `chat_type` varchar(50) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `deleted` bit(1) NOT NULL,
  `deleted_at` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `creator_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpq3qpoyj06rgoine0tnpniqlw` (`creator_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chats`
--

LOCK TABLES `chats` WRITE;
/*!40000 ALTER TABLE `chats` DISABLE KEYS */;
INSERT INTO `chats` VALUES (1,'Vasya','PRIVATE_MESSAGES','2018-09-30 16:51:19',_binary '\0',NULL,NULL,1),(2,'Anastasiia Bug','PRIVATE_MESSAGES','2018-09-30 16:51:19',_binary '\0',NULL,NULL,3),(3,'Lv-339Java','PRIVATE_GROUP','2018-09-30 16:51:19',_binary '\0',NULL,'Test chat for Lv-339Java group.',1);
/*!40000 ALTER TABLE `chats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chats_connected_users`
--

DROP TABLE IF EXISTS `chats_connected_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `chats_connected_users` (
  `connected_chats_id` bigint(20) NOT NULL,
  `connected_users_id` bigint(20) NOT NULL,
  KEY `FKc7w5gq0ixe0iucj4jw2tn9ccf` (`connected_users_id`),
  KEY `FK8h3k1tdr94oyu0ujhew2h0vtl` (`connected_chats_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chats_connected_users`
--

LOCK TABLES `chats_connected_users` WRITE;
/*!40000 ALTER TABLE `chats_connected_users` DISABLE KEYS */;
INSERT INTO `chats_connected_users` VALUES (1,1),(1,2),(2,1),(2,2),(3,1),(3,2),(3,3);
/*!40000 ALTER TABLE `chats_connected_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `comments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comment_massage` varchar(255) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `media_id` bigint(20) DEFAULT NULL,
  `travel_story_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKevhuv6vfaappiegpp8fcty3vb` (`media_id`),
  KEY `FKrvd6qs9hgg3q2cip1cilfwdbq` (`travel_story_id`),
  KEY `FK8omq0tc18jd43bu5tjh6jvraq` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `likes`
--

DROP TABLE IF EXISTS `likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `likes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `media_id` bigint(20) DEFAULT NULL,
  `travel_story_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKm6eg265o1cuph01kpyiq4rssh` (`media_id`),
  KEY `FKi3ktl3l5t983vs4jd9ieluwh3` (`travel_story_id`),
  KEY `FKnvx9seeqqyy71bij291pwiwrg` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `likes`
--

LOCK TABLES `likes` WRITE;
/*!40000 ALTER TABLE `likes` DISABLE KEYS */;
/*!40000 ALTER TABLE `likes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medias`
--

DROP TABLE IF EXISTS `medias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `medias` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medias`
--

LOCK TABLES `medias` WRITE;
/*!40000 ALTER TABLE `medias` DISABLE KEYS */;
/*!40000 ALTER TABLE `medias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `messages` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  `deleted_at` datetime DEFAULT NULL,
  `last_edited_at` datetime DEFAULT NULL,
  `message_content` varchar(255) NOT NULL,
  `message_type` varchar(50) NOT NULL,
  `chat_id` bigint(20) NOT NULL,
  `media_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK64w44ngcpqp99ptcb9werdfmb` (`chat_id`),
  KEY `FK61efydggojsv10a08klouvsf8` (`media_id`),
  KEY `FKpsmh6clh3csorw43eaodlqvkn` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES (1,'2018-09-30 16:54:07',_binary '\0',NULL,NULL,'Lorem ipsum dolor sit amet','TEXT',1,NULL,1),(2,'2018-09-30 16:54:07',_binary '\0',NULL,NULL,'Lorem ipsum dolor sit amet','TEXT',1,NULL,2),(3,'2018-09-30 16:54:07',_binary '\0',NULL,NULL,' metus magna','TEXT',1,NULL,1),(4,'2018-09-30 16:54:07',_binary '\0',NULL,NULL,' ut','TEXT',1,NULL,2),(5,'2018-09-30 16:54:07',_binary '\0',NULL,NULL,'at molestie vitae. Duis nec mattis diam. Morbi vehicula','TEXT',1,NULL,2),(6,'2018-09-30 16:54:07',_binary '\0',NULL,NULL,'at molestie vitae. Duis nec mattis diam. Morbi vehicula','TEXT',1,NULL,1),(7,'2018-09-30 16:54:07',_binary '\0',NULL,NULL,'Cool','TEXT',1,NULL,2),(8,'2018-09-30 16:54:07',_binary '\0',NULL,NULL,'Nunc nec pulvinar erat. Vestibulum pulvinar','TEXT',2,NULL,1),(9,'2018-09-30 16:54:07',_binary '\0',NULL,NULL,'s. Nunc nec pulvinar erat. Vestibulum pulvinar','TEXT',2,NULL,3),(10,'2018-09-30 16:54:07',_binary '\0',NULL,NULL,'Nunc nec pulvinar erat. Vestibulum pulvinar','TEXT',2,NULL,1),(11,'2018-09-30 16:54:07',_binary '\0',NULL,NULL,'llus lobortis dui a mi vulputate ornare. Curabitur eget semper mi. Quisque luctus velit consequat lorem rhoncus','TEXT',2,NULL,3),(12,'2018-09-30 16:54:07',_binary '\0',NULL,NULL,'si tempus dictum.','TEXT',2,NULL,1),(13,'2018-09-30 16:54:07',_binary '\0',NULL,NULL,'Nunc ut tellus posuere, interdum nisi ut, fringilla ','TEXT',2,NULL,3),(14,'2018-09-30 16:54:07',_binary '\0',NULL,NULL,'tor blandit pretium. Aenean auctor tortor mi','TEXT',2,NULL,1),(15,'2018-09-30 16:54:07',_binary '\0',NULL,NULL,'tor blandit pretium. Aenean auctor tortor mi','TEXT',2,NULL,3),(16,'2018-09-30 16:54:07',_binary '\0',NULL,NULL,'Aliquam nibh neque','TEXT',2,NULL,3),(17,'2018-09-30 16:54:07',_binary '\0',NULL,NULL,'Aliquam nibh neque','TEXT',2,NULL,1),(18,'2018-09-30 16:54:07',_binary '\0',NULL,NULL,'І смеркає, і світає,\r\nДень божий минає,\r\nІ знову люд потомлений,\r\nІ все спочиває.','TEXT',3,NULL,1),(19,'2018-09-30 16:54:07',_binary '\0',NULL,NULL,'На розпуттях велелюдних,','TEXT',3,NULL,2),(20,'2018-09-30 16:54:07',_binary '\0',NULL,NULL,'Тілько я, мов окаянний,\r\nІ день і ніч плачу\r\nНа розпуттях велелюдних,','TEXT',3,NULL,3),(21,'2018-09-30 16:54:07',_binary '\0',NULL,NULL,'Nunc nec pulvinar erat. Vestibulum pulvinar','TEXT',2,NULL,1),(22,'2018-09-30 16:54:07',_binary '\0',NULL,NULL,'s. Nunc nec pulvinar erat. Vestibulum pulvinar','TEXT',2,NULL,3),(23,'2018-09-30 16:54:07',_binary '\0',NULL,NULL,'Nunc nec pulvinar erat. Vestibulum pulvinar','TEXT',2,NULL,1),(24,'2018-09-30 16:54:07',_binary '\0',NULL,NULL,'llus lobortis dui a mi vulputate ornare. Curabitur eget semper mi. Quisque luctus velit consequat lorem rhoncus','TEXT',2,NULL,3),(25,'2018-09-30 16:54:07',_binary '\0',NULL,NULL,'si tempus dictum.','TEXT',2,NULL,1),(26,'2018-09-30 16:54:07',_binary '\0',NULL,NULL,'Nunc ut tellus posuere, interdum nisi ut, fringilla ','TEXT',2,NULL,3),(27,'2018-09-30 16:54:07',_binary '\0',NULL,NULL,'tor blandit pretium. Aenean auctor tortor mi','TEXT',2,NULL,1),(28,'2018-09-30 16:54:07',_binary '\0',NULL,NULL,'tor blandit pretium. Aenean auctor tortor mi','TEXT',2,NULL,3),(29,'2018-09-30 16:54:07',_binary '\0',NULL,NULL,'Aliquam nibh neque','TEXT',2,NULL,3),(30,'2018-09-30 16:54:07',_binary '\0',NULL,NULL,'Aliquam nibh neque','TEXT',2,NULL,1),(31,'2018-09-30 16:54:07',_binary '\0',NULL,NULL,'І смеркає, і світає,\r\nДень божий минає,\r\nІ знову люд потомлений,\r\nІ все спочиває.','TEXT',3,NULL,1),(32,'2018-09-30 16:54:07',_binary '\0',NULL,NULL,'На розпуттях велелюдних,','TEXT',3,NULL,2),(33,'2018-09-30 16:54:07',_binary '\0',NULL,NULL,'Тілько я, мов окаянний,\r\nІ день і ніч плачу\r\nНа розпуттях велелюдних,','TEXT',3,NULL,3),(34,'2018-09-30 16:54:07',_binary '\0',NULL,NULL,'А що вродить? побачите,\r\nЯкі будуть жни́ва!\r\nСхаменіться, недолюди,\r\nДіти юродиві!\r\nПодивіться на рай тихий,\r\nНа свою країну,\r\n\r\nПолюбіте щирим серцем\r\n\r\nВелику руїну,','TEXT',3,NULL,1),(35,'2018-09-30 16:54:07',_binary '\0',NULL,NULL,'Розкуйтеся, братайтеся,\r\nУ чужому краю\r\nНе шукайте, не питайте\r\nТого, що немає','TEXT',3,NULL,2),(36,'2018-09-30 16:54:07',_binary '\0',NULL,NULL,'Кайданами міняються','TEXT',3,NULL,1),(37,'2018-09-30 16:54:07',_binary '\0',NULL,NULL,'І на небі, а не тілько\r\nНа чужому полі.\r\nВ своїй хаті своя й правда,\r\nІ сила, і воля.','TEXT',3,NULL,3),(38,'2018-09-30 16:54:07',_binary '\0',NULL,NULL,'І ніхто не бачить,\r\nІ не бачить, і не знає —\r\nОглухли, не чують;\r\nКайданами міняються,\r\nПравдою торгують.','TEXT',3,NULL,2),(39,'2018-09-30 16:54:07',_binary '\0',NULL,NULL,'І Господа зневажають,\r\nЛюдей запрягають\r\nВ тяжкі ярма. Орють лихо,\r\nЛихом засівають,','TEXT',3,NULL,3);
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `social_networks`
--

DROP TABLE IF EXISTS `social_networks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `social_networks` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nick_name` varchar(255) DEFAULT NULL,
  `social_network` varchar(255) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfk5tyumyhqryx4yr5f58vgx79` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `social_networks`
--

LOCK TABLES `social_networks` WRITE;
/*!40000 ALTER TABLE `social_networks` DISABLE KEYS */;
/*!40000 ALTER TABLE `social_networks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `travel_story`
--

DROP TABLE IF EXISTS `travel_story`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `travel_story` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` date DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `head` varchar(255) DEFAULT NULL,
  `travel_story_status` varchar(255) DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  `user_owner_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKl8qp627fwhfrreskhvhoivihe` (`user_owner_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `travel_story`
--

LOCK TABLES `travel_story` WRITE;
/*!40000 ALTER TABLE `travel_story` DISABLE KEYS */;
/*!40000 ALTER TABLE `travel_story` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_of_birth` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `last_update_date` datetime DEFAULT NULL,
  `password` varchar(25) DEFAULT NULL,
  `profile_pic` varchar(255) DEFAULT NULL,
  `registration_date` datetime DEFAULT NULL,
  `user_role` varchar(255) NOT NULL,
  `user_state` varchar(255) NOT NULL,
  `user_status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'1999-06-29','pryzyhlei@gmail.com','Kostiantyn','MALE','Pryzyhlei',NULL,'1',NULL,'2018-09-26 16:41:39','ROLE_USER','ONLINE','ACTIVE'),(2,'1990-06-20','pupkin@gmail.com','Vasylii','MALE','Pupkin',NULL,'1',NULL,'2018-09-26 16:41:39','ROLE_USER','ONLINE','ACTIVE'),(3,'1990-06-20','nastya@gmail.com','Anastasiia','FEMALE','Bug',NULL,'1',NULL,'2018-09-26 16:41:39','ROLE_USER','ONLINE','ACTIVE');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-09-30 20:16:37
