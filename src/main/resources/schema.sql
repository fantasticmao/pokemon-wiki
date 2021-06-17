CREATE TABLE IF NOT EXISTS pw_pokemon (
    id         INTEGER PRIMARY KEY,
    `index`    INT         NOT NULL DEFAULT 0,
    nameZh     VARCHAR(32) NOT NULL DEFAULT '',
    nameJa     VARCHAR(32) NOT NULL DEFAULT '',
    nameEn     VARCHAR(32) NOT NULL DEFAULT '',
    type1      VARCHAR(16) NOT NULL DEFAULT '',
    type2      VARCHAR(16) NOT NULL DEFAULT '',
    generation INT         NOT NULL DEFAULT 0,
    createTime DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS pw_pokemon_ability (
    id          INTEGER PRIMARY KEY,
    `index`     INT         NOT NULL DEFAULT 0,
    nameZh      VARCHAR(32) NOT NULL DEFAULT '',
    type1       VARCHAR(16) NOT NULL DEFAULT '',
    type2       VARCHAR(16) NOT NULL DEFAULT '',
    ability1    VARCHAR(16) NOT NULL DEFAULT '',
    ability2    VARCHAR(16) NOT NULL DEFAULT '',
    abilityHide VARCHAR(16) NOT NULL DEFAULT '',
    generation  INT         NOT NULL DEFAULT 0,
    createTime  DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS pw_ability (
    id         INTEGER PRIMARY KEY,
    nameZh     VARCHAR(32) NOT NULL DEFAULT '',
    nameJa     VARCHAR(32) NOT NULL DEFAULT '',
    nameEn     VARCHAR(32) NOT NULL DEFAULT '',
    effect     VARCHAR(64) NOT NULL DEFAULT '',
    generation INT         NOT NULL DEFAULT 0,
    createTime DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS pw_nature (
    id             INTEGER PRIMARY KEY,
    nameZh         VARCHAR(32) NOT NULL DEFAULT '',
    nameJa         VARCHAR(32) NOT NULL DEFAULT '',
    nameEn         VARCHAR(32) NOT NULL DEFAULT '',
    increasedStat  VARCHAR(32) NOT NULL DEFAULT '',
    decreasedStat  VARCHAR(32) NOT NULL DEFAULT '',
    favoriteFlavor VARCHAR(32) NOT NULL DEFAULT '',
    dislikedFlavor VARCHAR(32) NOT NULL DEFAULT '',
    createTime     DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS pw_move (
    id         INTEGER PRIMARY KEY,
    nameZh     VARCHAR(32) NOT NULL DEFAULT '',
    nameJa     VARCHAR(32) NOT NULL DEFAULT '',
    nameEn     VARCHAR(32) NOT NULL DEFAULT '',
    type       VARCHAR(32) NOT NULL DEFAULT '',
    category   VARCHAR(32) NOT NULL DEFAULT '',
    power      VARCHAR(32) NOT NULL DEFAULT '',
    accuracy   VARCHAR(32) NOT NULL DEFAULT '',
    pp         VARCHAR(32) NOT NULL DEFAULT '',
    generation INT         NOT NULL DEFAULT 0,
    createTime DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS pw_item (
    id         INTEGER PRIMARY KEY,
    type       VARCHAR(32)  NOT NULL DEFAULT '',
    imgUrl     VARCHAR(128) NOT NULL DEFAULT '',
    nameZh     VARCHAR(32)  NOT NULL DEFAULT '',
    nameJa     VARCHAR(32)  NOT NULL DEFAULT '',
    nameEn     VARCHAR(32)  NOT NULL DEFAULT '',
    desc       VARCHAR(256) NOT NULL DEFAULT '',
    generation INT          NOT NULL DEFAULT 0,
    createTime DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS pw_pokemon_detail (
    id          INTEGER PRIMARY KEY,
    `index`     INT          NOT NULL DEFAULT 0,
    imgUrl      VARCHAR(128) NOT NULL DEFAULT '',
    type        VARCHAR(16)  NOT NULL DEFAULT '',
    category    VARCHAR(16)  NOT NULL DEFAULT '',
    ability     VARCHAR(32)  NOT NULL DEFAULT '',
    height      VARCHAR(8)   NOT NULL DEFAULT '',
    weight      VARCHAR(8)   NOT NULL DEFAULT '',
    bodyStyle   VARCHAR(128) NOT NULL DEFAULT '',
    catchRate   VARCHAR(8)   NOT NULL DEFAULT '',
    genderRatio VARCHAR(32)  NOT NULL DEFAULT '',
    eggGroup1   VARCHAR(16)  NOT NULL DEFAULT '',
    eggGroup2   VARCHAR(16)  NOT NULL DEFAULT '',
    hatchTime   VARCHAR(8)   NOT NULL DEFAULT '',
    effortValue VARCHAR(32)  NOT NULL DEFAULT '',
    createTime  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS pw_pokemon_detail_base_stat (
    id         INTEGER PRIMARY KEY,
    `index`    INT      NOT NULL DEFAULT 0,
    hp         INT      NOT NULL DEFAULT 0,
    attack     INT      NOT NULL DEFAULT 0,
    defense    INT      NOT NULL DEFAULT 0,
    spAttack   INT      NOT NULL DEFAULT 0,
    spDefense  INT      NOT NULL DEFAULT 0,
    speed      INT      NOT NULL DEFAULT 0,
    total      SMALLINT NOT NULL DEFAULT 0,
    average    FLOAT    NOT NULL DEFAULT 0,
    createTime DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS pw_pokemon_detail_learn_set_by_leveling_up (
    id         INTEGER PRIMARY KEY,
    `index`    INT         NOT NULL DEFAULT 0,
    level1     CHAR(4)     NOT NULL DEFAULT '',
    level2     CHAR(4)     NOT NULL DEFAULT '',
    move       VARCHAR(32) NOT NULL DEFAULT '',
    type       VARCHAR(32) NOT NULL DEFAULT '',
    category   VARCHAR(32) NOT NULL DEFAULT '',
    power      VARCHAR(32) NOT NULL DEFAULT '',
    accuracy   VARCHAR(32) NOT NULL DEFAULT '',
    pp         VARCHAR(32) NOT NULL DEFAULT '',
    createTime DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS pw_pokemon_detail_learn_set_by_technical_machine (
    id               INTEGER PRIMARY KEY,
    `index`          INT          NOT NULL DEFAULT 0,
    imgUrl           VARCHAR(128) NOT NULL DEFAULT '',
    technicalMachine VARCHAR(16)  NOT NULL DEFAULT '',
    move             VARCHAR(32)  NOT NULL DEFAULT '',
    type             VARCHAR(32)  NOT NULL DEFAULT '',
    category         VARCHAR(32)  NOT NULL DEFAULT '',
    power            VARCHAR(32)  NOT NULL DEFAULT '',
    accuracy         VARCHAR(32)  NOT NULL DEFAULT '',
    pp               VARCHAR(32)  NOT NULL DEFAULT '',
    createTime       DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS pw_pokemon_detail_learn_set_by_breeding (
    id         INTEGER PRIMARY KEY,
    `index`    INT           NOT NULL DEFAULT 0,
    parent     VARCHAR(2048) NOT NULL DEFAULT '',
    move       VARCHAR(32)   NOT NULL DEFAULT '',
    type       VARCHAR(32)   NOT NULL DEFAULT '',
    category   VARCHAR(32)   NOT NULL DEFAULT '',
    power      VARCHAR(32)   NOT NULL DEFAULT '',
    accuracy   VARCHAR(32)   NOT NULL DEFAULT '',
    pp         VARCHAR(32)   NOT NULL DEFAULT '',
    createTime DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS pw_ability_detail (
    id         INTEGER PRIMARY KEY,
    nameZh     VARCHAR(32)  NOT NULL DEFAULT '',
    desc       VARCHAR(256) NOT NULL DEFAULT '',
    effect     VARCHAR(512) NOT NULL DEFAULT '',
    pokemons   VARCHAR(256) NOT NULL DEFAULT '',
    createTime DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS pw_move_detail (
    id         INTEGER PRIMARY KEY,
    nameZh     VARCHAR(32)   NOT NULL DEFAULT '',
    desc       VARCHAR(256)  NOT NULL DEFAULT '',
    imgUrl     VARCHAR(256)  NOT NULL DEFAULT '',
    notes      VARCHAR(256)  NOT NULL DEFAULT '',
    scope      VARCHAR(256)  NOT NULL DEFAULT '',
    effect     VARCHAR(1024) NOT NULL DEFAULT '',
    createTime DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP
);