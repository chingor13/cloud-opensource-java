/*
 * Copyright 2019 Google LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.cloud.tools.opensource.classpath;

import static org.junit.Assert.assertEquals;

import com.google.common.testing.EqualsTester;
import com.google.common.testing.NullPointerTester;
import com.google.common.testing.NullPointerTester.Visibility;
import org.junit.Test;

public class MethodSymbolTest {

  @Test
  public void testMethodSymbolCreation() {
    MethodSymbol methodSymbol =
        new MethodSymbol(
            "java.lang.Object",
            "equals",
            "(Lcom/google/protobuf/Message;)Lio/grpc/MethodDescriptor$Marshaller;",
            false);
    assertEquals("java.lang.Object", methodSymbol.getClassName());
    assertEquals("equals", methodSymbol.getName());
    assertEquals(
        "(Lcom/google/protobuf/Message;)Lio/grpc/MethodDescriptor$Marshaller;",
        methodSymbol.getDescriptor());
  }

  @Test
  public void testNull() {
    new NullPointerTester().testConstructors(MethodSymbol.class, Visibility.PACKAGE);
  }

  @Test
  public void testMethodSymbolEquality() {
    new EqualsTester()
        .addEqualityGroup(
            new MethodSymbol("java.lang.Object", "equals", "foo", false),
            new MethodSymbol("java.lang.Object", "equals", "foo", false))
        .addEqualityGroup(new MethodSymbol("java.lang.Object", "hashCode", "foo", false))
        .addEqualityGroup(new MethodSymbol("Object", "equals", "foo", false))
        .addEqualityGroup(new MethodSymbol("java.lang.Object", "equals", "bar", false))
        .addEqualityGroup(new MethodSymbol("java.lang.Object", "equals", "foo", true))
        .addEqualityGroup(new ClassSymbol("java.lang.Object"))
        .addEqualityGroup(new FieldSymbol("java.lang.Object", "equals", "foo"))
        .testEquals();
  }
}
