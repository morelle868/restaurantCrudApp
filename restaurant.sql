-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mer. 29 mai 2024 à 19:11
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `restaurant`
--

-- --------------------------------------------------------

--
-- Structure de la table `fcategory`
--

CREATE TABLE `fcategory` (
  `categoryId` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `cat_des` varchar(255) DEFAULT NULL,
  `price_racge` decimal(10,1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `fcategory`
--

INSERT INTO `fcategory` (`categoryId`, `name`, `cat_des`, `price_racge`) VALUES
(1, 'traditional', 'village meals', 20.0),
(2, 'morden', 'state meals', 60.0),
(3, 'poping', 'nfjdfeziofjdkfhz', 505.0),
(4, 'african', 'foods made in africa', 452.0),
(5, 'Cameroon Dishes', 'Made in Cameroon', 100.0);

-- --------------------------------------------------------

--
-- Structure de la table `food`
--

CREATE TABLE `food` (
  `categoryId` int(11) NOT NULL,
  `foodName` varchar(100) NOT NULL,
  `foodPrice` decimal(10,2) NOT NULL,
  `foodDes` text DEFAULT NULL,
  `foodImage` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `food`
--

INSERT INTO `food` (`categoryId`, `foodName`, `foodPrice`, `foodDes`, `foodImage`, `id`) VALUES
(1, 'rice', 150.00, 'state', 'C:\\Users\\Morelle\\Desktop\\ReataurantCRUD\\target\\ReataurantCRUD-1.0-SNAPSHOT\\data.jpg', 1);

-- --------------------------------------------------------

--
-- Structure de la table `foods`
--

CREATE TABLE `foods` (
  `categoryId` int(11) NOT NULL,
  `foodName` varchar(100) NOT NULL,
  `foodPrice` decimal(10,2) NOT NULL,
  `foodDes` text DEFAULT NULL,
  `foodImage` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `foods`
--

INSERT INTO `foods` (`categoryId`, `foodName`, `foodPrice`, `foodDes`, `foodImage`, `id`) VALUES
(2, 'Ndole', 7000.00, 'Ndole is a traditional Cameroonian dish made with bitterleaf, groundnut paste, and often combined with meat or fish. It\'s rich, flavorful, and often enjoyed with a side of plantains or rice.', 'uploads\\ndole.jpeg', 9),
(5, 'Eru', 10000.00, '\"eru\" is a traditional dish made with finely shredded leaves of the eru plant (Gnetum africanum) cooked with various ingredients such as palm oil, waterleaf, cocoyam, and sometimes meat or fish.', 'uploads\\WhatsApp Image 2024-05-29 at 3.48.22 PM.jpeg', 10),
(2, 'Spaghettis', 5000.00, 'Spaghetti is a type of pasta characterized by long, thin, cylindrical noodles made from durum wheat semolina or flour and water. ', 'uploads\\WhatsApp Image 2024-05-29 at 3.48.25 PM (1).jpeg', 11),
(2, 'Pizza', 5000.00, 'Pizza is a beloved dish originating from Italy, consisting of a round, flattened base of dough topped with various ingredients, such as tomato sauce, cheese, meats, vegetables, and herbs. ', 'uploads\\WhatsApp Image 2024-05-29 at 3.51.57 PM.jpeg', 12);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `name` varchar(399) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `name`, `email`, `password`, `role`) VALUES
(1, 'chayie', 'chayie@gmail.com', '123456789', 'admin'),
(2, 'percy', 'percy@gmail.com', '45662', NULL),
(3, 'percy', 'sandra@gmail.com', '1234', NULL),
(4, 'emelda', 'emelda@gmail.com', '1234', NULL),
(5, 'youyou', 'youyou@gmail.com', 'youyou@gmail.com', NULL),
(6, 'yeahyeah', 'yeahyeah@gmail.com', 'yeahyeah@gmail.com', NULL);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `fcategory`
--
ALTER TABLE `fcategory`
  ADD PRIMARY KEY (`categoryId`);

--
-- Index pour la table `food`
--
ALTER TABLE `food`
  ADD PRIMARY KEY (`id`),
  ADD KEY `categoryId` (`categoryId`);

--
-- Index pour la table `foods`
--
ALTER TABLE `foods`
  ADD PRIMARY KEY (`id`),
  ADD KEY `categoryId` (`categoryId`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `fcategory`
--
ALTER TABLE `fcategory`
  MODIFY `categoryId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `food`
--
ALTER TABLE `food`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `foods`
--
ALTER TABLE `foods`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `food`
--
ALTER TABLE `food`
  ADD CONSTRAINT `food_ibfk_1` FOREIGN KEY (`categoryId`) REFERENCES `fcategory` (`categoryId`);

--
-- Contraintes pour la table `foods`
--
ALTER TABLE `foods`
  ADD CONSTRAINT `foods_ibfk_1` FOREIGN KEY (`categoryId`) REFERENCES `fcategory` (`categoryId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
