CREATE TABLE IF NOT EXISTS pw_pokemon (
  id         INT         NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `index`    INT         NOT NULL DEFAULT 0 COMMENT '全国图鉴编号',
  nameZh     VARCHAR(32) NOT NULL DEFAULT '' COMMENT '中文名称',
  nameJa     VARCHAR(32) NOT NULL DEFAULT '' COMMENT '日文名称',
  nameEn     VARCHAR(32) NOT NULL DEFAULT '' COMMENT '英文名称',
  type1      VARCHAR(16) NOT NULL DEFAULT '' COMMENT '第一属性',
  type2      VARCHAR(16) NOT NULL DEFAULT '' COMMENT '第二属性',
  generation INT         NOT NULL DEFAULT 0 COMMENT '第几世代',
  createTime DATETIME    NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  modifyTime DATETIME    NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '修改时间',
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS pw_pokemon_ability (
  id          INT         NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `index`     INT         NOT NULL DEFAULT 0 COMMENT '全国图鉴编号',
  nameZh      VARCHAR(32) NOT NULL DEFAULT '' COMMENT '中文名称',
  type1       VARCHAR(16) NOT NULL DEFAULT '' COMMENT '第一属性',
  type2       VARCHAR(16) NOT NULL DEFAULT '' COMMENT '第二属性',
  ability1    VARCHAR(16) NOT NULL DEFAULT '' COMMENT '第一特性',
  ability2    VARCHAR(16) NOT NULL DEFAULT '' COMMENT '第二特性',
  abilityHide VARCHAR(16) NOT NULL DEFAULT '' COMMENT '隐藏特性',
  generation  INT         NOT NULL DEFAULT 0 COMMENT '第几世代',
  createTime  DATETIME    NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  modifyTime  DATETIME    NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '修改时间',
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS pw_ability (
  id         INT         NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  nameZh     VARCHAR(32) NOT NULL DEFAULT '' COMMENT '中文名称',
  nameJa     VARCHAR(32) NOT NULL DEFAULT '' COMMENT '日文名称',
  nameEn     VARCHAR(32) NOT NULL DEFAULT '' COMMENT '英文名称',
  effect     VARCHAR(64) NOT NULL DEFAULT '' COMMENT '说明',
  generation INT         NOT NULL DEFAULT 0 COMMENT '第几世代',
  createTime DATETIME    NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  modifyTime DATETIME    NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '修改时间',
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS pw_nature (
  id             INT         NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  nameZh         VARCHAR(32) NOT NULL DEFAULT '' COMMENT '中文名称',
  nameJa         VARCHAR(32) NOT NULL DEFAULT '' COMMENT '日文名称',
  nameEn         VARCHAR(32) NOT NULL DEFAULT '' COMMENT '英文名称',
  increasedStat  VARCHAR(32) NOT NULL DEFAULT '' COMMENT '增加能力值',
  decreasedStat  VARCHAR(32) NOT NULL DEFAULT '' COMMENT '降低能力值',
  favoriteFlavor VARCHAR(32) NOT NULL DEFAULT '' COMMENT '喜欢口味',
  dislikedFlavor VARCHAR(32) NOT NULL DEFAULT '' COMMENT '不喜欢口味',
  createTime     DATETIME    NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  modifyTime     DATETIME    NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '修改时间',
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS pw_move (
  id         INT         NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  nameZh     VARCHAR(32) NOT NULL DEFAULT '' COMMENT '中文名称',
  nameJa     VARCHAR(32) NOT NULL DEFAULT '' COMMENT '日文名称',
  nameEn     VARCHAR(32) NOT NULL DEFAULT '' COMMENT '英文名称',
  type       VARCHAR(32) NOT NULL DEFAULT '' COMMENT '属性',
  category   VARCHAR(32) NOT NULL DEFAULT '' COMMENT '分类',
  power      VARCHAR(32) NOT NULL DEFAULT '' COMMENT '威力',
  accuracy   VARCHAR(32) NOT NULL DEFAULT '' COMMENT '命中',
  pp         VARCHAR(32) NOT NULL DEFAULT '' COMMENT 'PP',
  generation INT         NOT NULL DEFAULT 0 COMMENT '第几世代',
  createTime DATETIME    NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  modifyTime DATETIME    NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '修改时间',
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS pw_item (
  id         INT          NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  type       VARCHAR(32)  NOT NULL DEFAULT '' COMMENT '道具类别',
  imgUrl     VARCHAR(128) NOT NULL DEFAULT '' COMMENT '图片链接',
  nameZh     VARCHAR(32)  NOT NULL DEFAULT '' COMMENT '中文名称',
  nameJa     VARCHAR(32)  NOT NULL DEFAULT '' COMMENT '日文名称',
  nameEn     VARCHAR(32)  NOT NULL DEFAULT '' COMMENT '英文名称',
  `desc`     VARCHAR(256) NOT NULL DEFAULT '' COMMENT '道具描述',
  generation INT          NOT NULL DEFAULT 0 COMMENT '第几世代',
  createTime DATETIME     NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  modifyTime DATETIME     NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '修改时间',
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS pw_pokemon_detail (
  id          INT          NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `index`     INT          NOT NULL DEFAULT 0 COMMENT '全国图鉴编号',
  imgUrl      VARCHAR(128) NOT NULL DEFAULT '' COMMENT '图片链接',
  type        VARCHAR(16)  NOT NULL DEFAULT '' COMMENT '属性',
  category    VARCHAR(16)  NOT NULL DEFAULT '' COMMENT '分类',
  ability     VARCHAR(32)  NOT NULL DEFAULT '' COMMENT '特性',
  height      VARCHAR(8)   NOT NULL DEFAULT '' COMMENT '身高',
  weight      VARCHAR(8)   NOT NULL DEFAULT '' COMMENT '体重',
  bodyStyle   VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '体形',
  catchRate   VARCHAR(8)   NOT NULL DEFAULT '' COMMENT '捕获率',
  genderRatio VARCHAR(32)  NOT NULL DEFAULT '' COMMENT '性别比例',
  eggGroup1   VARCHAR(16)  NOT NULL DEFAULT '' COMMENT '第一生蛋分组',
  eggGroup2   VARCHAR(16)  NOT NULL DEFAULT '' COMMENT '第二生蛋分组',
  hatchTime   VARCHAR(8)   NOT NULL DEFAULT '' COMMENT '孵化时间',
  effortValue VARCHAR(32)  NOT NULL DEFAULT '' COMMENT '基础点数',
  createTime  DATETIME     NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  modifyTime  DATETIME     NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '修改时间',
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS pw_pokemon_detail_base_stat (
  id         INT      NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `index`    INT      NOT NULL DEFAULT 0 COMMENT '全国图鉴编号',
  hp         INT      NOT NULL DEFAULT 0 COMMENT 'HP',
  attack     INT      NOT NULL DEFAULT 0 COMMENT '攻击',
  defense    INT      NOT NULL DEFAULT 0 COMMENT '防御',
  spAttack   INT      NOT NULL DEFAULT 0 COMMENT '特攻',
  spDefense  INT      NOT NULL DEFAULT 0 COMMENT '特防',
  speed      INT      NOT NULL DEFAULT 0 COMMENT '速度',
  total      SMALLINT NOT NULL DEFAULT 0 COMMENT '总和',
  average    FLOAT    NOT NULL DEFAULT 0 COMMENT '平均值',
  createTime DATETIME NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  modifyTime DATETIME NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '修改时间',
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS pw_pokemon_detail_learn_set_by_leveling_up (
  id         INT         NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `index`    INT         NOT NULL DEFAULT 0 COMMENT '全国图鉴编号',
  level1     CHAR(4)     NOT NULL DEFAULT '' COMMENT '等级（太阳/月亮）',
  level2     CHAR(4)     NOT NULL DEFAULT '' COMMENT '等级（究极之日/究极之月）',
  move       VARCHAR(32) NOT NULL DEFAULT '' COMMENT '招式名称',
  type       VARCHAR(32) NOT NULL DEFAULT '' COMMENT '属性',
  category   VARCHAR(32) NOT NULL DEFAULT '' COMMENT '分类',
  power      VARCHAR(32) NOT NULL DEFAULT '' COMMENT '威力',
  accuracy   VARCHAR(32) NOT NULL DEFAULT '' COMMENT '命中',
  pp         VARCHAR(32) NOT NULL DEFAULT '' COMMENT 'PP',
  createTime DATETIME    NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  modifyTime DATETIME    NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '修改时间',
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS pw_pokemon_detail_learn_set_by_technical_machine (
  id               INT          NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `index`          INT          NOT NULL DEFAULT 0 COMMENT '全国图鉴编号',
  imgUrl           VARCHAR(128) NOT NULL DEFAULT '' COMMENT '招式学习器图片链接',
  technicalMachine VARCHAR(16)  NOT NULL DEFAULT '' COMMENT '招式学习器名称',
  move             VARCHAR(32)  NOT NULL DEFAULT '' COMMENT '招式名称',
  type             VARCHAR(32)  NOT NULL DEFAULT '' COMMENT '属性',
  category         VARCHAR(32)  NOT NULL DEFAULT '' COMMENT '分类',
  power            VARCHAR(32)  NOT NULL DEFAULT '' COMMENT '威力',
  accuracy         VARCHAR(32)  NOT NULL DEFAULT '' COMMENT '命中',
  pp               VARCHAR(32)  NOT NULL DEFAULT '' COMMENT 'PP',
  createTime       DATETIME     NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  modifyTime       DATETIME     NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '修改时间',
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS pw_pokemon_detail_learn_set_by_breeding (
  id         INT           NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `index`    INT           NOT NULL DEFAULT 0 COMMENT '全国图鉴编号',
  parent     VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '亲代',
  move       VARCHAR(32)   NOT NULL DEFAULT '' COMMENT '招式名称',
  type       VARCHAR(32)   NOT NULL DEFAULT '' COMMENT '属性',
  category   VARCHAR(32)   NOT NULL DEFAULT '' COMMENT '分类',
  power      VARCHAR(32)   NOT NULL DEFAULT '' COMMENT '威力',
  accuracy   VARCHAR(32)   NOT NULL DEFAULT '' COMMENT '命中',
  pp         VARCHAR(32)   NOT NULL DEFAULT '' COMMENT 'PP',
  createTime DATETIME      NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  modifyTime DATETIME      NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '修改时间',
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS pw_ability_detail (
  id         INT          NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  nameZh     VARCHAR(32)  NOT NULL DEFAULT '' COMMENT '特性名称',
  `desc`     VARCHAR(256) NOT NULL DEFAULT '' COMMENT '特性描述',
  effect     VARCHAR(512) NOT NULL DEFAULT '' COMMENT '特性效果',
  pokemons   VARCHAR(256) NOT NULL DEFAULT '' COMMENT '拥有此特性的宝可梦',
  createTime DATETIME     NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  modifyTime DATETIME     NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '修改时间',
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS pw_move_detail (
  id         INT           NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  nameZh     VARCHAR(32)   NOT NULL DEFAULT '' COMMENT '招式名称',
  `desc`     VARCHAR(256)  NOT NULL DEFAULT '' COMMENT '招式描述',
  imgUrl     VARCHAR(256)  NOT NULL DEFAULT '' COMMENT '图片链接',
  notes      VARCHAR(256)  NOT NULL DEFAULT '' COMMENT '注意事项',
  scope      VARCHAR(256)  NOT NULL DEFAULT '' COMMENT '作用范围',
  effect     VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '附加效果',
  createTime DATETIME      NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  modifyTime DATETIME      NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '修改时间',
  PRIMARY KEY (id)
);