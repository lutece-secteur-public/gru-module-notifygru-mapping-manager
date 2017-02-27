/*
 * Copyright (c) 2002-2015, Mairie de Paris
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
package fr.paris.lutece.plugins.modulenotifygrumappingmanager.business;

import javax.persistence.Column;
import javax.validation.constraints.*;

import org.hibernate.validator.constraints.*;

import java.io.Serializable;

/**
 * This is the business class for the object NotifygruMappingManager
 */
public class NotifygruMappingManager implements Serializable
{
    private static final long serialVersionUID = 1L;

    // Variables declarations
    private int _nId;

    @NotEmpty( message = "#i18n{modulenotifygrumappingmanager.validation.notifygrumappingmanager.BeanKey.notEmpty}" )
    @Size( max = 255, message = "#i18n{modulenotifygrumappingmanager.validation.notifygrumappingmanager.BeanKey.size}" )
    private String _strBeanKey;

    private int _nMobilePhoneNumber;

    private int _nFixedPhoneNumber;

    private int _nEmail;

    /**
     * Returns the Id
     * 
     * @return The Id
     */
    public int getId( )
    {
        return _nId;
    }

    /**
     * Sets the Id
     * 
     * @param nId
     *            The Id
     */
    public void setId( int nId )
    {
        _nId = nId;
    }

    /**
     * Returns the BeanKey
     * 
     * @return The BeanKey
     */
    public String getBeanKey( )
    {
        return _strBeanKey;
    }

    /**
     * Sets the BeanKey
     * 
     * @param strBeanKey
     *            The BeanKey
     */
    public void setBeanKey( String strBeanKey )
    {
        _strBeanKey = strBeanKey;
    }

    /**
     * Returns the MobilePhoneNumber
     * 
     * @return The MobilePhoneNumber
     */
    public int getMobilePhoneNumber( )
    {
        return _nMobilePhoneNumber;
    }

    /**
     * Sets the MobilePhoneNumber
     * 
     * @param nMobilePhoneNumber
     *            The MobilePhoneNumber
     */
    public void setMobilePhoneNumber( int nMobilePhoneNumber )
    {
        _nMobilePhoneNumber = nMobilePhoneNumber;
    }

    /**
     * Returns the FixedPhoneNumber
     * 
     * @return The FixedPhoneNumber
     */
    public int getFixedPhoneNumber( )
    {
        return _nFixedPhoneNumber;
    }

    /**
     * Sets the FixedPhoneNumber
     * 
     * @param nFixedPhoneNumber
     *            The FixedPhoneNumber
     */
    public void setFixedPhoneNumber( int nFixedPhoneNumber )
    {
        _nFixedPhoneNumber = nFixedPhoneNumber;
    }

    /**
     * Returns the Email
     * 
     * @return The Email
     */
    public int getEmail( )
    {
        return _nEmail;
    }

    /**
     * Sets the Email
     * 
     * @param nEmail
     *            The Email
     */
    public void setEmail( int nEmail )
    {
        _nEmail = nEmail;
    }
}
