-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 15-05-2024 a las 10:43:39
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

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
  `APELLIDO` varchar(255) NOT NULL,
  `ID_USUARIO` int(11) NOT NULL
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
  `FECHA` date NOT NULL,
  `VISIBLE` tinyint(1) NOT NULL,
  `COMENTARIO_ADMIN` varchar(255) NOT NULL,
  `CREADO_POR` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tb_bk_category`
--

INSERT INTO `tb_bk_category` (`ID`, `IDENTIFICADOR`, `NOMBRE`, `DESCRIPCION`, `RUTA_IMG`, `ETIQUETA`, `FECHA`, `VISIBLE`, `COMENTARIO_ADMIN`, `CREADO_POR`) VALUES
(1, 'CAT-001', 'Desayuno', 'Para el desayuno', 'receta.jpeg', 'Una etiqueta', '2024-05-12', 1, 'asdasd', 'No se');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_bk_comment`
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
-- Volcado de datos para la tabla `tb_bk_comment`
--

INSERT INTO `tb_bk_comment` (`ID`, `ID_RECETA`, `ID_USUARIO`, `IDENTIFICADOR`, `FECHA`, `PUNTUACION`, `COMENTARIO`, `REACCIONES`, `TITULO`, `DIFICULTAD`, `CLARIDAD_INST`, `SUGERENCIA`, `RECOMENDACION`, `MODIFICACION`) VALUES
(3, 2, 1, 'COM-00Hola2024-05-12T23:33:59.199445400', '2024-05-12 23:33:59', 3, 'asdf', 0, 'Hola', 'Fácil', 1, 'Ninguna', 0, 1),
(4, 2, 1, 'COM-00Segundo titulo2024-05-12T23:45:06.214281100', '2024-05-12 23:45:06', 1, 'Help', 0, 'Segundo titulo', 'Difícil', 9, '', 1, 0),
(5, 2, 1, 'COM-00Otro titulo2024-05-13T00:05:27.852865700', '2024-05-13 00:05:27', 1, 'Otro comentario', 0, 'Otro titulo', 'Intermedio', 10, 'Una sugerencia', 1, 0),
(6, 2, 1, 'COM-00Josue2024-05-13T12:47:26.626729900', '2024-05-13 12:47:26', 1, 'Comentario', 0, 'Josue', 'Fácil', 1, '', 1, 0),
(9, 2, 1, 'COM-00adsasdf122024-05-13T14:33:59.532592800', '2024-05-13 14:33:59', 1, 'asdasd', 0, 'adsasdf12', 'Fácil', 1, '', 1, 1),
(10, 6, 1, 'COM-00Hola2024-05-13T14:45:40.657159900', '2024-05-13 14:45:40', 1, 'asdasd', 0, 'Hola', 'Fácil', 1, '', 1, 1),
(16, 20, 1, 'COM-00Un titulosdfgsdfg2024-05-15T02:37:59.541293', '2024-05-15 02:37:59', 1, 'sdfgsdfg', 0, 'Un titulosdfgsdfg', 'Fácil', 1, 'sdfgsfdg', 1, 0);

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
-- Estructura de tabla para la tabla `tb_bk_meal_recip`
--

