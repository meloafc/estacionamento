CREATE TABLE `horarios` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dia_da_semana` int(11) DEFAULT NULL,
  `hora_final` time DEFAULT NULL,
  `hora_inicial` time DEFAULT NULL,
  `valor` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

CREATE TABLE `movimentos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cor` varchar(30) DEFAULT NULL,
  `data_final` datetime DEFAULT NULL,
  `data_inicial` datetime DEFAULT NULL,
  `feriado` bit(1) NOT NULL,
  `modelo` varchar(30) DEFAULT NULL,
  `placa` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;