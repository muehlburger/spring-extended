package org.springframework.extended.section05

import org.springframework.expression.EvaluationContext
import org.springframework.expression.Expression
import org.springframework.expression.spel.SpelCompilerMode
import org.springframework.expression.spel.SpelParserConfiguration
import org.springframework.expression.spel.standard.SpelExpressionParser
import org.springframework.expression.spel.support.StandardEvaluationContext
import org.springframework.util.ReflectionUtils
import org.springframework.extended.section04.MapAccessor
import spock.lang.Specification
import spock.lang.Unroll


class MapAccessorTest extends Specification {

    @Unroll
    def "test expression : #expression"() {
        given:
            SpelCompilerMode mode = SpelCompilerMode.IMMEDIATE;
            SpelParserConfiguration config = new SpelParserConfiguration(mode, null);

            SpelExpressionParser parser = new SpelExpressionParser(config);
            Expression expr = parser.parseExpression(expression);

            def map = [map: [key: "regal"]]

            EvaluationContext context = new StandardEvaluationContext(map);
            context.registerFunction("reverse", ReflectionUtils.findMethod(this.class, "reverse", String.class))

            context.addPropertyAccessor(new MapAccessor())
            context.setBeanResolver(new CustomBeanResolver())

        when:
            Boolean result = false
            0..10.each {
                result = expr.getValue(context, Boolean.class);
            }

        then:
            result

        where:
            expression                     | _
            "map.key == 'regal'"           | _
            "#reverse(map.key) == 'lager'" | _
            "@bean == 'resolved:bean'"     | _
    }

    public static String reverse(String str) {
        return new StringBuilder(str).reverse();
    }

}
