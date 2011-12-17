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

package org.zeroxlab.benchmark;

import java.util.Random;

import org.zeroxlab.graphics.BaseDrawView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;

public class TesterCanvas extends Tester {
    public final String TAG = "TesterCanvas";
    public final static String PACKAGE = "org.zeroxlab.benchmark";
    MyView mView;

    public String getTag() {
        return TAG;
    }

    public static String getPackage() {
        return PACKAGE;
    }

    public static String getFullClassName() {
        return getPackage() + ".TesterCanvas";
    }

    public int sleepBetweenRound() {
        return 0;
    }
    public int sleepBeforeStart() {
        return 1000;
    }

    public void oneRound() {
        mView.doDraw();
        decreaseCounter();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        mView = new MyView(this);
        setContentView(mView);
        startTester();
    }

    public static class MyView extends BaseDrawView {
        Random mRandom;

        MyView(Context context) {
            super(context);
            mRandom = new Random();
        }

		@Override
		public void drawScene(Canvas canvas) {
            int r = (0x00151515| mRandom.nextInt() ) | Color.BLACK;
            canvas.drawRGB(r, r, r);			
		}
    }
}
