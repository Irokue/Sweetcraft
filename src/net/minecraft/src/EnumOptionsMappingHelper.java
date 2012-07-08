package net.minecraft.src;

class EnumOptionsMappingHelper
{
    static final int enumOptionsMappingHelperArray[];

    static
    {
        enumOptionsMappingHelperArray = new int[EnumOptions.values().length];

        try
        {
            enumOptionsMappingHelperArray[EnumOptions.INVERT_MOUSE.ordinal()] = 1;
        }
        catch (NoSuchFieldError nosuchfielderror) { }

        try
        {
            enumOptionsMappingHelperArray[EnumOptions.VIEW_BOBBING.ordinal()] = 2;
        }
        catch (NoSuchFieldError nosuchfielderror1) { }

        try
        {
            enumOptionsMappingHelperArray[EnumOptions.ANAGLYPH.ordinal()] = 3;
        }
        catch (NoSuchFieldError nosuchfielderror2) { }

        try
        {
            enumOptionsMappingHelperArray[EnumOptions.ADVANCED_OPENGL.ordinal()] = 4;
        }
        catch (NoSuchFieldError nosuchfielderror3) { }

        try
        {
            enumOptionsMappingHelperArray[EnumOptions.AMBIENT_OCCLUSION.ordinal()] = 5;
        }
        catch (NoSuchFieldError nosuchfielderror4) { }

        try
        {
            enumOptionsMappingHelperArray[EnumOptions.RENDER_CLOUDS.ordinal()] = 6;
        }
        catch (NoSuchFieldError nosuchfielderror5) { }
        
        try
        {
            enumOptionsMappingHelperArray[EnumOptions.SEE_GLOBAL.ordinal()] = 7;
        }
        catch (NoSuchFieldError nosuchfielderror6) { }
        
        try
        {
            enumOptionsMappingHelperArray[EnumOptions.SEE_COMMERCE.ordinal()] = 8;
        }
        catch (NoSuchFieldError nosuchfielderror7) { }
        
        try
        {
            enumOptionsMappingHelperArray[EnumOptions.SEE_LOCAL.ordinal()] = 9;
        }
        catch (NoSuchFieldError nosuchfielderror8) { }
        
        try
        {
            enumOptionsMappingHelperArray[EnumOptions.TALK_GLOBAL.ordinal()] = 10;
        }
        catch (NoSuchFieldError nosuchfielderror8) { }
        
        try
        {
            enumOptionsMappingHelperArray[EnumOptions.TALK_COMMERCE.ordinal()] = 11;
        }
        catch (NoSuchFieldError nosuchfielderror8) { }
        
        try
        {
            enumOptionsMappingHelperArray[EnumOptions.TALK_LOCAL.ordinal()] = 12;
        }
        catch (NoSuchFieldError nosuchfielderror8) { }
        
        try
        {
            enumOptionsMappingHelperArray[EnumOptions.HD_SKINS.ordinal()] = 13;
        }
        catch (NoSuchFieldError nosuchfielderror9) { }
        
    }
}
