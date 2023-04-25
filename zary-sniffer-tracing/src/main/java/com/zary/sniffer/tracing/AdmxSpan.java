/*
 * Copyright 2016-2020 The OpenTracing Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.zary.sniffer.tracing;

import io.opentracing.References;
import io.opentracing.Span;
import io.opentracing.SpanContext;
import io.opentracing.tag.Tag;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * MockSpans 使用MockTracer.buildSpan(...)被创建
 * MockTracer.finishedSpans(). 它们提供所有 Span 状态的访问器。
 *
 * @see AdmxTracer#finishedSpans()
 */
public final class AdmxSpan implements Span {
    private static AtomicLong nextId = new AtomicLong(0);

    private final AdmxTracer mockTracer;
    private MockContext context;
    private final long parentId; // 0 if there's no parent.
    private final long startMicros;
    private boolean finished;
    private long finishMicros;
    private final Map<String, Object> tags;
    private final List<LogEntry> logEntries = new ArrayList<>();
    private String operationName;
    private final List<Reference> references;

    private final List<RuntimeException> errors = new ArrayList<>();

    public String operationName() {
        return this.operationName;
    }

    @Override
    public AdmxSpan setOperationName(String operationName) {
        finishedCheck("Setting operationName {%s} on already finished span", operationName);
        this.operationName = operationName;
        return this;
    }

    public long parentId() {
        return parentId;
    }
    public long startMicros() {
        return startMicros;
    }

    public long finishMicros() {
        assert finishMicros > 0 : "must call finish() before finishMicros()";
        return finishMicros;
    }

    public Map<String, Object> tags() {
        return new HashMap<>(this.tags);
    }

    public List<LogEntry> logEntries() {
        return new ArrayList<>(this.logEntries);
    }


    public List<RuntimeException> generatedErrors() {
        return new ArrayList<>(errors);
    }

    public List<Reference> references() {
        return new ArrayList<>(references);
    }

    @Override
    public synchronized MockContext context() {
        return this.context;
    }

    @Override
    public void finish() {
        this.finish(nowMicros());
    }

    @Override
    public synchronized void finish(long finishMicros) {
        finishedCheck("Finishing already finished span");
        this.finishMicros = finishMicros;
        this.mockTracer.appendFinishedSpan(this);
        this.finished = true;
    }

    @Override
    public AdmxSpan setTag(String key, String value) {
        return setObjectTag(key, value);
    }

    @Override
    public AdmxSpan setTag(String key, boolean value) {
        return setObjectTag(key, value);
    }

    @Override
    public AdmxSpan setTag(String key, Number value) {
        return setObjectTag(key, value);
    }

    @Override
    public <T> AdmxSpan setTag(Tag<T> tag, T value) {
        tag.set(this, value);
        return this;
    }

    private synchronized AdmxSpan setObjectTag(String key, Object value) {
        finishedCheck("Adding tag {%s:%s} to already finished span", key, value);
        tags.put(key, value);
        return this;
    }

    @Override
    public final Span log(Map<String, ?> fields) {
        return log(nowMicros(), fields);
    }

    @Override
    public final synchronized AdmxSpan log(long timestampMicros, Map<String, ?> fields) {
        finishedCheck("Adding logs %s at %d to already finished span", fields, timestampMicros);
        this.logEntries.add(new LogEntry(timestampMicros, fields));
        return this;
    }

    @Override
    public AdmxSpan log(String event) {
        return this.log(nowMicros(), event);
    }

    @Override
    public AdmxSpan log(long timestampMicroseconds, String event) {
        return this.log(timestampMicroseconds, Collections.singletonMap("event", event));
    }

    @Override
    public synchronized Span setBaggageItem(String key, String value) {
        finishedCheck("Adding baggage {%s:%s} to already finished span", key, value);
        this.context = this.context.withBaggageItem(key, value);
        return this;
    }

    @Override
    public synchronized String getBaggageItem(String key) {
        return this.context.getBaggageItem(key);
    }

    /**
     * MockContext实现了开放跟踪。具有跟踪和跨度 ID 的 SpanContext。
     *
     */
    public static final class MockContext implements SpanContext {
        private final long traceId;
        private final Map<String, String> baggage;
        private final long spanId;

