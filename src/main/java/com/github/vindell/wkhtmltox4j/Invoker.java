/**
 * Copyright (c) 2018, vindell (https://github.com/vindell).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.github.vindell.wkhtmltox4j;


import java.io.File;
import java.io.InputStream;

import com.github.vindell.wkhtmltox4j.exception.MavenInvocationException;

/**
 * Provides a facade to invoke wkhtmltopdf.
 */
public interface Invoker
{

    /**
     * The role name used to register implementations of this interface within Plexus.
     */
    String ROLE = Invoker.class.getName();

    /**
     * Executes wkhtmltopdf using the parameters specified by the given invocation request. Parameters not specified by the
     * invocation request will be derived from the state of this invoker instance. In case both the invoker instance and
     * the invocation request provide a value for a particular option, the value from the invocation request dominates.
     * 
     * @param request The invocation request to execute, must not be <code>null</code>.
     * @return The result of the wkhtmltopdf invocation, never <code>null</code>.
     * @throws MavenInvocationException
     */
    InvocationResult execute( InvocationRequest request )
        throws MavenInvocationException;

    /**
     * Gets the path to the base directory of the local repository to use for the wkhtmltopdf invocation.
     * 
     * @return The path to the base directory of the local repository or <code>null</code> to use the location from
     *         the <code>settings.xml</code>.
     */
    File getLocalRepositoryDirectory();

    /**
     * Gets the working directory for the wkhtmltopdf invocation.
     * 
     * @return The working directory for the wkhtmltopdf invocation or <code>null</code> if the working directory is derived
     *         from the base directory of the processed POM.
     */
    File getWorkingDirectory();

    /**
     * Gets the logger used by this invoker to output diagnostic messages.
     * 
     * @return The logger used by this invoker to output diagnostic messages, never <code>null</code>.
     */
    InvokerLogger getLogger();

    /**
     * Gets the path to the base directory of the wkhtmltopdf installation used to invoke wkhtmltopdf.
     * 
     * @return The path to the base directory of the wkhtmltopdf installation or <code>null</code> if using the default
     *         wkhtmltopdf installation.
     */
    File getMavenHome();

    /**
     * Sets the path to the base directory of the wkhtmltopdf installation used to invoke wkhtmltopdf. This parameter may be left
     * unspecified to use the default wkhtmltopdf installation which will be discovered by evaluating the system property
     * <code>maven.home</code> and the environment variable <code>M2_HOME</code>.
     * 
     * @param mavenHome The path to the base directory of the wkhtmltopdf installation, may be <code>null</code> to use the
     *            default wkhtmltopdf installation.
     * @return This invoker instance.
     */
    Invoker setMavenHome( File mavenHome );

    /**
     * Get the customized File of the wkhtmltopdf executable.
     * 
     * @return the custom wkhtmltopdf executable, otherwise {@code null} 
     */
    File getMavenExecutable();

    /**
     * {@code mavenExecutable} can either be a file relative to ${maven.home}/bin/ or an absolute file.
     * 
     * @param mavenExecutable the executable
     * @return This invoker instance
     */
    Invoker setMavenExecutable( File mavenExecutable );

    /**
     * Sets the path to the base directory of the local repository to use for the wkhtmltopdf invocation.
     * 
     * @param localRepositoryDirectory The path to the base directory of the local repository or <code>null</code> to
     *            use the location from the <code>settings.xml</code>.
     * @return This invoker instance.
     */
    Invoker setLocalRepositoryDirectory( File localRepositoryDirectory );

    /**
     * Sets the logger used by this invoker to output diagnostic messages.
     * 
     * @param logger The logger used by this invoker to output diagnostic messages, may be <code>null</code> to use a
     *            default logger.
     * @return This invoker instance.
     */
    Invoker setLogger( InvokerLogger logger );

    /**
     * Sets the working directory for the wkhtmltopdf invocation.
     * 
     * @param workingDirectory The working directory for the wkhtmltopdf invocation, may be <code>null</code> to derive the
     *            working directory from the base directory of the processed POM.
     * @return This invoker instance.
     */
    Invoker setWorkingDirectory( File workingDirectory );

    /**
     * Sets the input stream used to provide input for the invoked wkhtmltopdf build. This is in particular useful when
     * invoking wkhtmltopdf in interactive mode.
     * 
     * @param inputStream The input stream used to provide input for the invoked wkhtmltopdf build, may be <code>null</code>
     *            if not required.
     * @return This invoker instance.
     */
    Invoker setInputStream( InputStream inputStream );

    /**
     * Sets the handler used to capture the standard output from the wkhtmltopdf build.
     * 
     * @param outputHandler The output handler, may be <code>null</code> if the output is not of interest.
     * @return This invoker instance.
     */
    Invoker setOutputHandler( InvocationOutputHandler outputHandler );

    /**
     * Sets the handler used to capture the error output from the wkhtmltopdf build.
     * 
     * @param errorHandler The error handler, may be <code>null</code> if the output is not of interest.
     * @return This invoker instance.
     */
    Invoker setErrorHandler( InvocationOutputHandler errorHandler );
}
