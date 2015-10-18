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
  conf enum('WEST','EAST') NOT NULL,
  division enum('SOUTHEAST','CENTRAL','PACIFIC','NORTHWEST','SOUTHWEST','ATLANTIC') NOT NULL,
  wins int(11) NOT NULL DEFAULT '0',
  loss int(11) NOT NULL DEFAULT '0',
  wins_conf int(11) NOT NULL DEFAULT '0',
  loss_conf int(11) NOT NULL DEFAULT '0',
  wins_div int(11) NOT NULL DEFAULT '0',
  loss_div int(11) NOT NULL DEFAULT '0',
  div_ranking int(11) NOT NULL,
  conf_ranking int(11) NOT NULL,
  CONSTRAINT pk_shortname PRIMARY KEY (shortname),
  UNIQUE KEY shortname (shortname)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  status enum('FINAL','FINAL_OT','POSTPONED','TO_BE_PLAYED') NOT NULL,
  code_espn varchar(55) NOT NULL,
  date_eu varchar(10) NOT NULL,
  time_eu varchar(5) NOT NULL,
  CONSTRAINT pk_id PRIMARY KEY (id),
  CONSTRAINT fk_id_team_away FOREIGN KEY (team_away) REFERENCES nba_team (shortname),
  CONSTRAINT fk_id_team_home FOREIGN KEY (team_home) REFERENCES nba_team (shortname)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table gambler_group
--

DROP TABLE IF EXISTS gambler_group;
CREATE TABLE gambler_group (
  id int NOT NULL AUTO_INCREMENT,
  group_name varchar(20) NOT NULL UNIQUE,
  password varchar(20) NOT NULL,
  CONSTRAINT pk_id PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table gambler
--

DROP TABLE IF EXISTS gambler;
CREATE TABLE gambler (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(20) NOT NULL UNIQUE,
  password varchar(20) NOT NULL,
  CONSTRAINT pk_id PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table bets
--

DROP TABLE IF EXISTS bets;
CREATE TABLE bets (
  id varchar(20) NOT NULL,
  id_game varchar(20) NOT NULL,
  team_winner varchar(20) NOT NULL,
  CONSTRAINT pk_id PRIMARY KEY (id),
  CONSTRAINT fk_id_game FOREIGN KEY (id_game) REFERENCES games (id),
  CONSTRAINT fk_id_team_winner FOREIGN KEY (team_winner) REFERENCES nba_team (shortname)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


