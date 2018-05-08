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
package com.github.vindell.wkhtmltopdf.invoker;

import java.io.File;

import com.github.vindell.wkhtmltopdf.invoker.exception.WkhtmlToPdfInvocationException;
import com.github.vindell.wkhtmltopdf.invoker.request.InvocationRequest;

/**
 * Provides a facade to invoke wkhtmltopdf.
 */
public interface Invoker {

	/**
	 * The role name used to register implementations of this interface within Plexus.
	 */
	String ROLE = Invoker.class.getName();

	/**
	 * Executes wkhtmltopdf using the parameters specified by the given invocation
	 * request. Parameters not specified by the invocation request will be derived
	 * from the state of this invoker instance. In case both the invoker instance
	 * and the invocation request provide a value for a particular option, the value
	 * from the invocation request dominates.
	 * 
	 * @param request
	 *            The invocation request to execute, must not be <code>null</code>.
	 * @return The result of the wkhtmltopdf invocation, never <code>null</code>.
	 * @throws WkhtmlToPdfInvocationException
	 */
	InvocationResult execute(InvocationRequest request) throws WkhtmlToPdfInvocationException;

	/**
	 * Gets the working directory for the wkhtmltopdf invocation.
	 * 
	 * @return The working directory for the wkhtmltopdf invocation or <code>null</code>
	 *         if the working directory is derived from the base directory of the
	 *         processed POM.
	 */
	File getWorkingDirectory();

	/**
	 * Gets the logger used by this invoker to output diagnostic messages.
	 * 
	 * @return The logger used by this invoker to output diagnostic messages, never
	 *         <code>null</code>.
	 */
	InvokerLogger getLogger();

	/**
	 * Gets the path to the base directory of the wkhtmltopdf installation used to
	 * invoke wkhtmltopdf.
	 * 
	 * @return The path to the base directory of the wkhtmltopdf installation or
	 *         <code>null</code> if using the default wkhtmltopdf installation.
	 */
	File getWkhtmltopdfHome();

	/**
	 * Sets the path to the base directory of the wkhtmltopdf installation used to
	 * invoke wkhtmltopdf. This parameter may be left unspecified to use the default
	 * wkhtmltopdf installation which will be discovered by evaluating the system
	 * property <code>wkhtmltopdf.home</code> and the environment variable
	 * <code>WKHTMLTOPDF_HOME</code>.
	 * 
	 * @param wkhtmltopdfHome The path to the base directory of the wkhtmltopdf installation, may be
	 *            <code>null</code> to use the default wkhtmltopdf installation.
	 * @return This invoker instance.
	 */
	Invoker setWkhtmltopdfHome(File wkhtmltopdfHome);

	/**
	 * Sets the logger used by this invoker to output diagnostic messages.
	 * @param logger The logger used by this invoker to output diagnostic messages, may be <code>null</code> to use a default logger.
	 * @return This invoker instance.
	 */
	Invoker setLogger(InvokerLogger logger);

	/**
	 * Sets the working directory for the wkhtmltopdf invocation.
	 * @param workingDirectory The working directory for the wkhtmltopdf invocation 
	 * @return This invoker instance.
	 */
	Invoker setWorkingDirectory(File workingDirectory);

	/**
	 * Sets the handler used to capture the standard output from the wkhtmltopdf build.
	 * @param outputHandler The output handler, may be <code>null</code> if the output is not  of interest.
	 * @return This invoker instance.
	 */
	Invoker setOutputHandler(InvocationOutputHandler outputHandler);

	/**
	 * Sets the handler used to capture the error output from the wkhtmltopdf build.
	 * 
	 * @param errorHandler
	 *            The error handler, may be <code>null</code> if the output is not of interest.
	 * @return This invoker instance.
	 */
	Invoker setErrorHandler(InvocationOutputHandler errorHandler);
}
