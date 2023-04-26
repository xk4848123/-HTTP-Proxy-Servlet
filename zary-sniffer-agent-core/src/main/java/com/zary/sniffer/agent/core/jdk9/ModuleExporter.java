package com.zary.sniffer.agent.core.jdk9;

import net.bytebuddy.agent.builder.AgentBuilder;

import java.lang.instrument.Instrumentation;

public class ModuleExporter {
    private static final String[] ADM_CORE_CLASSES = {
            "com.zary.admx.agent.core.plugin.define.HandlerBeforeResult",
            "com.zary.admx.agent.core.plugin.define.IMorphCall",
            "com.zary.admx.agent.core.plugin.handler.IConstructorHandler",
            "com.zary.admx.agent.core.plugin.handler.IInstanceMethodHandler",
            "com.zary.admx.agent.core.plugin.handler.IStaticMethodHandler",
            "com.zary.admx.agent.core.plugin.interceptor.ConstructorInterceptor",
            "com.zary.admx.agent.core.plugin.interceptor.InstanceMethodInterceptor",
            "com.zary.admx.agent.core.plugin.interceptor.InstanceMethodMorphInterceptor",
            "com.zary.admx.agent.core.plugin.interceptor.StaticMethodInterceptor",
            "com.zary.admx.agent.core.plugin.interceptor.StaticMethodMorphInterceptor",
            "com.zary.admx.agent.core.plugin.point.IConstructorPoint",
            "com.zary.admx.agent.core.plugin.point.IInstanceMethodPoint",
            "com.zary.admx.agent.core.plugin.point.IStaticMethodPoint"
    };


    private static final String[] BYTEBUDDY_CORE_CLASSES = {
            "com.zary.admx.agent.depencies.net.bytebuddy.implementation.bind.annotation.RuntimeType",
            "com.zary.admx.agent.depencies.net.bytebuddy.implementation.bind.annotation.This",
            "com.zary.admx.agent.depencies.net.bytebuddy.implementation.bind.annotation.AllArguments",
            "com.zary.admx.agent.depencies.net.bytebuddy.implementation.bind.annotation.AllArguments$Assignment",
            "com.zary.admx.agent.depencies.net.bytebuddy.implementation.bind.annotation.SuperCall",
            "com.zary.admx.agent.depencies.net.bytebuddy.implementation.bind.annotation.Origin",
            "com.zary.admx.agent.depencies.net.bytebuddy.implementation.bind.annotation.Morph"
    };

    public static void export(Instrumentation instrumentation, AgentBuilder agentBuilder) {
        assureReadEdge(instrumentation, agentBuilder, BYTEBUDDY_CORE_CLASSES);
        assureReadEdge(instrumentation, agentBuilder, ADM_CORE_CLASSES);
    }

    private static AgentBuilder assureReadEdge(Instrumentation instrumentation, AgentBuilder agentBuilder, String[] classes) {
        for (String className : classes) {
            try {
                agentBuilder = agentBuilder.assureReadEdgeFromAndTo(instrumentation, Class.forName(className));
            } catch (ClassNotFoundException e) {
                throw new UnsupportedOperationException("Fail to open read edge for class " + className + " to public access in JDK9+", e);
            }
        }
        return agentBuilder;
    }
}