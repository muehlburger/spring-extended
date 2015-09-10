package org.springframework.extended.section04

import org.springframework.asm.MethodVisitor
import org.springframework.expression.spel.CodeFlow
import org.springframework.expression.spel.CompilablePropertyAccessor
import org.springframework.util.ReflectionUtils

import java.lang.reflect.Method

class CompilableMapAccessor extends MapAccessor implements CompilablePropertyAccessor {

    @Override
    boolean isCompilable() {
        return true
    }

    @Override
    Class<?> getPropertyType() {
        return Object.class
    }

    @Override
    void generateCode(String propertyName, MethodVisitor mv, CodeFlow cf) {
        Method method = ReflectionUtils.findMethod(Map.class, "get");
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/util/Map", "get", CodeFlow.createSignatureDescriptor(method), false)
    }

}
