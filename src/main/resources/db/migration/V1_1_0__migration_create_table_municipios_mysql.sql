CREATE TABLE IF NOT EXISTS `municipio` (
  `id` int(11) NOT NULL,
  `nome` varchar(45) DEFAULT NULL,
  `unidade_federativa_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `unidade_federativa_fk_idx` (`unidade_federativa_id`),
  CONSTRAINT `unidade_federativa_fk` FOREIGN KEY (`unidade_federativa_id`) REFERENCES `unidade_federativa` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
