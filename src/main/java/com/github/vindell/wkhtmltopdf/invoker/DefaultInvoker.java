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

import org.codehaus.plexus.util.cli.CommandLineException;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.codehaus.plexus.util.cli.Commandline;

import com.github.vindell.wkhtmltopdf.invoker.command.AbstractCommandLineBuilder;
import com.github.vindell.wkhtmltopdf.invoker.command.WkhtmlToPdfCommandLineBuilder;
import com.github.vindell.wkhtmltopdf.invoker.command.WkhtmlToImageCommandLineBuilder;
import com.github.vindell.wkhtmltopdf.invoker.exception.CommandLineConfigurationException;
import com.github.vindell.wkhtmltopdf.invoker.exception.WkhtmlToPdfInvocationException;
import com.github.vindell.wkhtmltopdf.invoker.request.InvocationRequest;
import com.github.vindell.wkhtmltopdf.invoker.request.WkhtmlToImageInvocationRequest;
import com.github.vindell.wkhtmltopdf.invoker.request.WkhtmlToPdfInvocationRequest;

/**
 * Class intended to be used by clients who wish to invoke a forked wkhtmltopdf
 * process from their applications
 */
public class DefaultInvoker implements Invoker {

	public static final String ROLE_HINT = "default";

	private static final InvokerLogger DEFAULT_LOGGER = new SystemOutLogger();

	private static final InvocationOutputHandler DEFAULT_OUTPUT_HANDLER = new SystemOutHandler();

	private InvokerLogger logger = DEFAULT_LOGGER;

	private File workingDirectory;
	
	private File wkhtmltopdfHome;

	private InvocationOutputHandler outputHandler = DEFAULT_OUTPUT_HANDLER;

	private InvocationOutputHandler errorHandler = DEFAULT_OUTPUT_HANDLER;
	
	protected AbstractCommandLineBuilder getCommandLineBuilder(InvocationRequest request) {
		if(request instanceof WkhtmlToPdfInvocationRequest) {
			return new WkhtmlToPdfCommandLineBuilder();
		}
		if(request instanceof WkhtmlToImageInvocationRequest) {
			return new WkhtmlToImageCommandLineBuilder();
		}
		return null;
	}
	
	public InvocationResult execute(InvocationRequest request) throws WkhtmlToPdfInvocationException {
		
		AbstractCommandLineBuilder cliBuilder = getCommandLineBuilder(request);

		InvokerLogger logger = getLogger();
		if (logger != null) {
			cliBuilder.setLogger(getLogger());
		}
 
		File wkhtmltopdfHome = getWkhtmltopdfHome();
		if (wkhtmltopdfHome != null) {
			cliBuilder.setCalibreHome(getWkhtmltopdfHome());
		}
		
		File workingDirectory = getWorkingDirectory();
		if (workingDirectory != null) {
			cliBuilder.setWorkingDirectory(getWorkingDirectory());
		}

		Commandline cli;
		try {
			cli = cliBuilder.build(request);
		} catch (CommandLineConfigurationException e) {
			throw new WkhtmlToPdfInvocationException("Error configuring command-line. Reason: " + e.getMessage(), e);
		}

		DefaultInvocationResult result = new DefaultInvocationResult();

		try {
			
			int exitCode = executeCommandLine(cli, request);

			result.setExitCode(exitCode);
		} catch (CommandLineException e) {
			result.setExecutionException(e);
		}

		return result;
	}

	private int executeCommandLine(Commandline cli, InvocationRequest request) throws CommandLineException {
		int result = Integer.MIN_VALUE;

		InvocationOutputHandler outputHandler = request.getOutputHandler(this.outputHandler);
		InvocationOutputHandler errorHandler = request.getErrorHandler(this.errorHandler);

		if (getLogger().isDebugEnabled()) {
			getLogger().debug("Executing: " + cli);
		}
		result = CommandLineUtils.executeCommandLine(cli, outputHandler, errorHandler);
		return result;
	}

	public InvokerLogger getLogger() {
		return logger;
	}

	public Invoker setLogger(InvokerLogger logger) {
		this.logger = (logger != null) ? logger : DEFAULT_LOGGER;
		return this;
	}
	
	public File getWorkingDirectory() {
		return workingDirectory;
	}

	public Invoker setWorkingDirectory(File workingDirectory) {
		this.workingDirectory = workingDirectory;
		return this;
	}

	public File getWkhtmltopdfHome() {
		return wkhtmltopdfHome;
	}

	public Invoker setWkhtmltopdfHome(File wkhtmltopdfHome) {
		this.wkhtmltopdfHome = wkhtmltopdfHome;
		return this;
	}

	public Invoker setErrorHandler(InvocationOutputHandler errorHandler) {
		this.errorHandler = errorHandler;
		return this;
	}

	public Invoker setOutputHandler(InvocationOutputHandler outputHandler) {
		this.outputHandler = outputHandler;
		return this;
	}
	
}
