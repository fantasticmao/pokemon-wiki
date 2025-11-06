package cn.fantasticmao.pokemon.web;

import cn.fantasticmao.pokemon.web.application.model.AbilityResponse;
import cn.fantasticmao.pokemon.web.application.model.ItemResponse;
import cn.fantasticmao.pokemon.web.application.model.MoveResponse;
import cn.fantasticmao.pokemon.web.application.model.PokemonResponse;
import cn.fantasticmao.pokemon.web.domain.ability.model.Ability;
import cn.fantasticmao.pokemon.web.domain.item.model.Item;
import cn.fantasticmao.pokemon.web.domain.move.model.Move;
import cn.fantasticmao.pokemon.web.domain.pokemon.model.Pokemon;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * SpringTest
 *
 * @author fantasticmao
 * @since 2018/8/5
 */
@AutoConfigureMockMvc
@SpringBootTest(classes = Main.class)
public class SpringTest {

    protected void assertStench(AbilityResponse stench) {
        Assertions.assertEquals(1, stench.getId());
        Assertions.assertEquals("恶臭", stench.getNameZh());
        Assertions.assertEquals("あくしゅう", stench.getNameJa());
        Assertions.assertEquals("Stench", stench.getNameEn());
        Assertions.assertEquals(3, stench.getGeneration());
        Assertions.assertEquals("通过释放臭臭的气味，在攻击的时候，有时会使对手畏缩。", stench.getDetail().getDesc());
        Assertions.assertEquals("对战中 使用招式攻击对手造成伤害时，对方有10%几率陷入畏缩状态。 对战外 当该特性的宝可梦配置在同行宝可梦首位时，野生宝可梦出现机率降低50%。", stench.getDetail().getEffect());
        Assertions.assertEquals("臭臭花,臭泥,臭臭泥,瓦斯弹,双弹瓦斯,臭鼬噗,坦克臭鼬,破破袋,灰尘山", stench.getDetail().getPokemons());
    }

    protected void assertStench(Ability stench) {
        Assertions.assertEquals(1, stench.getId());
        Assertions.assertEquals("恶臭", stench.getNameZh());
        Assertions.assertEquals("あくしゅう", stench.getNameJa());
        Assertions.assertEquals("Stench", stench.getNameEn());
        Assertions.assertEquals(3, stench.getGeneration());
        Assertions.assertEquals("通过释放臭臭的气味，在攻击的时候，有时会使对手畏缩。", stench.getDetail().getDesc());
        Assertions.assertEquals("对战中 使用招式攻击对手造成伤害时，对方有10%几率陷入畏缩状态。 对战外 当该特性的宝可梦配置在同行宝可梦首位时，野生宝可梦出现机率降低50%。", stench.getDetail().getEffect());
        Assertions.assertEquals("臭臭花,臭泥,臭臭泥,瓦斯弹,双弹瓦斯,臭鼬噗,坦克臭鼬,破破袋,灰尘山", stench.getDetail().getPokemons());
    }

    protected void assertRepel(ItemResponse repel) {
        Assertions.assertEquals(1, repel.getId());
        Assertions.assertEquals("道具 - 野外使用的道具", repel.getType());
        Assertions.assertEquals("https://s1.52poke.wiki/wiki/thumb/e/ef/Bag_%E9%99%A4%E8%99%AB%E5%96%B7%E9%9B%BE_SV_Sprite.png/30px-Bag_%E9%99%A4%E8%99%AB%E5%96%B7%E9%9B%BE_SV_Sprite.png", repel.getImgUrl());
        Assertions.assertEquals("除虫喷雾", repel.getNameZh());
        Assertions.assertEquals("むしよけスプレー", repel.getNameJa());
        Assertions.assertEquals("Repel", repel.getNameEn());
        Assertions.assertEquals("使用后，在较短的一段时间内，弱小的野生宝可梦将完全不会出现。", repel.getDesc());
        Assertions.assertEquals(0, repel.getGeneration());
    }

    protected void assertRepel(Item repel) {
        Assertions.assertEquals(1, repel.getId());
        Assertions.assertEquals("道具 - 野外使用的道具", repel.getType());
        Assertions.assertEquals("https://s1.52poke.wiki/wiki/thumb/e/ef/Bag_%E9%99%A4%E8%99%AB%E5%96%B7%E9%9B%BE_SV_Sprite.png/30px-Bag_%E9%99%A4%E8%99%AB%E5%96%B7%E9%9B%BE_SV_Sprite.png", repel.getImgUrl());
        Assertions.assertEquals("除虫喷雾", repel.getNameZh());
        Assertions.assertEquals("むしよけスプレー", repel.getNameJa());
        Assertions.assertEquals("Repel", repel.getNameEn());
        Assertions.assertEquals("使用后，在较短的一段时间内，弱小的野生宝可梦将完全不会出现。", repel.getDesc());
        Assertions.assertEquals(0, repel.getGeneration());
    }

