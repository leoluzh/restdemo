CREATE TABLE IF NOT EXISTS `unidade_federativa` (
  `id` int(11) NOT NULL,
  `sigla` varchar(2) NOT NULL,
  `nome` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `sigla_UNIQUE` (`sigla`),
  UNIQUE KEY `nome_UNIQUE` (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
