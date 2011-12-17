/*
 * Copyright (C) 2010 0xlab - http://0xlab.org/
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

package org.zeroxlab.graphics;

import org.zeroxlab.benchmark.R;
import org.zeroxlab.benchmark.Tester;

import android.os.Bundle;

public class DrawCircle2HW extends Tester {
    /** Called when the activity is first created. */

    private DrawCircle2View2 mView;

    public String getTag() {
        return "DrawCircle2";
    }

    public int sleepBeforeStart() {
        return 1000;
    }

    public int sleepBetweenRound() {
        return 0;
    }

    public void oneRound() {
        mView.doDraw();
        decreaseCounter();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circle3);

        mView = (DrawCircle2View2) findViewById(R.id.circle2);
        startTester();
    }
}

