/*
 * Mutability Detector
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * Further licensing information for this project can be found in 
 * 		license/LICENSE.txt
 */

package org.mutabilitydetector.checkers.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mutabilitydetector.checkers.info.MethodIdentifier.dotted;
import static org.mutabilitydetector.checkers.info.MethodIdentifier.forMethod;

import org.junit.Test;
import org.mutabilitydetector.CheckerRunner;
import org.mutabilitydetector.benchmarks.settermethod.ImmutableUsingPrivateFieldSettingMethod;
import org.mutabilitydetector.checkers.info.PrivateMethodInvocationInfo;


public class PrivateMethodInvocationInfoTest {

	private CheckerRunner checkerRunner = CheckerRunner.createWithCurrentClasspath();
	
	@Test public void returnsTrueForPrivateMethodCalledOnlyFromConstructor() throws Exception {
		String className = ImmutableUsingPrivateFieldSettingMethod.class.getName();
		String methodDescriptor = "setFields:()V";
		PrivateMethodInvocationInfo info = new PrivateMethodInvocationInfo(checkerRunner);
		boolean result = info.isOnlyCalledFromConstructor(forMethod(dotted(className), methodDescriptor));
		assertTrue("Result should be true for private method called only from constructor.", result);
	}
	
	@Test public void returnsFalseForPublicMethod() throws Exception {
		String className = ImmutableUsingPrivateFieldSettingMethod.class.getName();
		String methodDescriptor = "getField1:()I";
		PrivateMethodInvocationInfo info = new PrivateMethodInvocationInfo(checkerRunner);
		boolean result = info.isOnlyCalledFromConstructor(forMethod(dotted(className), methodDescriptor));
		assertFalse("Cannot guarantee a public method is called only from constructor.", result);
	}
	
}
