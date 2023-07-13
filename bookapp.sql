-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 18, 2023 at 04:23 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bookapp`
--

-- --------------------------------------------------------

--
-- Table structure for table `donate`
--

CREATE TABLE `donate` (
  `id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `bookname` varchar(100) NOT NULL,
  `subject` varchar(100) NOT NULL,
  `semester` int(11) NOT NULL,
  `booktype` varchar(100) NOT NULL,
  `mobile` bigint(12) NOT NULL,
  `flag` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `donate`
--

INSERT INTO `donate` (`id`, `username`, `bookname`, `subject`, `semester`, `booktype`, `mobile`, `flag`) VALUES
(53, '4GW20IS029', 'DS', 'Data structures', 3, 'text', 99445678, 1),
(65, '4GW20IS041', 'FS', 'File Structure ', 6, 'Textbook ', 96554566, 0),
(67, '4GW20IS002', 'FS', 'FS', 6, 'text', 87556779, 0),
(71, '4GW20IS022', 'DC', 'Data comm', 5, 'text', 98776655, 0),
(84, '4GW20IS029', 'WEB', 'WEB', 6, 'notes', 8767566778, 1),
(85, '4GW20IS029', 'Computer Organization', 'CO', 3, 'Textbook', 7866545409, 1);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `fullname` text NOT NULL,
  `username` varchar(15) NOT NULL,
  `password` text NOT NULL,
  `email` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `fullname`, `username`, `password`, `email`) VALUES
(1, 'punya', '4GW20IS041', '$2y$10$ajAnkkyNly9xv6LYGyI1zesct316wNfce13y3tPu1euptqtxCI8o6', 'punya@gmail.com'),
(2, 'Megha K Prasad', '4GW20IS029', '$2y$10$BbT3.dSWL6euf1dRHldE4.WtshU1mGGWjac22eenMd.aIOhGVt/QC', 'meghakprasad@gmail.com'),
(3, 'Anaya', '4GW20IS002', '$2y$10$kZhZhG4QueWP8sVNqq9uYuNvu9sY/09So0n1sx04gUYqjIDnV5fp2', 'ananya@gmail.com'),
(4, 'Shreya', '4GW20IS047', '$2y$10$wBgBT16Un6SWmtBMXTCf0.P6IGP.SuRWyEQE3d6bGDH9qvtqdEBNG', 'shreya@gmail.com');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `donate`
--
ALTER TABLE `donate`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `usn` (`username`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `donate`
--
ALTER TABLE `donate`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=86;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
