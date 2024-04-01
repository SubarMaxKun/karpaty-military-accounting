-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Mar 31, 2024 at 05:36 PM
-- Server version: 11.3.2-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `military_accounting_karpaty`
--
CREATE DATABASE IF NOT EXISTS `military_accounting_karpaty` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `military_accounting_karpaty`;

-- --------------------------------------------------------

--
-- Table structure for table `ranks`
--

DROP TABLE IF EXISTS `ranks`;
CREATE TABLE `ranks` (
  `id` int(11) NOT NULL,
  `rank` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ranks`
--

INSERT INTO `ranks` (`id`, `rank`) VALUES
(1, 'Рекрут'),
(2, 'Солдат'),
(3, 'Старший солдат'),
(4, 'Молодший сержант'),
(5, 'Сержант'),
(6, 'Старший сержант'),
(7, 'Головний сержант'),
(8, 'Штаб-сержант'),
(9, 'Старший майстер-сержант'),
(10, 'Головний майстер-сержант'),
(11, 'Молодший лейтенант'),
(12, 'Лейтенант'),
(13, 'Старший лейтенант'),
(14, 'Капітан'),
(15, 'Майор'),
(16, 'Підполковник'),
(17, 'Полковник'),
(18, 'Бригадний генерал'),
(19, 'Генерал-майор'),
(20, 'Генерал-лейтенант'),
(21, 'Генерал');

-- --------------------------------------------------------

--
-- Table structure for table `training`
--

DROP TABLE IF EXISTS `training`;
CREATE TABLE `training` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `training`
--

INSERT INTO `training` (`id`, `name`) VALUES
(1, 'Рядовий'),
(2, 'Сержантський'),
(3, 'Офіцерський');

-- --------------------------------------------------------

--
-- Table structure for table `workers`
--

DROP TABLE IF EXISTS `workers`;
CREATE TABLE `workers` (
  `id` int(11) NOT NULL,
  `rank` int(11) NOT NULL,
  `full_name` varchar(80) NOT NULL,
  `birth_date` date NOT NULL,
  `registration_number` varchar(80) NOT NULL,
  `military_specialty` int(5) NOT NULL,
  `training` int(11) NOT NULL,
  `accounting_category` varchar(30) NOT NULL,
  `degree` varchar(100) NOT NULL,
  `id_info` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `workers`
--

INSERT INTO `workers` (`id`, `rank`, `full_name`, `birth_date`, `registration_number`, `military_specialty`, `training`, `accounting_category`, `degree`, `id_info`) VALUES
(1, 1, 'Ворченко Микола Петрович', '1981-05-07', 'РНОКПП2943607634', 900, 1, 'І, ТП№211130', 'вища, банківські справа, КВ№22937940', 'МЕ№783517, виданий Шевченківським РУ ГУ МВС України в м.Києві, 29.09.2006'),
(2, 2, 'Семенченко Недан Зорянович', '1989-09-21', 'РНОКПП1943607689', 921, 1, 'І, ТП№218730', 'вища, банківські справа, КВ№22937970', 'МЕ№754317, виданий Шевченківським РУ ГУ МВС України в м.Києві, 12.04.2010'),
(3, 3, 'Бедзик Фауст Костянтинович', '1976-03-17', '567891278098665543227', 790, 1, 'ІІ, ТП№234730', 'вища, банківські справа, КВ№22937970', 'МЕ№729317, виданий Шевченківським РУ ГУ МВС України в м.Києві, 12.04.2010'),
(4, 4, 'Чортківський Йозеф Глібович', '1996-03-07', 'РНОКПП1760607689', 921, 2, 'І, ТП№235200', 'вища, банківські справа, КВ№22937970', 'МЕ№879317, виданий Шевченківським РУ ГУ МВС України в м.Києві, 12.04.2010'),
(5, 5, 'Іванов Іван Іванович', '1990-09-19', '876543218765432187613', 505, 2, 'І, ТП№218730', 'вища, банківські справа, КВ№22937970', 'МЕ№865317, виданий Шевченківським РУ ГУ МВС України в м.Києві, 12.04.2010'),
(6, 6, 'Петров Петро Петрович', '1985-03-14', '567812345678123456781', 303, 2, 'ІІ, ТП№234730', 'вища, банківські справа, КВ№22937970', 'МЕ№879567, виданий Шевченківським РУ ГУ МВС України в м.Києві, 12.04.2010');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `ranks`
--
ALTER TABLE `ranks`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `training`
--
ALTER TABLE `training`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `workers`
--
ALTER TABLE `workers`
  ADD PRIMARY KEY (`id`),
  ADD KEY `rank` (`rank`),
  ADD KEY `training` (`training`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `ranks`
--
ALTER TABLE `ranks`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `training`
--
ALTER TABLE `training`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `workers`
--
ALTER TABLE `workers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `workers`
--
ALTER TABLE `workers`
  ADD CONSTRAINT `workers_ibfk_1` FOREIGN KEY (`rank`) REFERENCES `ranks` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `workers_ibfk_2` FOREIGN KEY (`training`) REFERENCES `training` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
