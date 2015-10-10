-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: nbapals
-- ------------------------------------------------------
-- Server version	5.6.26-log

--
-- Table structure for table nba_team
--

DROP TABLE IF EXISTS nba_team;
CREATE TABLE nba_team (
  city varchar(20) NOT NULL,
  name varchar(20) NOT NULL,
  shortname varchar(4) NOT NULL,
  conf enum('WEST', 'EAST') NOT NULL,
  division enum('SOUTHEAST', 'CENTRAL', 'PACIFIC', 'NORTHWEST', 'SOUTHWEST', 'ATLANTIC') NOT NULL,
  team_ranking_id varchar(20) NOT NULL,
  CONSTRAINT pk_shortname PRIMARY KEY (shortname),
  UNIQUE KEY shortname (shortname)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS team_ranking;
CREATE TABLE team_ranking (
  id int NOT NULL AUTO_INCREMENT,
  team_id varchar(4) NOT NULL,
  regular_season enum('2011', '2012', '2013', '2014', '2015', 'NOW') NOT NULL,  
  wins int(11) NOT NULL DEFAULT '0',
  loss int(11) NOT NULL DEFAULT '0',
  wins_conf int(11) NOT NULL DEFAULT '0',
  loss_conf int(11) NOT NULL DEFAULT '0',
  wins_div int(11) NOT NULL DEFAULT '0',
  loss_div int(11) NOT NULL DEFAULT '0',
  div_ranking int(11) NOT NULL,
  conf_ranking int(11) NOT NULL,
  CONSTRAINT pk_id PRIMARY KEY (id),
  CONSTRAINT fk_team_id FOREIGN KEY (team_id) REFERENCES nba_team (shortname)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE nba_team ADD 
CONSTRAINT fk_team_ranking_id FOREIGN KEY (team_ranking_id) REFERENCES team_ranking (team_id);

--
-- Table structure for table games
--

DROP TABLE IF EXISTS games;
CREATE TABLE games (
  id varchar(20) NOT NULL,
  team_home varchar(4) NOT NULL,
  team_away varchar(4) NOT NULL,
  home_score int(11) NOT NULL DEFAULT '0',
  away_score int(11) NOT NULL DEFAULT '0',
  date_us varchar(10) NOT NULL,
  status enum('FINAL', 'FINAL_OT', 'POSTPONED', 'TO_BE_PLAYED') NOT NULL,
  code_espn varchar(55) NOT NULL,
  date_eu varchar(10) NOT NULL,
  time_eu varchar(5) NOT NULL,
  CONSTRAINT pk_id PRIMARY KEY (id),
  CONSTRAINT fk_id_team_away FOREIGN KEY (team_away) REFERENCES nba_team (shortname),
  CONSTRAINT fk_id_team_home FOREIGN KEY (team_home) REFERENCES nba_team (shortname)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS archieves;
CREATE TABLE archieves (
  id_archieves varchar(20) NOT NULL,
  team_home varchar(4) NOT NULL,
  team_away varchar(4) NOT NULL,
  home_score int(11) NOT NULL DEFAULT '0',
  away_score int(11) NOT NULL DEFAULT '0',
  date_us varchar(10) NOT NULL,
  status enum('FINAL', 'FINAL_OT', 'POSTPONED', 'TO_BE_PLAYED') NOT NULL,
  regular_season enum('2011', '2012', '2013', '2014', "2015") NOT NULL,
  CONSTRAINT pk_id_archieves PRIMARY KEY (id_archieves),
  CONSTRAINT fk_id_team_away_archieves FOREIGN KEY (team_away) REFERENCES nba_team (shortname),
  CONSTRAINT fk_id_team_home_archieves FOREIGN KEY (team_home) REFERENCES nba_team (shortname)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

