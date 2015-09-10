package org.springframework.extended.section04

import org.springframework.expression.EvaluationContext
import org.springframework.expression.Expression
import org.springframework.expression.spel.SpelCompilerMode
import org.springframework.expression.spel.SpelParserConfiguration
import org.springframework.expression.spel.standard.SpelExpressionParser
import org.springframework.expression.spel.support.StandardEvaluationContext
import spock.lang.Specification

class MapAccessorTest extends Specification {

    def "map accessor test"() {
        given:
            SpelCompilerMode mode = SpelCompilerMode.IMMEDIATE;
            SpelParserConfiguration config = new SpelParserConfiguration(mode, null);


            SpelExpressionParser parser = new SpelExpressionParser(config);
//            Expression expr = parser.parseExpression("#key == 'value'");
            Expression expr = parser.parseExpression("map.key == 'value'");

            def map = [map:[key:"value"]]

            EvaluationContext context = new StandardEvaluationContext(map);
//            context.setVariable("key", "value")

//            context.addPropertyAccessor(new MapAccessor())
            context.addPropertyAccessor(new CompilableMapAccessor())

        when:
            Boolean result
            0..10.each {
                result = expr.getValue(context, Boolean.class);
            }

        then:
            result
    }

}
