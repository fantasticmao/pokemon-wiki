# Pokemon-Wiki

> Pokemon-Wiki 数据来源自 **[神奇宝贝百科](https://wiki.52poke.com/wiki/主页)**。

Pokemon-Wiki 是爬取神奇宝贝百科站点，并提供组合数据查询接口的项目，爬虫模块基于 [java.net](https://docs.oracle.com/javase/10/docs/api/java/net/package-summary.html) + [Jsoup](https://github.com/jhy/jsoup)，接口模块基于 [Spring](https://spring.io/)。

Pokemon-Wiki 爬虫模块使用多线程技术，在网络良好的情况下，当配置线程池大小为 100 时，可以在一分钟内爬完所需的 1774 个页面。

Pokemon-Wiki 接口模块基于 HTTP 协议，具体请参考 [接口文档](https://pokemon.fantasticmao.cn/swagger-ui.html)。

常用接口示例：

- 查询宝可梦详情：[https://pokemon.fantasticmao.cn/pokemon/detail?nameZh=妙蛙种子](https://pokemon.fantasticmao.cn/pokemon/detail?nameZh=妙蛙种子)
- 查询特性详情：[https://pokemon.fantasticmao.cn/ability/detail?nameZh=茂盛](https://pokemon.fantasticmao.cn/ability/detail?nameZh=茂盛)
- 查询招式详情：[https://pokemon.fantasticmao.cn/move/detail?nameZh=飞叶快刀](https://pokemon.fantasticmao.cn/move/detail?nameZh=飞叶快刀)