        /**
         * span上下文
         * @param baggage 模拟上下文获取行李参数的所有权
         *
         * @see MockContext#withBaggageItem(String, String)
         */
        public MockContext(long traceId, long spanId, Map<String, String> baggage) {
            this.baggage = baggage;
            this.traceId = traceId;
            this.spanId = spanId;
        }

        public String getBaggageItem(String key) { return this.baggage.get(key); }
        public String toTraceId() { return String.valueOf(traceId); }
        public String toSpanId() { return String.valueOf(spanId); }
        public long traceId() { return traceId; }
        public long spanId() { return spanId; }


        public MockContext withBaggageItem(String key, String val) {
            Map<String, String> newBaggage = new HashMap<>(this.baggage);
            newBaggage.put(key, val);
            return new MockContext(this.traceId, this.spanId, newBaggage);
        }

        @Override
        public Iterable<Map.Entry<String, String>> baggageItems() {
            return baggage.entrySet();
        }
    }

    public static final class LogEntry {
        private final long timestampMicros;
        private final Map<String, ?> fields;

        public LogEntry(long timestampMicros, Map<String, ?> fields) {
            this.timestampMicros = timestampMicros;
            this.fields = fields;
        }

        public long timestampMicros() {
            return timestampMicros;
        }

        public Map<String, ?> fields() {
            return fields;
        }
    }

    public static final class Reference {
        private final MockContext context;
        private final String referenceType;

        public Reference(MockContext context, String referenceType) {
            this.context = context;
            this.referenceType = referenceType;
        }

        public MockContext getContext() {
            return context;
        }

        public String getReferenceType() {
            return referenceType;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Reference reference = (Reference) o;
            return Objects.equals(context, reference.context) &&
                Objects.equals(referenceType, reference.referenceType);
        }

        @Override
        public int hashCode() {
            return Objects.hash(context, referenceType);
        }
    }

    AdmxSpan(AdmxTracer tracer, String operationName, long startMicros, Map<String, Object> initialTags, List<Reference> refs) {
        this.mockTracer = tracer;
        this.operationName = operationName;
        this.startMicros = startMicros;
        if (initialTags == null) {
            this.tags = new HashMap<>();
        } else {
            this.tags = new HashMap<>(initialTags);
        }
        if(refs == null) {
            this.references = Collections.emptyList();
        } else {
            this.references = new ArrayList<>(refs);
        }
        MockContext parent = findPreferredParentRef(this.references);

        if (parent == null) {
            this.context = new MockContext(nextTracerId(), nextSpanId(), new HashMap<String, String>());
            this.parentId = 0;
        } else {
            this.context = new MockContext(parent.traceId, nextSpanId(), mergeBaggages(this.references));
            this.parentId = parent.spanId;
        }
    }

    private static MockContext findPreferredParentRef(List<Reference> references) {
        if(references.isEmpty()) {
            return null;
        }
        for (Reference reference : references) {
            if (References.CHILD_OF.equals(reference.getReferenceType())) {
                return reference.getContext();
            }
        }
        return references.get(0).getContext();
    }

    private static Map<String, String> mergeBaggages(List<Reference> references) {
        Map<String, String> baggage = new HashMap<>();
        for(Reference ref : references) {
            if(ref.getContext().baggage != null) {
                baggage.putAll(ref.getContext().baggage);
            }
        }
        return baggage;
    }

    static long nextSpanId() {
        return nextId.addAndGet(1);
    }

    static long nextTracerId() {
        return SnowFlakeUtil.nextId();
    }

    static long nowMicros() {
        return System.currentTimeMillis() * 1000;
    }

    private synchronized void finishedCheck(String format, Object... args) {
        if (finished) {
            RuntimeException ex = new IllegalStateException(String.format(format, args));
            errors.add(ex);
            throw ex;
        }
    }

    @Override
    public String toString() {
        return "{" +
                "traceId:" + context.traceId() +
                ", spanId:" + context.spanId() +
                ", parentId:" + parentId +
                ", operationName:\"" + operationName + "\"}";
    }
}
