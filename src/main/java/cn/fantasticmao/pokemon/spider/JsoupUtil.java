package cn.fantasticmao.pokemon.spider;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.select.Evaluator;
import org.jsoup.select.QueryParser;

/**
 * JsoupUtil
 *
 * @author maomao
 * @since 2021-10-26
 */
public interface JsoupUtil {
    /**
     * Get each of the following element siblings of each element in this list, until match the query.
     *
     * @param query CSS query to match siblings against
     * @return all following element siblings.
     */
    static Elements nextUntil(Elements elements, String query) {
        return siblingsUtil(elements, query, true);
    }

    /**
     * Get the immediate previous element sibling of each element in this list, until by the query.
     *
     * @param query CSS query to match siblings against
     * @return previous element siblings.
     */
    static Elements prevUntil(Elements elements, String query) {
        return siblingsUtil(elements, query, false);
    }

    static Elements siblingsUtil(Elements elements, String query, boolean next) {
        Elements els = new Elements();
        Evaluator eval = query != null ? QueryParser.parse(query) : null;
        for (Element e : elements) {
            do {
                Element sib = next ? e.nextElementSibling() : e.previousElementSibling();
                if (sib == null) break;
                if (eval == null || !sib.is(eval))
                    els.add(sib);
                else if (sib.is(eval))
                    break;
                e = sib;
            } while (true);
        }
        return els;
    }
}
