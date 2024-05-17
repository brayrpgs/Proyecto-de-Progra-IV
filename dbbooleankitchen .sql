-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 16, 2024 at 06:18 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbbooleankitchen`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_bk_admin`
--

CREATE TABLE `tb_bk_admin` (
  `ID` int(11) NOT NULL,
  `ID_CARNET` varchar(255) NOT NULL,
  `NOMBRE` varchar(255) NOT NULL,
  `APELLIDO` varchar(255) NOT NULL,
  `ID_USUARIO` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tb_bk_category`
--

CREATE TABLE `tb_bk_category` (
  `ID` int(11) NOT NULL,
  `IDENTIFICADOR` varchar(255) NOT NULL,
  `NOMBRE` varchar(255) NOT NULL,
  `DESCRIPCION` varchar(255) NOT NULL,
  `RUTA_IMG` varchar(100) NOT NULL,
  `ETIQUETA` varchar(255) NOT NULL,
  `FECHA` date NOT NULL,
  `VISIBLE` tinyint(1) NOT NULL,
  `COMENTARIO_ADMIN` varchar(255) NOT NULL,
  `CREADO_POR` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_bk_category`
--

INSERT INTO `tb_bk_category` (`ID`, `IDENTIFICADOR`, `NOMBRE`, `DESCRIPCION`, `RUTA_IMG`, `ETIQUETA`, `FECHA`, `VISIBLE`, `COMENTARIO_ADMIN`, `CREADO_POR`) VALUES
(4, 'CAT-001', 'Dieta', 'Categoria para una dieta', '2024 05 15 03 49 27Noticia1.jpg', 'RECETAS', '2024-05-15', 0, 'NS', 'Josue Porras'),
(15, 'CAT-066', 'Dietas', 'Lo mas importante para mantener la salud', '2024 05 15 00 57 10dieta.jpg', 'UTENCILIO', '2024-05-15', 1, 'NS', 'Josue Porras'),
(16, 'CAT-002', 'Gourmet', 'Comida de alta categoria', '2024 05 15 00 58 01gourmet.jpg', 'RECETAS', '2024-05-15', 1, 'NS', 'Josue Porras'),
(17, 'CAT-004', 'cubiertos', 'Se usa especialmente para comer', '2024 05 15 00 59 38tenedor.jpeg', 'UTENCILIO', '2024-05-15', 1, 'NS', 'Josue Porras'),
(23, 'CAT-030', 'Postres ', 'Rico en azucares', '2024 05 15 12 32 45postres.jpg', 'INGREDIENTE', '2024-05-15', 0, 'NS', 'Josue Porras');

-- --------------------------------------------------------

--
-- Table structure for table `tb_bk_comment`
--

CREATE TABLE `tb_bk_comment` (
  `ID` int(11) NOT NULL,
  `ID_RECETA` int(11) NOT NULL,
  `ID_USUARIO` int(11) NOT NULL,
  `IDENTIFICADOR` varchar(255) NOT NULL,
  `FECHA` datetime NOT NULL,
  `PUNTUACION` int(11) NOT NULL,
  `COMENTARIO` varchar(255) NOT NULL,
  `REACCIONES` int(11) NOT NULL,
  `TITULO` varchar(50) NOT NULL,
  `DIFICULTAD` varchar(100) NOT NULL,
  `CLARIDAD_INST` int(11) NOT NULL,
  `SUGERENCIA` varchar(255) NOT NULL,
  `RECOMENDACION` tinyint(1) NOT NULL,
  `MODIFICACION` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_bk_comment`
--

INSERT INTO `tb_bk_comment` (`ID`, `ID_RECETA`, `ID_USUARIO`, `IDENTIFICADOR`, `FECHA`, `PUNTUACION`, `COMENTARIO`, `REACCIONES`, `TITULO`, `DIFICULTAD`, `CLARIDAD_INST`, `SUGERENCIA`, `RECOMENDACION`, `MODIFICACION`) VALUES
(18, 22, 1, 'COM-00Buena receta2024-05-15T11:32:34.874513500', '2024-05-15 11:32:34', 3, 'Un comentario', 1, 'Buena receta', 'Intermedio', 10, '', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `tb_bk_ingredient`
--

CREATE TABLE `tb_bk_ingredient` (
  `ID` int(11) NOT NULL,
  `IDENTIFICADOR` varchar(255) NOT NULL,
  `NOMBRE` varchar(255) NOT NULL,
  `CANTIDAD` float NOT NULL,
  `PESO` float NOT NULL,
  `ID_CATEGORIA` int(11) NOT NULL,
  `CALORIAS` float NOT NULL,
  `DESCRIPCION` varchar(100) NOT NULL,
  `RUTA_IMG` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_bk_ingredient`
--

INSERT INTO `tb_bk_ingredient` (`ID`, `IDENTIFICADOR`, `NOMBRE`, `CANTIDAD`, `PESO`, `ID_CATEGORIA`, `CALORIAS`, `DESCRIPCION`, `RUTA_IMG`) VALUES
(5, 'ING-9975', 'Cebolla', 9, 9, 23, 9, 'Sin descripción', '2024 05 15 19 01 43Noticia3.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `tb_bk_mealplan`
--

CREATE TABLE `tb_bk_mealplan` (
  `ID` int(11) NOT NULL,
  `IDENTIFICADOR` varchar(255) NOT NULL,
  `NOMBRE` varchar(255) NOT NULL,
  `DESCRIPCION` varchar(200) NOT NULL,
  `FECHA` date NOT NULL,
  `PRECIO` float NOT NULL,
  `TIPO_DIETA` varchar(100) NOT NULL,
  `ESTADO` tinyint(1) NOT NULL,
  `DURACION` time NOT NULL,
  `ID_USUARIO` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tb_bk_meal_recip`
--

CREATE TABLE `tb_bk_meal_recip` (
  `ID` int(11) NOT NULL,
  `ID_MEAL` int(11) NOT NULL,
  `ID_RECETA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tb_bk_notice`
--

CREATE TABLE `tb_bk_notice` (
  `ID` int(11) NOT NULL,
  `IDENTIFICADOR` varchar(255) NOT NULL,
  `TITULO` varchar(255) NOT NULL,
  `RESUMEN` varchar(800) NOT NULL,
  `FECHA` date NOT NULL,
  `AUTOR` varchar(50) NOT NULL,
  `ESTADO` tinyint(1) NOT NULL,
  `URL` varchar(255) NOT NULL,
  `RUTA_IMG` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_bk_notice`
--

INSERT INTO `tb_bk_notice` (`ID`, `IDENTIFICADOR`, `TITULO`, `RESUMEN`, `FECHA`, `AUTOR`, `ESTADO`, `URL`, `RUTA_IMG`) VALUES
(3, 'NOT-12', 'Famoso chef descubre...', 'un nuevo ingrediente', '2000-01-21', 'Ceasar', 1, 'ninguna', '2024 05 15 21 56 41Soup_Spoon.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `tb_bk_origin`
--

CREATE TABLE `tb_bk_origin` (
  `ID` int(11) NOT NULL,
  `IDENTIFICADOR` varchar(100) NOT NULL,
  `NOMBRE` varchar(100) NOT NULL,
  `DESCRIPCION` varchar(255) NOT NULL,
  `PAIS` varchar(100) NOT NULL,
  `RUTA_IMG` varchar(100) NOT NULL,
  `CONTINENTE` varchar(255) NOT NULL,
  `SABOR` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_bk_origin`
--

INSERT INTO `tb_bk_origin` (`ID`, `IDENTIFICADOR`, `NOMBRE`, `DESCRIPCION`, `PAIS`, `RUTA_IMG`, `CONTINENTE`, `SABOR`) VALUES
(1, 'ORI-001', 'Mexicana', 'desc', 'Mexico', 'receta.jpeg', 'jvg', 'jhg');

-- --------------------------------------------------------

--
-- Table structure for table `tb_bk_preparation`
--

CREATE TABLE `tb_bk_preparation` (
  `ID` int(11) NOT NULL,
  `IDENTIFICADOR` varchar(255) NOT NULL,
  `ID_RECETA` int(11) NOT NULL,
  `DIFICULTAD` varchar(50) NOT NULL,
  `TIEMPO` time NOT NULL,
  `RUTA_IMG` varchar(100) NOT NULL,
  `NOTA_AUTOR` varchar(255) NOT NULL,
  `ALERTA` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_bk_preparation`
--

INSERT INTO `tb_bk_preparation` (`ID`, `IDENTIFICADOR`, `ID_RECETA`, `DIFICULTAD`, `TIEMPO`, `RUTA_IMG`, `NOTA_AUTOR`, `ALERTA`) VALUES
(11, 'PREP-Media2024-05-15T03:59:58.398913100', 22, 'Media', '01:00:00', 'PREP-Media2024-05-15T03-59-58.398913100Noticia3.jpg', 'Sencilla de hacer', 'Ninguna'),
(12, 'PREP-Media2024-05-15T04:08:44.614140', 23, 'Media', '01:00:00', 'PREP-Media2024-05-15T04-08-44.614140Noticia2.jpg', 'Sencilla de hacer', 'Ninguna'),
(13, 'PREP-Media2024-05-15T04:11:11.104447900', 24, 'Media', '01:00:00', 'PREP-Media2024-05-15T04-11-11.104447900Noticia1.jpg', 'Muy buena la receta', 'Ninguna'),
(15, 'PREP-solo bueno2024-05-15T21:54:54.573677100', 26, 'solo bueno', '01:12:00', 'PREP-solo-bueno2024-05-15T21-54-54.573677100Tenedor-Mesa-Munique-1-55290.webp', 'ninguna', 'ninguna');

-- --------------------------------------------------------

--
-- Table structure for table `tb_bk_preparation_list`
--

CREATE TABLE `tb_bk_preparation_list` (
  `ID` int(11) NOT NULL,
  `TITULO_PASO` varchar(100) NOT NULL,
  `RUTA_IMG` varchar(255) NOT NULL,
  `ID_PREPARACION` int(11) NOT NULL,
  `PASO` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_bk_preparation_list`
--

INSERT INTO `tb_bk_preparation_list` (`ID`, `TITULO_PASO`, `RUTA_IMG`, `ID_PREPARACION`, `PASO`) VALUES
(11, 'Se cocinan las papas con salsa', '2024 05 15 03 59 11Noticia3.jpg', 11, 'Cocinar las papas'),
(12, 'Se cocina la carne', '2024 05 15 03 59 32Noticia3.jpg', 11, 'Cocinar la carne'),
(13, 'Se le agregan olores ', '2024 05 15 03 59 56Noticia3.jpg', 11, 'Poner olores '),
(14, 'Se pone a hervir agua ', '2024 05 15 04 08 09Noticia2.jpg', 12, 'Hervir agua'),
(15, 'Se pone la pasta y se le pone sal', '2024 05 15 04 08 40Noticia2.jpg', 12, 'Poner la pasta'),
(16, 'Se cocina el pescado', '2024 05 15 04 10 30Noticia1.jpg', 13, 'Cocinar el pescado'),
(17, 'Se le pone limón al pescado', '2024 05 15 04 11 09Noticia1.jpg', 13, 'Poner limón'),
(19, 'paso', '2024 05 15 21 54 41Tenedor-Mesa-Munique-1-55290.webp', 15, 'paso juan'),
(20, 'paso tu', '2024 05 15 21 54 53Soup_Spoon.jpg', 15, 'paso tu');

-- --------------------------------------------------------

--
-- Table structure for table `tb_bk_recipe`
--

CREATE TABLE `tb_bk_recipe` (
  `ID` int(11) NOT NULL,
  `IDENTIFICADOR` varchar(255) NOT NULL,
  `NOMBRE` varchar(255) NOT NULL,
  `ID_ORIGEN` int(11) NOT NULL,
  `ID_CATEGORIA` int(11) NOT NULL,
  `CALIFICACION` int(11) NOT NULL,
  `RUTA_IMG` varchar(100) NOT NULL,
  `TOTAL_CALIFICACION` int(11) NOT NULL,
  `ID_USUARIO` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_bk_recipe`
--

INSERT INTO `tb_bk_recipe` (`ID`, `IDENTIFICADOR`, `NOMBRE`, `ID_ORIGEN`, `ID_CATEGORIA`, `CALIFICACION`, `RUTA_IMG`, `TOTAL_CALIFICACION`, `ID_USUARIO`) VALUES
(22, 'REC-Carne con papas2024-05-15T03:59:58.399909300', 'Carne con papas', 1, 4, 0, 'REC-Carne-con-papas2024-05-15T03-59-58.401908800Noticia3.jpg', 0, 1),
(23, 'REC-Pasta blanca2024-05-15T04:08:44.615140100', 'Pasta blanca', 1, 4, 0, 'REC-Pasta-blanca2024-05-15T04-08-44.616138300Noticia2.jpg', 0, 1),
(24, 'REC-Pescado al lim2024-05-15T04:11:11.104447900', 'Pescado al lim', 1, 4, 0, 'REC-Pescado-al-lim2024-05-15T04-11-11.105447Noticia1.jpg', 0, 1),
(26, 'REC-gallopinto2024-05-15T21:54:54.575724400', 'gallopinto', 1, 4, 0, 'REC-gallopinto2024-05-15T21-54-54.575724400Tenedor-Mesa-Munique-1-55290.webp', 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `tb_bk_recipe_in`
--

CREATE TABLE `tb_bk_recipe_in` (
  `ID` int(11) NOT NULL,
  `ID_INGREDIENTE` int(11) NOT NULL,
  `ID_RECETA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tb_bk_recipe_utens`
--

CREATE TABLE `tb_bk_recipe_utens` (
  `ID` int(11) NOT NULL,
  `ID_UTENCILIO` int(11) NOT NULL,
  `ID_RECETA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tb_bk_shoplist`
--

CREATE TABLE `tb_bk_shoplist` (
  `ID` int(11) NOT NULL,
  `IDENTIFICADOR` varchar(255) NOT NULL,
  `NOMBRE` varchar(100) NOT NULL,
  `CANTIDAD` float NOT NULL,
  `NOTA` varchar(255) NOT NULL,
  `MARCA` varchar(200) NOT NULL,
  `ESTADO` tinyint(1) NOT NULL,
  `ID_USUARIO` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tb_bk_shop_ingred`
--

CREATE TABLE `tb_bk_shop_ingred` (
  `ID` int(11) NOT NULL,
  `ID_SHOP` int(11) NOT NULL,
  `ID_INGRENDIENTE` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tb_bk_shop_utens`
--

CREATE TABLE `tb_bk_shop_utens` (
  `ID` int(11) NOT NULL,
  `ID_SHOP` int(11) NOT NULL,
  `ID_UTENCILIO` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tb_bk_user`
--

CREATE TABLE `tb_bk_user` (
  `ID` int(11) NOT NULL,
  `CREACION_AT` date NOT NULL,
  `IDENTIFICADOR` varchar(255) NOT NULL,
  `NOMBRE_USUARIO` varchar(255) NOT NULL,
  `EMAIL` varchar(255) NOT NULL,
  `PASSWORD` varchar(255) NOT NULL,
  `RUTA_IMG` varchar(100) NOT NULL,
  `TIPO_USUARIO` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_bk_user`
--

INSERT INTO `tb_bk_user` (`ID`, `CREACION_AT`, `IDENTIFICADOR`, `NOMBRE_USUARIO`, `EMAIL`, `PASSWORD`, `RUTA_IMG`, `TIPO_USUARIO`) VALUES
(1, '2024-05-12', 'USER-001', 'Daniel Briones', 'daniel@gmail.com', '123', 'no hay', 'ADMIN');

-- --------------------------------------------------------

--
-- Table structure for table `tb_bk_utensil`
--

CREATE TABLE `tb_bk_utensil` (
  `ID` int(11) NOT NULL,
  `IDENTIFICADOR` varchar(100) NOT NULL,
  `NOMBRE` varchar(150) NOT NULL,
  `MATERIAL` varchar(100) NOT NULL,
  `ID_CATEGORIA` int(11) NOT NULL,
  `PRECIO` float NOT NULL,
  `CANTIDAD` int(11) NOT NULL,
  `RUTA_IMG` varchar(255) NOT NULL,
  `DESCRIPCION` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_bk_admin`
--
ALTER TABLE `tb_bk_admin`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `ID_CARNET` (`ID_CARNET`),
  ADD KEY `ID_USUARIO` (`ID_USUARIO`) USING BTREE;

--
-- Indexes for table `tb_bk_category`
--
ALTER TABLE `tb_bk_category`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `IDENTIFICADOR` (`IDENTIFICADOR`),
  ADD UNIQUE KEY `NOMBRE` (`NOMBRE`);

--
-- Indexes for table `tb_bk_comment`
--
ALTER TABLE `tb_bk_comment`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `IDENTIFICADOR` (`IDENTIFICADOR`),
  ADD KEY `ID_RECETA` (`ID_RECETA`),
  ADD KEY `ID_USUARIO` (`ID_USUARIO`) USING BTREE;

--
-- Indexes for table `tb_bk_ingredient`
--
ALTER TABLE `tb_bk_ingredient`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `NOMBRE` (`NOMBRE`),
  ADD UNIQUE KEY `IDENTIFICADOR` (`IDENTIFICADOR`),
  ADD KEY `ID_CATEGORIA` (`ID_CATEGORIA`);

--
-- Indexes for table `tb_bk_mealplan`
--
ALTER TABLE `tb_bk_mealplan`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `IDENTIFICADOR` (`IDENTIFICADOR`),
  ADD KEY `ID_USUARIO` (`ID_USUARIO`);

--
-- Indexes for table `tb_bk_meal_recip`
--
ALTER TABLE `tb_bk_meal_recip`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_MEAL` (`ID_MEAL`),
  ADD KEY `ID_RECETA` (`ID_RECETA`) USING BTREE;

--
-- Indexes for table `tb_bk_notice`
--
ALTER TABLE `tb_bk_notice`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `IDENTIFICADOR` (`IDENTIFICADOR`),
  ADD UNIQUE KEY `URL` (`URL`);

--
-- Indexes for table `tb_bk_origin`
--
ALTER TABLE `tb_bk_origin`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `IDENTIFICADOR` (`IDENTIFICADOR`),
  ADD UNIQUE KEY `NOMBRE` (`NOMBRE`);

--
-- Indexes for table `tb_bk_preparation`
--
ALTER TABLE `tb_bk_preparation`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `IDENTIFICADOR` (`IDENTIFICADOR`),
  ADD KEY `ID_RECETA` (`ID_RECETA`) USING BTREE;

--
-- Indexes for table `tb_bk_preparation_list`
--
ALTER TABLE `tb_bk_preparation_list`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_PREPARACION` (`ID_PREPARACION`);

--
-- Indexes for table `tb_bk_recipe`
--
ALTER TABLE `tb_bk_recipe`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `IDENTIFICADOR` (`IDENTIFICADOR`),
  ADD KEY `ID_ORIGEN` (`ID_ORIGEN`) USING BTREE,
  ADD KEY `ID_CATEGORIA` (`ID_CATEGORIA`) USING BTREE,
  ADD KEY `ID_USUARIO` (`ID_USUARIO`) USING BTREE;

--
-- Indexes for table `tb_bk_recipe_in`
--
ALTER TABLE `tb_bk_recipe_in`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_INGREDIENTE` (`ID_INGREDIENTE`),
  ADD KEY `ID_RECETA` (`ID_RECETA`);

--
-- Indexes for table `tb_bk_recipe_utens`
--
ALTER TABLE `tb_bk_recipe_utens`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_UTENCILIO` (`ID_UTENCILIO`),
  ADD KEY `ID_RECETA` (`ID_RECETA`);

--
-- Indexes for table `tb_bk_shoplist`
--
ALTER TABLE `tb_bk_shoplist`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `IDENTIFICADOR` (`IDENTIFICADOR`),
  ADD KEY `ID_USUARIO` (`ID_USUARIO`);

--
-- Indexes for table `tb_bk_shop_ingred`
--
ALTER TABLE `tb_bk_shop_ingred`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_SHOP` (`ID_SHOP`),
  ADD KEY `ID_INGRENDIENTE` (`ID_INGRENDIENTE`) USING BTREE;

--
-- Indexes for table `tb_bk_shop_utens`
--
ALTER TABLE `tb_bk_shop_utens`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_SHOP` (`ID_SHOP`),
  ADD KEY `ID_UTENCILIO` (`ID_UTENCILIO`);

--
-- Indexes for table `tb_bk_user`
--
ALTER TABLE `tb_bk_user`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `IDENTIFICADOR` (`IDENTIFICADOR`),
  ADD UNIQUE KEY `NOMBRE_USUARIO` (`NOMBRE_USUARIO`),
  ADD UNIQUE KEY `EMAIL` (`EMAIL`);

--
-- Indexes for table `tb_bk_utensil`
--
ALTER TABLE `tb_bk_utensil`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `NOMBRE` (`NOMBRE`),
  ADD UNIQUE KEY `IDENTIFICADOR` (`IDENTIFICADOR`),
  ADD KEY `ID_CATEGORIA` (`ID_CATEGORIA`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_bk_admin`
--
ALTER TABLE `tb_bk_admin`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tb_bk_category`
--
ALTER TABLE `tb_bk_category`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `tb_bk_comment`
--
ALTER TABLE `tb_bk_comment`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `tb_bk_ingredient`
--
ALTER TABLE `tb_bk_ingredient`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `tb_bk_mealplan`
--
ALTER TABLE `tb_bk_mealplan`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tb_bk_meal_recip`
--
ALTER TABLE `tb_bk_meal_recip`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tb_bk_notice`
--
ALTER TABLE `tb_bk_notice`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tb_bk_origin`
--
ALTER TABLE `tb_bk_origin`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `tb_bk_preparation`
--
ALTER TABLE `tb_bk_preparation`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `tb_bk_preparation_list`
--
ALTER TABLE `tb_bk_preparation_list`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `tb_bk_recipe`
--
ALTER TABLE `tb_bk_recipe`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `tb_bk_recipe_in`
--
ALTER TABLE `tb_bk_recipe_in`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tb_bk_recipe_utens`
--
ALTER TABLE `tb_bk_recipe_utens`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tb_bk_shoplist`
--
ALTER TABLE `tb_bk_shoplist`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tb_bk_shop_ingred`
--
ALTER TABLE `tb_bk_shop_ingred`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tb_bk_shop_utens`
--
ALTER TABLE `tb_bk_shop_utens`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tb_bk_user`
--
ALTER TABLE `tb_bk_user`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tb_bk_utensil`
--
ALTER TABLE `tb_bk_utensil`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tb_bk_admin`
--
ALTER TABLE `tb_bk_admin`
  ADD CONSTRAINT `tb_bk_admin_ibfk_1` FOREIGN KEY (`ID_USUARIO`) REFERENCES `tb_bk_user` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `tb_bk_comment`
--
ALTER TABLE `tb_bk_comment`
  ADD CONSTRAINT `tb_bk_comment_ibfk_1` FOREIGN KEY (`ID_RECETA`) REFERENCES `tb_bk_recipe` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `tb_bk_comment_ibfk_2` FOREIGN KEY (`ID_USUARIO`) REFERENCES `tb_bk_user` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `tb_bk_ingredient`
--
ALTER TABLE `tb_bk_ingredient`
  ADD CONSTRAINT `tb_bk_ingredient_ibfk_1` FOREIGN KEY (`ID_CATEGORIA`) REFERENCES `tb_bk_category` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `tb_bk_mealplan`
--
ALTER TABLE `tb_bk_mealplan`
  ADD CONSTRAINT `tb_bk_mealplan_ibfk_1` FOREIGN KEY (`ID_USUARIO`) REFERENCES `tb_bk_user` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `tb_bk_meal_recip`
--
ALTER TABLE `tb_bk_meal_recip`
  ADD CONSTRAINT `tb_bk_meal_recip_ibfk_1` FOREIGN KEY (`ID_RECETA`) REFERENCES `tb_bk_recipe` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `tb_bk_meal_recip_ibfk_2` FOREIGN KEY (`ID_MEAL`) REFERENCES `tb_bk_mealplan` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `tb_bk_preparation`
--
ALTER TABLE `tb_bk_preparation`
  ADD CONSTRAINT `tb_bk_preparation_ibfk_1` FOREIGN KEY (`ID_RECETA`) REFERENCES `tb_bk_recipe` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `tb_bk_preparation_list`
--
ALTER TABLE `tb_bk_preparation_list`
  ADD CONSTRAINT `tb_bk_preparation_list_ibfk_1` FOREIGN KEY (`ID_PREPARACION`) REFERENCES `tb_bk_preparation` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `tb_bk_recipe`
--
ALTER TABLE `tb_bk_recipe`
  ADD CONSTRAINT `tb_bk_recipe_ibfk_2` FOREIGN KEY (`ID_CATEGORIA`) REFERENCES `tb_bk_category` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `tb_bk_recipe_ibfk_3` FOREIGN KEY (`ID_ORIGEN`) REFERENCES `tb_bk_origin` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `tb_bk_recipe_ibfk_4` FOREIGN KEY (`ID_USUARIO`) REFERENCES `tb_bk_user` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `tb_bk_recipe_in`
--
ALTER TABLE `tb_bk_recipe_in`
  ADD CONSTRAINT `tb_bk_recipe_in_ibfk_1` FOREIGN KEY (`ID_RECETA`) REFERENCES `tb_bk_recipe` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `tb_bk_recipe_in_ibfk_2` FOREIGN KEY (`ID_INGREDIENTE`) REFERENCES `tb_bk_ingredient` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `tb_bk_recipe_utens`
--
ALTER TABLE `tb_bk_recipe_utens`
  ADD CONSTRAINT `tb_bk_recipe_utens_ibfk_1` FOREIGN KEY (`ID_UTENCILIO`) REFERENCES `tb_bk_utensil` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `tb_bk_recipe_utens_ibfk_2` FOREIGN KEY (`ID_RECETA`) REFERENCES `tb_bk_recipe` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `tb_bk_shoplist`
--
ALTER TABLE `tb_bk_shoplist`
  ADD CONSTRAINT `tb_bk_shoplist_ibfk_1` FOREIGN KEY (`ID_USUARIO`) REFERENCES `tb_bk_user` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `tb_bk_shop_ingred`
--
ALTER TABLE `tb_bk_shop_ingred`
  ADD CONSTRAINT `tb_bk_shop_ingred_ibfk_1` FOREIGN KEY (`ID_INGRENDIENTE`) REFERENCES `tb_bk_ingredient` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `tb_bk_shop_ingred_ibfk_2` FOREIGN KEY (`ID_SHOP`) REFERENCES `tb_bk_shoplist` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `tb_bk_shop_utens`
--
ALTER TABLE `tb_bk_shop_utens`
  ADD CONSTRAINT `tb_bk_shop_utens_ibfk_1` FOREIGN KEY (`ID_UTENCILIO`) REFERENCES `tb_bk_utensil` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `tb_bk_shop_utens_ibfk_2` FOREIGN KEY (`ID_SHOP`) REFERENCES `tb_bk_shoplist` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `tb_bk_utensil`
--
ALTER TABLE `tb_bk_utensil`
  ADD CONSTRAINT `tb_bk_utensil_ibfk_1` FOREIGN KEY (`ID_CATEGORIA`) REFERENCES `tb_bk_category` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
