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
            SpelExpressionParser parser = new SpelExpressionParser();
            Expression expr = parser.parseExpression("map.key == 'value'");

            def map = [map:[key:"value"]]

            EvaluationContext context = new StandardEvaluationContext(map);

        when:
            Boolean result = false
            0..10.each {
                result = expr.getValue(context, Boolean.class);
            }

        then:
            result
    }

}
