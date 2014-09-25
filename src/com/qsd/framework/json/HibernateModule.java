package com.qsd.framework.json;

import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.*;

public class HibernateModule extends Module
{
    private final String NAME = "HibernateModule";
    
    // Should externalize this somehow
    private final static Version VERSION = new Version(0, 1, 0, null); // 0.1.0
    
    /**
     * Enumeration that defines all togglable features this module
     */
    public enum Feature {
        /**
         * Whether lazy-loaded object should be forced to be loaded and then serialized
         * (true); or serialized as nulls (false).
         *<p>
         * Default value is false.
         */
        FORCE_LAZY_LOADING(false),

        /**
         * Whether @Transient annotation should be checked or not; if true, will consider
         * @Transient to mean that property is to be ignored; if false annotation will
         * have no effect.
         *<p>
         * Default value is true.
         * 
         * @since 0.7.0
         */
        USE_TRANSIENT_ANNOTATION(true)
        ;

        final boolean _defaultState;
        final int _mask;
        
        /**
         * Method that calculates bit set (flags) of all features that
         * are enabled by default.
         */
        public static int collectDefaults()
        {
            int flags = 0;
            for (Feature f : values()) {
                if (f.enabledByDefault()) {
                    flags |= f.getMask();
                }
            }
            return flags;
        }
        
        private Feature(boolean defaultState) {
            _defaultState = defaultState;
            _mask = (1 << ordinal());
        }
        
        public boolean enabledByDefault() { return _defaultState; }
        public int getMask() { return _mask; }
    }

    protected final static int DEFAULT_FEATURES = Feature.collectDefaults();
    
    /**
     * Bit flag composed of bits that indicate which
     * {@link Feature}s
     * are enabled.
     */
    protected int _moduleFeatures = DEFAULT_FEATURES;
    
    /*
    /**********************************************************************
    /* Life-cycle
    /**********************************************************************
     */
    
    public HibernateModule() { }

    @Override public String getModuleName() { return NAME; }
    @Override public Version version() { return VERSION; }
    
    @Override
    public void setupModule(SetupContext context)
    {
        /* First, append annotation introspector (no need to override, esp.
         * as we just implement couple of methods)
         */
        // Then add serializers we need
        AnnotationIntrospector ai = annotationIntrospector();
        if (ai != null) {
            context.appendAnnotationIntrospector(ai);
        }
        context.addSerializers(new HibernateSerializers(_moduleFeatures));
    }

    /**
     * Method called during {@link #setupModule}, to create {@link AnnotationIntrospector}
     * to register along with module. If null is returned, no introspector is added.
     */
    protected AnnotationIntrospector annotationIntrospector() {
        HibernateAnnotationIntrospector ai = new HibernateAnnotationIntrospector();
        ai.setUseTransient(isEnabled(Feature.USE_TRANSIENT_ANNOTATION));
        return ai;
    }
    
    /*
    /**********************************************************************
    /* Extended API, configuration
    /**********************************************************************
     */

    public HibernateModule enable(Feature f) {
        _moduleFeatures |= f.getMask();
        return this;
    }

    public HibernateModule disable(Feature f) {
        _moduleFeatures &= ~f.getMask();
        return this;
    }

    public final boolean isEnabled(Feature f) {
        return (_moduleFeatures & f.getMask()) != 0;
    }

    public HibernateModule configure(Feature f, boolean state) {
        if (state) {
            enable(f);
        } else {
            disable(f);
        }
        return this;
    }

}