CREATE TABLE `tb_bk_meal_recip` (
  `ID` int(11) NOT NULL,
  `ID_MEAL` int(11) NOT NULL,
  `ID_RECETA` int(11) NOT NULL
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
  `FECHA` date NOT NULL,
  `AUTOR` varchar(50) NOT NULL,
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
  `DESCRIPCION` varchar(255) NOT NULL,
  `PAIS` varchar(100) NOT NULL,
  `RUTA_IMG` varchar(100) NOT NULL,
  `CONTINENTE` varchar(255) NOT NULL,
  `SABOR` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tb_bk_origin`
--

INSERT INTO `tb_bk_origin` (`ID`, `IDENTIFICADOR`, `NOMBRE`, `DESCRIPCION`, `PAIS`, `RUTA_IMG`, `CONTINENTE`, `SABOR`) VALUES
(1, 'ORI-001', 'Mexicana', 'desc', 'Mexico', 'receta.jpeg', 'jvg', 'jhg');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_bk_preparation`
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
-- Volcado de datos para la tabla `tb_bk_preparation`
--

INSERT INTO `tb_bk_preparation` (`ID`, `IDENTIFICADOR`, `ID_RECETA`, `DIFICULTAD`, `TIEMPO`, `RUTA_IMG`, `NOTA_AUTOR`, `ALERTA`) VALUES
(3, 'PREP-asd2024-05-14T22:44:26.424870600', 13, 'asd', '12:22:00', 'PREP-asd2024-05-14T22:44:26.424870600Noticia2.jpg', 'Ninguna', 'QAWEQAWE'),
(7, 'PREP-josue2024-05-15T00:21:16.361728200', 17, 'josue', '12:22:00', 'PREP-josue2024-05-15T00:21:16.361728200Noticia3.jpg', 'Ninguna', 'Ninguna'),
(9, 'PREP-josueqweqqweweqweawd2024-05-15T01:16:55.906869400', 19, 'josueqweqqweweqweawd', '12:22:00', 'PREP-josueqweqqweweqweawd2024-05-15T01:16:55.906869400Noticia3.jpg', 'Ninguna', 'Ninguna'),
(10, 'PREP-josue2024-05-15T01:32:39.601612300', 20, 'josue', '12:22:00', 'PREP-josue2024-05-15T01-32-39.601612300Noticia3.jpg', 'Ninguna', 'Ninguna');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_bk_preparation_list`
--

CREATE TABLE `tb_bk_preparation_list` (
  `ID` int(11) NOT NULL,
  `TITULO_PASO` varchar(100) NOT NULL,
  `RUTA_IMG` varchar(255) NOT NULL,
  `ID_PREPARACION` int(11) NOT NULL,
  `PASO` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tb_bk_preparation_list`
--

INSERT INTO `tb_bk_preparation_list` (`ID`, `TITULO_PASO`, `RUTA_IMG`, `ID_PREPARACION`, `PASO`) VALUES
(1, 'asdasd', '2024 05 15 00 21 06Noticia2.jpg', 7, 'josue'),
(9, 'asdasd', '2024 05 15 01 16 53Noticia1.jpg', 9, 'josue'),
(10, 'asdasd', '2024 05 15 01 32 36Noticia3.jpg', 10, 'asdasd');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_bk_recipe`
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
-- Volcado de datos para la tabla `tb_bk_recipe`
--

INSERT INTO `tb_bk_recipe` (`ID`, `IDENTIFICADOR`, `NOMBRE`, `ID_ORIGEN`, `ID_CATEGORIA`, `CALIFICACION`, `RUTA_IMG`, `TOTAL_CALIFICACION`, `ID_USUARIO`) VALUES
(2, 'REC-Daniel2024-05-12T23:17:28.240848800', 'Daniel', 1, 1, 0, 'REC-Daniel2024-05-12T23-17-28.240848800ats_20240215_174217_00.png', 0, 1),
(4, 'REC-Receta2024-05-13T14:38:48.602572700', 'Receta', 1, 1, 0, 'REC-Receta2024-05-13T14-38-48.602572700ats_20240425_234123_00.png', 0, 1),
(6, 'REC-Otra2024-05-13T14:44:59.009188100', 'Otra', 1, 1, 0, 'REC-Otra2024-05-13T14-44-59.009188100ats_20240426_103229_00.png', 0, 1),
(7, 'REC-Tu2024-05-14T20:58:16.105791200', 'Tu', 1, 1, 0, 'REC-Tu2024-05-14T20-58-16.105791200Noticia2.jpg', 0, 1),
(8, 'REC-Receta 22024-05-14T21:08:58.302942400', 'Receta 2', 1, 1, 0, 'REC-Receta 22024-05-14T21-08-58.302942400Noticia2.jpg', 0, 1),
(9, 'REC-KKKkk2024-05-14T21:25:30.964676300', 'KKKkk', 1, 1, 0, 'REC-KKKkk2024-05-14T21-25-30.964676300Noticia3.jpg', 0, 1),
(10, 'REC-Pepe2024-05-14T21:37:22.216286300', 'Pepe', 1, 1, 0, 'REC-Pepe2024-05-14T21-37-22.216286300Noticia1.jpg', 0, 1),
(13, 'REC-Vamos a ver josue2024-05-14T22:44:26.434863700', 'Vamos a ver josue', 1, 1, 0, 'REC-Vamos a ver josue2024-05-14T22:44:26.434863700Noticia2.jpg', 0, 1),
(17, 'REC-Ceasar2024-05-15T00:21:16.369812600', 'Ceasar', 1, 1, 0, 'REC-Ceasar2024-05-15T00:21:16.370371400Noticia3.jpg', 0, 1),
(19, 'REC-carlos2024-05-15T01:16:55.907869200', 'carlos', 1, 1, 0, 'REC-carlos2024-05-15T01-16-55.907869200Noticia2.jpg', 0, 1),
(20, 'REC-andres2024-05-15T01:32:39.603610600', 'andres', 1, 1, 0, 'REC-andres2024-05-15T01-32-39.603610600Noticia3.jpg', 0, 1);

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
-- Estructura de tabla para la tabla `tb_bk_shop_ingred`
--

CREATE TABLE `tb_bk_shop_ingred` (
  `ID` int(11) NOT NULL,
  `ID_SHOP` int(11) NOT NULL,
  `ID_INGRENDIENTE` int(11) NOT NULL
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
  `CREACION_AT` date NOT NULL,
  `IDENTIFICADOR` varchar(255) NOT NULL,
  `NOMBRE_USUARIO` varchar(255) NOT NULL,
  `EMAIL` varchar(255) NOT NULL,
  `PASSWORD` varchar(255) NOT NULL,
  `RUTA_IMG` varchar(100) NOT NULL,
  `TIPO_USUARIO` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tb_bk_user`
--

INSERT INTO `tb_bk_user` (`ID`, `CREACION_AT`, `IDENTIFICADOR`, `NOMBRE_USUARIO`, `EMAIL`, `PASSWORD`, `RUTA_IMG`, `TIPO_USUARIO`) VALUES
(1, '2024-05-12', 'USER-001', 'Daniel Briones', '[value-5]', '[value-6]', '[value-7]', '[value-8]');

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
  `PRECIO` float NOT NULL,
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
  ADD KEY `ID_USUARIO` (`ID_USUARIO`) USING BTREE;

--
-- Indices de la tabla `tb_bk_category`
--
ALTER TABLE `tb_bk_category`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `IDENTIFICADOR` (`IDENTIFICADOR`),
  ADD UNIQUE KEY `NOMBRE` (`NOMBRE`);

--
-- Indices de la tabla `tb_bk_comment`
--
ALTER TABLE `tb_bk_comment`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `IDENTIFICADOR` (`IDENTIFICADOR`),
  ADD KEY `ID_RECETA` (`ID_RECETA`),
  ADD KEY `ID_USUARIO` (`ID_USUARIO`) USING BTREE;

--
-- Indices de la tabla `tb_bk_ingredient`
--
ALTER TABLE `tb_bk_ingredient`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `NOMBRE` (`NOMBRE`),
  ADD UNIQUE KEY `IDENTIFICADOR` (`IDENTIFICADOR`),
  ADD KEY `ID_CATEGORIA` (`ID_CATEGORIA`);

--
-- Indices de la tabla `tb_bk_mealplan`
--
ALTER TABLE `tb_bk_mealplan`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `IDENTIFICADOR` (`IDENTIFICADOR`),
  ADD KEY `ID_USUARIO` (`ID_USUARIO`);

--
-- Indices de la tabla `tb_bk_meal_recip`
--
ALTER TABLE `tb_bk_meal_recip`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_MEAL` (`ID_MEAL`),
  ADD KEY `ID_RECETA` (`ID_RECETA`) USING BTREE;

--
-- Indices de la tabla `tb_bk_notice`
--
ALTER TABLE `tb_bk_notice`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `IDENTIFICADOR` (`IDENTIFICADOR`),
  ADD UNIQUE KEY `URL` (`URL`);

--
-- Indices de la tabla `tb_bk_origin`
--
ALTER TABLE `tb_bk_origin`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `IDENTIFICADOR` (`IDENTIFICADOR`),
  ADD UNIQUE KEY `NOMBRE` (`NOMBRE`);

--
-- Indices de la tabla `tb_bk_preparation`
--
ALTER TABLE `tb_bk_preparation`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `IDENTIFICADOR` (`IDENTIFICADOR`),
  ADD KEY `ID_RECETA` (`ID_RECETA`) USING BTREE;

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
  ADD UNIQUE KEY `IDENTIFICADOR` (`IDENTIFICADOR`),
  ADD KEY `ID_ORIGEN` (`ID_ORIGEN`) USING BTREE,
  ADD KEY `ID_CATEGORIA` (`ID_CATEGORIA`) USING BTREE,
  ADD KEY `ID_USUARIO` (`ID_USUARIO`) USING BTREE;

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
  ADD UNIQUE KEY `IDENTIFICADOR` (`IDENTIFICADOR`),
  ADD KEY `ID_USUARIO` (`ID_USUARIO`);

--
-- Indices de la tabla `tb_bk_shop_ingred`
--
ALTER TABLE `tb_bk_shop_ingred`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_SHOP` (`ID_SHOP`),
  ADD KEY `ID_INGRENDIENTE` (`ID_INGRENDIENTE`) USING BTREE;

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
  ADD UNIQUE KEY `IDENTIFICADOR` (`IDENTIFICADOR`),
  ADD UNIQUE KEY `NOMBRE_USUARIO` (`NOMBRE_USUARIO`),
  ADD UNIQUE KEY `EMAIL` (`EMAIL`);

--
-- Indices de la tabla `tb_bk_utensil`
--
ALTER TABLE `tb_bk_utensil`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `NOMBRE` (`NOMBRE`),
  ADD UNIQUE KEY `IDENTIFICADOR` (`IDENTIFICADOR`),
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
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `tb_bk_comment`
--
ALTER TABLE `tb_bk_comment`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de la tabla `tb_bk_ingredient`
--
ALTER TABLE `tb_bk_ingredient`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

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
-- AUTO_INCREMENT de la tabla `tb_bk_preparation`
--
ALTER TABLE `tb_bk_preparation`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `tb_bk_preparation_list`
--
ALTER TABLE `tb_bk_preparation_list`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `tb_bk_recipe`
--
ALTER TABLE `tb_bk_recipe`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

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
  ADD CONSTRAINT `tb_bk_admin_ibfk_1` FOREIGN KEY (`ID_USUARIO`) REFERENCES `tb_bk_user` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `tb_bk_comment`
--
ALTER TABLE `tb_bk_comment`
  ADD CONSTRAINT `tb_bk_comment_ibfk_1` FOREIGN KEY (`ID_RECETA`) REFERENCES `tb_bk_recipe` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `tb_bk_comment_ibfk_2` FOREIGN KEY (`ID_USUARIO`) REFERENCES `tb_bk_user` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

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
  ADD CONSTRAINT `tb_bk_meal_recip_ibfk_1` FOREIGN KEY (`ID_RECETA`) REFERENCES `tb_bk_recipe` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `tb_bk_meal_recip_ibfk_2` FOREIGN KEY (`ID_MEAL`) REFERENCES `tb_bk_mealplan` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `tb_bk_preparation`
--
ALTER TABLE `tb_bk_preparation`
  ADD CONSTRAINT `tb_bk_preparation_ibfk_1` FOREIGN KEY (`ID_RECETA`) REFERENCES `tb_bk_recipe` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `tb_bk_preparation_list`
--
ALTER TABLE `tb_bk_preparation_list`
  ADD CONSTRAINT `tb_bk_preparation_list_ibfk_1` FOREIGN KEY (`ID_PREPARACION`) REFERENCES `tb_bk_preparation` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `tb_bk_recipe`
--
ALTER TABLE `tb_bk_recipe`
  ADD CONSTRAINT `tb_bk_recipe_ibfk_2` FOREIGN KEY (`ID_CATEGORIA`) REFERENCES `tb_bk_category` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `tb_bk_recipe_ibfk_3` FOREIGN KEY (`ID_ORIGEN`) REFERENCES `tb_bk_origin` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `tb_bk_recipe_ibfk_4` FOREIGN KEY (`ID_USUARIO`) REFERENCES `tb_bk_user` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

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
  ADD CONSTRAINT `tb_bk_shop_ingred_ibfk_1` FOREIGN KEY (`ID_INGRENDIENTE`) REFERENCES `tb_bk_ingredient` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
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