    protected void assertPound(MoveResponse pound) {
        Assertions.assertEquals(1, pound.getId());
        Assertions.assertEquals("拍击", pound.getNameZh());
        Assertions.assertEquals("はたく", pound.getNameJa());
        Assertions.assertEquals("Pound", pound.getNameEn());
        Assertions.assertEquals("一般", pound.getType());
        Assertions.assertEquals("物理", pound.getCategory());
        Assertions.assertEquals("40", pound.getPower());
        Assertions.assertEquals("100", pound.getAccuracy());
        Assertions.assertEquals("35", pound.getPp());
        Assertions.assertEquals(1, pound.getGeneration());
        Assertions.assertEquals("使用长长的尾巴或手等拍打对手进行攻击。", pound.getDetail().getDesc());
        Assertions.assertEquals("https://s1.52poke.wiki/assets/animoves/AniMove001.gif", pound.getDetail().getImgUrl());
        Assertions.assertEquals("是接触类招式 受守住影响 不受魔法反射影响 不可以被抢夺 受鹦鹉学舌影响 受王者之证等类似道具影响", pound.getDetail().getNotes());
        Assertions.assertEquals("除自身以外场上一只可以攻击到的宝可梦", pound.getDetail().getScope());
        Assertions.assertEquals("攻击目标造成伤害。", pound.getDetail().getEffect());
    }

    protected void assertPound(Move pound) {
        Assertions.assertEquals(1, pound.getId());
        Assertions.assertEquals("拍击", pound.getNameZh());
        Assertions.assertEquals("はたく", pound.getNameJa());
        Assertions.assertEquals("Pound", pound.getNameEn());
        Assertions.assertEquals("一般", pound.getType());
        Assertions.assertEquals("物理", pound.getCategory());
        Assertions.assertEquals("40", pound.getPower());
        Assertions.assertEquals("100", pound.getAccuracy());
        Assertions.assertEquals("35", pound.getPp());
        Assertions.assertEquals(1, pound.getGeneration());
        Assertions.assertEquals("使用长长的尾巴或手等拍打对手进行攻击。", pound.getDetail().getDesc());
        Assertions.assertEquals("https://s1.52poke.wiki/assets/animoves/AniMove001.gif", pound.getDetail().getImgUrl());
        Assertions.assertEquals("是接触类招式 受守住影响 不受魔法反射影响 不可以被抢夺 受鹦鹉学舌影响 受王者之证等类似道具影响", pound.getDetail().getNotes());
        Assertions.assertEquals("除自身以外场上一只可以攻击到的宝可梦", pound.getDetail().getScope());
        Assertions.assertEquals("攻击目标造成伤害。", pound.getDetail().getEffect());
    }

