-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 11, 2021 at 06:38 PM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mystock`
--

-- --------------------------------------------------------

--
-- Table structure for table `article`
--

CREATE TABLE `article` (
  `id_article` int(11) NOT NULL,
  `nom_article` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `qauntitie` int(11) DEFAULT NULL,
  `unitie` varchar(3) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nom_category` text COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `bon_comande`
--

CREATE TABLE `bon_comande` (
  `id_bc` int(11) NOT NULL,
  `date_de_creation` date NOT NULL DEFAULT current_timestamp(),
  `username` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nom_article` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `qauntitie` int(11) DEFAULT NULL,
  `unitie` varchar(3) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `bon_domande`
--

CREATE TABLE `bon_domande` (
  `id_bd` int(11) NOT NULL,
  `date_de_creation` date DEFAULT current_timestamp(),
  `username` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `id_article` int(11) DEFAULT NULL,
  `nom_article` text COLLATE utf8_unicode_ci DEFAULT NULL,
  `qauntitie` int(11) DEFAULT NULL,
  `unitie` varchar(3) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `bon_livrison`
--

CREATE TABLE `bon_livrison` (
  `id_bl` int(11) NOT NULL,
  `date_de_creation` date NOT NULL DEFAULT current_timestamp(),
  `nom_article` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `qauntitie` int(11) DEFAULT NULL,
  `unitie` varchar(3) COLLATE utf8_unicode_ci DEFAULT NULL,
  `id_bc` int(11) DEFAULT NULL,
  `nom_fournisseur` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `bon_reception`
--

CREATE TABLE `bon_reception` (
  `id_br` int(11) NOT NULL,
  `date_de_creation` date NOT NULL,
  `username` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `qauntitie` int(11) DEFAULT NULL,
  `unitie` varchar(3) COLLATE utf8_unicode_ci DEFAULT NULL,
  `id_bl` int(11) DEFAULT NULL,
  `nom_fournisseur` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nom_article` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `bon_sortie`
--

CREATE TABLE `bon_sortie` (
  `id_bs` int(11) NOT NULL,
  `date_de_creation` date DEFAULT current_timestamp(),
  `username` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `id_article` int(11) DEFAULT NULL,
  `qauntitie` int(11) DEFAULT NULL,
  `unitie` varchar(3) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nom_article` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `fournisseur`
--

CREATE TABLE `fournisseur` (
  `nom_fournisseur` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `id_fournisseur` int(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `username` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `first_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pass` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `service_name` text COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `article`
--
ALTER TABLE `article`
  ADD PRIMARY KEY (`id_article`),
  ADD UNIQUE KEY `nom_article` (`nom_article`),
  ADD KEY `qauntitie` (`qauntitie`),
  ADD KEY `unitie` (`unitie`);

--
-- Indexes for table `bon_comande`
--
ALTER TABLE `bon_comande`
  ADD PRIMARY KEY (`id_bc`),
  ADD KEY `username` (`username`),
  ADD KEY `nom_article` (`nom_article`);

--
-- Indexes for table `bon_domande`
--
ALTER TABLE `bon_domande`
  ADD PRIMARY KEY (`id_bd`),
  ADD KEY `username` (`username`),
  ADD KEY `id_article` (`id_article`);

--
-- Indexes for table `bon_livrison`
--
ALTER TABLE `bon_livrison`
  ADD PRIMARY KEY (`id_bl`),
  ADD KEY `id_bc` (`id_bc`),
  ADD KEY `nom_article` (`nom_article`),
  ADD KEY `qauntitie` (`qauntitie`),
  ADD KEY `unitie` (`unitie`),
  ADD KEY `nom_fournisseur` (`nom_fournisseur`);

--
-- Indexes for table `bon_reception`
--
ALTER TABLE `bon_reception`
  ADD PRIMARY KEY (`id_br`),
  ADD KEY `username` (`username`),
  ADD KEY `id_bl` (`id_bl`),
  ADD KEY `nom_article_from_bon_ivrison` (`nom_article`) USING BTREE,
  ADD KEY `qauntitie` (`qauntitie`),
  ADD KEY `unitie` (`unitie`),
  ADD KEY `bon_reception_ibfk_4` (`nom_fournisseur`);

--
-- Indexes for table `bon_sortie`
--
ALTER TABLE `bon_sortie`
  ADD PRIMARY KEY (`id_bs`),
  ADD KEY `id_article` (`id_article`),
  ADD KEY `nom_article` (`nom_article`),
  ADD KEY `unitie` (`unitie`),
  ADD KEY `username` (`username`);

--
-- Indexes for table `fournisseur`
--
ALTER TABLE `fournisseur`
  ADD PRIMARY KEY (`nom_fournisseur`),
  ADD UNIQUE KEY `id_fournisseur` (`id_fournisseur`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `article`
--
ALTER TABLE `article`
  MODIFY `id_article` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `bon_comande`
--
ALTER TABLE `bon_comande`
  MODIFY `id_bc` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `bon_domande`
--
ALTER TABLE `bon_domande`
  MODIFY `id_bd` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `bon_livrison`
--
ALTER TABLE `bon_livrison`
  MODIFY `id_bl` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `bon_reception`
--
ALTER TABLE `bon_reception`
  MODIFY `id_br` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `bon_sortie`
--
ALTER TABLE `bon_sortie`
  MODIFY `id_bs` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `fournisseur`
--
ALTER TABLE `fournisseur`
  MODIFY `id_fournisseur` int(8) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bon_comande`
--
ALTER TABLE `bon_comande`
  ADD CONSTRAINT `bon_comande_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`),
  ADD CONSTRAINT `bon_comande_ibfk_2` FOREIGN KEY (`nom_article`) REFERENCES `article` (`nom_article`);

--
-- Constraints for table `bon_domande`
--
ALTER TABLE `bon_domande`
  ADD CONSTRAINT `bon_domande_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`),
  ADD CONSTRAINT `bon_domande_ibfk_2` FOREIGN KEY (`id_article`) REFERENCES `article` (`id_article`);

