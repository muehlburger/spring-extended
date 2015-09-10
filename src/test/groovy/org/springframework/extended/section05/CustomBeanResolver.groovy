package org.springframework.extended.section05

import org.springframework.expression.AccessException
import org.springframework.expression.BeanResolver
import org.springframework.expression.EvaluationContext

class CustomBeanResolver implements BeanResolver {

    @Override
    Object resolve(EvaluationContext context, String beanName) throws AccessException {
        return "resolved:" + beanName;
    }

}
