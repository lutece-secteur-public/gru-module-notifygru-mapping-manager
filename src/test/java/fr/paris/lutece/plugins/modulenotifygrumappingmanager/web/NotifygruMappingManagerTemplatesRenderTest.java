/*
 * Copyright (c) 2002-2026, City of Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.modulenotifygrumappingmanager.web;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.junit.jupiter.api.Test;

import fr.paris.lutece.portal.service.template.HtmlAttributesMethod;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.core.HTMLOutputFormat;
import freemarker.template.Configuration;
import freemarker.template.TemplateMethodModelEx;

/**
 * Renders the creation page of a mapping with HTML auto-escaping (LUT-33153, LUT-34169), the back office macros of the
 * core (the webapp exploded by lutece:exploded) and the core function htmlAttributes in strict mode: a params string, or
 * an event handler given to a macro, fails the rendering as it does in production. The provider select submits its form
 * through data-lutece-action, and the provider and field labels and the bean key read from the request are escaped.
 */
public class NotifygruMappingManagerTemplatesRenderTest
{
    private static final String TEMPLATES_ROOT = "webapp/WEB-INF/templates";
    private static final String CORE_TEMPLATES_ROOT = "target/lutece/WEB-INF/templates";
    private static final String COMMONS = "commons_bs5_tabler.html";
    private static final String TEMPLATE_CREATE = "admin/plugins/modulenotifygrumappingmanager/create_notifygrumappingmanager.html";

    private static final String HOSTILE = "Form <b>x</b> \"q\" <script>alert(1)</script>";
    private static final String HOSTILE_ESCAPED = "Form &lt;b&gt;x&lt;/b&gt; &quot;q&quot; &lt;script&gt;alert(1)&lt;/script&gt;";

    /**
     * Renders a template of the plugin with the configuration of the core: HTML output format, auto-escaping, the back
     * office commons included, htmlAttributes strict.
     *
     * @param strTemplate
     *            the template path
     * @param model
     *            the model
     * @return the HTML
     * @throws Exception
     *             if the rendering fails
     */
    private static String render( String strTemplate, Map<String, Object> model ) throws Exception
    {
        File coreTemplates = new File( CORE_TEMPLATES_ROOT );
        assertTrue( new File( coreTemplates, COMMONS ).isFile( ), "core templates not found under " + CORE_TEMPLATES_ROOT + ": run lutece:exploded first" );
        Configuration cfg = new Configuration( Configuration.VERSION_2_3_0 );
        cfg.setOutputFormat( HTMLOutputFormat.INSTANCE );
        cfg.setAutoEscapingPolicy( Configuration.ENABLE_IF_DEFAULT_AUTO_ESCAPING_POLICY );
        cfg.setLocale( Locale.FRENCH );
        cfg.setURLEscapingCharset( "UTF-8" );
        cfg.setNumberFormat( "0.######" );
        cfg.setTemplateLoader( new MultiTemplateLoader( new TemplateLoader [ ] {
                new FileTemplateLoader( new File( TEMPLATES_ROOT ) ), new FileTemplateLoader( coreTemplates )
        } ) );
        cfg.addAutoInclude( COMMONS );
        cfg.setSharedVariable( "htmlAttributes", new HtmlAttributesMethod( true ) );
        cfg.setSharedVariable( "i18n", (TemplateMethodModelEx) arguments -> String.valueOf( arguments.get( 0 ) ) );
        StringWriter writer = new StringWriter( );
        cfg.getTemplate( strTemplate ).process( model, writer );

        return writer.toString( );
    }

    /**
     * An item of a reference list.
     *
     * @param strCode
     *            the code
     * @param strName
     *            the name
     * @return the item
     */
    private static Map<String, Object> item( String strCode, String strName )
    {
        Map<String, Object> item = new HashMap<>( );
        item.put( "code", strCode );
        item.put( "name", strName );
        item.put( "checked", false );

        return item;
    }

    /**
     * The model the JspBean builds for the creation page.
     *
     * @param strProviderName
     *            the name of the first provider
     * @param strBeanKey
     *            the bean key read from the request
     * @return the model
     */
    private static Map<String, Object> createModel( String strProviderName, String strBeanKey )
    {
        Map<String, Object> model = new HashMap<>( );
        model.put( "errors", List.of( ) );
        model.put( "list_provider", List.of( item( "forms.1", strProviderName ), item( "forms.2", "Contact" ) ) );
        model.put( "list_position", List.of( item( "1", strProviderName ), item( "2", "E-mail" ) ) );
        model.put( "beankey", strBeanKey );
        model.put( "notifygrumappingmanager", Map.of( "id", 0 ) );

        return model;
    }

    /**
     * The provider select submits its form when its value changes through data-lutece-action, with no event handler: a
     * params string with onChange fails the rendering in htmlAttributes.
     *
     * @throws Exception
     *             if the rendering fails
     */
    @Test
    public void testProviderSelectSubmitsWithoutInlineHandler( ) throws Exception
    {
        String strHtml = render( TEMPLATE_CREATE, createModel( "Request", "forms.1" ) );

        assertTrue( strHtml.matches( "(?s).*<select[^>]*\\bdata-lutece-action=\"submit\"[^>]*\\bname=\"beankey\".*" )
                || strHtml.matches( "(?s).*<select[^>]*\\bname=\"beankey\"[^>]*\\bdata-lutece-action=\"submit\".*" ), strHtml );
        assertFalse( strHtml.toLowerCase( Locale.ROOT ).matches( "(?s).*<[^>]*\\son[a-z]+\\s*=.*" ), strHtml );
        assertTrue( strHtml.contains( "<option selected=\"selected\" title=\"Request\" value=\"forms.1\"" ), strHtml );
    }

    /**
     * The provider and field labels and the bean key read from the request are escaped, in text and in attributes.
     *
     * @throws Exception
     *             if the rendering fails
     */
    @Test
    public void testLabelsAndBeanKeyAreEscaped( ) throws Exception
    {
        String strHtml = render( TEMPLATE_CREATE, createModel( HOSTILE, HOSTILE ) );

        assertTrue( strHtml.contains( "title=\"" + HOSTILE_ESCAPED + "\"" ), strHtml );
        assertTrue( strHtml.contains( ">" + HOSTILE_ESCAPED + "</option>" ), strHtml );
        assertTrue( strHtml.contains( "value=\"" + HOSTILE_ESCAPED + "\"" ), strHtml );
        assertFalse( strHtml.contains( HOSTILE ), strHtml );
        assertFalse( strHtml.contains( "<script>alert(1)" ), strHtml );
    }
}
