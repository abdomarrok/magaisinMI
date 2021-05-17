SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;


CREATE TABLE IF NOT EXISTS article (
  id_article int(11) NOT NULL AUTO_INCREMENT,
  nom_article varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  qauntitie int(11) DEFAULT NULL,
  unitie varchar(3) COLLATE utf8_unicode_ci DEFAULT NULL,
  nom_category text COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (id_article),
  UNIQUE KEY nom_article (nom_article),
  KEY qauntitie (qauntitie),
  KEY unitie (unitie)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS bon_comande (
  id_bc int(11) NOT NULL AUTO_INCREMENT,
  date_de_creation date NOT NULL DEFAULT current_timestamp(),
  username varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  nom_article varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  qauntitie int(11) DEFAULT NULL,
  unitie varchar(3) COLLATE utf8_unicode_ci DEFAULT NULL,
  destinataire text COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (id_bc),
  KEY username (username),
  KEY nom_article (nom_article)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS bon_domande (
  id_bd int(11) NOT NULL AUTO_INCREMENT,
  date_de_creation date DEFAULT current_timestamp(),
  username varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  id_article int(11) DEFAULT NULL,
  nom_article text COLLATE utf8_unicode_ci DEFAULT NULL,
  qauntitie int(11) DEFAULT NULL,
  unitie varchar(3) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (id_bd),
  KEY username (username),
  KEY id_article (id_article)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS bon_livrison (
  id_bl int(11) NOT NULL AUTO_INCREMENT,
  date_de_creation date NOT NULL DEFAULT current_timestamp(),
  nom_article varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  qauntitie int(11) DEFAULT NULL,
  unitie varchar(3) COLLATE utf8_unicode_ci DEFAULT NULL,
  id_bc int(11) DEFAULT NULL,
  nom_fournisseur varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (id_bl),
  KEY id_bc (id_bc),
  KEY nom_article (nom_article),
  KEY qauntitie (qauntitie),
  KEY unitie (unitie),
  KEY nom_fournisseur (nom_fournisseur)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS bon_reception (
  id_br int(11) NOT NULL AUTO_INCREMENT,
  date_de_creation date NOT NULL,
  username varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  qauntitie int(11) DEFAULT NULL,
  unitie varchar(3) COLLATE utf8_unicode_ci DEFAULT NULL,
  id_bl int(11) DEFAULT NULL,
  nom_fournisseur varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  nom_article varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (id_br),
  KEY username (username),
  KEY id_bl (id_bl),
  KEY nom_article_from_bon_ivrison (nom_article) USING BTREE,
  KEY qauntitie (qauntitie),
  KEY unitie (unitie),
  KEY bon_reception_ibfk_4 (nom_fournisseur)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS bon_sortie (
  id_bs int(11) NOT NULL AUTO_INCREMENT,
  date_de_creation date DEFAULT current_timestamp(),
  username varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  id_article int(11) DEFAULT NULL,
  qauntitie int(11) DEFAULT NULL,
  unitie varchar(3) COLLATE utf8_unicode_ci DEFAULT NULL,
  nom_article varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (id_bs),
  KEY id_article (id_article),
  KEY nom_article (nom_article),
  KEY unitie (unitie),
  KEY username (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS fournisseur (
  nom_fournisseur varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  id_fournisseur int(8) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (nom_fournisseur),
  UNIQUE KEY id_fournisseur (id_fournisseur)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `user` (
  username varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  first_name varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  last_name varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  pass varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  service_name text COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


ALTER TABLE bon_comande
  ADD CONSTRAINT bon_comande_ibfk_1 FOREIGN KEY (username) REFERENCES `user` (username),
  ADD CONSTRAINT bon_comande_ibfk_2 FOREIGN KEY (nom_article) REFERENCES article (nom_article);

ALTER TABLE bon_domande
  ADD CONSTRAINT bon_domande_ibfk_1 FOREIGN KEY (username) REFERENCES `user` (username),
  ADD CONSTRAINT bon_domande_ibfk_2 FOREIGN KEY (id_article) REFERENCES article (id_article);

ALTER TABLE bon_livrison
  ADD CONSTRAINT bon_livrison_ibfk_1 FOREIGN KEY (id_bc) REFERENCES bon_comande (id_bc),
  ADD CONSTRAINT bon_livrison_ibfk_2 FOREIGN KEY (nom_article) REFERENCES article (nom_article),
  ADD CONSTRAINT bon_livrison_ibfk_3 FOREIGN KEY (nom_fournisseur) REFERENCES fournisseur (nom_fournisseur);

ALTER TABLE bon_reception
  ADD CONSTRAINT bon_reception_ibfk_3 FOREIGN KEY (username) REFERENCES `user` (username),
  ADD CONSTRAINT bon_reception_ibfk_4 FOREIGN KEY (nom_fournisseur) REFERENCES bon_livrison (nom_fournisseur),
  ADD CONSTRAINT bon_reception_ibfk_5 FOREIGN KEY (id_bl) REFERENCES bon_livrison (id_bl),
  ADD CONSTRAINT bon_reception_ibfk_6 FOREIGN KEY (nom_article) REFERENCES bon_livrison (nom_article),
  ADD CONSTRAINT bon_reception_ibfk_7 FOREIGN KEY (qauntitie) REFERENCES bon_livrison (qauntitie),
  ADD CONSTRAINT bon_reception_ibfk_8 FOREIGN KEY (unitie) REFERENCES bon_livrison (unitie);

ALTER TABLE bon_sortie
  ADD CONSTRAINT bon_sortie_ibfk_1 FOREIGN KEY (id_article) REFERENCES article (id_article),
  ADD CONSTRAINT bon_sortie_ibfk_2 FOREIGN KEY (nom_article) REFERENCES article (nom_article),
  ADD CONSTRAINT bon_sortie_ibfk_4 FOREIGN KEY (unitie) REFERENCES article (unitie),
  ADD CONSTRAINT bon_sortie_ibfk_5 FOREIGN KEY (username) REFERENCES `user` (username);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
