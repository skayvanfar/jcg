package ir.sk.jcg.jcglanguageparsertoolkit.lparser.scanner;

import ir.sk.jcg.jcglanguageparsertoolkit.lparser.tokenizer.TokenStream;

/**
 * This is the base class for scanners which turn tokens streams
 * into prototypes and other language specific elements.
 * It is meant to be overridden for each language.
 *
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/5/2017.
 */
public interface LanguageScanner {
    void parse(TokenStream tokenStream);
}
