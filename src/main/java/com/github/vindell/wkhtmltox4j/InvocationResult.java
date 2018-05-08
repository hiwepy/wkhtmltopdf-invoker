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


import org.codehaus.plexus.util.cli.CommandLineException;

/**
 * Describes the result of a wkhtmltopdf invocation.
 */
public interface InvocationResult
{

    /**
     * Gets the exception that possibly occurred during the execution of the command line.
     * 
     * @return The exception that prevented to invoke wkhtmltopdf or <code>null</code> if the command line was successfully
     *         processed by the operating system.
     */
    CommandLineException getExecutionException();

    /**
     * Gets the exit code from the wkhtmltopdf invocation. A non-zero value indicates a build failure. <strong>Note:</strong>
     * This value is undefined if {@link #getExecutionException()} reports an exception.
     * 
     * @return The exit code from the wkhtmltopdf invocation.
     */
    int getExitCode();

}
