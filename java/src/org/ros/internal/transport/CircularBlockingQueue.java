/*
 * Copyright (C) 2011 Google Inc.
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

package org.ros.internal.transport;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author damonkohler@google.com (Damon Kohler)
 */
public class CircularBlockingQueue<T> extends LinkedBlockingDeque<T> {
  
  public CircularBlockingQueue(int capacity) {
    super(capacity);
  }
  
  @Override
  public void put(T entry) {
    if (remainingCapacity() == 1) {
      removeLast();
    }
    try {
      super.put(entry);
    } catch (InterruptedException e) {
      throw new IllegalStateException(e);
    }
  }

}