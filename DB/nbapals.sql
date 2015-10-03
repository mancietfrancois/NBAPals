-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: nbapals
-- ------------------------------------------------------
-- Server version	5.6.26-log

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
-- Table structure for table `games`
--

DROP TABLE IF EXISTS `games`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `games` (
  `id` varchar(20) NOT NULL,
  `team_home` varchar(4) NOT NULL,
  `team_away` varchar(4) NOT NULL,
  `home_score` int(11) NOT NULL DEFAULT '0',
  `away_score` int(11) NOT NULL DEFAULT '0',
  `date_us` varchar(10) NOT NULL,
  `status` enum('FINAL','FINAL/OT','POSTPONED','TO_BE_PLAYED') NOT NULL,
  `code_espn` varchar(55) NOT NULL,
  `date_eu` varchar(10) NOT NULL,
  `time_eu` varchar(5) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `team_home` (`team_home`,`team_away`),
  KEY `team_away` (`team_away`),
  CONSTRAINT `id_team_away` FOREIGN KEY (`team_away`) REFERENCES `nba_team` (`shortname`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `id_team_home` FOREIGN KEY (`team_home`) REFERENCES `nba_team` (`shortname`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `nba_team`
--

DROP TABLE IF EXISTS `nba_team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nba_team` (
  `city` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `shortname` varchar(4) NOT NULL,
  `conf` enum('WEST','EAST') NOT NULL,
  `division` enum('SOUTHEAST','CENTRAL','PACIFIC','NORTHWEST','SOUTHWEST','ATLANTIC') NOT NULL,
  `wins` int(11) NOT NULL DEFAULT '0',
  `loss` int(11) NOT NULL DEFAULT '0',
  `wins_conf` int(11) NOT NULL DEFAULT '0',
  `loss_conf` int(11) NOT NULL DEFAULT '0',
  `wins_div` int(11) NOT NULL DEFAULT '0',
  `loss_div` int(11) NOT NULL DEFAULT '0',
  `div_ranking` int(11) NOT NULL,
  `conf_ranking` int(11) NOT NULL,
  `playoff_wins` int(11) NOT NULL,
  `playoff_loss` int(11) NOT NULL,
  PRIMARY KEY (`shortname`),
  UNIQUE KEY `shortname` (`shortname`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `playoff_games`
--

DROP TABLE IF EXISTS `playoff_games`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `playoff_games` (
  `id` varchar(30) NOT NULL,
  `team_home` varchar(4) NOT NULL,
  `team_away` varchar(4) NOT NULL,
  `home_score` int(11) NOT NULL DEFAULT '0',
  `away_score` int(11) NOT NULL DEFAULT '0',
  `date_us` varchar(10) NOT NULL,
  `status` enum('FINAL','FINAL/OT','POSTPONED','TO_BE_PLAYED') NOT NULL,
  `code_espn` varchar(55) NOT NULL,
  `date_eu` varchar(10) NOT NULL,
  `time_eu` varchar(5) NOT NULL,
  `id_playoff_serie` int(11) NOT NULL,
  `game_number` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `team_home` (`team_home`,`team_away`),
  KEY `team_away` (`team_away`),
  KEY `team_home_2` (`team_home`),
  KEY `team_away_2` (`team_away`),
  KEY `id_playoff_serie` (`id_playoff_serie`),
  CONSTRAINT `playoff_games_ibfk_1` FOREIGN KEY (`team_home`) REFERENCES `nba_team` (`shortname`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `playoff_games_ibfk_2` FOREIGN KEY (`team_away`) REFERENCES `nba_team` (`shortname`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `playoff_games_ibfk_3` FOREIGN KEY (`id_playoff_serie`) REFERENCES `playoff_series` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `playoff_series`
--

DROP TABLE IF EXISTS `playoff_series`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `playoff_series` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` enum('EASTERN_FIRST_ROUND','EASTERN_SEMI_FINALS','EASTERN_FINALS','WESTERN_FIRST_ROUND','WESTER_SEMI_FINALS','WESTERN_FINALS','NBA_FINALS') NOT NULL,
  `team_hca` varchar(3) NOT NULL,
  `team_opponent` varchar(3) NOT NULL,
  `team_hca_ranking` int(11) NOT NULL,
  `team_opponent_ranking` int(11) NOT NULL,
  `id_game1` varchar(30) DEFAULT NULL,
  `id_game2` varchar(30) DEFAULT NULL,
  `id_game3` varchar(30) DEFAULT NULL,
  `id_game4` varchar(30) DEFAULT NULL,
  `id_game5` varchar(30) DEFAULT NULL,
  `id_game6` varchar(30) DEFAULT NULL,
  `id_game7` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `team_hca` (`team_hca`,`team_opponent`,`id_game1`,`id_game2`,`id_game3`,`id_game4`,`id_game5`,`id_game6`,`id_game7`),
  KEY `team_hca_2` (`team_hca`),
  KEY `team_opponent` (`team_opponent`),
  KEY `id_game1` (`id_game1`),
  KEY `id_game2` (`id_game2`),
  KEY `id_game3` (`id_game3`),
  KEY `id_game4` (`id_game4`),
  KEY `id_game5` (`id_game5`),
  KEY `id_game6` (`id_game6`),
  KEY `id_game7` (`id_game7`),
  CONSTRAINT `playoff_series_ibfk_1` FOREIGN KEY (`team_hca`) REFERENCES `nba_team` (`shortname`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `playoff_series_ibfk_2` FOREIGN KEY (`team_opponent`) REFERENCES `nba_team` (`shortname`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `playoff_series_ibfk_3` FOREIGN KEY (`id_game1`) REFERENCES `playoff_games` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `playoff_series_ibfk_4` FOREIGN KEY (`id_game2`) REFERENCES `playoff_games` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `playoff_series_ibfk_5` FOREIGN KEY (`id_game3`) REFERENCES `playoff_games` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `playoff_series_ibfk_6` FOREIGN KEY (`id_game4`) REFERENCES `playoff_games` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `playoff_series_ibfk_7` FOREIGN KEY (`id_game5`) REFERENCES `playoff_games` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `playoff_series_ibfk_8` FOREIGN KEY (`id_game6`) REFERENCES `playoff_games` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `playoff_series_ibfk_9` FOREIGN KEY (`id_game7`) REFERENCES `playoff_games` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-09-30 18:25:31
