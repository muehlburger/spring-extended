package org.springframework.extended.section05

import org.spockframework.util.ReflectionUtil
import org.springframework.context.expression.MapAccessor
import org.springframework.expression.EvaluationContext
import org.springframework.expression.Expression
import org.springframework.expression.spel.SpelCompilerMode
import org.springframework.expression.spel.SpelParserConfiguration
import org.springframework.expression.spel.standard.SpelExpressionParser
import org.springframework.expression.spel.support.StandardEvaluationContext
import org.springframework.util.ReflectionUtils
import spock.lang.Specification
import spock.lang.Unroll

import java.lang.reflect.Method


class SpELExpressionTest extends Specification {

    @Unroll
    def "test expression : #expression"() {
        given:
            SpelExpressionParser parser = new SpelExpressionParser();
            Expression expr = parser.parseExpression(expression);

            def map = [map: [key: "regal"]]

            EvaluationContext context = new StandardEvaluationContext(map);
            context.addPropertyAccessor(new MapAccessor())
            context.setBeanResolver(new CustomBeanResolver());
            Method method = ReflectionUtils.findMethod(SpELExpressionTest.class, "reverse", String.class);
            context.registerFunction("reverse", method);

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
