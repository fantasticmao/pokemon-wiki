DROP TABLE IF EXISTS pw_pokemon;

CREATE TABLE IF NOT EXISTS pw_pokemon (
  id         INT UNSIGNED NOT NULL AUTO_INCREMENT
  COMMENT '自增主键',
  `index`    INT UNSIGNED NOT NULL DEFAULT 0
  COMMENT '全国图鉴编号',
  nameZh     VARCHAR(32)  NOT NULL DEFAULT ''
  COMMENT '中文名称',
  nameJa     VARCHAR(32)  NOT NULL DEFAULT ''
  COMMENT '日文名称',
  nameEn     VARCHAR(32)  NOT NULL DEFAULT ''
  COMMENT '英文名称',
  type1      VARCHAR(16)  NOT NULL DEFAULT ''
  COMMENT '第一属性',
  type2      VARCHAR(16)  NOT NULL DEFAULT ''
  COMMENT '第二属性',
  generation TINYINT      NOT NULL DEFAULT 0
  COMMENT '第几世代',
  createTime DATETIME     NOT NULL DEFAULT current_timestamp
  COMMENT '创建时间',
  modifyTime DATETIME     NOT NULL DEFAULT current_timestamp
  ON UPDATE current_timestamp
  COMMENT '修改时间',
  PRIMARY KEY (id)
)
  ENGINE InnoDB
  DEFAULT CHARSET 'utf8mb4'
  COMMENT '宝可梦列表（按全国图鉴编号）';

## ----------------------------------------------------------

DROP TABLE IF EXISTS pw_pokemon_ability;

CREATE TABLE IF NOT EXISTS pw_pokemon_ability (
  id          INT UNSIGNED NOT NULL AUTO_INCREMENT
  COMMENT '自增主键',
  `index`     INT UNSIGNED NOT NULL DEFAULT 0
  COMMENT '全国图鉴编号',
  nameZh      VARCHAR(32)  NOT NULL DEFAULT ''
  COMMENT '中文名称',
  type1       VARCHAR(16)  NOT NULL DEFAULT ''
  COMMENT '第一属性',
  type2       VARCHAR(16)  NOT NULL DEFAULT ''
  COMMENT '第二属性',
  ability1    VARCHAR(16)  NOT NULL DEFAULT ''
  COMMENT '第一特性',
  ability2    VARCHAR(16)  NOT NULL DEFAULT ''
  COMMENT '第二特性',
  abilityHide VARCHAR(16)  NOT NULL DEFAULT ''
  COMMENT '隐藏特性',
  generation  TINYINT      NOT NULL DEFAULT 0
  COMMENT '第几世代',
  createTime  DATETIME     NOT NULL DEFAULT current_timestamp
  COMMENT '创建时间',
  modifyTime  DATETIME     NOT NULL DEFAULT current_timestamp
  ON UPDATE current_timestamp
  COMMENT '修改时间',
  PRIMARY KEY (id)
)
  ENGINE InnoDB
  DEFAULT CHARSET 'utf8mb4'
  COMMENT '宝可梦特性列表（按全国图鉴编号）';

## ----------------------------------------------------------

DROP TABLE IF EXISTS pw_pokemon_base_stat;

CREATE TABLE IF NOT EXISTS pw_pokemon_base_stat (
  id         INT UNSIGNED      NOT NULL AUTO_INCREMENT
  COMMENT '自增主键',
  `index`    INT UNSIGNED      NOT NULL DEFAULT 0
  COMMENT '全国图鉴编号',
  nameZh     VARCHAR(32)       NOT NULL DEFAULT ''
  COMMENT '中文名称',
  hp         TINYINT UNSIGNED  NOT NULL DEFAULT 0
  COMMENT 'HP',
  attack     TINYINT UNSIGNED  NOT NULL DEFAULT 0
  COMMENT '攻击',
  defense    TINYINT UNSIGNED  NOT NULL DEFAULT 0
  COMMENT '防御',
  spAttack   TINYINT UNSIGNED  NOT NULL DEFAULT 0
  COMMENT '特攻',
  spDefense  TINYINT UNSIGNED  NOT NULL DEFAULT 0
  COMMENT '特防',
  speed      TINYINT UNSIGNED  NOT NULL DEFAULT 0
  COMMENT '速度',
  total      SMALLINT UNSIGNED NOT NULL DEFAULT 0
  COMMENT '总和',
  average    FLOAT UNSIGNED    NOT NULL DEFAULT 0
  COMMENT '平均值',
  createTime DATETIME          NOT NULL DEFAULT current_timestamp
  COMMENT '创建时间',
  modifyTime DATETIME          NOT NULL DEFAULT current_timestamp
  ON UPDATE current_timestamp
  COMMENT '修改时间',
  PRIMARY KEY (id)
)
  ENGINE InnoDB
  DEFAULT CHARSET 'utf8mb4'
  COMMENT '宝可梦种族值列表（第七世代）';

## ----------------------------------------------------------

