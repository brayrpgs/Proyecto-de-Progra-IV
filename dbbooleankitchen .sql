-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 12-04-2024 a las 19:08:14
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `dbbooleankitchen`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_bk_admin`
--

CREATE TABLE `tb_bk_admin` (
  `ID` int(11) NOT NULL,
  `ID_CARNET` varchar(255) NOT NULL,
  `NOMBRE` varchar(255) NOT NULL,
  `SECNAME` varchar(255) NOT NULL,
  `ID_USERDATE` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_bk_category`
--

CREATE TABLE `tb_bk_category` (
  `ID` int(11) NOT NULL,
  `IDENTIFICADOR` varchar(255) NOT NULL,
  `NOMBRE` varchar(255) NOT NULL,
  `DESCRIPCION` varchar(255) NOT NULL,
  `RUTA_IMG` varchar(100) NOT NULL,
  `ETIQUETA` varchar(255) NOT NULL,
  `CANTIDAD` int(11) NOT NULL,
  `FECHA` date NOT NULL,
  `VISIBLE` tinyint(1) NOT NULL,
  `ADMIN_COMMENT` varchar(255) NOT NULL,
  `CREATE_BY_ADMIN` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_bk_comment`
--

CREATE TABLE `tb_bk_comment` (
  `ID` int(11) NOT NULL,
  `ID_RECETA` int(11) NOT NULL,
  `ID_USER` int(11) NOT NULL,
  `FECHA` time NOT NULL,
  `PUNTUACION` int(11) NOT NULL,
  `COMENTARIO` varchar(255) NOT NULL,
  `REACCIONES` int(11) NOT NULL,
  `TITULO` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_bk_ingredient`
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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_bk_mealplan`
--

CREATE TABLE `tb_bk_mealplan` (
  `ID` int(11) NOT NULL,
  `NOMBRE` varchar(255) NOT NULL,
  `DESCRIPCION` varchar(200) NOT NULL,
  `FECHA` time NOT NULL,
  `PRECIO` float NOT NULL,
  `TIPO_DIETA` varchar(30) NOT NULL,
  `ESTADO` tinyint(1) NOT NULL,
  `DURACION` time NOT NULL,
  `ID_USUARIO` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_bk_meal_recip`
--

CREATE TABLE `tb_bk_meal_recip` (
  `ID` int(11) NOT NULL,
  `ID_MEAL` int(11) NOT NULL,
  `ID_RECIPE` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_bk_notice`
--

CREATE TABLE `tb_bk_notice` (
  `ID` int(11) NOT NULL,
  `IDENTIFICADOR` varchar(255) NOT NULL,
  `TITULO` varchar(255) NOT NULL,
  `RESUMEN` varchar(400) NOT NULL,
  `FECHA` time NOT NULL,
  `AUTHOR` varchar(50) NOT NULL,
  `ESTADO` tinyint(1) NOT NULL,
  `URL` varchar(255) NOT NULL,
  `RUTA_IMG` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_bk_origin`
--

CREATE TABLE `tb_bk_origin` (
  `ID` int(11) NOT NULL,
  `IDENTIFICADOR` varchar(100) NOT NULL,
  `NOMBRE` varchar(100) NOT NULL,
  `DESCRIPCION` text NOT NULL,
  `RUTA_IMG` varchar(100) NOT NULL,
  `CONTINENTE` varchar(255) NOT NULL,
  `SABOR` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_bk_origin_list`
--

CREATE TABLE `tb_bk_origin_list` (
  `ID` int(11) NOT NULL,
  `ID_RECETA` int(11) NOT NULL,
  `ID_ORIGIN` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_bk_preparation`
--

CREATE TABLE `tb_bk_preparation` (
  `ID` int(11) NOT NULL,
  `IDENTIFICADOR` varchar(255) NOT NULL,
  `ID_RECIPE` int(11) NOT NULL,
  `DIFICULTAD` varchar(50) NOT NULL,
  `TIEMPO` time NOT NULL,
  `RUTA_IMG` varchar(100) NOT NULL,
  `CANT_PASOS` int(11) NOT NULL,
  `NOTA_AUTOR` varchar(255) NOT NULL,
  `ALTERNATIVAS_INGRE` varchar(255) NOT NULL,
  `WARNINGS` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_bk_preparation_list`
--

CREATE TABLE `tb_bk_preparation_list` (
  `ID` int(11) NOT NULL,
  `ID_PREPARACION` int(11) NOT NULL,
  `PASO` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_bk_recipe`
--

CREATE TABLE `tb_bk_recipe` (
  `ID` int(11) NOT NULL,
  `IDENTIFICADOR` varchar(255) NOT NULL,
  `NOMBRE` varchar(255) NOT NULL,
  `ORIGIN` int(11) NOT NULL,
  `ID_CATEGORY` int(11) NOT NULL,
  `CALIFICACION` int(11) NOT NULL,
  `RUTA_IMG` varchar(100) NOT NULL,
  `ID_USER` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_bk_recipe_in`
--

CREATE TABLE `tb_bk_recipe_in` (
  `ID` int(11) NOT NULL,
  `ID_INGREDIENTE` int(11) NOT NULL,
  `ID_RECETA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_bk_recipe_utens`
--

CREATE TABLE `tb_bk_recipe_utens` (
  `ID` int(11) NOT NULL,
  `ID_UTENCILIO` int(11) NOT NULL,
  `ID_RECETA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_bk_shoplist`
--

CREATE TABLE `tb_bk_shoplist` (
  `ID` int(11) NOT NULL,
  `NOMBRE` varchar(100) NOT NULL,
  `CANTIDAD` float NOT NULL,
  `NOTA` varchar(255) NOT NULL,
  `MARCA` varchar(200) NOT NULL,
  `ESTADO` tinyint(1) NOT NULL,
  `ID_USUARIO` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_bk_shop_ingred`
--

CREATE TABLE `tb_bk_shop_ingred` (
  `ID` int(11) NOT NULL,
  `ID_SHOP` int(11) NOT NULL,
  `ID_INDRENDIENTE` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_bk_shop_utens`
--

CREATE TABLE `tb_bk_shop_utens` (
  `ID` int(11) NOT NULL,
  `ID_SHOP` int(11) NOT NULL,
  `ID_UTENCILIO` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_bk_user`
--

CREATE TABLE `tb_bk_user` (
  `ID` int(11) NOT NULL,
  `IDENTIFICADOR` varchar(255) NOT NULL,
  `USERNAME` varchar(255) NOT NULL,
  `EMAIL` varchar(255) NOT NULL,
  `PASSWORD` varchar(255) NOT NULL,
  `RUTA_IMG` varchar(100) NOT NULL,
  `USERTYPE` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tb_bk_user`
--

INSERT INTO `tb_bk_user` (`ID`, `IDENTIFICADOR`, `USERNAME`, `EMAIL`, `PASSWORD`, `RUTA_IMG`, `USERTYPE`) VALUES
(1, '', 'Daniel', 'joto.com', '1234', '1', 'Admin');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_bk_utensil`
--

CREATE TABLE `tb_bk_utensil` (
  `ID` int(11) NOT NULL,
  `IDENTIFICADOR` varchar(100) NOT NULL,
  `NOMBRE` varchar(150) NOT NULL,
  `MATERIAL` varchar(100) NOT NULL,
  `ID_CATEGORIA` int(11) NOT NULL,
  `PRICE` float NOT NULL,
  `CANTIDAD` int(11) NOT NULL,
  `RUTA_IMG` varchar(255) NOT NULL,
  `DESCRIPCION` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `tb_bk_admin`
--
ALTER TABLE `tb_bk_admin`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `ID_CARNET` (`ID_CARNET`),
  ADD KEY `ID_USERDATE` (`ID_USERDATE`);

--
-- Indices de la tabla `tb_bk_category`
--
ALTER TABLE `tb_bk_category`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `NOMBRE` (`NOMBRE`);

--
-- Indices de la tabla `tb_bk_comment`
--
ALTER TABLE `tb_bk_comment`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_RECETA` (`ID_RECETA`),
  ADD KEY `ID_USER` (`ID_USER`);

--
-- Indices de la tabla `tb_bk_ingredient`
--
ALTER TABLE `tb_bk_ingredient`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `NOMBRE` (`NOMBRE`),
  ADD KEY `ID_CATEGORIA` (`ID_CATEGORIA`);

--
-- Indices de la tabla `tb_bk_mealplan`
--
ALTER TABLE `tb_bk_mealplan`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_USUARIO` (`ID_USUARIO`);

--
-- Indices de la tabla `tb_bk_meal_recip`
--
ALTER TABLE `tb_bk_meal_recip`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_MEAL` (`ID_MEAL`),
  ADD KEY `ID_RECIPE` (`ID_RECIPE`);

--
-- Indices de la tabla `tb_bk_notice`
--
ALTER TABLE `tb_bk_notice`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `tb_bk_origin`
--
ALTER TABLE `tb_bk_origin`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `tb_bk_origin_list`
--
ALTER TABLE `tb_bk_origin_list`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_RECETA` (`ID_RECETA`),
  ADD KEY `ID_ORIGIN` (`ID_ORIGIN`);

--
-- Indices de la tabla `tb_bk_preparation`
--
ALTER TABLE `tb_bk_preparation`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_RECIPE` (`ID_RECIPE`);

--
-- Indices de la tabla `tb_bk_preparation_list`
--
ALTER TABLE `tb_bk_preparation_list`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_PREPARACION` (`ID_PREPARACION`);

--
-- Indices de la tabla `tb_bk_recipe`
--
ALTER TABLE `tb_bk_recipe`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_CATEGORY` (`ID_CATEGORY`),
  ADD KEY `ORIGIN` (`ORIGIN`),
  ADD KEY `ID_USER` (`ID_USER`);

--
-- Indices de la tabla `tb_bk_recipe_in`
--
ALTER TABLE `tb_bk_recipe_in`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_INGREDIENTE` (`ID_INGREDIENTE`),
  ADD KEY `ID_RECETA` (`ID_RECETA`);

--
-- Indices de la tabla `tb_bk_recipe_utens`
--
ALTER TABLE `tb_bk_recipe_utens`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_UTENCILIO` (`ID_UTENCILIO`),
  ADD KEY `ID_RECETA` (`ID_RECETA`);

--
-- Indices de la tabla `tb_bk_shoplist`
--
ALTER TABLE `tb_bk_shoplist`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_USUARIO` (`ID_USUARIO`);

--
-- Indices de la tabla `tb_bk_shop_ingred`
--
ALTER TABLE `tb_bk_shop_ingred`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_SHOP` (`ID_SHOP`),
  ADD KEY `ID_INDRENDIENTE` (`ID_INDRENDIENTE`);

--
-- Indices de la tabla `tb_bk_shop_utens`
--
ALTER TABLE `tb_bk_shop_utens`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_SHOP` (`ID_SHOP`),
  ADD KEY `ID_UTENCILIO` (`ID_UTENCILIO`);

--
-- Indices de la tabla `tb_bk_user`
--
ALTER TABLE `tb_bk_user`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `EMAIL` (`EMAIL`);

--
-- Indices de la tabla `tb_bk_utensil`
--
ALTER TABLE `tb_bk_utensil`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `NOMBRE` (`NOMBRE`),
  ADD KEY `ID_CATEGORIA` (`ID_CATEGORIA`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `tb_bk_admin`
--
ALTER TABLE `tb_bk_admin`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `tb_bk_category`
--
ALTER TABLE `tb_bk_category`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `tb_bk_comment`
--
ALTER TABLE `tb_bk_comment`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `tb_bk_ingredient`
--
ALTER TABLE `tb_bk_ingredient`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `tb_bk_mealplan`
--
ALTER TABLE `tb_bk_mealplan`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tb_bk_meal_recip`
--
ALTER TABLE `tb_bk_meal_recip`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tb_bk_notice`
--
ALTER TABLE `tb_bk_notice`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `tb_bk_origin`
--
ALTER TABLE `tb_bk_origin`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `tb_bk_origin_list`
--
ALTER TABLE `tb_bk_origin_list`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tb_bk_preparation`
--
ALTER TABLE `tb_bk_preparation`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `tb_bk_preparation_list`
--
ALTER TABLE `tb_bk_preparation_list`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tb_bk_recipe`
--
ALTER TABLE `tb_bk_recipe`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `tb_bk_recipe_in`
--
ALTER TABLE `tb_bk_recipe_in`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tb_bk_recipe_utens`
--
ALTER TABLE `tb_bk_recipe_utens`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tb_bk_shoplist`
--
ALTER TABLE `tb_bk_shoplist`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tb_bk_shop_ingred`
--
ALTER TABLE `tb_bk_shop_ingred`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tb_bk_shop_utens`
--
ALTER TABLE `tb_bk_shop_utens`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tb_bk_user`
--
ALTER TABLE `tb_bk_user`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `tb_bk_utensil`
--
ALTER TABLE `tb_bk_utensil`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `tb_bk_admin`
--
ALTER TABLE `tb_bk_admin`
  ADD CONSTRAINT `tb_bk_admin_ibfk_1` FOREIGN KEY (`ID_USERDATE`) REFERENCES `tb_bk_user` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `tb_bk_comment`
--
ALTER TABLE `tb_bk_comment`
  ADD CONSTRAINT `tb_bk_comment_ibfk_1` FOREIGN KEY (`ID_RECETA`) REFERENCES `tb_bk_recipe` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `tb_bk_comment_ibfk_2` FOREIGN KEY (`ID_USER`) REFERENCES `tb_bk_user` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `tb_bk_ingredient`
--
ALTER TABLE `tb_bk_ingredient`
  ADD CONSTRAINT `tb_bk_ingredient_ibfk_1` FOREIGN KEY (`ID_CATEGORIA`) REFERENCES `tb_bk_category` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `tb_bk_mealplan`
--
ALTER TABLE `tb_bk_mealplan`
  ADD CONSTRAINT `tb_bk_mealplan_ibfk_1` FOREIGN KEY (`ID_USUARIO`) REFERENCES `tb_bk_user` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `tb_bk_meal_recip`
--
ALTER TABLE `tb_bk_meal_recip`
  ADD CONSTRAINT `tb_bk_meal_recip_ibfk_1` FOREIGN KEY (`ID_RECIPE`) REFERENCES `tb_bk_recipe` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `tb_bk_meal_recip_ibfk_2` FOREIGN KEY (`ID_MEAL`) REFERENCES `tb_bk_mealplan` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `tb_bk_origin_list`
--
ALTER TABLE `tb_bk_origin_list`
  ADD CONSTRAINT `tb_bk_origin_list_ibfk_1` FOREIGN KEY (`ID_RECETA`) REFERENCES `tb_bk_recipe` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `tb_bk_origin_list_ibfk_2` FOREIGN KEY (`ID_ORIGIN`) REFERENCES `tb_bk_origin` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `tb_bk_preparation`
--
ALTER TABLE `tb_bk_preparation`
  ADD CONSTRAINT `tb_bk_preparation_ibfk_1` FOREIGN KEY (`ID_RECIPE`) REFERENCES `tb_bk_recipe` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `tb_bk_preparation_list`
--
ALTER TABLE `tb_bk_preparation_list`
  ADD CONSTRAINT `tb_bk_preparation_list_ibfk_1` FOREIGN KEY (`ID_PREPARACION`) REFERENCES `tb_bk_preparation` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `tb_bk_recipe`
--
ALTER TABLE `tb_bk_recipe`
  ADD CONSTRAINT `tb_bk_recipe_ibfk_2` FOREIGN KEY (`ID_CATEGORY`) REFERENCES `tb_bk_category` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `tb_bk_recipe_ibfk_3` FOREIGN KEY (`ORIGIN`) REFERENCES `tb_bk_origin` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `tb_bk_recipe_ibfk_4` FOREIGN KEY (`ID_USER`) REFERENCES `tb_bk_user` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `tb_bk_recipe_in`
--
ALTER TABLE `tb_bk_recipe_in`
  ADD CONSTRAINT `tb_bk_recipe_in_ibfk_1` FOREIGN KEY (`ID_RECETA`) REFERENCES `tb_bk_recipe` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `tb_bk_recipe_in_ibfk_2` FOREIGN KEY (`ID_INGREDIENTE`) REFERENCES `tb_bk_ingredient` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `tb_bk_recipe_utens`
--
ALTER TABLE `tb_bk_recipe_utens`
  ADD CONSTRAINT `tb_bk_recipe_utens_ibfk_1` FOREIGN KEY (`ID_UTENCILIO`) REFERENCES `tb_bk_utensil` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `tb_bk_recipe_utens_ibfk_2` FOREIGN KEY (`ID_RECETA`) REFERENCES `tb_bk_recipe` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `tb_bk_shoplist`
--
ALTER TABLE `tb_bk_shoplist`
  ADD CONSTRAINT `tb_bk_shoplist_ibfk_1` FOREIGN KEY (`ID_USUARIO`) REFERENCES `tb_bk_user` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `tb_bk_shop_ingred`
--
ALTER TABLE `tb_bk_shop_ingred`
  ADD CONSTRAINT `tb_bk_shop_ingred_ibfk_1` FOREIGN KEY (`ID_INDRENDIENTE`) REFERENCES `tb_bk_ingredient` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `tb_bk_shop_ingred_ibfk_2` FOREIGN KEY (`ID_SHOP`) REFERENCES `tb_bk_shoplist` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `tb_bk_shop_utens`
--
ALTER TABLE `tb_bk_shop_utens`
  ADD CONSTRAINT `tb_bk_shop_utens_ibfk_1` FOREIGN KEY (`ID_UTENCILIO`) REFERENCES `tb_bk_utensil` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `tb_bk_shop_utens_ibfk_2` FOREIGN KEY (`ID_SHOP`) REFERENCES `tb_bk_shoplist` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `tb_bk_utensil`
--
ALTER TABLE `tb_bk_utensil`
  ADD CONSTRAINT `tb_bk_utensil_ibfk_1` FOREIGN KEY (`ID_CATEGORIA`) REFERENCES `tb_bk_category` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
