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
package org.mutabilitydetector.benchmarks.circular;

import org.mutabilitydetector.benchmarks.circular.MimicAwtCircularDependencies.Component.NativeInLightFixer;

@SuppressWarnings("unused")
public class MimicAwtCircularDependencies {
    private final Container container;

    public MimicAwtCircularDependencies(Container container) {
        this.container = container;
    }

    public static class Container {
        Container parent;
        NativeInLightFixer nativeInLightFixer;

        public Container(Container parent, NativeInLightFixer nativeInLightFixer) {
            this.parent = parent;
            this.nativeInLightFixer = nativeInLightFixer;
        }
    }

    public static class Component {
        NativeInLightFixer nativeInLightFixer = new NativeInLightFixer();

        class NativeInLightFixer {
            Container container = new Container(null, null);
        }
    }
}