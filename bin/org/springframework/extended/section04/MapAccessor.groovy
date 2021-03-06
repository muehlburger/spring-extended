package org.springframework.extended.section04

import org.springframework.expression.AccessException
import org.springframework.expression.EvaluationContext
import org.springframework.expression.PropertyAccessor
import org.springframework.expression.TypedValue


class MapAccessor implements PropertyAccessor {

    @Override
    Class<?>[] getSpecificTargetClasses() {
        return null
    }

    @Override
    boolean canRead(EvaluationContext context, Object target, String name) throws AccessException {
        return false
    }

    @Override
    TypedValue read(EvaluationContext context, Object target, String name) throws AccessException {
        return new TypedValue(null)
    }

    @Override
    boolean canWrite(EvaluationContext context, Object target, String name) throws AccessException {
        throw new UnsupportedOperationException()
    }

    @Override
    void write(EvaluationContext context, Object target, String name, Object newValue) throws AccessException {
        throw new UnsupportedOperationException()
    }
}
