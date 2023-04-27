-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 27, 2023 at 10:44 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbashwani`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbakun`
--

CREATE TABLE `tbakun` (
  `id` int(11) NOT NULL,
  `nama` varchar(40) NOT NULL,
  `email` varchar(100) NOT NULL,
  `alamat` text NOT NULL,
  `password` text NOT NULL,
  `level` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbakun`
--

INSERT INTO `tbakun` (`id`, `nama`, `email`, `alamat`, `password`, `level`) VALUES
(1, 'Yahya', 'yha@gmail.com', 'Jl Panjaitan', '123', 'USER'),
(2, 'owner', 'owner', 'owner', 'owner', 'OWNER'),
(4, 'Asep Gunawan', 'asep@gmail.com', 'Jl Mangkuraja', 'asep', 'KARYAWAN'),
(5, 'Kurniawan', 'awan@gmail.com', 'Jl Menuju Kebatilan', '123', 'KARYAWAN');

-- --------------------------------------------------------

--
-- Table structure for table `tbbarang`
--

CREATE TABLE `tbbarang` (
  `id` int(11) NOT NULL,
  `nama` text NOT NULL,
  `tipe` text NOT NULL,
  `jenis` text NOT NULL,
  `stok` int(11) NOT NULL,
  `harga` int(11) NOT NULL,
  `desk` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbbarang`
--

INSERT INTO `tbbarang` (`id`, `nama`, `tipe`, `jenis`, `stok`, `harga`, `desk`) VALUES
(2, 'Martil', 'Alat', 'Non-Elektrik', 0, 0, '-'),
(3, 'Paku', 'Bahan', 'Bangunan', 122, 3000, 'paku beton'),
(4, 'Palu', 'Alat', 'Non-Elektrik', 94, 0, '-'),
(5, 'Semen', 'Bahan', 'Bangunan', 0, 0, 'Semen Halus');

-- --------------------------------------------------------

--
-- Table structure for table `tbgawai`
--

CREATE TABLE `tbgawai` (
  `gaji` int(11) NOT NULL,
  `shift` text NOT NULL,
  `email` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbgawai`
--

INSERT INTO `tbgawai` (`gaji`, `shift`, `email`) VALUES
(400000, 'Malam', 'asep@gmail.com'),
(400000, 'Siang', 'awan@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `tbpesan`
--

CREATE TABLE `tbpesan` (
  `idPesan` int(11) NOT NULL,
  `idPel` int(11) NOT NULL,
  `idBar` int(11) NOT NULL,
  `qty` int(11) NOT NULL,
  `status` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tbriwayat`
--

CREATE TABLE `tbriwayat` (
  `id` int(11) NOT NULL,
  `nama` text NOT NULL,
  `item` text NOT NULL,
  `alamat` text NOT NULL,
  `status` text NOT NULL,
  `qty` int(11) NOT NULL,
  `harga` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbriwayat`
--

INSERT INTO `tbriwayat` (`id`, `nama`, `item`, `alamat`, `status`, `qty`, `harga`) VALUES
(1, 'Yahya', 'Palu', 'Jl Panjaitan', 'Selesai', 5, 56000),
(1, 'Yahya', 'Paku', 'Jl Panjaitan', 'Selesai', 3, 3000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbakun`
--
ALTER TABLE `tbakun`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `tbbarang`
--
ALTER TABLE `tbbarang`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbgawai`
--
ALTER TABLE `tbgawai`
  ADD KEY `email` (`email`(768));

--
-- Indexes for table `tbpesan`
--
ALTER TABLE `tbpesan`
  ADD PRIMARY KEY (`idPesan`),
  ADD KEY `fk_idPelanggan` (`idPel`),
  ADD KEY `fk_idBarang` (`idBar`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbakun`
--
ALTER TABLE `tbakun`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `tbbarang`
--
ALTER TABLE `tbbarang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `tbpesan`
--
ALTER TABLE `tbpesan`
  MODIFY `idPesan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tbpesan`
--
ALTER TABLE `tbpesan`
  ADD CONSTRAINT `fk_idBarang` FOREIGN KEY (`idBar`) REFERENCES `tbbarang` (`id`),
  ADD CONSTRAINT `fk_idPelanggan` FOREIGN KEY (`idPel`) REFERENCES `tbakun` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