--
-- Constraints for table `bon_livrison`
--
ALTER TABLE `bon_livrison`
  ADD CONSTRAINT `bon_livrison_ibfk_1` FOREIGN KEY (`id_bc`) REFERENCES `bon_comande` (`id_bc`),
  ADD CONSTRAINT `bon_livrison_ibfk_2` FOREIGN KEY (`nom_article`) REFERENCES `article` (`nom_article`),
  ADD CONSTRAINT `bon_livrison_ibfk_3` FOREIGN KEY (`nom_fournisseur`) REFERENCES `fournisseur` (`nom_fournisseur`);

--
-- Constraints for table `bon_reception`
--
ALTER TABLE `bon_reception`
  ADD CONSTRAINT `bon_reception_ibfk_3` FOREIGN KEY (`username`) REFERENCES `user` (`username`),
  ADD CONSTRAINT `bon_reception_ibfk_4` FOREIGN KEY (`nom_fournisseur`) REFERENCES `bon_livrison` (`nom_fournisseur`),
  ADD CONSTRAINT `bon_reception_ibfk_5` FOREIGN KEY (`id_bl`) REFERENCES `bon_livrison` (`id_bl`),
  ADD CONSTRAINT `bon_reception_ibfk_6` FOREIGN KEY (`nom_article`) REFERENCES `bon_livrison` (`nom_article`),
  ADD CONSTRAINT `bon_reception_ibfk_7` FOREIGN KEY (`qauntitie`) REFERENCES `bon_livrison` (`qauntitie`),
  ADD CONSTRAINT `bon_reception_ibfk_8` FOREIGN KEY (`unitie`) REFERENCES `bon_livrison` (`unitie`);

--
-- Constraints for table `bon_sortie`
--
ALTER TABLE `bon_sortie`
  ADD CONSTRAINT `bon_sortie_ibfk_1` FOREIGN KEY (`id_article`) REFERENCES `article` (`id_article`),
  ADD CONSTRAINT `bon_sortie_ibfk_2` FOREIGN KEY (`nom_article`) REFERENCES `article` (`nom_article`),
  ADD CONSTRAINT `bon_sortie_ibfk_4` FOREIGN KEY (`unitie`) REFERENCES `article` (`unitie`),
  ADD CONSTRAINT `bon_sortie_ibfk_5` FOREIGN KEY (`username`) REFERENCES `user` (`username`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
