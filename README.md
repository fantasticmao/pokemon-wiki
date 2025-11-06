# Pokemon Wiki

[![Actions Status](https://github.com/fantasticmao/pokemon-wiki/workflows/ci/badge.svg)](https://github.com/fantasticmao/pokemon-wiki/actions)
![JDK Version](https://img.shields.io/badge/JDK-21%2B-blue)
[![codecov](https://codecov.io/gh/fantasticmao/pokemon-wiki/branch/master/graph/badge.svg)](https://codecov.io/gh/fantasticmao/pokemon-wiki)
[![Docker Hub](https://img.shields.io/badge/docker_hub-released-blue.svg?logo=docker)](https://hub.docker.com/r/maomao233/pokemon-wiki)
[![License](https://img.shields.io/github/license/fantasticmao/pokemon-wiki)](https://github.com/fantasticmao/pokemon-wiki/blob/master/LICENSE)

Pokemon-Wiki 是爬取 **[神奇宝贝百科](https://wiki.52poke.com/wiki/主页)** 站点，并提供组合数据查询的项目。

## 实现方式

Pokemon-Wiki 爬虫模块基于 [jsoup](https://github.com/jhy/jsoup)，使用多线程实现并行数据爬取，在网络畅通的情况下，当线程池大小配置为 100 时，可以在一分钟内爬完所需的 1700+ 个页面。

Pokemon-Wiki 爬虫模块获取的数据存于 [SQLite](https://www.sqlite.org/index.html) 中，二次开发可直接使用 [pokemon_wiki.db](https://github.com/fantasticmao/pokemon-wiki/blob/master/pokemon_wiki.db) 文件。

Pokemon-Wiki 接口模块基于 [Spring Web MVC](https://spring.io/)，API 详情可见 [接口文档](https://github.com/fantasticmao/pokemon-wiki/tree/master/apiDoc)，常用接口示例：

- 查询宝可梦详情：[https://pokemon.fantasticmao.cn/pokemon/detail?nameZh=妙蛙种子](https://pokemon.fantasticmao.cn/pokemon/detail?nameZh=妙蛙种子)
- 查询特性详情：[https://pokemon.fantasticmao.cn/ability/detail?nameZh=茂盛](https://pokemon.fantasticmao.cn/ability/detail?nameZh=茂盛)
- 查询招式详情：[https://pokemon.fantasticmao.cn/move/detail?nameZh=飞叶快刀](https://pokemon.fantasticmao.cn/move/detail?nameZh=飞叶快刀)

## 许可声明

Pokemon-Wiki 数据来源自 **[神奇宝贝百科](https://wiki.52poke.com/wiki/主页)** 站点，Pokemon-Wiki 仅提供组合数据查询。

神奇宝贝百科 [版权声明](https://wiki.52poke.com/wiki/%E7%A5%9E%E5%A5%87%E5%AE%9D%E8%B4%9D%E7%99%BE%E7%A7%91:%E7%89%88%E6%9D%83%E5%A3%B0%E6%98%8E)

Pokemon-Wiki [许可声明](https://github.com/fantasticmao/pokemon-wiki/blob/master/LICENSE)

Copyright (c) 2018 fantasticmao