DROP TABLE IF EXISTS pw_ability;

CREATE TABLE IF NOT EXISTS pw_ability (
  id         INT UNSIGNED NOT NULL AUTO_INCREMENT
  COMMENT '自增主键',
  nameZh     VARCHAR(32)  NOT NULL DEFAULT ''
  COMMENT '中文名称',
  nameJa     VARCHAR(32)  NOT NULL DEFAULT ''
  COMMENT '日文名称',
  nameEn     VARCHAR(32)  NOT NULL DEFAULT ''
  COMMENT '英文名称',
  effect     VARCHAR(64)  NOT NULL DEFAULT ''
  COMMENT '说明',
  generation TINYINT      NOT NULL DEFAULT 0
  COMMENT '第几世代',
  createTime DATETIME     NOT NULL DEFAULT current_timestamp
  COMMENT '创建时间',
  modifyTime DATETIME     NOT NULL DEFAULT current_timestamp
  ON UPDATE current_timestamp
  COMMENT '修改时间',
  PRIMARY KEY (id)
)
  ENGINE InnoDB
  DEFAULT CHARSET 'utf8mb4'
  COMMENT '特性列表';

## ----------------------------------------------------------

DROP TABLE IF EXISTS pw_nature;

CREATE TABLE IF NOT EXISTS pw_nature (
  id             INT UNSIGNED NOT NULL AUTO_INCREMENT
  COMMENT '自增主键',
  nameZh         VARCHAR(32)  NOT NULL DEFAULT ''
  COMMENT '中文名称',
  nameJa         VARCHAR(32)  NOT NULL DEFAULT ''
  COMMENT '日文名称',
  nameEn         VARCHAR(32)  NOT NULL DEFAULT ''
  COMMENT '英文名称',
  increasedStat  VARCHAR(32)  NOT NULL DEFAULT ''
  COMMENT '增加能力值',
  decreasedStat  VARCHAR(32)  NOT NULL DEFAULT ''
  COMMENT '降低能力值',
  favoriteFlavor VARCHAR(32)  NOT NULL DEFAULT ''
  COMMENT '喜欢口味',
  dislikedFlavor VARCHAR(32)  NOT NULL DEFAULT ''
  COMMENT '不喜欢口味',
  createTime     DATETIME     NOT NULL DEFAULT current_timestamp
  COMMENT '创建时间',
  modifyTime     DATETIME     NOT NULL DEFAULT current_timestamp
  ON UPDATE current_timestamp
  COMMENT '修改时间',
  PRIMARY KEY (id)
)
  ENGINE InnoDB
  DEFAULT CHARSET 'utf8mb4'
  COMMENT '性格';

## ----------------------------------------------------------

DROP TABLE IF EXISTS pw_move;

CREATE TABLE IF NOT EXISTS pw_move (
  id         INT UNSIGNED NOT NULL AUTO_INCREMENT
  COMMENT '自增主键',
  nameZh     VARCHAR(32)  NOT NULL DEFAULT ''
  COMMENT '中文名称',
  nameJa     VARCHAR(32)  NOT NULL DEFAULT ''
  COMMENT '日文名称',
  nameEn     VARCHAR(32)  NOT NULL DEFAULT ''
  COMMENT '英文名称',
  type       VARCHAR(32)  NOT NULL DEFAULT ''
  COMMENT '属性',
  category   VARCHAR(32)  NOT NULL DEFAULT ''
  COMMENT '分类',
  power      VARCHAR(32)  NOT NULL DEFAULT ''
  COMMENT '威力',
  accuracy   VARCHAR(32)  NOT NULL DEFAULT ''
  COMMENT '命中',
  pp         VARCHAR(32)  NOT NULL DEFAULT ''
  COMMENT 'PP',
  generation TINYINT      NOT NULL DEFAULT 0
  COMMENT '第几世代',
  createTime DATETIME     NOT NULL DEFAULT current_timestamp
  COMMENT '创建时间',
  modifyTime DATETIME     NOT NULL DEFAULT current_timestamp
  ON UPDATE current_timestamp
  COMMENT '修改时间',
  PRIMARY KEY (id)
)
  ENGINE InnoDB
  DEFAULT CHARSET 'utf8mb4'
  COMMENT '招式列表';

## ----------------------------------------------------------

DROP TABLE IF EXISTS pw_item;

CREATE TABLE IF NOT EXISTS pw_item (
  id         INT UNSIGNED NOT NULL AUTO_INCREMENT
  COMMENT '自增主键',
  ## TODO 完善
  createTime DATETIME     NOT NULL DEFAULT current_timestamp
  COMMENT '创建时间',
  modifyTime DATETIME     NOT NULL DEFAULT current_timestamp
  ON UPDATE current_timestamp
  COMMENT '修改时间',
  PRIMARY KEY (id)
)
  ENGINE InnoDB
  DEFAULT CHARSET 'utf8mb4'
  COMMENT '道具列表（主系列）';