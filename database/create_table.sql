DROP TABLE IF EXISTS pw_pokemon;

CREATE TABLE IF NOT EXISTS pw_pokemon (
  id         INT UNSIGNED NOT NULL AUTO_INCREMENT
  COMMENT '自增主键',
  `index`    INT UNSIGNED NOT NULL DEFAULT 0
  COMMENT '全国图鉴编号',
  infoUrl    VARCHAR(256) NOT NULL DEFAULT ''
  COMMENT '详情页面',
  nameZh     VARCHAR(32)  NOT NULL DEFAULT ''
  COMMENT '中文',
  nameJp     VARCHAR(32)  NOT NULL DEFAULT ''
  COMMENT '日文',
  nameEn     VARCHAR(32)  NOT NULL DEFAULT ''
  COMMENT '英文',
  type1       VARCHAR(16)  NOT NULL DEFAULT ''
  COMMENT '属性1',
  type2       VARCHAR(16)  NOT NULL DEFAULT ''
  COMMENT '属性2',
  createTime DATETIME     NOT NULL DEFAULT current_timestamp
  COMMENT '创建时间',
  modifyTime DATETIME     NOT NULL DEFAULT current_timestamp
  ON UPDATE current_timestamp
  COMMENT '修改时间',
  PRIMARY KEY (id)
)
  ENGINE InnoDB
  DEFAULT CHARSET 'utf8mb4'
  COMMENT '宝可梦列表';

## ----------------------------------------------------------

DROP TABLE IF EXISTS pw_pokemon_type;

CREATE TABLE IF NOT EXISTS pw_pokemon_type (
  id         INT UNSIGNED NOT NULL AUTO_INCREMENT
  COMMENT '自增主键',
  name       VARCHAR(16)  NOT NULL DEFAULT ''
  COMMENT '属性名称',
  url        VARCHAR(256) NOT NULL DEFAULT ''
  COMMENT '属性链接',
  createTime DATETIME     NOT NULL DEFAULT current_timestamp
  COMMENT '创建时间',
  modifyTime DATETIME     NOT NULL DEFAULT current_timestamp
  ON UPDATE current_timestamp
  COMMENT '修改时间',
  PRIMARY KEY (id),
  UNIQUE KEY `uniq_name`(name)
)
  ENGINE InnoDB
  DEFAULT CHARSET 'utf8mb4'
  COMMENT '属性列表';

## ----------------------------------------------------------

DROP TABLE IF EXISTS pw_pokemon_ability;

CREATE TABLE IF NOT EXISTS pw_pokemon_ability (
  id         INT UNSIGNED NOT NULL AUTO_INCREMENT
  COMMENT '自增主键',
  createTime DATETIME     NOT NULL DEFAULT current_timestamp
  COMMENT '创建时间',
  modifyTime DATETIME     NOT NULL DEFAULT current_timestamp
  ON UPDATE current_timestamp
  COMMENT '修改时间',
  PRIMARY KEY (id)
)
  ENGINE InnoDB
  DEFAULT CHARSET 'utf8mb4'
  COMMENT '宝可梦特性列表';

## ----------------------------------------------------------

DROP TABLE IF EXISTS pw_item;

CREATE TABLE IF NOT EXISTS pw_item (
  id         INT UNSIGNED NOT NULL AUTO_INCREMENT
  COMMENT '自增主键',
  createTime DATETIME     NOT NULL DEFAULT current_timestamp
  COMMENT '创建时间',
  modifyTime DATETIME     NOT NULL DEFAULT current_timestamp
  ON UPDATE current_timestamp
  COMMENT '修改时间',
  PRIMARY KEY (id)
)
  ENGINE InnoDB
  DEFAULT CHARSET 'utf8mb4'
  COMMENT '道具列表';

## ----------------------------------------------------------

DROP TABLE IF EXISTS pw_pokemon_nature;

CREATE TABLE IF NOT EXISTS pw_pokemon_nature (
  id         INT UNSIGNED NOT NULL AUTO_INCREMENT
  COMMENT '自增主键',
  createTime DATETIME     NOT NULL DEFAULT current_timestamp
  COMMENT '创建时间',
  modifyTime DATETIME     NOT NULL DEFAULT current_timestamp
  ON UPDATE current_timestamp
  COMMENT '修改时间',
  PRIMARY KEY (id)
)
  ENGINE InnoDB
  DEFAULT CHARSET 'utf8mb4'
  COMMENT '宝可梦性格列表';

## ----------------------------------------------------------

DROP TABLE IF EXISTS pw_pokemon_move;

CREATE TABLE IF NOT EXISTS pw_pokemon_move (
  id         INT UNSIGNED NOT NULL AUTO_INCREMENT
  COMMENT '自增主键',
  createTime DATETIME     NOT NULL DEFAULT current_timestamp
  COMMENT '创建时间',
  modifyTime DATETIME     NOT NULL DEFAULT current_timestamp
  ON UPDATE current_timestamp
  COMMENT '修改时间',
  PRIMARY KEY (id)
)
  ENGINE InnoDB
  DEFAULT CHARSET 'utf8mb4'
  COMMENT '宝可梦招式列表';

## ----------------------------------------------------------

DROP TABLE IF EXISTS pw_pokemon_base_stats;

CREATE TABLE IF NOT EXISTS pw_pokemon_base_stats (
  id         INT UNSIGNED NOT NULL AUTO_INCREMENT
  COMMENT '自增主键',
  createTime DATETIME     NOT NULL DEFAULT current_timestamp
  COMMENT '创建时间',
  modifyTime DATETIME     NOT NULL DEFAULT current_timestamp
  ON UPDATE current_timestamp
  COMMENT '修改时间',
  PRIMARY KEY (id)
)
  ENGINE InnoDB
  DEFAULT CHARSET 'utf8mb4'
  COMMENT '种族值列表';