    protected void assertBulbasaur(PokemonResponse bulbasaur) {
        Assertions.assertEquals(1, bulbasaur.getIndex());
        Assertions.assertEquals("妙蛙种子", bulbasaur.getNameZh());
        Assertions.assertEquals("フシギダネ", bulbasaur.getNameJa());
        Assertions.assertEquals("Bulbasaur", bulbasaur.getNameEn());
        Assertions.assertEquals("草", bulbasaur.getType1());
        Assertions.assertEquals("毒", bulbasaur.getType2());
        Assertions.assertEquals("茂盛", bulbasaur.getAbility1());
        Assertions.assertEquals("", bulbasaur.getAbility2());
        Assertions.assertEquals("叶绿素", bulbasaur.getAbilityHide());
        Assertions.assertEquals("", bulbasaur.getForm());
        Assertions.assertEquals(1, bulbasaur.getGeneration());

        Assertions.assertEquals(45, bulbasaur.getBaseStat().getHp());
        Assertions.assertEquals(49, bulbasaur.getBaseStat().getAttack());
        Assertions.assertEquals(49, bulbasaur.getBaseStat().getDefense());
        Assertions.assertEquals(65, bulbasaur.getBaseStat().getSpAttack());
        Assertions.assertEquals(65, bulbasaur.getBaseStat().getSpDefense());
        Assertions.assertEquals(45, bulbasaur.getBaseStat().getSpeed());
        Assertions.assertEquals(318, bulbasaur.getBaseStat().getTotal());
        Assertions.assertEquals(53F, bulbasaur.getBaseStat().getAverage());

        Assertions.assertEquals("https://s1.52poke.wiki/wiki/thumb/2/21/001Bulbasaur.png/300px-001Bulbasaur.png", bulbasaur.getDetail().getImgUrl());
        Assertions.assertEquals("种子宝可梦", bulbasaur.getDetail().getCategory());
        Assertions.assertEquals("0.7m", bulbasaur.getDetail().getHeight());
        Assertions.assertEquals("6.9kg", bulbasaur.getDetail().getWeight());
        Assertions.assertEquals("https://s1.52poke.wiki/wiki/thumb/c/cc/Body08.png/32px-Body08.png", bulbasaur.getDetail().getBodyStyle());
        Assertions.assertEquals("5.9%", bulbasaur.getDetail().getCatchRate());
        Assertions.assertEquals("雄性 87.5%,雌性 12.5%", bulbasaur.getDetail().getGenderRatio());
        Assertions.assertEquals("怪兽", bulbasaur.getDetail().getEggGroup1());
        Assertions.assertEquals("植物", bulbasaur.getDetail().getEggGroup2());
        Assertions.assertEquals("5140步", bulbasaur.getDetail().getHatchTime());
        Assertions.assertEquals("ＨＰ 0,攻击 0,防御 0,特攻 1,特防 0,速度 0", bulbasaur.getDetail().getEffortValue());
    }

    protected void assertBulbasaur(Pokemon bulbasaur) {
        Assertions.assertEquals(1, bulbasaur.getIndex());
        Assertions.assertEquals("妙蛙种子", bulbasaur.getNameZh());
        Assertions.assertEquals("フシギダネ", bulbasaur.getNameJa());
        Assertions.assertEquals("Bulbasaur", bulbasaur.getNameEn());
        Assertions.assertEquals("", bulbasaur.getForm());
        Assertions.assertEquals(1, bulbasaur.getGeneration());

        Assertions.assertEquals("草", bulbasaur.getAbility().getType1());
        Assertions.assertEquals("毒", bulbasaur.getAbility().getType2());
        Assertions.assertEquals("茂盛", bulbasaur.getAbility().getAbility1());
        Assertions.assertEquals("", bulbasaur.getAbility().getAbility2());
        Assertions.assertEquals("叶绿素", bulbasaur.getAbility().getAbilityHide());

        Assertions.assertEquals(45, bulbasaur.getBaseStat().getHp());
        Assertions.assertEquals(49, bulbasaur.getBaseStat().getAttack());
        Assertions.assertEquals(49, bulbasaur.getBaseStat().getDefense());
        Assertions.assertEquals(65, bulbasaur.getBaseStat().getSpAttack());
        Assertions.assertEquals(65, bulbasaur.getBaseStat().getSpDefense());
        Assertions.assertEquals(45, bulbasaur.getBaseStat().getSpeed());
        Assertions.assertEquals(318, bulbasaur.getBaseStat().getTotal());
        Assertions.assertEquals(53F, bulbasaur.getBaseStat().getAverage());

        Assertions.assertEquals("https://s1.52poke.wiki/wiki/thumb/2/21/001Bulbasaur.png/300px-001Bulbasaur.png", bulbasaur.getDetail().getImgUrl());
        Assertions.assertEquals("种子宝可梦", bulbasaur.getDetail().getCategory());
        Assertions.assertEquals("0.7m", bulbasaur.getDetail().getHeight());
        Assertions.assertEquals("6.9kg", bulbasaur.getDetail().getWeight());
        Assertions.assertEquals("https://s1.52poke.wiki/wiki/thumb/c/cc/Body08.png/32px-Body08.png", bulbasaur.getDetail().getBodyStyle());
        Assertions.assertEquals("5.9%", bulbasaur.getDetail().getCatchRate());
        Assertions.assertEquals("雄性 87.5%,雌性 12.5%", bulbasaur.getDetail().getGenderRatio());
        Assertions.assertEquals("怪兽", bulbasaur.getDetail().getEggGroup1());
        Assertions.assertEquals("植物", bulbasaur.getDetail().getEggGroup2());
        Assertions.assertEquals("5140步", bulbasaur.getDetail().getHatchTime());
        Assertions.assertEquals("ＨＰ 0,攻击 0,防御 0,特攻 1,特防 0,速度 0", bulbasaur.getDetail().getEffortValue());
    }
}
