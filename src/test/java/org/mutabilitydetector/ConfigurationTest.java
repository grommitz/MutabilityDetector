/*
 *    Copyright (c) 2008-2013 Graham Allan
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */
package org.mutabilitydetector;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mutabilitydetector.locations.Dotted.dotted;

import java.util.Map;

import org.junit.Test;
import org.mutabilitydetector.locations.Dotted;

public class ConfigurationTest {

    @Test
    public void hasHardcodedResultForClass() throws Exception {
        Configuration hasIt = new ConfigurationBuilder() {
            @Override public void configure() {
                hardcodeResult(AnalysisResult.definitelyImmutable("i.am.hardcoded"));
            }
        }.build();
        Configuration doesNotHaveIt = new ConfigurationBuilder() {
            @Override public void configure() {
                hardcodeResult(AnalysisResult.definitelyImmutable("i.am.not.the.same.hardcoded.class"));
            }
        }.build();

        Dotted isHardcoded = dotted("i.am.hardcoded");
        Dotted notHardcoded = dotted("i.am.not.hardcoded");

        Map<Dotted, AnalysisResult> hardcodedResults = hasIt.hardcodedResults();
        assertThat(hardcodedResults.containsKey(isHardcoded), is(true));
        assertThat(hardcodedResults.containsKey(notHardcoded), is(false));
        assertThat(doesNotHaveIt.hardcodedResults().containsKey(notHardcoded), is(false));
    }

}
